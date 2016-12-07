import java.util.ArrayList;

import org.json.JSONException;

/**
 * This class represents collection of all garages in Philadelphia airport.
 * @author huongvu
 *
 */
public class GarageCollection {
	
	private ArrayList<Garage> garages = new ArrayList<Garage>();
	
	/**
	 * This is the constructor
	 * @param parser
	 * @throws JSONException
	 */
	public GarageCollection(GarageJSONParser parser) throws JSONException {
		for (int i = 0; i < parser.getGarageList().size(); i++) {
			Garage garage = parser.garageParser(parser.getGarageList().get(i));
			garages.add(garage);
		}
	}

	/**
	 * @return the garages
	 */
	public ArrayList<Garage> getGarages() {
		return garages;
	}
	
	

}
