package tr.edu.ege.seagent.zemberek;

import java.io.IOException;
import java.util.ArrayList;

import zemberek.morphology.ambiguity.Z3MarkovModelDisambiguator;
import zemberek.morphology.apps.TurkishMorphParser;
import zemberek.morphology.apps.TurkishSentenceParser;
import zemberek.morphology.parser.MorphParse;
import zemberek.morphology.parser.SentenceMorphParse;

public class SentenceParser {

	TurkishSentenceParser sentenceParser;

	public SentenceParser() {
	}

	public SentenceParser(TurkishSentenceParser sentenceParser) {
		this.sentenceParser = sentenceParser;
	}

	public ArrayList<String> parseAndDisambiguate(String sentence) {
		System.out.println("Sentence  = " + sentence);
		SentenceMorphParse sentenceParse = sentenceParser.parse(sentence);

		return obtainParsedResult(sentenceParse);

	}

	public ArrayList<String> prepareSentenceParser(String sentence) {
		ArrayList<String> nerList = new ArrayList<String>();
		TurkishMorphParser morphParser;
		try {
			morphParser = TurkishMorphParser.createWithDefaults();
			Z3MarkovModelDisambiguator disambiguator = new Z3MarkovModelDisambiguator();

			TurkishSentenceParser sentenceParser = new TurkishSentenceParser(
					morphParser, disambiguator);
			nerList = new SentenceParser(sentenceParser)
					.parseAndDisambiguate(sentence);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nerList;
	}

	public ArrayList<String> obtainParsedResult(SentenceMorphParse sentenceParse) {
		ArrayList<String> nerList = new ArrayList<String>();
		for (SentenceMorphParse.Entry entry : sentenceParse) {
			// System.out.println("Word = " + entry.input);

			for (MorphParse parse : entry.parses) {
				// System.out.println(parse.formatLong());
				String parsedWord = parse.inflectionalGroups.get(0).toString();
				// (Noun,Prop;A3sg+Pnon+Nom)
				if (parsedWord.contains("Noun,Prop")) {
					// System.out.println("Word = " + entry.input + " {{ "
					// + parsedWord + " }} is proper noun.");
					nerList.add(entry.input);
				}
			}
		}
		return nerList;
	}

}
