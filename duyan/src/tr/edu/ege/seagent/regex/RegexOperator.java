package tr.edu.ege.seagent.regex;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexOperator {

	// public static final String EXAMPLE_TEXT =
	// "Mustafa Kemal Atatürk İstanbul'dan Samsun'a çıktı.";
	public static final String EXAMPLE_TEXT = "CHP Grup Başkanvekili Muharrem İnce, Twitter üzerinden 'Yalova'da kamp kuracağım' diyen Ankara Büyükşehir Belediye Başkanı Melih Gökçek'e çadır göndermesi yaptı: Yalova'da dün akşam apar topar terk edilmiş bir kamp çadırı bulunmuş! Yalova'da kamp kuracağım diyen kişiyi göreniniz var mı?";
	private static final String START_WITH_LOWERCASE = "\\b\\p{Ll}\\p{L}*\\b";
	private static final String PUNCT_PATTERN = "['\"‘’,.:;!?(){}\\[\\]<>%]";

	public final String DELIMITER = "#";

	public static void main(String[] args) {
		RegexOperator rgOperator = new RegexOperator();
		StringBuffer sb = rgOperator.eliminateLowerCaseWords(EXAMPLE_TEXT,rgOperator.DELIMITER);
		rgOperator.showListMembers(rgOperator.obtainCandidateNamedEntities(sb, rgOperator.DELIMITER));
	}

	public TreeSet<String> obtainCandidateNamedEntities(StringBuffer sb,
			String delimiter) {
		TreeSet<String> candidateNerList = new TreeSet<String>();
		String[] split = sb.toString().split(delimiter);
		for (int i = 0; i < split.length; i++) {
			if (!split[i].equals(" ")) {
				candidateNerList.add(processWord(split[i]).trim());
			}
		}
		return candidateNerList;
	}

	public StringBuffer eliminateLowerCaseWords(String content, String delimiter) {
		Pattern pattern = Pattern.compile(START_WITH_LOWERCASE);
		Matcher matcher = pattern.matcher(content);

		StringBuffer sb = new StringBuffer();

		// check all occurance
		while (matcher.find()) {
			matcher.appendReplacement(sb, delimiter);
		}
		return sb;
	}

	public String processWord(String x) {
		return x.replaceAll(PUNCT_PATTERN, "");
	}

	public void showListMembers(TreeSet<String> candidateNerList) {
		System.out.println("Size : " + candidateNerList.size());
		for (String ner : candidateNerList) {
			System.out.println(ner);
		}
	}
}
