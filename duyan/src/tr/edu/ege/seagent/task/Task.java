package tr.edu.ege.seagent.task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import tr.edu.ege.seagent.entity.Entity;

public interface Task {

	// handles the content and converts it to a jSon string
	String perform(String text) throws IOException, SAXException, TransformerException, ParserConfigurationException;
	
	ArrayList<Entity> generateEntities(TreeSet<Entity> content) throws IOException, SAXException, TransformerException, ParserConfigurationException;
}
