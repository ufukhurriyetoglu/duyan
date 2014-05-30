package tr.edu.ege.seagent.zemberek;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class NGramTest {

	@Test
	public void capitalLetterNGramTest() throws Exception {
		/*
		 * Expected NGram : (the worst case = 32) 1. MHP Genel 2. Genel Başkanı
		 * 3. Başkanı Devlet 4. Devlet Bahçeli 5. MHP Genel Başkanı 6. Genel
		 * Başkanı Devlet 7. Başkanı Devlet Bahçeli 8. MHP Genel Başkanı Devlet
		 * 9. Genel Başkanı Devlet Bahçeli 10. MHP Genel Başkanı Devlet Bahçeli
		 * 11. MHP 12. Genel 13. Başkanı 14. Devlet 15. Bahçeli
		 */

		ArrayList<String> properNounList = new ArrayList<String>();
		properNounList.add("MHP");
		properNounList.add("Genel");
		properNounList.add("Başkanı");
		properNounList.add("Devlet");
		properNounList.add("Bahçeli");

		NGramOperator nGram = new NGramOperator();
		nGram.showNgramList(properNounList, properNounList.size());

		assertEquals(15,
				nGram.generateNGramList(properNounList, properNounList.size())
						.size());
	}
	
	@Test
	public void smallLetterNGramTest() throws Exception {
		ArrayList<String> properNounList = new ArrayList<String>();
		properNounList.add("mhp");
		properNounList.add("genel");
		properNounList.add("başkanı");
		properNounList.add("devlet");
		properNounList.add("bahçeli");

		NGramOperator nGram = new NGramOperator();
		nGram.showNgramList(properNounList, properNounList.size());

		assertEquals(15,
				nGram.generateNGramList(properNounList, properNounList.size())
						.size());
	}

}
