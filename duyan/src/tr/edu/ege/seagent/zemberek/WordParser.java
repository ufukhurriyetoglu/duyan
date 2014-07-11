package tr.edu.ege.seagent.zemberek;

import java.util.List;
import java.util.TreeSet;

import zemberek.morphology.apps.TurkishMorphParser;
import zemberek.morphology.parser.MorphParse;

public class WordParser {

	TurkishMorphParser parser;

	public WordParser(TurkishMorphParser parser) {
		this.parser = parser;
	}

	// public String parse(String word) {
	// String actualResult = "";
	// List<MorphParse> parses = parser.parse(word);
	// for (MorphParse parse : parses) {
	// String parsedWord = parse.inflectionalGroups.get(0).toString();
	// if (parsedWord.contains("A3sg+Pnon")) {
	// System.out.println(word + " is proper noun.");
	// actualResult = word + " is proper noun.";
	// }
	// }
	// return actualResult;
	// }

	public TreeSet<String> parse(String[] split) {
		TreeSet<String> parsedWordList = new TreeSet<String>();
		for (int i = 0; i < split.length; i++) {

			List<MorphParse> parses = parser.parse(split[i]);
			for (MorphParse parse : parses) {
				String parsedWord = parse.inflectionalGroups.get(0).toString();
				if (parsedWord.contains("A3sg+Pnon")) {
					// System.out.println(word + " is proper noun.");
					parsedWordList.add(split[i]);
				}
			}
		}
		return parsedWordList;
	}

}
