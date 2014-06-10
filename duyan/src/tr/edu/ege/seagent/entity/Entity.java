package tr.edu.ege.seagent.entity;

import java.util.ArrayList;
import java.util.TreeSet;

import tr.edu.ege.seagent.dbpedia.SemanticTag;
import tr.edu.ege.seagent.json.JsonEntity;

public class Entity implements Comparable<Entity> {
	private String id;
	private int no;
	private String content;
	private String name;
	private String uri;
	private String type;
	private String position;
	private int start;
	private int end;
	private TreeSet<String> entityList;
	private ArrayList<SemanticTag> dbpediaList;
	private ArrayList<JsonEntity> entityJsonList;
	

	public Entity(int no, String name, String content) {
		this.no = no;
		this.name = name;
		this.content = content;
	}

	public Entity(String content) {
		this.content = content;
	}

	public Entity(String u, String t) {
		this.setUri(u);
		this.setType(t);
	}

	public Entity(String id, String n, String t, String uri) {
		this.id = id;
		this.name = n;
		this.type = t;
		this.uri = uri;
	}

	public Entity(String id, String type, int start, int end) {
		this.id = id;
		this.type = type;
		this.start = start;
		this.end = end;
	}

	public Entity(String name, String uri, String id, String type, int start,
			int end) {
		this.name = name;
		this.uri = uri;
		this.id = id;
		this.type = type;
		this.start = start;
		this.end = end;
	}
	
	 public Entity(String sentence, TreeSet<String> parsedNerList) {
		 this.content = sentence;
		 this.setEntityList(parsedNerList);
		 
	}

	 private TreeSet<Entity> entitySet = new TreeSet<Entity>();
	public void addSet(Entity e) {
	      entitySet.add(e);
	   }

	public TreeSet<Entity> getEntitySet() {
		return entitySet;
	}

	public String toString() {
		return ("Entity :[ Name : " + name + ", id : " + no + ", content :"
				+ content + " ]");
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int compareTo(Entity o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public TreeSet<String> getEntityList() {
		return entityList;
	}

	public void setEntityList(TreeSet<String> entityList) {
		this.entityList = entityList;
	}

	public ArrayList<SemanticTag> getDbpediaList() {
		return dbpediaList;
	}

	public void setDbpediaList(ArrayList<SemanticTag> dbpediaResolvedList) {
		this.dbpediaList = dbpediaResolvedList;
	}

	public ArrayList<JsonEntity> getEntityJsonList() {
		return entityJsonList;
	}

	public void setEntityJsonList(ArrayList<JsonEntity> entityJsonList) {
		this.entityJsonList = entityJsonList;
	}

}
