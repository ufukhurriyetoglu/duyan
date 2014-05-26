package tr.edu.ege.seagent.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONFileMaker {

	@SuppressWarnings("unchecked")
	public String createJson(String sentence, ArrayList<Entities> entities) {

		JSONObject obj = new JSONObject();
		obj.put("text", sentence);

		String entityStr = "entity";
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		Map<String, List<Integer>> mapEntityNumbers = new HashMap<String, List<Integer>>();

		// creates child nodes
		int i = 1;
		for (Entities entity : entities) {
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

}
