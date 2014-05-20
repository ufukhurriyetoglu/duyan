package tr.edu.ege.seagent.json;

public class Entities {
	private String id;
	private String dbpediaUri;
	private String type;
	private String position;
	private int start;
	private int end;

	public Entities(String id, String type, int start, int end) {
		this.id = id;
		this.type = type;
		this.start = start;
		this.end = end;
	}
	
	public Entities(String dbpediaUri, String id,  String type, int start, int end) {
		this.dbpediaUri = dbpediaUri;
		this.id = id;
		this.type = type;
		this.start = start;
		this.end = end;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getT() {
		return id;
	}

	public void setT(String id) {
		this.id = id;
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

	public String getDbpediaUri() {
		return dbpediaUri;
	}

	public void setDbpediaUri(String dbpediaUri) {
		this.dbpediaUri = dbpediaUri;
	}

}
