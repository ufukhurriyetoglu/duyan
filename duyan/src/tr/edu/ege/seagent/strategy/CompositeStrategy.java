package tr.edu.ege.seagent.strategy;

import java.util.ArrayList;
import java.util.List;

import tr.edu.ege.seagent.entity.Entity;

public class CompositeStrategy implements Strategy {
	List<Strategy> lstStrategy = new ArrayList<Strategy>();

	private MorphologicalAnalysisStrategy morphAnalyStrategy;
	private OntologyLookupStrategy ontoLookupStrategy;

	public CompositeStrategy() {
	}

	public CompositeStrategy(MorphologicalAnalysisStrategy morphAnalyStrategy,
			OntologyLookupStrategy ontoLookupStrategy) {
		this.morphAnalyStrategy = morphAnalyStrategy;
		this.ontoLookupStrategy = ontoLookupStrategy;
	}
	
	public void execute(Entity entity) {
		for (Strategy strategy : lstStrategy) {
			strategy.doOperation(entity);
 		}
	}

	public void addStrategy(Strategy st) {
		lstStrategy.add(st);
	}

	public Entity executeMorphologicalAnalysisStrategy(
			Entity content) {
		return morphAnalyStrategy.doOperation(content);
	}

	public Entity executeOntologyLookupStrategy(
			Entity candidateNerList) {
		return ontoLookupStrategy.doOperation(candidateNerList);
	}

	@Override
	public Entity doOperation(Entity entity) {
		return null;
	}

}
