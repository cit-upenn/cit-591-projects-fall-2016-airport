package src;
import java.util.ArrayList;

import org.json.JSONException;

/**
 * This class represents the most recent 25 wait times at Philadelphia airport.
 * @author huongvu
 *
 */
public class WaitTimeCollection {
	
	private ArrayList<WaitTime> waitTimes = new ArrayList<WaitTime>();
	
	/**
	 * This is the constructor
	 * @param parser
	 * @throws JSONException
	 */
	public WaitTimeCollection(WaitTimeParser parser) throws JSONException {
		for (int i = 0; i < parser.getWaitTimeList().size(); i++) {
			WaitTime waitTime = parser.waitParser(parser.getWaitTimeList().get(i));
			waitTimes.add(waitTime);
		}
	}

	/**
	 * @return the waitTimes
	 */
	public ArrayList<WaitTime> getWaitTimes() {
		return waitTimes;
	}
	
	

}
