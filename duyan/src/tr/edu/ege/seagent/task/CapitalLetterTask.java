package tr.edu.ege.seagent.task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import tr.edu.ege.seagent.entity.Entity;
import tr.edu.ege.seagent.main.TextAnalyser;

public class CapitalLetterTask implements Task {

	@Override
	public String perform(String text) throws IOException, SAXException, TransformerException, ParserConfigurationException {

		TextAnalyser tAnalyser = new TextAnalyser();
		String jsonContent = tAnalyser.analyzeCapitalLetterText(text);
		return jsonContent;
	}

	@Override
	public ArrayList<Entity> generateEntities(TreeSet<Entity> content) throws IOException, SAXException, TransformerException, ParserConfigurationException {
		TextAnalyser tAnalyser = new TextAnalyser();
		return tAnalyser.getEntitiesCapitalLetterText(content);
	}

}
