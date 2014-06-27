package tr.edu.ege.seagent.wgb;

import java.util.ArrayList;

import tr.edu.ege.seagent.evaluation.Measurement;
import tr.edu.ege.seagent.fileio.FileOperator;

public class TestPoolAngularConvertor {

	private static final String READ_TEST_POOL_FILE = "/home/etmen/Desktop/testPool.txt";
	private static final String WRITE_TEST_POOL_FILE = "/home/etmen/Desktop/angularPool.txt";
	
	public static void main(String[] args) {

		ArrayList<Measurement> testPoolList = new FileOperator().readTestFile(READ_TEST_POOL_FILE);
		
		
		new FileOperator().writeToAngularFile(testPoolList, WRITE_TEST_POOL_FILE);
		
	}

}
