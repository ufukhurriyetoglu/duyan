package tr.edu.ege.seagent.zemberek;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import zemberek.morphology.apps.TurkishMorphParser;

public class ZemberekTest {

	@Test
	public void properNounParserTest() throws Exception {
		TurkishMorphParser parser = TurkishMorphParser.createWithDefaults();
		// small letter sensitivity
		String actualResult = new WordParser(parser).parse("bahçeli");

		assertEquals("bahçeli is proper noun.", actualResult);
	}

	@Test
	public void sentenceParserCapitalLetterTest() throws Exception {
		SentenceParser sParser = new SentenceParser();
		ArrayList<String> nerList = new ArrayList<String>();
		
		// starting with capital letters
		nerList = sParser.prepareSentenceParser("MHP Genel Başkanı Devlet Bahçeli geldi.");
		/*
		 * Sentence = MHP Genel Başkanı Devlet Bahçeli geldi. 
		 * Word = MHP {{ (Noun,Prop;A3sg+Pnon+Nom) }} is proper noun. 
		 * Word = Genel {{ (Noun,Prop;A3sg+Pnon+Nom) }} is proper noun. 
		 * Word = Başkanı {{ (Noun,Prop;A3sg+Pnon+Nom) }} is proper noun. 
		 * Word = Devlet {{ (Noun,Prop;A3sg+Pnon+Nom) }} is proper noun. 
		 * Word = Bahçeli {{ (Noun,Prop;A3sg+Pnon+Nom) }} is proper noun.
		 */

		assertEquals(5, nerList.size());
	}

	@Test
	public void sentenceParserSmallLetterTest() throws Exception {
		SentenceParser sParser = new SentenceParser();
		// starting with capital letters
		sParser.prepareSentenceParser("mhp genel başkanı devlet bahçeli geldi.");
		/*
		 * Sentence  = mhp genel başkanı devlet bahçeli geldi.
		 * Word = bahçeli {{ (Noun,Prop;A3sg+Pnon+Nom) }} is proper noun.
		 */
	}

}
