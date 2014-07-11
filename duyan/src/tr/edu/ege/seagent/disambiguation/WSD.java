package tr.edu.ege.seagent.disambiguation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import tr.edu.ege.seagent.dbpedia.SemanticTag;
import tr.edu.ege.seagent.json.JSONGenerator;
import tr.edu.ege.seagent.json.JsonEntity;
import tr.edu.ege.seagent.letter.LetterOperator;
import tr.edu.ege.seagent.politics.OntoGenerator;
import tr.edu.ege.seagent.regex.RegexOperator;
import tr.edu.ege.seagent.zemberek.NGramOperator;

public class WSD {

//	public static void main(String[] args) {
//		String content = "Atatürk yılında İstanbul'dan ayrılarak Samsun'a çıktı.";
//
//		String perFullPath = "WebContent/WEB-INF/files/dbpediaDisambiNames.csv";
//		String locFullPath = "WebContent/WEB-INF/files/dbpediaLocations.csv";
//		String orgFullPath = "WebContent/WEB-INF/files/DbpediaOrg.csv";
//
//		try {
//			ArrayList<JsonEntity> regexCapitalLetterLookupPipeline = new WSD()
//					.regexCapitalLetterLookupPipeline(content, perFullPath,
//							locFullPath, orgFullPath);
//			String resultContent = new JSONGenerator().createJsonRegex(content,
//					regexCapitalLetterLookupPipeline);
//			System.out.println(resultContent);
//
//		} catch (IOException | SAXException | TransformerException
//				| ParserConfigurationException | URISyntaxException e) {
//			e.printStackTrace();
//		}
//	}

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
					String oldStr = new LetterOperator()
							.convertAllUpperCase(obtainedNer);
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

	/*
	 * tries to eliminate problems such that if DBpedia link and
	 * found named entity are different things.
	 */
	public int longestSubstr(String s, String t) {
		if (s.isEmpty() || t.isEmpty()) {
			return 0;
		}

		int m = s.length();
		int n = t.length();
		int cost = 0;
		int maxLen = 0;
		int[] p = new int[n];
		int[] d = new int[n];

		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				// calculate cost/score
				if (s.charAt(i) != t.charAt(j)) {
					cost = 0;
				} else {
					if ((i == 0) || (j == 0)) {
						cost = 1;
					} else {
						cost = p[j - 1] + 1;
					}
				}
				d[j] = cost;

				if (cost > maxLen) {
					maxLen = cost;
				}
			} // for {}

			int[] swap = p;
			p = d;
			d = swap;
		}

		return maxLen;
	}
}
