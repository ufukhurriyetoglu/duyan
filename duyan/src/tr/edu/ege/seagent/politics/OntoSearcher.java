package tr.edu.ege.seagent.politics;

import java.util.Iterator;
import java.util.TreeSet;

import tr.edu.ege.seagent.dbpedia.SemanticTag;

public class OntoSearcher {
	
	public static void main(String[] args) {
		OntoGenerator onto = new OntoGenerator();
		// dbpediaFile.searchInDbpedia();

		TreeSet<String> nerList = new TreeSet<String>();
		nerList.add("Yalova");
		nerList.add("Yalova");
//		TreeSet<SemanticTag> resolveInOnto = onto.searchInOnto(nerList);

//		Iterator<SemanticTag> iterator = resolveInOnto.iterator();
//		while (iterator.hasNext()) {
//			SemanticTag semanticTag = (SemanticTag) iterator.next();
//			System.out.println(semanticTag.getUri() + " -- " + semanticTag.getType());
//		}

	}

}
