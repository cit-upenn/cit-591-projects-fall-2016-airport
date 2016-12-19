package src;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

public class TSAAnalyzerTest {

	private JSONArray array;
	private final static String waitFeed = "{\"WaitTimes\" : [{\"CheckpointIndex\" : \"5\",\"WaitTime\" : \"3\",\"Created_Datetime\" : \"12/18/2016 9:12:49 AM\"},{\"CheckpointIndex\" : \"3\",\"WaitTime\" : \"1\",\"Created_Datetime\" : \"12/16/2016 3:46:51 PM\"},{\"CheckpointIndex\" : \"2\",\"WaitTime\" : \"1\",\"Created_Datetime\" : \"12/15/2016 1:41:33 AM\"},{\"CheckpointIndex\" : \"0\",\"WaitTime\" : \"1\",\"Created_Datetime\" : \"12/14/2016 12:37:43 PM\"},{\"CheckpointIndex\" : \"2\",\"WaitTime\" : \"1\",\"Created_Datetime\" : \"12/12/2016 6:43:15 PM\"},{\"CheckpointIndex\" : \"2\",\"WaitTime\" : \"3\",\"Created_Datetime\" : \"12/12/2016 5:23:08 AM\"},{\"CheckpointIndex\" : \"0\",\"WaitTime\" : \"1\",\"Created_Datetime\" : \"12/10/2016 10:38:21 PM\"},{\"CheckpointIndex\" : \"5\",\"WaitTime\" : \"1\",\"Created_Datetime\" : \"12/10/2016 8:08:55 AM\"},{\"CheckpointIndex\" : \"3\",\"WaitTime\" : \"3\",\"Created_Datetime\" : \"12/9/2016 9:36:59 AM\"},{\"CheckpointIndex\" : \"3\",\"WaitTime\" : \"2\",\"Created_Datetime\" : \"12/9/2016 4:30:46 AM\"},{\"CheckpointIndex\" : \"1\",\"WaitTime\" : \"1\",\"Created_Datetime\" : \"12/8/2016 8:49:30 PM\"},{\"CheckpointIndex\" : \"1\",\"WaitTime\" : \"2\",\"Created_Datetime\" : \"12/8/2016 9:23:29 AM\"},{\"CheckpointIndex\" : \"3\",\"WaitTime\" : \"1\",\"Created_Datetime\" : \"12/8/2016 8:38:25 AM\"},{\"CheckpointIndex\" : \"1\",\"WaitTime\" : \"3\",\"Created_Datetime\" : \"12/7/2016 1:03:36 PM\"},{\"CheckpointIndex\" : \"0\",\"WaitTime\" : \"1\",\"Created_Datetime\" : \"12/6/2016 7:53:32 PM\"},{\"CheckpointIndex\" : \"4\",\"WaitTime\" : \"1\",\"Created_Datetime\" : \"12/6/2016 6:01:50 AM\"},{\"CheckpointIndex\" : \"0\",\"WaitTime\" : \"1\",\"Created_Datetime\" : \"12/6/2016 12:37:33 AM\"},{\"CheckpointIndex\" : \"5\",\"WaitTime\" : \"1\",\"Created_Datetime\" : \"12/5/2016 7:38:02 PM\"},{\"CheckpointIndex\" : \"7\",\"WaitTime\" : \"4\",\"Created_Datetime\" : \"12/4/2016 12:27:08 PM\"},{\"CheckpointIndex\" : \"1\",\"WaitTime\" : \"1\",\"Created_Datetime\" : \"12/2/2016 8:53:16 PM\"},{\"CheckpointIndex\" : \"1\",\"WaitTime\" : \"3\",\"Created_Datetime\" : \"12/2/2016 11:00:18 AM\"},{\"CheckpointIndex\" : \"0\",\"WaitTime\" : \"1\",\"Created_Datetime\" : \"12/1/2016 10:09:44 AM\"},{\"CheckpointIndex\" : \"4\",\"WaitTime\" : \"3\",\"Created_Datetime\" : \"11/30/2016 2:20:03 PM\"},{\"CheckpointIndex\" : \"3\",\"WaitTime\" : \"1\",\"Created_Datetime\" : \"11/30/2016 9:05:54 AM\"},{\"CheckpointIndex\" : \"4\",\"WaitTime\" : \"3\",\"Created_Datetime\" : \"11/30/2016 4:18:01 AM\"}]}";
	private WaitTimeParser parser;
	private ArrayList<WaitTime> waitTimes = new ArrayList<WaitTime>();
	private TSAAnalyzer ana;

	
	@Before
	public void setup() throws JSONException {
		JSONObject obj = new JSONObject(waitFeed);
		array = obj.getJSONArray("WaitTimes");
		parser = new WaitTimeParser(array);
		ana = new TSAAnalyzer(parser);
	}
	
	@Test
	public void testGetTimeAtCheckpoint() {
		HashMap<String, Integer> checkpoints = ana.getTimeAtCheckpoint();
		
		assertNotNull("Checkpoints should not be null", checkpoints);
	}

}
