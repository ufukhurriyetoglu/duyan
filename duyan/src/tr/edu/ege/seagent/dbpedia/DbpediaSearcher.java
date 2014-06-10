package tr.edu.ege.seagent.dbpedia;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.Syntax;

public class DbpediaSearcher {
	public final String DBPEDIA_ENDPOINT = "http://dbpedia.org/sparql";
	public final String RDFS_LABEL = "<http://www.w3.org/2000/01/rdf-schema#label>";
	public final String FOAF_NAME = "<http://xmlns.com/foaf/0.1/name>";
	public final String DBPROPNAME_NAME = "<http://dbpedia.org/property/name>";
	public final String RDF_TYPE = "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>";
	public final String DBPEDIA_PERSON = "<http://dbpedia.org/ontology/Person>";
	public final String DBPEDIA_ORGANIZATION = "<http://dbpedia.org/ontology/Organisation>";
	public final String DBPEDIA_LOCATION = "<http://dbpedia.org/ontology/Place>";

	public final String DBPEDIA_CNTRL_URL = "http://dbpedia.org/sparql?default-graph-uri=http%3A%2F%2Fdbpedia.org&query=select+%3Fs+%3Fp+%3Fo+where+%7B%3Fs+%3Fp+%3Fo%7D+LIMIT+1&format=text%2Fhtml&timeout=30000&debug=on";

	public boolean checkUri(String uri,
			ArrayList<SemanticTag> dbpediaCandidateUriList) {
		boolean check = false;
		for (SemanticTag dbpedia : dbpediaCandidateUriList) {
			if (dbpedia.getUri().contains(uri))
				check = true;
			break;
		}
		return check;
	}

	// clarified resolve event
	public ArrayList<SemanticTag> resolveNGramsInDbpedia(List<String> ngramList) {
		ArrayList<SemanticTag> dbpediaCandidateUriList = new ArrayList<SemanticTag>();
		for (String nGram : ngramList) {
			String resolvedNounUri = resolveNounInDbpedia(nGram);
			if (resolvedNounUri.isEmpty()) {
				// System.out.println("unresolved uri");
			} else {
				if (!checkUri(resolvedNounUri, dbpediaCandidateUriList)) {

					String askPer = "ASK WHERE { <" + resolvedNounUri + "> "
							+ RDF_TYPE + " " + DBPEDIA_PERSON + " } ";
					String askOrg = "ASK WHERE { <" + resolvedNounUri + "> "
							+ RDF_TYPE + " " + DBPEDIA_ORGANIZATION + " } ";
					String askLoc = "ASK WHERE { <" + resolvedNounUri + "> "
							+ RDF_TYPE + " " + DBPEDIA_LOCATION + " } ";

					String type = "";
					if (askToDbpedia(askPer)) {
						type = "Person";
						dbpediaCandidateUriList.add(new SemanticTag(nGram, type,
								resolvedNounUri));
					} else if (askToDbpedia(askLoc)) {
						type = "Location";
						dbpediaCandidateUriList.add(new SemanticTag(nGram, type,
								resolvedNounUri));
					} else if (askToDbpedia(askOrg)) {
						type = "Organization";
						dbpediaCandidateUriList.add(new SemanticTag(nGram, type,
								resolvedNounUri));
					} else {
						type = "";
					}
				}
			}
		}
		return dbpediaCandidateUriList;
	}

	// each noun in NGram List is resolved in Dbpedia.
	public String resolveNounInDbpedia(String nGram) {
		String possibleFoundDbpediUri = "";
		String queryString = "Select ?s Where { " + "{ ?s " + RDFS_LABEL
				+ " \"" + nGram + "\"@en }" + " UNION " + "{ ?s " + FOAF_NAME
				+ " \"" + nGram + "\"@en }" + " UNION " + "{ ?s "
				+ DBPROPNAME_NAME + " \"" + nGram + "\"@en }" + "} LIMIT 2";
		Query query = QueryFactory.create(queryString, Syntax.syntaxARQ);
		QueryExecution qe = QueryExecutionFactory.sparqlService(
				DBPEDIA_ENDPOINT, query);
		ResultSet results = qe.execSelect();
		if (!results.hasNext()) {
			qe.close();
		} else {
			for (; results.hasNext();) {
				QuerySolution sol = (QuerySolution) results.next();
				String uri = sol.get("?s").asResource().getURI();
				if (!uri.contains("wikidata"))
					possibleFoundDbpediUri = uri;
			}
			qe.close();
		}
		return possibleFoundDbpediUri;
	}

	// ESKIII
	public ArrayList<SemanticTag> resolveInDbpedia(ArrayList<String> nerList)
			throws IOException {
		ArrayList<SemanticTag> dbpediaList = new ArrayList<SemanticTag>();
		ArrayList<String> possibleList = new ArrayList<String>();
		ArrayList<String> possibleFoundList = new ArrayList<String>();

		URL url2 = new URL(DBPEDIA_CNTRL_URL);
		HttpURLConnection urlh = (HttpURLConnection) url2.openConnection();
		int status = urlh.getResponseCode();
		// if dbpedia does not work
		if (status == 502) {
			dbpediaList.get(0).setName("error");
		} else {

			for (String nerMember : nerList) {
				// String queryString = "Select ?s ?p ?o " +
				// " Where { ?s ?p ?o "
				// + " Filter(regex(?o, \"" + tokenStr + "\"))" + " } LIMIT 2";
				String queryString = "Select ?s Where { " + "{ ?s "
						+ RDFS_LABEL + " \"" + nerMember + "\"@en }"
						+ " UNION " + "{ ?s " + FOAF_NAME + " \"" + nerMember
						+ "\"@en }" + " UNION " + "{ ?s " + DBPROPNAME_NAME
						+ " \"" + nerMember + "\"@en }" + "} LIMIT 2";

				Query query = QueryFactory
						.create(queryString, Syntax.syntaxARQ);
				QueryExecution qe = QueryExecutionFactory.sparqlService(
						DBPEDIA_ENDPOINT, query);
				ResultSet results = qe.execSelect();
				// there is no named entity extraction
				if (!results.hasNext()) {
					qe.close();
					break;
				} else {
					String[] splittedWords = nerMember.split(" ");
					String word = splittedWords[0];
					possibleList.add(word);
					for (int i = 1; i < splittedWords.length; i++) {
						// tek kelime olarak ekle
						possibleList.add(splittedWords[i]);

						if (i < splittedWords.length - 1) {
							word = word + " " + splittedWords[i];
							possibleList.add(word);
							// word = splittedWords[i];
						}
					}

					// for (String str : possibleList) {
					// System.out.println(str);
					// }

					for (int i = 0; i < splittedWords.length; i++) {
						if (i < splittedWords.length - 1) {
							word = word + " " + splittedWords[i];
							String queryString2 = "Select ?s Where { "
									+ "{ ?s " + RDFS_LABEL + " \""
									+ splittedWords[i] + "\"@en }" + " UNION "
									+ "{ ?s " + FOAF_NAME + " \""
									+ splittedWords[i] + "\"@en }" + " UNION "
									+ "{ ?s " + DBPROPNAME_NAME + " \""
									+ splittedWords[i] + "\"@en }"
									+ "} LIMIT 2";

							Query query2 = QueryFactory.create(queryString2,
									Syntax.syntaxARQ);
							QueryExecution qe2 = QueryExecutionFactory
									.sparqlService(DBPEDIA_ENDPOINT, query2);
							ResultSet results2 = qe2.execSelect();
							for (; results2.hasNext();) {
								QuerySolution sol = (QuerySolution) results2
										.next();
								String uri = sol.get("?s").asResource()
										.getURI();
								if (!uri.contains("wikidata"))
									possibleFoundList.add(uri);
							}
							qe.close();
						}

					} // end of for
				} // end of if

				for (; results.hasNext();) {
					QuerySolution sol = (QuerySolution) results.next();

					// resource un urisini alır URL deki "page" -> "resource"
					// replace etmeye gerek yok.
					String uri = sol.get("?s").asResource().getURI();

					String askPer = "ASK WHERE { <" + uri + "> " + RDF_TYPE
							+ " " + DBPEDIA_PERSON + " } ";

					// "ASK WHERE { <http://dbpedia.org/resource/Mustafa_Kemal_Atat%C3%BCrk> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://dbpedia.org/ontology/Person>}"
					String askOrg = "ASK WHERE { <" + uri + "> " + RDF_TYPE
							+ " " + DBPEDIA_ORGANIZATION + " } ";
					String askLoc = "ASK WHERE { <" + uri + "> " + RDF_TYPE
							+ " " + DBPEDIA_LOCATION + " } ";

					String type = "";
					if (askToDbpedia(askPer)) {
						type = "Person";
						dbpediaList.add(new SemanticTag(nerMember, type, uri));
						break;
					} else if (askToDbpedia(askLoc)) {
						type = "Location";
						dbpediaList.add(new SemanticTag(nerMember, type, uri));
						break;
					} else if (askToDbpedia(askOrg)) {
						type = "Organization";
						dbpediaList.add(new SemanticTag(nerMember, type, uri));
						break;
					} else {
						type = "";
					}
				}
				qe.close();
			}
		}
		return dbpediaList;
	}

	// asks DBpedia to find the rdf:type of the resource URI
	public boolean askToDbpedia(String askQueryStr) {
		Query query = QueryFactory.create(askQueryStr, Syntax.syntaxARQ);
		QueryExecution qe = QueryExecutionFactory.sparqlService(
				DBPEDIA_ENDPOINT, query);
		return qe.execAsk();
	}

}
