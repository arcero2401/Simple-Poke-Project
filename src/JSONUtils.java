import java.io.*;
import java.util.Scanner;
import org.json.*;

public class JSONUtils {

	public static String getJSONStringFromFile(String path) {
		Scanner scanner;
		InputStream in = inputStreamFromFile(path);
		scanner = new Scanner(in);
		String json = scanner.useDelimiter("\\Z").next();
		scanner.close();
		return json;
	}
	
	public static JSONObject getJSONObjectFromFile(String path) {
		return new JSONObject(getJSONStringFromFile(path));
	}
	
	public static boolean objectExists(JSONObject jsonObject, String key) {
		Object o;
		try {
			o = jsonObject.get(key);
		} catch(Exception e) {
			return false;
		}
		return o != null;
	}
	public static InputStream inputStreamFromFile(String path) {
		try {
			InputStream inputStream = JSONUtils.class.getResourceAsStream(path);
			return inputStream;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
