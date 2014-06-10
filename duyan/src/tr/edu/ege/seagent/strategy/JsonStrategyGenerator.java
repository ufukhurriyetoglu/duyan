package tr.edu.ege.seagent.strategy;

import tr.edu.ege.seagent.entity.Entity;
import tr.edu.ege.seagent.json.JSONGenerator;

public class JsonStrategyGenerator {

	public String generateJson(Entity entity) {
		return new JSONGenerator().createJson(entity.getContent(),
				entity.getEntityJsonList());

	}

}
