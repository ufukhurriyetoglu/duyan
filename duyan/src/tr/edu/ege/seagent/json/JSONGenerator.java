package tr.edu.ege.seagent.json;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import tr.edu.ege.seagent.dbpedia.SemanticTag;
import tr.edu.ege.seagent.entity.Entity;
import tr.edu.ege.seagent.letter.LetterOperator;
import tr.edu.ege.seagent.servlet.HtmlContentProvider;

import com.google.gson.GsonBuilder;

public class JSONGenerator {

	public String createJsonRegex(String sentence,
			HashSet<JsonEntity> luceneLookupPipeline) throws URISyntaxException {

		JsonEntityHeader jsonHeader = new JsonEntityHeader();
		jsonHeader.setText(sentence);

		String entityStr = "entity";

		// creates child nodes
		int i = 1;

		ArrayList<JsonEntity> entities = new ArrayList<JsonEntity>();
		for (JsonEntity ent : luceneLookupPipeline) {
			JsonEntity entity = new JsonEntity();
			entity.setName(entityStr + i);
			if (!ent.getDbpediaUri().isEmpty()) {
				String uriStr = ent.getDbpediaUri().substring(1,
						ent.getDbpediaUri().length() - 1);
				entity.setDbpediaUri(uriStr);
			}
			entity.setT(ent.getT());
			entity.setType(ent.getType());
//			String color = new HtmlContentProvider()
//					.identifyBackgroundColor(ent.getType());
			String color = new HtmlContentProvider()
			.identifyBackgroundColor(ent.getType());
			
			entity.setColor(color);
			entity.setStart(ent.getStart());
			entity.setEnd(ent.getEnd());
			entities.add(entity);
			i++;
		}
		jsonHeader.setEntities(entities);

		return new GsonBuilder().setPrettyPrinting().create()
				.toJson(jsonHeader);
	}

	@SuppressWarnings("unchecked")
	public String createJson(String sentence, ArrayList<JsonEntity> entities) {

		JSONObject obj = new JSONObject();
		obj.put("text", sentence);

		String entityStr = "entity";
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		Map<String, List<Integer>> mapEntityNumbers = new HashMap<String, List<Integer>>();

		// creates child nodes
		int i = 1;
		for (JsonEntity entity : entities) {
			JSONArray list = new JSONArray();
			ArrayList<Integer> tmpNumbers = new ArrayList<Integer>();
			list.add(entity.getT());
			list.add(entity.getType());
			list.add(entity.getStart());
			list.add(entity.getEnd());
			tmpNumbers.add(entity.getStart());
			tmpNumbers.add(entity.getEnd());
			mapEntityNumbers.put(entityStr + i, tmpNumbers);
			map.put(entityStr + i, list);
			i++;
		}

		JSONObject objTmp = new JSONObject();
		objTmp.putAll(map);
		obj.put("entities", objTmp);

		String jsonString = obj.toJSONString();
		for (int j = 1; j <= mapEntityNumbers.size(); j++) {
			Integer start = mapEntityNumbers.get(entityStr + j).get(0);
			Integer end = mapEntityNumbers.get(entityStr + j).get(1);
			jsonString = jsonString.replace("" + start + "," + end + "",
					" [ [ " + start + "," + end + " ] ]");
		}

		return jsonString;
	}

	public ArrayList<JsonEntity> acquireEntitiesSet(Entity contentEntity,
			Entity disambiguatedDbpediaList) {
		ArrayList<JsonEntity> entities = new ArrayList<JsonEntity>();

		int cnt = 1;

		Iterator<SemanticTag> iterator = disambiguatedDbpediaList
				.getDbpediaList().iterator();
		while (iterator.hasNext()) {
			SemanticTag dbpedia = iterator.next();
			String namedEntity = dbpedia.getName();
			// find all occurrences forward
			String content = contentEntity.getContent();
			for (int beginOffset = -1; (beginOffset = content.indexOf(
					namedEntity, beginOffset + 1)) != -1;) {
				int endOffset = namedEntity.length() + beginOffset;
				// System.out.println("basi : " + beginOffset + " sonu : "
				// + endOffset);

				// TODO uri
				entities.add(new JsonEntity(namedEntity, dbpedia.getUri(), "T"
						+ cnt, dbpedia.getType(), beginOffset, endOffset));
				cnt++;
			}
		}

		return entities;
	}

	public ArrayList<JsonEntity> acquireEntities(String content,
			ArrayList<SemanticTag> disambiguatedDbpediaList) {
		ArrayList<JsonEntity> entities = new ArrayList<JsonEntity>();

		int cnt = 1;
		for (SemanticTag dbpedia : disambiguatedDbpediaList) {
			String namedEntity = dbpedia.getName().replaceAll("\"", "");

			// find all occurrences forward
			for (int beginOffset = -1; (beginOffset = content.indexOf(
					namedEntity, beginOffset + 1)) != -1;) {
				int endOffset = namedEntity.length() + beginOffset;
				entities.add(new JsonEntity(namedEntity,
						disambiguatedDbpediaList.get(cnt - 1).getUri(), "T"
								+ cnt, disambiguatedDbpediaList.get(cnt - 1)
								.getType(), beginOffset, endOffset));
			}
			cnt++;
		}

		return entities;
	}

	public ArrayList<JsonEntity> acquireEntitiesRegex(String content,
			TreeSet<SemanticTag> resolveNamedEntityLookupDbpedia) {
		ArrayList<JsonEntity> entities = new ArrayList<JsonEntity>();

		int cnt = 1;
		Iterator<SemanticTag> iterator = resolveNamedEntityLookupDbpedia
				.iterator();
		while (iterator.hasNext()) {
			SemanticTag semanticTag = (SemanticTag) iterator.next();

			// getName i getOldName yaptım
			String namedEntityBef = semanticTag.getOldName().replaceAll("\"",
					"");
			String namedEntity = "";
			if (content.contains(namedEntityBef)) {
				namedEntity = namedEntityBef;
			} else {
				namedEntity = new LetterOperator()
						.convertAllUpperCase(namedEntityBef);
				content = content.replace(namedEntity, namedEntityBef);
				namedEntity = namedEntityBef;
			}

			// find all occurrences forward
			for (int beginOffset = -1; (beginOffset = content.indexOf(
					namedEntity, beginOffset + 1)) != -1;) {
				int endOffset = namedEntity.length() + beginOffset;
				// System.out.println("basi : " + beginOffset + " sonu : "
				// + endOffset);

				// TODO uri
				entities.add(new JsonEntity(namedEntity, semanticTag.getUri(),
						"T" + cnt, semanticTag.getType(), beginOffset,
						endOffset));
			}
			cnt++;
		}

		return entities;
	}
	
	
	public ArrayList<JsonEntity> acquireEntitiesLucene(String content,
			ArrayList<JsonEntity> luceneLookupPipeline) {
		ArrayList<JsonEntity> entities = new ArrayList<JsonEntity>();

		int cnt = 1;
		
		for (JsonEntity semanticTag : entities) {
			
			// getName i getOldName yaptım
			String namedEntityBef = semanticTag.getName().replaceAll("\"",
					"");
			String namedEntity = "";
			if (content.contains(namedEntityBef)) {
				namedEntity = namedEntityBef;
			} else {
				namedEntity = new LetterOperator()
						.convertAllUpperCase(namedEntityBef);
				content = content.replace(namedEntity, namedEntityBef);
				namedEntity = namedEntityBef;
			}

			// find all occurrences forward
			for (int beginOffset = -1; (beginOffset = content.indexOf(
					namedEntity, beginOffset + 1)) != -1;) {
				int endOffset = namedEntity.length() + beginOffset;
				// System.out.println("basi : " + beginOffset + " sonu : "
				// + endOffset);

				// TODO uri
				entities.add(new JsonEntity(namedEntity, semanticTag.getDbpediaUri(),
						"T" + cnt, semanticTag.getType(), beginOffset,
						endOffset));
			}
			cnt++;
		}

		return entities;
	}

}
