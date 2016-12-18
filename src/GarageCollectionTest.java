package src;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

public class GarageCollectionTest {
	
	private JSONArray array;
	private final static String garagesFeed = "{ \"garages\": [{ \"lat\": \"39.8775100347\", \"lng\": \"-75.2472191649\", \"total_spaces\": \"3489\", \"type\": \"garage\", \"display_name\": \"A\", \"ada_spaces\": \"35\", \"spaces_available\": \"3106.000000\" }, { \"lat\": \"39.877964439\", \"lng\": \"-75.2456045136\", \"type\": \"garage\", \"display_name\": \"B\",  \"total_spaces\": \"870\", \"ada_spaces\": \"20\", \"spaces_available\": \"290.000000\"},  { \"lat\": \"39.878479289\", \"lng\": \"-75.2437663668\", \"type\": \"garage\", \"display_name\": \"C\",  \"total_spaces\": \"1771\", \"ada_spaces\": \"26\", \"spaces_available\": \"878.000000\" }, { \"lat\": \"39.8788499486\", \"lng\": \"-75.243037793\", \"type\": \"garage\",  \"total_spaces\": \"1523\", \"display_name\": \"D\", \"ada_spaces\": \"32\", \"spaces_available\": \"976.000000\"},  { \"lat\": \"39.8792214437\", \"lng\": \"-75.2426930308\", \"type\": \"garage\",  \"total_spaces\": \"3331\", \"display_name\": \"EF\", \"ada_spaces\": \"47\", \"spaces_available\": \"2446.000000\"},  { \"lat\": \"39.8792214437\", \"lng\": \"-75.2426930308\", \"type\": \"garage\",  \"total_spaces\": \"7117\", \"display_name\": \"Economy\", \"ada_spaces\": \"47\", \"spaces_available\": \"4242\"} ] }";
	private GarageJSONParser parser;
	private ArrayList<Garage> garages = new ArrayList<Garage>();
	
	@Before
	public void setup() throws JSONException {
		JSONObject obj = new JSONObject(garagesFeed);
		array = obj.getJSONArray("garages");
		parser = new GarageJSONParser(array);
	}
	
	@Test
	public void testGetGarages() throws JSONException {
		GarageCollection col = new GarageCollection(parser);
		assertNotNull("Garages should not be null", col.getGarages());
	}

}
