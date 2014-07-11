package tr.edu.ege.seagent.json;

public class JsonEntity {
	private String id;
	private String dbpediaUri;
	private String type;
	private String position;
	private int start;
	private int end;
	private String name;
	private String color;

	public JsonEntity() {

	}

	public JsonEntity(String uri, String name) {
		this.dbpediaUri = uri;
		this.name = name;
	}

	public JsonEntity(String id, String type, int start, int end) {
		this.id = id;
		this.type = type;
		this.start = start;
		this.end = end;
	}

	public JsonEntity(String name, String dbpediaUri, String id, String type,
			int start, int end) {
		this.name = name;
		this.dbpediaUri = dbpediaUri;
		this.id = id;
		this.type = type;
		this.start = start;
		this.end = end;
	}

	public JsonEntity(String name, int start, int end) {
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	// eliminates entities have same name,start and end positions
	public int hashCode() {
		int hashcode = 0;
		hashcode = start;
		hashcode += end;
		hashcode += name.hashCode();
		return hashcode;
	}

	public boolean equals(Object obj) {
		// System.out.println("In equals");
		if (obj instanceof JsonEntity) {
			JsonEntity pp = (JsonEntity) obj;
			return (pp.name.equals(this.name) && pp.start == this.start && pp.end == this.end);
		} else {
			return false;
		}
	}

	public String toString() {
		return "name: " + name + "  start: " + start + " end :" + end;
	}

}
