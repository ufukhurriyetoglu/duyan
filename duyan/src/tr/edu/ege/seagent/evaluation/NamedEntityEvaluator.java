package tr.edu.ege.seagent.evaluation;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import tr.edu.ege.seagent.entity.Entity;
import tr.edu.ege.seagent.json.JsonEntity;
import tr.edu.ege.seagent.main.TextAnalyser;
import tr.edu.ege.seagent.strategy.CapitalLetterCompositeStrategy;
import tr.edu.ege.seagent.timer.TimeWatch;

public class NamedEntityEvaluator {

	static Logger log = Logger.getLogger(NamedEntityEvaluator.class.getName());
	// Run --> Run Configurations --> Classpath (tab) --> Click on
	// "User Entries" --> Advanced (button on the right) --> Add Folders -->
	// then navigate to the folder that contains your log4j.properties file -->
	// click Apply --> click Run

	public final String TEST_POOL_PATH = "files/testPool.txt";
	DecimalFormat df = new DecimalFormat("#.###");

	public void calculateTotalAccuracy(
			ArrayList<Measurement> calculatedAccuracyList) {
		Double totalAccuracy = 0.0;
		int cnt = 0;
		for (Measurement measurement : calculatedAccuracyList) {
			totalAccuracy += measurement.getAccuracy();
			cnt++;
		}
		log.info("Total Accuracy : " + df.format(totalAccuracy / cnt)
				+ " -- Percentage : %" + df.format(totalAccuracy / cnt * 100));
	}

	public ArrayList<Measurement> calculateStrategyAccuracy(
			ArrayList<Measurement> testPoolList) throws IOException,
			SAXException, TransformerException, ParserConfigurationException {

		ArrayList<Measurement> measurementList = new ArrayList<Measurement>();
		for (Measurement measurement : testPoolList) {
			TimeWatch watch = TimeWatch.start();

			Entity analyzeTextList = new CapitalLetterCompositeStrategy()
					.doOperation(new Entity(measurement.getContent()));
			String[] trueList = measurement.getTrueNamedEntityList();

			int equalNer = 0;
			for (int i = 0; i < trueList.length; i++) {
				for (JsonEntity entity : analyzeTextList.getEntityJsonList()) {
					String foundNamedEntity = entity.getName();
					if (trueList[i].equals(foundNamedEntity)) {
						log.info(" Found : " + trueList[i] + " = "
								+ foundNamedEntity);
						equalNer++;
						break;
					}
				}
			}

			double accuracy = equalNer / (double) trueList.length;
			double accuracyPercentage = accuracy * 100;

			measurementList.add(new Measurement(accuracy, accuracyPercentage));

			long passedTimeInSeconds = watch.time(TimeUnit.SECONDS);
			log.info("Time : " + passedTimeInSeconds + " seconds. Accuracy : %"
					+ df.format(accuracyPercentage));
		}

		return measurementList;
	}

	public ArrayList<Measurement> calculateAccuracy(
			ArrayList<Measurement> testPoolList) throws IOException,
			SAXException, TransformerException, ParserConfigurationException {

		ArrayList<Measurement> measurementList = new ArrayList<Measurement>();
		for (Measurement measurement : testPoolList) {
			TimeWatch watch = TimeWatch.start();

			// ArrayList<Entity> analyzeTextList = new CapitalLetterTask()
			// .generateEntities(new Entity(measurement.getContent()));
//			ArrayList<JsonEntity> regexCapitalLetterLookupPipeline = new TextAnalyser()
//					.regexCapitalLetterLookupPipeline(measurement.getContent());
			String[] trueList = measurement.getTrueNamedEntityList();
//
			int equalNer = 0;
//			for (int i = 0; i < trueList.length; i++) {
//				for (JsonEntity entity : regexCapitalLetterLookupPipeline) {
//					String foundNamedEntity = entity.getName();
//					if (trueList[i].equals(foundNamedEntity)) {
//						 log.info(" Found : " + trueList[i] + " = " +
//						 foundNamedEntity);
//						equalNer++;
//						break;
//					}
//				}
//			}

			double accuracy = equalNer / (double) trueList.length;
			double accuracyPercentage = accuracy * 100;

			measurementList.add(new Measurement(accuracy, accuracyPercentage));

			long passedTimeInSeconds = watch.time(TimeUnit.SECONDS);
			log.info("Time : " + passedTimeInSeconds + " seconds. Accuracy : %"
					+ df.format(accuracyPercentage));
		}

		return measurementList;
	}

	public void showOutput(ArrayList<Measurement> testPoolList) {
		for (Measurement test : testPoolList) {
			System.out.println(test.getContent() + " --- "
					+ test.getTrueNamedEntityList().toString());
		}
		System.out.println(testPoolList.size());
	}

}
