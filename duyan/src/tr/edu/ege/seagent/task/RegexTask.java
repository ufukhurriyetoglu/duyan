package tr.edu.ege.seagent.task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import tr.edu.ege.seagent.evaluation.Measurement;
import tr.edu.ege.seagent.evaluation.NamedEntityEvaluator;
import tr.edu.ege.seagent.fileio.FileOperator;
import tr.edu.ege.seagent.json.JsonEntity;
import tr.edu.ege.seagent.main.TextAnalyser;
import tr.edu.ege.seagent.timer.TimeWatch;

public class RegexTask {
public static void main(String[] args) throws IOException, SAXException, TransformerException, ParserConfigurationException {
	
	
	// TASK 2 : Regex Task
	TimeWatch watch = TimeWatch.start();

	FileOperator fo = new FileOperator();
	NamedEntityEvaluator neEvaluator = new NamedEntityEvaluator();
	ArrayList<Measurement> testPoolList = fo
			.readTestFile(neEvaluator.TEST_POOL_PATH);

	TextAnalyser tAnalyser = new TextAnalyser();
	for (Measurement measurement : testPoolList) {
		System.out.println(measurement.getContent());
//		ArrayList<JsonEntity> regexCapitalLetterLookupPipeline = tAnalyser.regexCapitalLetterLookupPipeline(measurement.getContent());
//		System.out.println(regexCapitalLetterLookupPipeline.size());
//		for (JsonEntity jsonEntity : regexCapitalLetterLookupPipeline) {
//			System.out.println(jsonEntity.getName() + jsonEntity.getType() + jsonEntity.getDbpediaUri());
//		}
	}


	long passedTimeInSeconds = watch.time(TimeUnit.SECONDS);

	System.out
			.println("Passed Time : " + passedTimeInSeconds + " seconds.");
}
}
