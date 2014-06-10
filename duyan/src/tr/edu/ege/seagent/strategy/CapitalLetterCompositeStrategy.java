package tr.edu.ege.seagent.strategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import tr.edu.ege.seagent.dbpedia.SemanticTag;
import tr.edu.ege.seagent.entity.Entity;
import tr.edu.ege.seagent.json.JSONGenerator;
import tr.edu.ege.seagent.json.JsonEntity;
import tr.edu.ege.seagent.zemberek.NGramOperator;
import zemberek.core.logging.Log;

public class CapitalLetterCompositeStrategy implements Strategy {

	@Override
	public Entity doOperation(Entity entity) {
		MorphologicalAnalysisStrategy morphAnalyStrategy = new MorphologicalAnalysisStrategy();
		Entity candidateNerList = morphAnalyStrategy
				.doOperation(entity);
		OntologyLookupStrategy ontoLookupStrategy = new OntologyLookupStrategy();
		Entity entityLookupDbpedia = ontoLookupStrategy
				.doOperation(entity);

		ArrayList<JsonEntity> jsonEntityList= new ArrayList<JsonEntity>();
		if (!entityLookupDbpedia.getDbpediaList().isEmpty()) {
			// DBpedia da çözümlenebilen CandidateNerList elemanlarını sil
			for (SemanticTag dbpedia : entityLookupDbpedia.getDbpediaList()) {
				TreeSet<String> candidateEntityList = candidateNerList.getEntityList();
				Log.info(dbpedia.getName());
				candidateEntityList.remove(new String(dbpedia.getName()));
			}
			try {
				obtainTempResolvedEntityList(entityLookupDbpedia);
				jsonEntityList = new JSONGenerator().acquireEntitiesSet(
						entity, entityLookupDbpedia);
			} catch (IOException | SAXException | TransformerException
					| ParserConfigurationException e) {
				e.printStackTrace();
			}
		} else {

//			Entity resolveNamedEntityLookupDbpedia = new Entity(entity.getContent());
			try {
				obtainTempResolvedEntityList(candidateNerList);
				jsonEntityList = new JSONGenerator().acquireEntitiesSet(
						entity, candidateNerList);
			} catch (IOException | SAXException | TransformerException
					| ParserConfigurationException e) {
				e.printStackTrace();
			}
		}
		entity.setEntityJsonList(jsonEntityList);
		return entity;
	}

	public void execute(Entity contentEntity) {
		doOperation(contentEntity);
	}

	public void obtainTempResolvedEntityList(Entity candidateNerList)
			throws IOException, SAXException, TransformerException,
			ParserConfigurationException {
		OntologyLookupStrategy ontoLookupStrategy = new OntologyLookupStrategy();
		Iterator<String> iterator = candidateNerList.getEntityList().iterator();
		while (iterator.hasNext()) {
			String word = iterator.next().toString().trim();
			String[] splitedWords = word.split(" ");
			TreeSet<String> createNGramArray = new NGramOperator().createNGramArray(
					splitedWords, splitedWords.length);
			
			Entity cloned = new Entity("");
			
			cloned.setEntityList(createNGramArray);
			Entity tempResolveNamedEntityLookup = ontoLookupStrategy
					.doOperation(cloned);
			if(!cloned.getDbpediaList().isEmpty())
				candidateNerList.getDbpediaList()
					.addAll(tempResolveNamedEntityLookup.getDbpediaList());
		}
	}

}
