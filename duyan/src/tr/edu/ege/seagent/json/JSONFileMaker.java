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

//		try {

			// FileWriter file = new FileWriter(
			// "/home/etmen/Downloads/CalculatorJSF/WebContent/json/"+
			// outputFile);
			// file.write(jsonString);
			// file.flush();
			// file.close();

		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		//
		// System.out.println(outputFile + " is created.");
		return jsonString;
	}

	// public static void main(String[] args) {
	// ArrayList<Entities> entities = new ArrayList<Entities>();
	// entities.add(new Entities("T1", "Person", 18, 32));
	// entities.add(new Entities("T2", "Organization", 34, 57));
	// entities.add(new Entities("T3", "Person", 60, 70));
	// entities.add(new Entities("T4", "Location", 215, 222));
	// new JSONFileMaker().createJsonFile("test1.json",
	// "MHP Genel Ba��kan�� Devlet Bah��eli, Ba��bakan Tayyip Erdo��an�����n �����imdi MHP���nin genel ba��kan�� ����km����, CHP���nin genel ba��kan��n�� savunuyor��� s��zlerine ���S��n��f arkada����m�� ni��in savunmay��m, sahip ����kmayay��m��� yan��t��n�� verdini. Fethiye���deki olaylar��n da bir provakasyon oldu��unu belirten Bah��eli, ���Hi��bir arkada����m��z��n ili��kisi yoktur��� tepkisini g��sterdi.",
	// entities);
	// new JSONFileMaker().createJsonFile("test2.json",
	// "Ba��bakan Tayyip Erdo��an�����n �����imdi MHP���nin genel ba��kan�� ����km����, CHP���nin genel ba��kan��n�� savunuyor��� s��zlerine ���S��n��f arkada����m�� ni��in savunmay��m, sahip ����kmayay��m��� yan��t��n�� verdini. Fethiye���deki olaylar��n da bir provakasyon oldu��unu belirten Bah��eli, ���Hi��bir arkada����m��z��n ili��kisi yoktur��� tepkisini g��sterdi.",
	// entities);
	//
	// }

}
