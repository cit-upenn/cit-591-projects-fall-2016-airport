import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;

/**
 * This class analyzes the data from TSA wait time API and Garage Parking API
 * @author huongvu
 *
 */
public class DataAnalyzer {

	private ArrayList<WaitTime> waitTimes = new ArrayList<WaitTime>();
	private ArrayList<Garage> garages = new ArrayList<Garage>();
	
	/**
	 * This is the constructor
	 * @param parser1
	 * @param parser2
	 * @throws JSONException
	 */
	public DataAnalyzer(WaitTimeParser parser1, GarageJSONParser parser2) throws JSONException {
		WaitTimeCollection col1 = new WaitTimeCollection(parser1);
		waitTimes = col1.getWaitTimes();
		GarageCollection col2 = new GarageCollection(parser2);
		garages = col2.getGarages();
	}
	
	/**
	 * This method calculates the average wait time at each checkpoint
	 * @return HashMap of checkpoints along with their average wait times
	 */
	public HashMap<String, Integer> getTimeAtCheckpoint() {
		HashMap<String, Integer> checkpoints = new HashMap<String, Integer>();
		HashMap<String, Integer> counts = new HashMap<String, Integer>();
		for (int i = 0; i < waitTimes.size(); i++) {
			String checkPoint = waitTimes.get(i).getCheckPoint();
			int wait = waitTimes.get(i).getWait();
			if (!checkpoints.containsKey(checkPoint)) {
				counts.put(checkPoint, 1);
				checkpoints.put(checkPoint, wait);
			} else {
				int count = counts.get(checkPoint);
				counts.put(checkPoint, count + 1);
				checkpoints.put(checkPoint, (checkpoints.get(checkPoint)*count + wait)/(count + 1));
			}
		}
		return checkpoints;
	}
	
}
