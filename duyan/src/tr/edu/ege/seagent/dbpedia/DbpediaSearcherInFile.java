package tr.edu.ege.seagent.dbpedia;

import java.util.ArrayList;

import tr.edu.ege.seagent.fileio.FileOperator;

public class DbpediaSearcherInFile {

	// TODO absolute path
	private static final String DBPEDIA_LIST_FILE = "/home/etmen/git/duyan/duyan/WebContent/files/dbpediaList.txt";

	public ArrayList<Dbpedia> resolveInDbpedia(ArrayList<String> nerList) {
		FileOperator fo = new FileOperator();
		ArrayList<Dbpedia> dbpediaFileList = fo.ReadFile(DBPEDIA_LIST_FILE);
		showOutput(DBPEDIA_LIST_FILE, fo);

		// foreach
		ArrayList<Dbpedia> dbpediaList = new ArrayList<Dbpedia>();
		for (int i = 0; i < dbpediaFileList.size(); i++) {
			for (int j = 0; j < nerList.size(); j++) {
				if (dbpediaFileList.get(i).getName().contains(nerList.get(j))) {
					System.out.println(dbpediaFileList.get(i).getName()
							+ " hee " + nerList.get(j));
					dbpediaList.add(new Dbpedia(
							dbpediaFileList.get(i).getUri(), dbpediaFileList
									.get(i).getType()));
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
		ArrayList<Dbpedia> readFile = fo.ReadFile(filePath);
		for (Dbpedia dbpediaMember : readFile) {
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
		ArrayList<Dbpedia> resolveInDbpedia = dbpediaFile
				.resolveInDbpedia(nerList);
		for (Dbpedia dbpedia : resolveInDbpedia) {
			System.out.println(dbpedia.getUri() + " -- " + dbpedia.getType());
		}

	}
}
