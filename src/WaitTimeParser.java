package src;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class processes JSONObject to create WaitTime object.
 * @author huongvu
 *
 */
public class WaitTimeParser {

	private ArrayList<JSONObject> waitTimeList = new ArrayList<JSONObject>();
	
	/**
	 * This is the constructor
	 * @param waitTimes
	 * @throws JSONException
	 */
	public WaitTimeParser(JSONArray waitTimes) throws JSONException {
		for (int i = 0; i < waitTimes.length(); i++) {
			JSONObject waitTimeObj = waitTimes.getJSONObject(i);
			waitTimeList.add(waitTimeObj);
		}
	}
	
	/**
	 * This method processes each wait time JSON object to create a WaitTime object
	 * @param obj is the JSON object for wait time
	 * @return WaitTime object
	 * @throws JSONException
	 */
	public WaitTime waitParser(JSONObject obj) throws JSONException {
		int checkPointIndex = Integer.parseInt(obj.getString("CheckpointIndex"));
		String checkPoint;
		switch(checkPointIndex) {
			case 1: checkPoint = "Checkpoint D/E";
					break;
			case 2: checkPoint = "Checkpoint A-East";
					break;
			case 3: checkPoint = "Checkpoint B";
					break;
			case 4: checkPoint = "Checkpoint C";
					break;
			case 5: checkPoint = "Checkpoint A-West Sec 5";
					break;
			case 6: checkPoint = "Checkpoint A-West Sec 7";
					break;
			case 7: checkPoint = "Checkpoint F";
					break;
			default: checkPoint = "N/A";
					break;
		}
		String createdTime = obj.getString("Created_Datetime");
		int waitTimeType = Integer.parseInt(obj.getString("WaitTime"));
		int wait = 0;
		switch(waitTimeType) {
			case 1: wait = 0;
					break;
			case 2: wait = 5;
					break;
			case 3: wait = 15;
					break;
			case 4: wait = 25;
					break;
			case 5: wait = 38;
					break;
			case 6: wait = 53;
					break;
			case 7: wait = 68;
					break;
			case 8: wait = 83;
					break;
			case 9: wait = 98;
					break;
			default: wait = 100;
					break;
		}
		WaitTime waitTime = new WaitTime(checkPoint, createdTime, wait);
		return waitTime;
	}

	/**
	 * @return the waitTimeList
	 */
	public ArrayList<JSONObject> getWaitTimeList() {
		return waitTimeList;
	}
	
	
	
}
