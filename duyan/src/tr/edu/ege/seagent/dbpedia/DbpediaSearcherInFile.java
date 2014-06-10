package tr.edu.ege.seagent.dbpedia;

import java.util.ArrayList;
import java.util.List;

import tr.edu.ege.seagent.fileio.FileOperator;

public class DbpediaSearcherInFile {

	// TODO absolute path
	private static final String DBPEDIA_LIST_FILE = "files/dbpediaList.txt";

	public ArrayList<SemanticTag> resolveInDbpedia(List<String> ngramList) {
		FileOperator fo = new FileOperator();
		ArrayList<SemanticTag> dbpediaFileList = fo.ReadFile(DBPEDIA_LIST_FILE);
		// showOutput(DBPEDIA_LIST_FILE, fo);

		// foreach
		ArrayList<SemanticTag> dbpediaList = new ArrayList<SemanticTag>();
		for (int i = 0; i < dbpediaFileList.size(); i++) {
			for (int j = 0; j < ngramList.size(); j++) {
				if (dbpediaFileList.get(i).getName().equals(ngramList.get(j))) {
					dbpediaList.add(new SemanticTag(dbpediaFileList.get(i)
							.getName(), dbpediaFileList.get(i).getType(),
							dbpediaFileList.get(i).getUri()));
				}
			}
		}
		return dbpediaList;
	}

	public void searchInDbpedia() {
		FileOperator fo = new FileOperator();
		showOutput(DBPEDIA_LIST_FILE, fo);
	}

	private void showOutput(String filePath, FileOperator fo) {
		ArrayList<SemanticTag> readFile = fo.ReadFile(filePath);
		for (SemanticTag dbpediaMember : readFile) {
			System.out.println(dbpediaMember.getName() + " "
					+ dbpediaMember.getType() + " " + dbpediaMember.getUri());
		}
		System.out.println(readFile.size());
	}

	public static void main(String[] args) {
		DbpediaSearcherInFile dbpediaFile = new DbpediaSearcherInFile();
		// dbpediaFile.searchInDbpedia();

		ArrayList<String> nerList = new ArrayList<String>();
		nerList.add("Ankara");
		ArrayList<SemanticTag> resolveInDbpedia = dbpediaFile
				.resolveInDbpedia(nerList);
		for (SemanticTag dbpedia : resolveInDbpedia) {
			System.out.println(dbpedia.getUri() + " -- " + dbpedia.getType());
		}

	}
}
