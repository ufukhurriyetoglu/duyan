package tr.edu.ege.seagent.strategy;

import java.util.TreeSet;

import tr.edu.ege.seagent.dbpedia.SemanticTag;

public interface Context {
	// public Strategy strategy = null;

	public TreeSet<String> executeMorphologicalAnalysisStrategy(String content);

	public TreeSet<SemanticTag> executeOntologyLookupStrategy(
			TreeSet<String> candidateNerList);

}
