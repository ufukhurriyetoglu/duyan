package tr.edu.ege.seagent.dbpedia;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tr.edu.ege.seagent.zemberek.NGramOperator;

public class DbpediaTest {

	@Test
	public void checkDbpediaStatus() throws Exception {
		// String service = "http://dbpedia.org/sparql";
		// String query = "ASK { }";
		// QueryExecution qe = QueryExecutionFactory.sparqlService(service,
		// query);
		// try {
		// if (qe.execAsk()) {
		// System.out.println(service + " is UP");
		// } // end if
		// } catch (QueryExceptionHTTP e) {
		// System.out.println(service + " is DOWN");
		// } finally {
		// qe.close();
		// } // end try/catch/finally
		// String askQueryStr =
		// "ASK WHERE { <http://dbpedia.org/resource/Mustafa_Kemal_Atat%C3%BCrk> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://dbpedia.org/ontology/Person>}";
		// new DbpediaSearcher().askToDbpedia(askQueryStr);

		// Document doc;
		// try {
		//
		// // need http protocol
		// doc = Jsoup
		// .connect(
		// "http://dbpedia.org/sparql?default-graph-uri=http%3A%2F%2Fdbpedia.org&query=ASK+%7B%7D&format=text%2Fhtml&timeout=30000&debug=on")
		// .get();
		//
		// // get page title
		// String title = doc.title();
		// System.out.println("title : " + title);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

	}

	@Test
	public void disambiguateDbpediTest() throws Exception {
		ArrayList<SemanticTag> resolveNGramsInDbpedia = createResolvedNGramsStartBigLetter();

		DBpediaDisambiguator dbpediaDis = new DBpediaDisambiguator();
		dbpediaDis.disambiguateDbpedia(resolveNGramsInDbpedia);

		assertEquals(1, dbpediaDis.disambiguateDbpedia(resolveNGramsInDbpedia)
				.size());
		// assertEquals("http://dbpedia.org/resource/MHP", dbpediaDis
		// .disambiguateDbpedia(resolveNGramsInDbpedia).get(0).getUri());
		assertEquals("http://dbpedia.org/resource/Devlet_Bah%C3%A7eli",
				dbpediaDis.disambiguateDbpedia(resolveNGramsInDbpedia).get(0)
						.getUri());
	}

	@Test
	public void searchStartCapitalLetterDbpediaTest() throws Exception {

		assertEquals(1, createResolvedNGramsStartBigLetter().size());
		// assertEquals("http://dbpedia.org/resource/MHP",
		// createResolvedNGramsStartBigLetter().get(0).getUri());
		// assertEquals("http://dbpedia.org/resource/Devlet",
		// createResolvedNGramsStartBigLetter().get(1).getUri());
		// assertEquals("http://dbpedia.org/resource/Bah%C3%A7eli",
		// createResolvedNGramsStartBigLetter().get(2).getUri());
		assertEquals("http://dbpedia.org/resource/Devlet_Bah%C3%A7eli",
				createResolvedNGramsStartBigLetter().get(0).getUri());

	}

	private ArrayList<SemanticTag> createResolvedNGramsStartBigLetter() {
		ArrayList<String> properNounList = new ArrayList<String>();
		properNounList.add("MHP");
		properNounList.add("Genel");
		properNounList.add("Başkanı");
		properNounList.add("Devlet");
		properNounList.add("Bahçeli");

		// generate NGrams
		List<String> ngramList = new NGramOperator().generateNGramList(
				properNounList, properNounList.size());

		// resolve generated NGrams in DBpedia
		ArrayList<SemanticTag> resolveNGramsInDbpedia = new DbpediaSearcher()
				.resolveNGramsInDbpedia(ngramList);
		return resolveNGramsInDbpedia;
	}

	// @Test
	// public void searchStartSmallLetterDbpediaTest() throws Exception {
	// ArrayList<String> properNounList = new ArrayList<String>();
	// // properNounList.add("mhp"); // next problem Mhp -> MHP
	// properNounList.add("genel");
	// properNounList.add("başkanı");
	// properNounList.add("devlet");
	// properNounList.add("bahçeli");
	//
	// // generate NGrams
	// List<String> ngramList = new NGramOperator().generateNGramList(
	// properNounList, properNounList.size());
	//
	// // resolve generated NGrams in DBpedia
	// ArrayList<Dbpedia> resolveNGramsInDbpedia = new DbpediaSearcher()
	// .resolveNGramsInDbpedia(ngramList);
	// for (Dbpedia dbpedia : resolveNGramsInDbpedia) {
	// if (dbpedia.getUri().contains(
	// resolveNGramsInDbpedia.get(0).getUri())
	// && dbpedia.getUri().contains(
	// resolveNGramsInDbpedia.get(1).getUri()))
	// System.out.println(dbpedia.getUri());
	// }
	//
	// assertEquals(1, resolveNGramsInDbpedia.size());
	// // assertEquals("http://dbpedia.org/resource/Devlet",
	// // resolveNGramsInDbpedia.get(0).getUri());
	// // assertEquals("http://dbpedia.org/resource/Bah%C3%A7eli",
	// // resolveNGramsInDbpedia.get(1).getUri());
	// assertEquals("http://dbpedia.org/resource/Devlet_Bah%C3%A7eli",
	// resolveNGramsInDbpedia.get(0).getUri());
	// }
}
