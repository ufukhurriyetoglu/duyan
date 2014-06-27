package tr.edu.ege.seagent.dbpedia;


public class SemanticTag implements Comparable<SemanticTag>{
	private String name;
	private String oldName; // JARO_WINKLER new value, old value 
	private String uri;
	private String type;
	
	public SemanticTag(String name, String oldName, String type, String uri) {
		this.name = name;
		this.setOldName(oldName);
		this.type = type;
		this.uri = uri;
	}

	public SemanticTag(String n, String t, String uri) {
		this.name = n;
		this.type = t;
		this.uri = uri;
	}

	public SemanticTag(String u, String t) {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	 @Override
	    public boolean equals(Object o) {
	        if (o instanceof SemanticTag) {
	            if (name.equals(((SemanticTag) o).name) && uri == ((SemanticTag) o).uri && type.equals(((SemanticTag) o).type)) {
	                System.out.println("List already contains this object with Name - " + name + ", uri - " + uri
	                        + " & type - " + type);
	                return true;
	            }
	        }
	        return false;
	    }

	@Override
	public int compareTo(SemanticTag o) {
		return this.name.compareTo(o.name);
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

}
