package src;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;
/**
 * This class calls the API for garage parking.
 * @author huongvu
 *
 */
public class GarageCaller extends RestCaller {
	
	private static final String link = "https://api.phila.gov/airport-parking/v1/";
	private static final String key = "garages";
	
	/**
	 * This is the constructor.
	 */
	public GarageCaller() {
		super();
	}
	
	/**
	 * This method returns the list of Garage objects
	 * @return ArrayList of Garage objects
	 * @throws JSONException
	 * @throws IOException
	 */
	public ArrayList<Garage> getGarages() throws JSONException, IOException {
		GarageJSONParser parser = new GarageJSONParser(getJSONArray(link, key));
		GarageCollection col = new GarageCollection(parser);
		return col.getGarages();
	}
	
	

}
