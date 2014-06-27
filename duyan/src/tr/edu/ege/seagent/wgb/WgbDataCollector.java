package tr.edu.ege.seagent.wgb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import tr.edu.ege.seagent.fileio.FileOperator;
import tr.edu.ege.seagent.regex.RegexOperator;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

public class WgbDataCollector {
	private static final String SPARQL_ENDPOINT = "http://www.webgraphbuilder.com:3030/webgraph/query";
	private final static String PREFIX_SIOC = " PREFIX sioc: <http://rdfs.org/sioc/ns#> \n";
	private final static String PREFIX_DCTERMS = " PREFIX dcterms: <http://purl.org/dc/terms/> \n";
	private final static String PREFIX_RDF = " PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n";
	private final static String PREFIX_XSD = " PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> \n";

	public ArrayList<WGB> collectDataOnTheGivenDomain(String givenDomain)
			throws IOException {
		ArrayList<WGB> wgbResultList = new ArrayList<WGB>();
		String queryStr = PREFIX_SIOC + PREFIX_DCTERMS + PREFIX_RDF
				+ PREFIX_XSD + " SELECT DISTINCT * " + " WHERE { ?s ?p ?o . "
				+ " FILTER (regex(?o, \"" + givenDomain + "\")) " + " }";
		Query query = QueryFactory.create(queryStr);
		QueryExecution qExec = QueryExecutionFactory.sparqlService(
				SPARQL_ENDPOINT, query);
		ResultSet resultSet = qExec.execSelect();

		int cnt = 0;
		for (; resultSet.hasNext();) {
			QuerySolution sol = (QuerySolution) resultSet.next();
			String subjStr = sol.get("?s").toString();
			if (subjStr.contains("http://www.hurriyet.com.tr/gundem/")) {
				URL hurr = new URL(subjStr);
				BufferedReader in = new BufferedReader(new InputStreamReader(
						hurr.openStream()));
				Document doc1 = Jsoup.connect(subjStr).get();
				Element contentDiv = doc1.select("div[class=ctx_content]")
						.first();
				String text = contentDiv.getElementsByTag("div").text();
				System.out.println("Content : " + text);
				in.close();
				if (!text.equals("")) { // content boş gelmiyorsa
					
					RegexOperator rgOperator = new RegexOperator();
					StringBuffer sb = rgOperator.eliminateLowerCaseWords(text,rgOperator.DELIMITER);
					
					wgbResultList.add(new WGB(text,rgOperator.obtainCandidateNamedEntities(sb, rgOperator.DELIMITER)));
				}
				cnt++;
			}
		}
		System.out.println("Total retrieved content : " + cnt);
		qExec.close();
		return wgbResultList;
	}

	private static final String HURRIYET_GUNDEM = "hurriyet.com.tr/gundem";
	private static final String TEST_POOL_FILE = "/home/etmen/Desktop/testPool2.txt";

	public static void main(String[] args) throws IOException {
		ArrayList<WGB> wgbResultList = new WgbDataCollector()
				.collectDataOnTheGivenDomain(HURRIYET_GUNDEM);

		new FileOperator().writeToFile(wgbResultList, TEST_POOL_FILE);
	}
}
