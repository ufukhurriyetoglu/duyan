package tr.edu.ege.seagent.fileio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tr.edu.ege.seagent.dbpedia.SemanticTag;
import tr.edu.ege.seagent.evaluation.Measurement;
import tr.edu.ege.seagent.wgb.WGB;

import com.hp.hpl.jena.util.FileUtils;

public class FileOperator {

	/*
	 * reads three files obtained by SPARQL queries to DBpedia endpoint
	 */
	public ArrayList<SemanticTag> ReadFileDbpedia(String filePath, String type) {
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
//				System.out.println(sCurrentLine);
				// if there is no disambiguated word in the list
				if (list.size() > 2)
					temp.add(new SemanticTag(list.get(1), "", list.get(2),
							type, list.get(0)));
				else
					temp.add(new SemanticTag(list.get(1), "", "empty", type,
							list.get(0)));
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
				System.out.println(sCurrentLine);
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

	public void writeToAngularFile(ArrayList<Measurement> testPoolList,
			String writeTestPoolFile) {
		try {
			File file = new File(writeTestPoolFile);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			// {name : "  Cümle 1  ", sentence :
			// "MHP Genel Başkanı Devlet Bahçeli, cumhurbaşkanı seçimi öncesinde ortak çatı aday çerçevesinde CHP Genel Başkanı Kemal Kılıçdaroğlu ile görüştü."},
			int cnt = 1;
			for (Measurement mea : testPoolList) {
				String withoutQuote = mea.getContent().replace("\"", "\\\"")
						.replace("\\\"}", "\"}");
				bw.write("{name : \"" + "  Cümle" + cnt + "  \""
						+ ", sentence : " + withoutQuote + "}");
				if (cnt != testPoolList.size())
					bw.write(",");
				bw.newLine();
				cnt++;
			}
			bw.close();
			System.out.println("Contents are written to the file :"
					+ writeTestPoolFile);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeToFile(ArrayList<WGB> resultList, String filePath) {
		try {

			File file = new File(filePath);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for (WGB wgb : resultList) {
				bw.write("\"" + wgb.getContent() + "\"");
				bw.write(";");
				for (String ner : wgb.getContentList()) {
					bw.write(ner);
					bw.write(",");
				}
				bw.newLine();
			}
			bw.close();
			System.out.println("Contents are written to the file :" + filePath);

		} catch (IOException e) {
			e.printStackTrace();
		}
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

}
