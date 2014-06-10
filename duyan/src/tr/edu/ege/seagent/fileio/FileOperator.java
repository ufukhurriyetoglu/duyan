package tr.edu.ege.seagent.fileio;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		String filePath = "files/dbpediaList.txt";
		FileOperator fo = new FileOperator();
		ArrayList<SemanticTag> readFile = fo.ReadFile(filePath);
		for (SemanticTag string : readFile) {
			System.out.println(string.getName());
		}
		System.out.println(readFile.size());
	}
}
