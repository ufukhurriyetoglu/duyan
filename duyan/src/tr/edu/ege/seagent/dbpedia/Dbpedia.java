package tr.edu.ege.seagent.dbpedia;

public class Dbpedia {
	private String uri;
	private String type;

	public Dbpedia(String u, String t) {
		this.setUri(u);
		this.setType(t);
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
