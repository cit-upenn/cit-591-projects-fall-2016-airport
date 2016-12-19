package src;
import java.io.IOException;
import java.util.HashMap;

import org.json.JSONException;

public class TSACaller extends RestCaller {
	
	private static final String link = "http://apps.tsa.dhs.gov/MyTSAWebService/GetTSOWaitTimes.ashx?ap=PHL&output=json";
	private static final String key = "WaitTimes";
	
	public TSACaller() {
		super();
	}
	
	public HashMap<String, Integer> getAllCheckPoint() throws JSONException, IOException {
		WaitTimeParser parser = new WaitTimeParser(getJSONArray(link, key));
		TSAAnalyzer ana = new TSAAnalyzer(parser);
		return ana.getTimeAtCheckpoint();
	}

	public int getWaitTime(String checkpoint) throws JSONException, IOException {
		return getAllCheckPoint().get(checkpoint);
	}
	
}
