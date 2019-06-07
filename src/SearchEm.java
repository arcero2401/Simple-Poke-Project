import org.json.*;

public class SearchEm {

	
	//Needs to REturn JSON
	public static String route(String int3) { 
		return int3;
		
	}
	public static String type(String int2) {
		return int2;
		
	}
	public static JSONObject name(String int1) {
		
		JSONObject nameSearch = JSONUtils.getJSONObjectFromFile("/pokeInfo1.json");
		//String[] name = JSONObject.getNames(nameSearch);
		//JSONObject returnObject = nameSearch.getJSONObject(int1);
		JSONObject returnObject = new JSONObject();
		for(int i = 1; i<4; i++) {
			String iChange = Integer.toString(i);
			Object comparer = nameSearch.getJSONObject(iChange).get("Name");
				if(comparer == int1) {
					returnObject = nameSearch.getJSONObject(int1);
					return returnObject;
				}else{
					System.out.print("Failed");
				}
		}
		return returnObject;
		
	}
}
