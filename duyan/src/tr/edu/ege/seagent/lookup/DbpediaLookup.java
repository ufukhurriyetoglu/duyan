package tr.edu.ege.seagent.lookup;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import tr.edu.ege.seagent.dbpedia.SemanticTag;
import tr.edu.ege.seagent.entity.Entity;

public class DbpediaLookup {

	private static Logger log = Logger.getLogger(DbpediaLookup.class.getName());

	private static final String DBPEDIA_ORGANISATION = "http://dbpedia.org/ontology/Organisation";
	private static final String FOAF_PERSON = "http://xmlns.com/foaf/0.1/Person";
	private static final String DBPEDIA_PLACE = "http://dbpedia.org/ontology/Place";
	// private static final String LOOKUP_DBPEDIA_URL =
	// "http://lookup.dbpedia.org/api/search/PrefixSearch?QueryClass=&MaxHits=5&QueryString=";
	private static final String LOOKUP_DBPEDIA_URL = "http://lookup.dbpedia.org/api/search/KeywordSearch?QueryString=";

	public ArrayList<SemanticTag> lookupDbpedia(List<String> ngramList)
			throws IOException, SAXException, TransformerException,
			ParserConfigurationException {

		ArrayList<SemanticTag> dbpediaResolvedList = new ArrayList<SemanticTag>();

		for (String keyword : ngramList) {

			String keywordEncoded = URLEncoder.encode(keyword, "UTF-8");

			String urlString = LOOKUP_DBPEDIA_URL + keywordEncoded;

			try {
				URL url = new URL(urlString);
				URLConnection connection = url.openConnection();

				Document doc = parseXML(connection.getInputStream());

				doc.getDocumentElement().normalize();

				// loop through each item
				NodeList nList = doc.getElementsByTagName("Result");

				// System.out.println(nList.getLength());
				for (int temp = 0; temp < nList.getLength(); temp++) {

					Node nNode = nList.item(temp);

					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) nNode;
						// String label = eElement.getElementsByTagName("Label")
						// .item(0).getTextContent();
						String classDef = eElement
								.getElementsByTagName("Classes").item(0)
								.getTextContent();

						// if (keyword.contains(label)) {
						// boolean status = checkName(keyword,
						// dbpediaResolvedList);
						// System.out.println(status);
						// if (status) {
						if (classDef.contains(FOAF_PERSON)) {
							dbpediaResolvedList.add(new SemanticTag(keyword,
									"Person", eElement
											.getElementsByTagName("URI")
											.item(0).getTextContent()));
						} else if (classDef.contains(DBPEDIA_PLACE)) {
							dbpediaResolvedList.add(new SemanticTag(keyword,
									"Location", eElement
											.getElementsByTagName("URI")
											.item(0).getTextContent()));
						} else if (classDef.contains(DBPEDIA_ORGANISATION)) {
							dbpediaResolvedList.add(new SemanticTag(keyword,
									"Organization", eElement
											.getElementsByTagName("URI")
											.item(0).getTextContent()));
							// System.out.println("Label : "
							// + eElement
							// .getElementsByTagName("Label")
							// .item(0).getTextContent()
							// + " - URI : "
							// + eElement.getElementsByTagName("URI")
							// .item(0).getTextContent()
							// + " - CLASSES: " + classDef);
						}
					}

				}
				// }
				// }

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

//		for (SemanticTag d : dbpediaResolvedList) {
//			System.out.println(d.getName() + " - " + d.getType() + " - "
//					+ d.getUri());
//		}

		return dbpediaResolvedList;
	}

	public Entity lookupDbpediaSet(Entity ngramList) {

		TreeSet<Entity> entityResolvedList = new TreeSet<Entity>();
		ArrayList<SemanticTag> dbpediaResolvedList = new ArrayList<SemanticTag>();

		for (String keywordEntity : ngramList.getEntityList()) {

			String keywordEncoded;
			String keyword = keywordEntity.trim();

			try {
				keywordEncoded = URLEncoder.encode(keyword, "UTF-8");
				String urlString = LOOKUP_DBPEDIA_URL + keywordEncoded;
				// log.info(urlString);

				URL url = new URL(urlString);
				URLConnection connection = url.openConnection();

				Document doc = parseXML(connection.getInputStream());

				doc.getDocumentElement().normalize();

				// loop through each item
				NodeList nList = doc.getElementsByTagName("Result");

				for (int temp = 0; temp < nList.getLength(); temp++) {

					Node nNode = nList.item(temp);

					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) nNode;
						String classDef = eElement
								.getElementsByTagName("Classes").item(0)
								.getTextContent();
						if (!classDef.isEmpty()) {
							String uriLink = eElement
									.getElementsByTagName("URI").item(0)
									.getTextContent();
							if (classDef.contains(FOAF_PERSON)) {
								dbpediaResolvedList.add(new SemanticTag(keyword,
										"Person", uriLink));

							} else if (classDef.contains(DBPEDIA_PLACE)) {
								dbpediaResolvedList.add(new SemanticTag(keyword,
										"Location", uriLink));

							} else if (classDef.contains(DBPEDIA_ORGANISATION)) {
								dbpediaResolvedList.add(new SemanticTag(keyword,
										"Organization", uriLink));
							}
						}

					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		ngramList.setDbpediaList(dbpediaResolvedList);
		entityResolvedList.add(ngramList);

		for (Entity ent : entityResolvedList) {
			log.info("content : " + ent.getContent());
			for (SemanticTag d : dbpediaResolvedList)
				log.info(" name :" + d.getName() + " type :" + d.getType()
						+ " uri : " + d.getUri());
		}

		return ngramList;
	}

	public boolean checkName(String name,
			ArrayList<SemanticTag> dbpediaCandidateUriList) {
		boolean check = true;
		for (SemanticTag dbpedia : dbpediaCandidateUriList) {
			if (dbpedia.getName().equals(name)) {
				check = false;
				break;
			}
		}
		return check;
	}

	private Document parseXML(InputStream stream) throws Exception {
		DocumentBuilderFactory objDocumentBuilderFactory = null;
		DocumentBuilder objDocumentBuilder = null;
		Document doc = null;
		try {
			objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
			objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();

			doc = objDocumentBuilder.parse(stream);
		} catch (Exception ex) {
			throw ex;
		}

		return doc;
	}
}
