package tr.edu.ege.seagent.deasciifier;

public class TurkishDeasciifier {

	public static void main(String[] args) {
		String filePath ="";
		String sentence = "CHP Genel Başkanı Kemal Kilicdaroglu Istanbul'dan Izmır'e geldi.";
		System.out
				.println(new TurkishDeasciifier().deasciifySentence(sentence,filePath));
	}

	public String deasciifySentence(String sentence,String filePath) {
		Deasciifier d = new Deasciifier(filePath);
		d.setAsciiString(sentence);
		return d.convertToTurkish();
	}

}
