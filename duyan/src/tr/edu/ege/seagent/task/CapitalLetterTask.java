package tr.edu.ege.seagent.task;

import java.io.IOException;
import java.util.ArrayList;

import tr.edu.ege.seagent.json.Entities;
import tr.edu.ege.seagent.main.TextAnalyser;

public class CapitalLetterTask implements Task {

	@Override
	public String perform(String text) throws IOException {

		TextAnalyser tAnalyser = new TextAnalyser();
		String jsonContent = tAnalyser.analyzeCapitalLetterText(text);
		return jsonContent;
	}

	@Override
	public ArrayList<Entities> generateEntities(String content) throws IOException {
		TextAnalyser tAnalyser = new TextAnalyser();
		return tAnalyser.getEntitiesCapitalLetterText(content);
	}

}
