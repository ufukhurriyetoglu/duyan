package tr.edu.ege.seagent.main;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import tr.edu.ege.seagent.dbpedia.Dbpedia;
import tr.edu.ege.seagent.dbpedia.DbpediaSearcher;
import tr.edu.ege.seagent.dbpedia.DbpediaSearcherInFile;
import tr.edu.ege.seagent.json.Entities;
import tr.edu.ege.seagent.json.JSONFileMaker;
import tr.edu.ege.seagent.zemberek.DisambiguateSentences;
import zemberek.morphology.ambiguity.Z3MarkovModelDisambiguator;
import zemberek.morphology.apps.TurkishMorphParser;
import zemberek.morphology.apps.TurkishSentenceParser;

public class TextAnalyser {

	// for "Vites"
	public ArrayList<Entities> analyzeTextList(String content)
			throws IOException {

		ArrayList<Entities> entities = null;
		ArrayList<String> nerList = extractNamedEntity(content);

//		 ArrayList<Dbpedia> dbpediaList = new DbpediaSearcher()
//		 .resolveInDbpedia(nerList);

		ArrayList<Dbpedia> dbpediaList = new DbpediaSearcherInFile()
				.resolveInDbpedia(nerList);

		if (nerList.size() == dbpediaList.size()) {
			// if it is accomplished by %100
			entities = new ArrayList<Entities>();
			int cnt = 1;
			for (String namedEntity : nerList) {
				// find all occurrences forward
				for (int beginOffset = -1; (beginOffset = content.indexOf(
						namedEntity, beginOffset + 1)) != -1;) {
					int endOffset = namedEntity.length() + beginOffset;

					entities.add(new Entities(
							dbpediaList.get(cnt - 1).getUri(), "T" + cnt,
							dbpediaList.get(cnt - 1).getType(), beginOffset,
							endOffset));
					cnt++;
				}
			}
		}
		return entities;
	}

	// uses "zemberek 2" to extract named entities
	private ArrayList<String> extractNamedEntity(String content)
			throws IOException {
		TurkishMorphParser morphParser = TurkishMorphParser
				.createWithDefaults();
		Z3MarkovModelDisambiguator disambiguator = new Z3MarkovModelDisambiguator();
		TurkishSentenceParser sentenceParser = new TurkishSentenceParser(
				morphParser, disambiguator);
		DisambiguateSentences disa = new DisambiguateSentences(sentenceParser);
		ArrayList<String> nerList = disa.parseAndDisambiguate(content);
		return nerList;
	}

	// for "brat"
	public String analyzeText(String content) throws IOException {

		String jsonResult = "";
		ArrayList<String> nerList = extractNamedEntity(content);

//		 ArrayList<Dbpedia> dbpediaList = new DbpediaSearcher()
//		 .resolveInDbpedia(nerList);
		
		ArrayList<Dbpedia> dbpediaList = new DbpediaSearcherInFile()
				.resolveInDbpedia(nerList);

		if (nerList.size() == dbpediaList.size()) {
			// if it is accomplished by %100
			ArrayList<Entities> entities = new ArrayList<Entities>();
			int cnt = 1;
			for (String namedEntity : nerList) {

				// find all occurrences forward
				for (int beginOffset = -1; (beginOffset = content.indexOf(
						namedEntity, beginOffset + 1)) != -1;) {
					int endOffset = namedEntity.length() + beginOffset;
					System.out.println("basi : " + beginOffset + " sonu : "
							+ endOffset);

					entities.add(new Entities("T" + cnt, dbpediaList.get(
							cnt - 1).getType(), beginOffset, endOffset));
					cnt++;
				}
			}
			// creates json content from the Named Entities
			jsonResult = new JSONFileMaker().createJson(content, entities);
		}
		return jsonResult;
	}

	public String demonstrateHTMLContent(String content) throws IOException {
		ArrayList<Entities> analyzeTextList = new ArrayList<Entities>();
		analyzeTextList = new TextAnalyser()
				.analyzeTextList(content);

		String label = "";
		String resultContent = content;

		if (analyzeTextList == null) {
			resultContent = "";
		} else {

			String oldNamedEntity, newNamedEntity = "";
			String coloringEnd = "</span></a></mark>";

			for (Entities entities : analyzeTextList) {
				label = label + "<li>" + entities.getType() + " "
						+ entities.getStart() + " " + entities.getEnd()
						+ "</li>";

				oldNamedEntity = content.substring(entities.getStart(),
						entities.getEnd());
				String coloringStart = " <mark style=\"background-color:blue\">"
						+ " <a href="
						+ entities.getDbpediaUri()
						+ " title="
						+ entities.getType()
						+ " class=\"tooltip\">"
						+ "	<span title=" + entities.getDbpediaUri() + ">";

				newNamedEntity = coloringStart + oldNamedEntity + coloringEnd;
				resultContent = resultContent.replace(oldNamedEntity,
						newNamedEntity);
			}
		}
		return resultContent;
	}

	public static void main(String[] args) throws IOException, ParseException {
		// String content =
		// "Mustafa Kemal Atatürk ve İsmet İnönü, Türkiye Büyük Millet Meclisi açılışı için İstanbul'dan gelerek Ankara'da kaldılar.";
		// new TextAnalyser().demonstrateHTMLContent(content);

		String content = "Yapılan düğünde Arda Turan hazır bulundu.";

		// // test named entities
		ArrayList<String> nerList = new TextAnalyser()
				.extractNamedEntity(content);
		System.out.println("The size of Named Entity List : " + nerList.size());
		for (String str : nerList) {
			System.out.println(str);
		}

		System.out.println(new TextAnalyser().demonstrateHTMLContent(content));

		// problem bundan sonra başlar "Mustafa Kemal Atatürk İzmir" dbpedia
		// search
		// için n-gram uygulaması
		ArrayList<Dbpedia> dbpediaList = new DbpediaSearcherInFile()
				.resolveInDbpedia(nerList);
		for (Dbpedia dbpedia : dbpediaList) {
			System.out.println(dbpedia.getUri());
		}

	}

}
