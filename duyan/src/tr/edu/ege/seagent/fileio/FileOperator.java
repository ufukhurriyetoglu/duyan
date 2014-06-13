package tr.edu.ege.seagent.fileio;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import tr.edu.ege.seagent.dbpedia.SemanticTag;
import tr.edu.ege.seagent.evaluation.Measurement;

import com.hp.hpl.jena.util.FileUtils;

public class FileOperator {
	public ArrayList<Measurement> readTestFile(String filePath) {
		BufferedReader br = null;
		ArrayList<Measurement> temp = new ArrayList<Measurement>();
		try {

			String sCurrentLine;

			FileInputStream fr = new FileInputStream(filePath);
			InputStreamReader isr = new InputStreamReader(fr,
					Charset.forName(FileUtils.encodingUTF8));
			br = new BufferedReader(isr);

			while ((sCurrentLine = br.readLine()) != null) {
				List<String> list = new ArrayList<String>(
						Arrays.asList(sCurrentLine.split(";")));
				String[] trueList = list.get(1).split(",");
				temp.add(new Measurement(list.get(0), trueList));
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return temp;
	}

	public ArrayList<SemanticTag> ReadFile(String filePath) {
		BufferedReader br = null;
		ArrayList<SemanticTag> temp = new ArrayList<SemanticTag>();
		try {

			String sCurrentLine;

			FileInputStream fr = new FileInputStream(filePath);
			InputStreamReader isr = new InputStreamReader(fr,
					Charset.forName(FileUtils.encodingUTF8));
			br = new BufferedReader(isr);

			while ((sCurrentLine = br.readLine()) != null) {
				List<String> list = new ArrayList<String>(
						Arrays.asList(sCurrentLine.split(",")));
				temp.add(new SemanticTag(list.get(0), list.get(1), list.get(2)));
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return temp;
	}

	public TreeSet<SemanticTag> ReadFileDbpedia(String filePath, String type) {
		BufferedReader br = null;
		TreeSet<SemanticTag> temp = new TreeSet<SemanticTag>();
		try {

			String sCurrentLine;

			FileInputStream fr = new FileInputStream(filePath);
			InputStreamReader isr = new InputStreamReader(fr,
					Charset.forName(FileUtils.encodingUTF8));
			br = new BufferedReader(isr);

			while ((sCurrentLine = br.readLine()) != null) {
				List<String> list = new ArrayList<String>(
						Arrays.asList(sCurrentLine.split(",")));
//				 System.out.println(list.get(1) + "Person" + list.get(0));
				temp.add(new SemanticTag(list.get(1), type, list.get(0)));
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return temp;
	}

	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		String filePathPer = "files/dbpediaNames.csv";
		String filePathLoc = "files/dbpediaLocations.csv";
		String filePathOrg = "files/DbpediaOrg.csv";
		FileOperator fo = new FileOperator();
		FileOperator foL = new FileOperator();
		FileOperator FoO = new FileOperator();
		TreeSet<SemanticTag> readFile = fo.ReadFileDbpedia(filePathPer,"Person");
//		TreeSet<SemanticTag> readFileDbpediaLoc = foL
//				.ReadFileDbpedia(filePathLoc);
//		TreeSet<SemanticTag> readFileDbpediaOrg = FoO
//				.ReadFileDbpedia(filePathOrg);
		Iterator<SemanticTag> iterator = readFile.iterator();
		while (iterator.hasNext()) {
			SemanticTag semanticTag = (SemanticTag) iterator.next();
			// System.out.println(semanticTag.getName() + " - " +
			// semanticTag.getType() + " - " + semanticTag.getUri());
			if (semanticTag.getName().contains("Devlet Bahçeli"))
				System.out.println("Found Per: " + semanticTag.getName());
		}

//		Iterator<SemanticTag> iteratorLoc = readFileDbpediaLoc.iterator();
//		while (iteratorLoc.hasNext()) {
//			SemanticTag semanticTag = (SemanticTag) iteratorLoc.next();
//			if (semanticTag.getName().contains("\"İstanbul\""))
//				System.out.println("Found Loc : " + semanticTag.getName());
//		}
//
//		Iterator<SemanticTag> iteratorOrg = readFileDbpediaOrg.iterator();
//		while (iteratorOrg.hasNext()) {
//			SemanticTag semanticTag = (SemanticTag) iteratorOrg.next();
//			if (semanticTag.getName().contains("Türkiye Basketbol Ligi"))
//				System.out.println("Found Org : " + semanticTag.getName());
//
//		}
//
//		System.out.println(readFileDbpediaLoc.size());

	}
}
