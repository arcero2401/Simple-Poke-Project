import org.json.JSONObject;
public class Main {

	public static void main(String[] args) {
		
		WindowFrameWork.createSearchEngine();
		JSONObject Poke = JSONUtils.getJSONObjectFromFile("/pokeInfo1.json");
		String[] name = JSONObject.getNames(Poke);
		for(String string : name) {
			System.out.println( string + ": " + Poke.get(string));
		}
		
		System.out.print("\n");
		System.out.println("Check This Shit Out!");
		
		
	}

}
