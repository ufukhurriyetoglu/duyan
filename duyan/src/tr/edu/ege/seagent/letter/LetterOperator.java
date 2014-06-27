package tr.edu.ege.seagent.letter;

import java.util.Locale;

public class LetterOperator {

	// Abbre. FIFA, UEFA, CHP, MHP, AK Parti 4 ve üzeri olursa kısaltma olmaz
	// heuristic uygulandı
	private static final int ABBR_LEN_LIMIT = 4;

	public static void main(String[] args) {
		String content = "CHP İSTANBUL'dan Konya’ya gelen DEVLET BAHÇELİ daha sonra KEMAL KILIÇDAROĞLU ile ANKARA'da görüştü.";

		String word = "CHP";
		String word2 = new LetterOperator().convertStartWithUppercase(word);
		System.out.println("kelime : " + word2);
		System.out.println("eski : "
				+ new LetterOperator().convertAllUpperCase(word2));
		System.out.println("cümle : " + content.replace(word, word2));

	}

	public String convertStartWithUppercase(String word) {

		String startWithUpperWord = "";
		// Abbre. FIFA, UEFA, CHP, MHP
		// System.out.println(" Word : " + word.trim() + " LEN : "
		// + word.trim().length());
		if (word.trim().length() > ABBR_LEN_LIMIT) {
			if (Character.isUpperCase(word.charAt(ABBR_LEN_LIMIT))) {
				Locale trLocale = Locale.forLanguageTag("tr-TR");
				if (!word.contains(" ")) {
					char first = word.charAt(0);
					startWithUpperWord = first
							+ word.substring(1).toLowerCase(trLocale);
				} else {
					String[] split = word.split(" ");
					for (int i = 0; i < split.length; i++) {
						char first = split[i].charAt(0);
						startWithUpperWord = startWithUpperWord + " " + first
								+ split[i].substring(1).toLowerCase(trLocale);
					}
				}
			} else {
				startWithUpperWord = word;
			}
		} else {
			startWithUpperWord = word;
		}
		return startWithUpperWord.trim();
	}

	public String convertAllUpperCase(String word) {

		String startWithLowerWord = "";
		// Abbre. FIFA, UEFA, CHP, MHP
		if (word.trim().length() > ABBR_LEN_LIMIT) {
			if (Character.isLowerCase(word.charAt(ABBR_LEN_LIMIT))) {
				Locale trLocale = Locale.forLanguageTag("tr-TR");
				if (!word.contains(" ")) {
					char first = word.charAt(0);
					startWithLowerWord = first
							+ word.substring(1).toUpperCase(trLocale);
				} else {
					String[] split = word.split(" ");
					for (int i = 0; i < split.length; i++) {
						char first = split[i].charAt(0);
						startWithLowerWord = startWithLowerWord + " " + first
								+ split[i].substring(1).toUpperCase(trLocale);
					}
				}
			} else {
				startWithLowerWord = word;
			}
		} else {
			startWithLowerWord = word;
		}
		return startWithLowerWord.trim();
	}

}
