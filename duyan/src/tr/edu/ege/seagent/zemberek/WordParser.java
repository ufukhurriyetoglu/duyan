package tr.edu.ege.seagent.zemberek;

import java.util.List;

import zemberek.morphology.apps.TurkishMorphParser;
import zemberek.morphology.parser.MorphParse;

public class WordParser {

	TurkishMorphParser parser;

	public WordParser(TurkishMorphParser parser) {
		this.parser = parser;
	}

	public String parse(String word) {
		String actualResult = "";
		System.out.println("Word = " + word);
		List<MorphParse> parses = parser.parse(word);
		for (MorphParse parse : parses) {
			System.out.println(parse.formatLong());
			String parsedWord = parse.inflectionalGroups.get(0).toString();
			System.out.println("inf: " + parsedWord);
			if (parsedWord.contains("Noun,Prop")) {
				System.out.println(word + " is proper noun.");
				actualResult = word + " is proper noun.";
			}
		}
		return actualResult;
	}

}
