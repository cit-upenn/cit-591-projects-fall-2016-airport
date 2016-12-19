package src;
import java.io.IOException;
import java.util.HashMap;

import org.json.JSONException;
/**
 * This class calls the API for TSA wait time.
 * @author huongvu
 *
 */
public class TSACaller extends RestCaller {
	
	private static final String link = "http://apps.tsa.dhs.gov/MyTSAWebService/GetTSOWaitTimes.ashx?ap=PHL&output=json";
	private static final String key = "WaitTimes";
	
	/**
	 * This is the constructor.
	 */
	public TSACaller() {
		super();
	}
	
	/**
	 * This method returns the HashMap of all checkpoints with wait times.
	 * @return HashMap of all checkpoints with wait times.
	 * @throws JSONException
	 * @throws IOException
	 */
	public HashMap<String, Integer> getAllCheckPoint() throws JSONException, IOException {
		WaitTimeParser parser = new WaitTimeParser(getJSONArray(link, key));
		TSAAnalyzer ana = new TSAAnalyzer(parser);
		return ana.getTimeAtCheckpoint();
	}

	/**
	 * This class returns the wait time for given checkpoint.
	 * @param checkpoint you want to check wait time for
	 * @return the wait time 
	 * @throws JSONException
	 * @throws IOException
	 */
	public int getWaitTime(String checkpoint) throws JSONException, IOException {
		return getAllCheckPoint().get(checkpoint);
	}
	
}
