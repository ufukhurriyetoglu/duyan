package tr.edu.ege.seagent.dbpedia;

import java.util.ArrayList;
import java.util.Iterator;

public class DBpediaDisambiguator {
	public ArrayList<SemanticTag> disambiguateDbpedia(
			ArrayList<SemanticTag> resolveNGramsInDbpedia) {
		ArrayList<SemanticTag> disambiguatedDbpediaList = new ArrayList<SemanticTag>();

		String clearedUrl = "http://dbpedia.org/resource/";

		for (int i = 0; i < resolveNGramsInDbpedia.size(); i++) {
			String first = resolveNGramsInDbpedia.get(i).getUri()
					.replace(clearedUrl, "");
			for (int j = 0; j < resolveNGramsInDbpedia.size(); j++) {
				String second = resolveNGramsInDbpedia.get(j).getUri()
						.replace(clearedUrl, "");
				if (i != j && first.contains(second)) {
					// System.out.println(first + " contains " + second
					// + " removed.");
					if (!disambiguatedDbpediaList
							.contains(resolveNGramsInDbpedia.get(i).getUri()))
						disambiguatedDbpediaList.add(new SemanticTag(
								resolveNGramsInDbpedia.get(j).getUri(),
								resolveNGramsInDbpedia.get(j).getType()));
				}
			}
		}

		// TODO Arraylist<Dbpedia> olunca removeAll çalışmadı
		for (Iterator<SemanticTag> it = resolveNGramsInDbpedia.iterator(); it
				.hasNext();) {
			SemanticTag dbpedia = it.next();
			for (SemanticTag dbp : disambiguatedDbpediaList) {
				if (dbpedia.getUri().equals(dbp.getUri())) {
					it.remove();
				}
			}
		}

		return resolveNGramsInDbpedia;
	}

	public void ToString(ArrayList<String> disambiguatedDbpediaList) {
		System.out.println(disambiguatedDbpediaList);
	}
}
