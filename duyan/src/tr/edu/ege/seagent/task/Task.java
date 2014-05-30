package tr.edu.ege.seagent.task;

import java.io.IOException;
import java.util.ArrayList;

import tr.edu.ege.seagent.json.Entities;

public interface Task {

	// handles the content and converts it to a jSon string
	String perform(String text) throws IOException;
	
	ArrayList<Entities> generateEntities(String content) throws IOException;
}
