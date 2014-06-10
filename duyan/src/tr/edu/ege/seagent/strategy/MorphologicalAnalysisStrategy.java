package tr.edu.ege.seagent.strategy;

import tr.edu.ege.seagent.entity.Entity;
import tr.edu.ege.seagent.main.TextAnalyser;

public class MorphologicalAnalysisStrategy implements Strategy {

	@Override
	public Entity doOperation(Entity entity) {
		return new TextAnalyser().extractNamedEntitySet(entity);
	}


}
