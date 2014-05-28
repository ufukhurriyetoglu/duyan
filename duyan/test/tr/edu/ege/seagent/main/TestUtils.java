package tr.edu.ege.seagent.main;

public class TestUtils {

	public final String PER = "Person";
	public final String ORG = "Organization";
	public final String LOC = "Location";
	
	public final String EMPTY_JSON = "{\"text\":\"\",\"entities\":{}}";
	public final String DBPEDIA_CNTRL_URL = "http://dbpedia.org/sparql?default-graph-uri=http%3A%2F%2Fdbpedia.org&query=select+%3Fs+%3Fp+%3Fo+where+%7B%3Fs+%3Fp+%3Fo%7D+LIMIT+1&format=text%2Fhtml&timeout=30000&debug=on";


	public void findBeginEndOffset(String content, String word, int entityNo,
			String entityType) {
		// find all occurrences forward
		for (int beginOffset = -1; (beginOffset = content.indexOf(word,
				beginOffset + 1)) != -1;) {
			int endOffset = word.length() + beginOffset;
			System.out.println("\\\"entity" + entityNo + "\\\":[\\\"T" + entityNo
					+ "\\\",\\\"" + entityType + "\\\", [ [ " + beginOffset + ","
					+ endOffset + " ] ]]");
		}
	}

}
