package tr.edu.ege.seagent.regex;

public class Regex {
	private String word;
	private int start;
	private int end;
	
	public Regex(String word, int start, int end) {
		this.setWord(word);
		this.setStart(start);
		this.setEnd(end);
	}
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
}
