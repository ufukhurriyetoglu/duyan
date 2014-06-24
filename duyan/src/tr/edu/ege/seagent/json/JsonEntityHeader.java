package tr.edu.ege.seagent.json;

import java.util.ArrayList;

public class JsonEntityHeader {

	private String text;
	private ArrayList<JsonEntity> entities;

	public ArrayList<JsonEntity> getjsonEntityList() {
		return entities;
	}

	public void setEntities(ArrayList<JsonEntity> jsonEntityList) {
		this.entities = jsonEntityList;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}