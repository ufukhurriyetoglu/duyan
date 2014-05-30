package tr.edu.ege.seagent.zemberek;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class NGramOperator {
	public void showNgramList(ArrayList<String> properNounList, int ngramSize) {
		long startTime = System.currentTimeMillis();
		List<String> ngrams = generateNGramList(properNounList,
				properNounList.size());
		long stopTime = System.currentTimeMillis();
		System.out.println("Time : " + (stopTime - startTime)
				+ " miliSeconds and NGram size : " + ngrams.size()
				+ " NGrams List : " + ngrams.toString());
	}

	public List<String> generateNGramList(ArrayList<String> properNounList,
			int maxGramSize) {

		// List<String> sentence =
		// Arrays.asList(properNounList.split("[\\W+]"));
		// boolean isUpperCase = Character.isUpperCase(properNounList.get(0)
		// .charAt(0));

		List<String> ngrams = new ArrayList<String>();
		List<String> capitalizedProperNounList = new ArrayList<String>();

		for (String word : properNounList) {
			String capitalizedNGram = word.substring(0, 1).toUpperCase()
					+ word.substring(1);
			capitalizedProperNounList.add(capitalizedNGram);
		}

		List<String> sentence = capitalizedProperNounList;

		int ngramSize = 0;
		StringBuilder sb = null;

		// sentence becomes ngrams
		for (ListIterator<String> it = sentence.listIterator(); it.hasNext();) {
			String word = (String) it.next();

			// 1- add the word itself
			sb = new StringBuilder(word);
			ngrams.add(word);
			ngramSize = 1;
			it.previous();

			// 2- insert prevs of the word and add those too
			while (it.hasPrevious() && ngramSize < maxGramSize) {
				sb.insert(0, ' ');
				sb.insert(0, it.previous());
				ngrams.add(sb.toString());
				ngramSize++;
			}

			// go back to initial position
			while (ngramSize > 0) {
				ngramSize--;
				it.next();
			}
		}
		return ngrams;
	}

}
