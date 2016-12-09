package src;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

public class GarageCaller extends RestCaller {
	
	private static final String link = "https://api.phila.gov/airport-parking/v1/";
	private static final String key = "garages";
	
	public GarageCaller() {
		super();
	}
	
	public ArrayList<Garage> getGarages() throws JSONException, IOException {
		GarageJSONParser parser = new GarageJSONParser(getJSONArray(link, key));
		GarageCollection col = new GarageCollection(parser);
		return col.getGarages();
	}
	
	

}
