import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class processes JSONObject to create Garage object.
 * @author huongvu
 *
 */
public class GarageJSONParser {

	private ArrayList<JSONObject> garageList = new ArrayList<JSONObject>();
	
	/**
	 * This is the constructor
	 * @param garages
	 * @throws JSONException
	 */
	public GarageJSONParser(JSONArray garages) throws JSONException {
		for (int i = 0; i < garages.length(); i++) {
			JSONObject garageObj = garages.getJSONObject(i);
			garageList.add(garageObj);
		}
	}
	
	/**
	 * This method processes each garage JSONObject to create a Garage object
	 * @param obj is the JSON object for garage
	 * @return Garage object
	 * @throws JSONException
	 */
	public Garage garageParser(JSONObject obj) throws JSONException {
		String name = obj.getString("display_name");
    	double latitude = Double.parseDouble(obj.getString("lat"));
    	double longitude = Double.parseDouble(obj.getString("lng"));
    	int adaSpaces = Integer.parseInt(obj.getString("ada_spaces"));
    	int availSpaces = (int)Double.parseDouble(obj.getString("spaces_available"));
    	int totalSpaces = Integer.parseInt(obj.getString("total_spaces"));
		Garage garage = new Garage(name, latitude, longitude, adaSpaces, availSpaces, totalSpaces);
		return garage;
	}

	/**
	 * @return the garageList
	 */
	public ArrayList<JSONObject> getGarageList() {
		return garageList;
	}
	
	
	
}
