package tr.edu.ege.seagent.evaluation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import tr.edu.ege.seagent.fileio.FileOperator;
import tr.edu.ege.seagent.timer.TimeWatch;

public class CapitalLetterStrategyTest {
	@Test
	public void CapitalLetterStrategyAccuracyTest() throws Exception {
		
		// TASK 1 : Capital Letter Task
		TimeWatch watch = TimeWatch.start();

		FileOperator fo = new FileOperator();
		NamedEntityEvaluator neEvaluator = new NamedEntityEvaluator();
		ArrayList<Measurement> testPoolList = fo
				.readTestFile(neEvaluator.TEST_POOL_PATH);
		ArrayList<Measurement> calculatedAccuracyList = new NamedEntityEvaluator()
				.calculateStrategyAccuracy(testPoolList);

		neEvaluator.calculateTotalAccuracy(calculatedAccuracyList);

		long passedTimeInSeconds = watch.time(TimeUnit.SECONDS);

		System.out
				.println("Passed Time : " + passedTimeInSeconds + " seconds.");
		
		
	}

}
