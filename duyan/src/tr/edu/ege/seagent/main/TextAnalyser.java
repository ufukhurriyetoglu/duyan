package tr.edu.ege.seagent.main;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import tr.edu.ege.seagent.dbpedia.DBpediaDisambiguator;
import tr.edu.ege.seagent.dbpedia.DbpediaSearcher;
import tr.edu.ege.seagent.dbpedia.DbpediaSearcherInFile;
import tr.edu.ege.seagent.dbpedia.SemanticTag;
import tr.edu.ege.seagent.deasciifier.TurkishDeasciifier;
import tr.edu.ege.seagent.entity.Entity;
import tr.edu.ege.seagent.json.JsonEntity;
import tr.edu.ege.seagent.json.JSONGenerator;
import tr.edu.ege.seagent.letter.LetterOperator;
import tr.edu.ege.seagent.lookup.DbpediaLookup;
import tr.edu.ege.seagent.politics.OntoGenerator;
import tr.edu.ege.seagent.regex.RegexOperator;
import tr.edu.ege.seagent.zemberek.DisambiguateSentences;
import tr.edu.ege.seagent.zemberek.NGramOperator;
import tr.edu.ege.seagent.zemberek.SentenceParser;
import zemberek.morphology.ambiguity.Z3MarkovModelDisambiguator;
import zemberek.morphology.apps.TurkishMorphParser;
import zemberek.morphology.apps.TurkishSentenceParser;

public class TextAnalyser {

	public ArrayList<JsonEntity> regexCapitalLetterLookupPipeline(
			String content, String perFilePath, String locFilePath,
			String orgFilePath) throws IOException, SAXException,
			TransformerException, ParserConfigurationException {

		ArrayList<JsonEntity> entities = new ArrayList<JsonEntity>();

		RegexOperator rgOperator = new RegexOperator();

		StringBuffer sb = rgOperator.eliminateLowerCaseWords(content,
				rgOperator.DELIMITER);
		TreeSet<String> candidateNerList = rgOperator
				.obtainCandidateNamedEntities(sb, rgOperator.DELIMITER);

		for (String string : candidateNerList) {
			System.out.println("cand :[" + string + "]");
		}

		TreeSet<SemanticTag> sTagList = new TreeSet<SemanticTag>();
		// TODO BURADA SEMANTIC TAG LIST SIKINTILI

		TreeSet<SemanticTag> resolveNamedEntityListLookupDbpedia = new OntoGenerator()
				.searchInOnto(sTagList, candidateNerList, perFilePath,
						locFilePath, orgFilePath);

		if (!resolveNamedEntityListLookupDbpedia.isEmpty()) {
			// DBpedia da çözümlenebilen CandidateNerList elemanlarını sil
			for (SemanticTag dbpedia : resolveNamedEntityListLookupDbpedia) {
				String obtainedNer = dbpedia.getName().replace("\"", "");
				
				// Hepsi Büyük harfle olan bir kelime geldiyse
				if (obtainedNer.length() > 3
						&& Character.isUpperCase(obtainedNer.charAt(4))) {
					String oldStr = new LetterOperator().convertAllUpperCase(
							obtainedNer);
					System.out.println("[" + oldStr + "]-[" + dbpedia.getName()
							+ "]");
					candidateNerList.remove(new String(obtainedNer));
				}
				
				// Jaro-Winkler e göre benzerliği bulunduysa
				candidateNerList.remove(new String(obtainedNer));
			}
			regexResolvedEntityList(candidateNerList,
					resolveNamedEntityListLookupDbpedia, perFilePath,
					locFilePath, orgFilePath);
			entities = new JSONGenerator().acquireEntitiesRegex(content,
					resolveNamedEntityListLookupDbpedia);
		} else {

			TreeSet<SemanticTag> resolveNamedEntityLookupDbpedia = new TreeSet<SemanticTag>();
			regexResolvedEntityList(candidateNerList,
					resolveNamedEntityLookupDbpedia, perFilePath, locFilePath,
					orgFilePath);
			entities = new JSONGenerator().acquireEntitiesRegex(content,
					resolveNamedEntityLookupDbpedia);
		}
		return entities;

	}

	public void regexResolvedEntityList(TreeSet<String> candidateNerList,
			TreeSet<SemanticTag> resolveNamedEntityListLookupDbpedia,
			String perFilePath, String locFilePath, String orgFilePath)
			throws IOException, SAXException, TransformerException,
			ParserConfigurationException {
		TreeSet<SemanticTag> sTagList = new TreeSet<SemanticTag>();
		for (String word : candidateNerList) {
			String[] splitedWords = word.split(" ");
			TreeSet<String> ngramList = new NGramOperator().generateNgramsUpto(
					word, splitedWords.length);

			TreeSet<SemanticTag> tempResolveNamedEntityLookup = new OntoGenerator()
					.searchInOnto(sTagList, ngramList, perFilePath,
							locFilePath, orgFilePath);
			if (!tempResolveNamedEntityLookup.isEmpty())
				resolveNamedEntityListLookupDbpedia
						.addAll(tempResolveNamedEntityLookup);
		}
	}

	public Entity extractNamedEntitySet(Entity entityContent) {
		TurkishMorphParser morphParser;
		TreeSet<Entity> nerList = new TreeSet<Entity>();
		String content = entityContent.getContent();
		try {
			morphParser = TurkishMorphParser.createWithDefaults();
			Z3MarkovModelDisambiguator disambiguator = new Z3MarkovModelDisambiguator();
			TurkishSentenceParser sentenceParser = new TurkishSentenceParser(
					morphParser, disambiguator);
			DisambiguateSentences disa = new DisambiguateSentences(
					sentenceParser);
			nerList = disa.parseAndDisambiguateSet(content);
			Entity first = nerList.first();
			entityContent.setEntityList(first.getEntityList());
			entityContent.addSet(first);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entityContent;
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

		// Set up a simple configuration that logs on the console.
		// BasicConfigurator.configure();
		String jsonResult = "{\"text\":\"\",\"entities\":{}}";
		ArrayList<String> nerList = extractNamedEntity(content);

		ArrayList<SemanticTag> dbpediaList = new DbpediaSearcher()
				.resolveInDbpedia(nerList);

		if (nerList.size() == dbpediaList.size()) {
			// if it is accomplished by %100
			ArrayList<JsonEntity> entities = new ArrayList<JsonEntity>();
			int cnt = 1;
			for (String namedEntity : nerList) {

				// find all occurrences forward
				for (int beginOffset = -1; (beginOffset = content.indexOf(
						namedEntity, beginOffset + 1)) != -1;) {
					int endOffset = namedEntity.length() + beginOffset;

					entities.add(new JsonEntity("T" + cnt, dbpediaList.get(
							cnt - 1).getType(), beginOffset, endOffset));
					cnt++;
				}
			}
			// creates json content from the Named Entities
			jsonResult = new JSONGenerator().createJson(content, entities);
		}
		return jsonResult;
	}

	// for "Vites"
	public ArrayList<JsonEntity> analyzeTextList(String content)
			throws IOException {

		ArrayList<JsonEntity> entities = null;
		ArrayList<String> nerList = extractNamedEntity(content);

		ArrayList<SemanticTag> dbpediaList = new DbpediaSearcher()
				.resolveInDbpedia(nerList);

		// ArrayList<Dbpedia> dbpediaList = new DbpediaSearcherInFile()
		// .resolveInDbpedia(nerList);

		if (nerList.size() == dbpediaList.size()) {
			// if it is accomplished by %100
			entities = new ArrayList<JsonEntity>();
			int cnt = 1;
			for (String namedEntity : nerList) {
				// find all occurrences forward
				for (int beginOffset = -1; (beginOffset = content.indexOf(
						namedEntity, beginOffset + 1)) != -1;) {
					int endOffset = namedEntity.length() + beginOffset;

					entities.add(new JsonEntity(namedEntity, dbpediaList.get(
							cnt - 1).getUri(), "T" + cnt, dbpediaList.get(
							cnt - 1).getType(), beginOffset, endOffset));
					cnt++;
				}
			}
		}
		return entities;
	}

	public ArrayList<Entity> getEntitiesCapitalLetterText(
			TreeSet<Entity> content) throws IOException, SAXException,
			TransformerException, ParserConfigurationException {
		// ArrayList<Entities> entities = getCapitalLetterPipeline(content);
		ArrayList<Entity> entities = getCapitalLetterLookupPipeline(content);

		return entities;

	}

	public String analyzeCapitalLetterText(String content) throws IOException,
			SAXException, TransformerException, ParserConfigurationException {
		String jsonResult = "{\"text\":\"\",\"entities\":{}}";

		ArrayList<JsonEntity> entities = getCapitalLetterPipeline(content);
		jsonResult = new JSONGenerator().createJson(content, entities);

		return jsonResult;

	}

	private ArrayList<Entity> getCapitalLetterLookupPipeline(
			TreeSet<Entity> content) throws IOException, SAXException,
			TransformerException, ParserConfigurationException {
		ArrayList<Entity> entities = new ArrayList<Entity>();
		TreeSet<Entity> candidateNerList = new TreeSet<Entity>();
		// candidateNerList = extractNamedEntitySet(content);
		// TreeSet<Entity> resolveNamedEntityListLookupDbpedia = new
		// DbpediaLookup()
		// .lookupDbpediaSet(candidateNerList);
		// if (!resolveNamedEntityListLookupDbpedia.isEmpty()) {
		// // DBpedia da çözümlenebilen CandidateNerList elemanlarını sil
		// for (Entity dbpedia : resolveNamedEntityListLookupDbpedia) {
		// candidateNerList.remove(new String(dbpedia.getName()));
		// }
		// obtainTempResolvedEntityList(candidateNerList,
		// resolveNamedEntityListLookupDbpedia);
		// entities = new JSONGenerator().acquireEntitiesSet(content,
		// resolveNamedEntityListLookupDbpedia);
		// } else {
		//
		// TreeSet<Entity> resolveNamedEntityLookupDbpedia = new
		// TreeSet<Entity>();
		// obtainTempResolvedEntityList(candidateNerList,
		// resolveNamedEntityLookupDbpedia);
		// entities = new JSONGenerator().acquireEntitiesSet(content,
		// resolveNamedEntityLookupDbpedia);
		// }
		return entities;
	}

	public void obtainTempResolvedEntityList(TreeSet<Entity> candidateNerList,
			TreeSet<Entity> resolveNamedEntityListLookupDbpedia)
			throws IOException, SAXException, TransformerException,
			ParserConfigurationException {
		Iterator<Entity> iterator = candidateNerList.iterator();
		while (iterator.hasNext()) {
			String word = iterator.next().getName().toString();
			String[] splitedWords = word.split(" ");
			// TreeSet<Entity> ngramList = new NGramOperator()
			// .createNGramArray(splitedWords, splitedWords.length);

			// TreeSet<Entity> tempResolveNamedEntityLookup = new
			// DbpediaLookup()
			// .lookupDbpediaSet(ngramList);
			// resolveNamedEntityListLookupDbpedia
			// .addAll(tempResolveNamedEntityLookup);
		}
	}

	// pipeline for Capital Letter Task
	private ArrayList<JsonEntity> getCapitalLetterPipeline(String content)
			throws IOException, SAXException, TransformerException,
			ParserConfigurationException {

		ArrayList<JsonEntity> entities = new ArrayList<JsonEntity>();
		ArrayList<String> nerList = extractNamedEntity(content);
		System.out.println(nerList.toString());

		// burası fazla zaman harcıyo
		ArrayList<SemanticTag> dbpediaList = new DbpediaSearcher()
				.resolveInDbpedia(nerList);
		if (!dbpediaList.isEmpty()) {

			entities = new JSONGenerator()
					.acquireEntities(content, dbpediaList);
		} else {
			SentenceParser sParser = new SentenceParser();
			ArrayList<String> properNounList = new ArrayList<String>();
			// starting with capital letters
			properNounList = sParser.prepareSentenceParser(content);

			// generate NGrams
			List<String> ngramList = new NGramOperator().generateNGramList(
					properNounList, properNounList.size());

			// // resolve in file
			// ArrayList<Dbpedia> resolveNamedEntityInFileDbpedia = new
			// DbpediaSearcherInFile()
			// .resolveInDbpedia(ngramList);
			// if (resolveNamedEntityInFileDbpedia.size() > 0) {
			// entities = new JSONGenerator().acquireEntities(content,
			// resolveNamedEntityInFileDbpedia);

			// resolve in file
			ArrayList<SemanticTag> resolveNamedEntityLookupDbpedia = new DbpediaLookup()
					.lookupDbpedia(ngramList);
			if (resolveNamedEntityLookupDbpedia.size() > 0) {
				entities = new JSONGenerator().acquireEntities(content,
						resolveNamedEntityLookupDbpedia);
			} else {

				// resolve generated NGrams in DBpedia
				ArrayList<SemanticTag> resolveNGramsInDbpedia = new DbpediaSearcher()
						.resolveNGramsInDbpedia(ngramList);

				DBpediaDisambiguator dbpediaDis = new DBpediaDisambiguator();
				ArrayList<SemanticTag> disambiguatedDbpediaList = dbpediaDis
						.disambiguateDbpedia(resolveNGramsInDbpedia);

				// creates json content from the Named Entities
				JSONGenerator jsonGenerator = new JSONGenerator();
				entities = jsonGenerator.acquireEntities(content,
						disambiguatedDbpediaList);
			}
		}
		return entities;
	}

	public String demonstrateHTMLContent(String content) throws IOException {
		ArrayList<JsonEntity> analyzeTextList = new ArrayList<JsonEntity>();
		analyzeTextList = new TextAnalyser().analyzeTextList(content);

		String label = "";
		String resultContent = content;

		if (analyzeTextList == null) {
			resultContent = "";
		} else {

			String oldNamedEntity, newNamedEntity = "";
			String coloringEnd = "</span></a></mark>";

			for (JsonEntity entities : analyzeTextList) {
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

	public static void main(String[] args) throws IOException, ParseException,
			URISyntaxException, SAXException, TransformerException,
			ParserConfigurationException {
		String perFullPath = "files/dbpediaNames.csv";
		String locFullPath = "files/dbpediaLocations.csv";
		String orgFullPath = "files/DbpediaOrg.csv";

		String deasciiFullPath = "files/turkishPatternTable";

		String content = "CHP Genel Başkanı Kml Klçdrglu Istnbl'dan Izmr'e geldi.";
		// String content = "Kml Klçdrglu";
		content = new TurkishDeasciifier().deasciifySentence(content,
				deasciiFullPath);

		ArrayList<JsonEntity> regexCapitalLetterLookupPipeline = new TextAnalyser()
				.regexCapitalLetterLookupPipeline(content, perFullPath,
						locFullPath, orgFullPath);
		String resultContent = new JSONGenerator().createJsonRegex(content,
				regexCapitalLetterLookupPipeline);
		System.out.println(resultContent);

		// String content =
		// "Mustafa Kemal Atatürk ve İsmet İnönü, Türkiye Büyük Millet Meclisi açılışı için İstanbul'dan gelerek Ankara'da kaldılar.";
		// new TextAnalyser().demonstrateHTMLContent(content);

		// String content = "Yapılan düğünde Arda Turan hazır bulundu.";
		//
		// // // test named entities
		// ArrayList<String> nerList = new TextAnalyser()
		// .extractNamedEntity(content);
		// System.out.println("The size of Named Entity List : " +
		// nerList.size());
		// for (String str : nerList) {
		// System.out.println(str);
		// }
		//
		// System.out.println(new
		// TextAnalyser().demonstrateHTMLContent(content));
		//
		// // problem bundan sonra başlar "Mustafa Kemal Atatürk İzmir" dbpedia
		// // search
		// // için n-gram uygulaması
		// ArrayList<SemanticTag> dbpediaList = new DbpediaSearcherInFile()
		// .resolveInDbpedia(nerList);
		// for (SemanticTag dbpedia : dbpediaList) {
		// System.out.println(dbpedia.getUri());
		// }

	}
}
