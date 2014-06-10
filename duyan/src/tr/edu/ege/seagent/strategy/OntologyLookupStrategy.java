package tr.edu.ege.seagent.strategy;

import tr.edu.ege.seagent.entity.Entity;
import tr.edu.ege.seagent.lookup.DbpediaLookup;

public class OntologyLookupStrategy implements Strategy{

	@Override
	public Entity doOperation(Entity entity) {
		return new DbpediaLookup()
		.lookupDbpediaSet(entity);
	}

}
