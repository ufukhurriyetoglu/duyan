package tr.edu.ege.seagent.zemberek;

import java.util.ArrayList;

import zemberek.morphology.apps.TurkishSentenceParser;
import zemberek.morphology.parser.MorphParse;
import zemberek.morphology.parser.SentenceMorphParse;

public class DisambiguateSentences {

	TurkishSentenceParser sentenceParser;

	public DisambiguateSentences(TurkishSentenceParser sentenceParser) {
		this.sentenceParser = sentenceParser;
	}

	public ArrayList<String> parseAndDisambiguate(String sentence) {
		SentenceMorphParse sentenceParse = sentenceParser.parse(sentence);
		sentenceParser.disambiguate(sentenceParse);
		return obtainParsedResult(sentenceParse);
	}

	private ArrayList<String> obtainParsedResult(SentenceMorphParse sentenceParse) {
		ArrayList<String> nerList = new ArrayList<String>();
		String result = "",igWord = "";
		for (SentenceMorphParse.Entry entry : sentenceParse) {
			// find the words started with capital letter
			// A3sg : 3sg number person agreement Third Person Singular Noun
			for (MorphParse parse : entry.parses) {
				igWord = parse.inflectionalGroups.get(0).toString();
				if (Character.isUpperCase(entry.input.charAt(0))
						&& igWord.contains("(Noun,Prop;A3sg+Pnon+Nom)")
						&& (!result.contains(entry.input)))
					result = result + " " + entry.input;
				// Location info: Loc, Dat(Dative = ismin -e hali) and
				// Abl(Ablative = ismin -den hali)
				else if (Character.isUpperCase(entry.input.charAt(0)) && (!result.contains(entry.input) && (igWord
						.contains("Dat") || igWord.contains("Loc") || igWord
							.contains("Abl"))))
					result = result + " " + entry.input;
			}

			if (!igWord.contains("(Noun,Prop;A3sg+Pnon+Nom)")
					&& (!result.contains(entry.input))) {
				result = result + " ";
				if (!result.equals(" ")) // result text is not empty string
					if (result.contains("'")) {
						int apostPos = result.indexOf("'");
						if (apostPos > 0) {
							result = result.substring(0, apostPos);
							nerList.add(result.trim());
						}
					} else {
						nerList.add(result.trim());
					}

				result = "";
			}

		}
		return nerList;
	}

}