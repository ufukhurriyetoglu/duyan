package tr.edu.ege.seagent.json;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import tr.edu.ege.seagent.dbpedia.SemanticTag;
import tr.edu.ege.seagent.entity.Entity;
import tr.edu.ege.seagent.servlet.HtmlContentProvider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONGenerator {

	// {
	// "text":"Yapılan düğünde Arda Turan ve Hakan Şükür hazır bulundu.",
	// "entities": [
	// {
	// "name":"entity2",
	// "uri":"http://dbpedia.org/resource/Hakan_%C5%9E%C3%BCk%C3%BCr",
	// "id":"T2",
	// "type":"Person",
	// "color":"#9CC2E6",
	// "start":30,
	// "end":41
	// },
	// {"name":"entity1",
	// "uri":"http://dbpedia.org/resource/Arda_Turan",
	// "id":"T1",
	// "type":"Person",
	// "color":"#9CC2E6",
	// "start":16,
	// "end":26
	// }
	// ]
	// }

	@SuppressWarnings("unchecked")
	public String createJsonRegex(String sentence,
			ArrayList<JsonEntity> entitiesList) throws URISyntaxException {

//		JSONObject obj = new JSONObject();
//		obj.put("text", sentence);
		JsonEntityHeader jsonHeader = new JsonEntityHeader();
		  jsonHeader.setText(sentence);
		 

		String entityStr = "entity";

		// creates child nodes
		int i = 1;
		
		ArrayList<JsonEntity> entities = new ArrayList<JsonEntity>();
//		JSONArray list = new JSONArray();
		for (JsonEntity ent : entitiesList) {
//			JSONObject entity = new JSONObject();
			  JsonEntity entity = new JsonEntity();
			entity.setName(entityStr + i);
			String uriStr = ent.getDbpediaUri().substring(1, ent.getDbpediaUri().length()-1);
//			String str = ent.getDbpediaUri().toString();
			entity.setDbpediaUri(uriStr);
			entity.setT(ent.getT());
			entity.setType(ent.getType());
			String color = new HtmlContentProvider().identifyBackgroundColor(ent.getType());
			entity.setColor(color);
			entity.setStart(ent.getStart());
			entity.setEnd(ent.getEnd());
			entities.add(entity);
//			list.add(entity);
			i++;
		}
//		obj.put("entities", list);
		
		  jsonHeader.setEntities(entities);
		  
		  Gson gson = new GsonBuilder().setPrettyPrinting().create();
		  //you don't need the GsonBuilder if you are just sending the 
		  //data to another system or AJAX response, in that case just use
		  //Gson gson = new Gson();
		   
		  // convert java object to JSON format, so easy
		  String jsonString = gson.toJson(jsonHeader);
		

//		String jsonString = obj.toJSONString();
//		System.out.println(((JSONObject)((JSONArray)obj.get("entities")).get(0)).get("uri"));
		System.out.println(jsonString);

		return jsonString;
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

		// for (Dbpedia dbpedia : disambiguatedDbpediaList) {
		// String namedEntity = dbpedia.getName();
		//
		// // find all occurrences forward
		// for (int beginOffset = -1; (beginOffset = content.indexOf(
		// namedEntity, beginOffset + 1)) != -1;) {
		// int endOffset = namedEntity.length() + beginOffset;
		// // System.out.println("basi : " + beginOffset + " sonu : "
		// // + endOffset);
		//
		// // TODO uri
		// entities.add(new Entities(namedEntity, disambiguatedDbpediaList
		// .get(cnt - 1).getUri(), "T" + cnt,
		// disambiguatedDbpediaList.get(cnt - 1).getType(),
		// beginOffset, endOffset));
		// cnt++;
		// }
		// }

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
				// System.out.println("basi : " + beginOffset + " sonu : "
				// + endOffset);

				// TODO uri
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

			String namedEntity = semanticTag.getName().replaceAll("\"", "");

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

}
