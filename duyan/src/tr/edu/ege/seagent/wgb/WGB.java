package tr.edu.ege.seagent.wgb;

import java.util.TreeSet;

public class WGB {
	private String content;
	private TreeSet<String> contentList;

	public WGB() {
	}

	public WGB(String text, TreeSet<String> obtainCandidateNamedEntities) {
		setContent(text);
		setContentList(obtainCandidateNamedEntities);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public TreeSet<String> getContentList() {
		return contentList;
	}

	public void setContentList(TreeSet<String> contentList) {
		this.contentList = contentList;
	}

}
