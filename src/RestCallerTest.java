package src;

import static org.junit.Assert.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

public class RestCallerTest {
	
	RestCaller rc;
	
	
	@Before
	public void testRestCallerNotNull() {
		rc = new RestCaller();
		assertNotNull("RestCaller cannot be null", rc);
	}
	
	@Test
	public void testReadAll(){
		
	}
	
	@Test
	public void testReadJson(){
		JSONObject j = new JSONObject();
		try{
			j = rc.readJsonFromUrl("http://apps.tsa.dhs.gov/MyTSAWebService/GetTSOWaitTimes.ashx?ap=PHL&output=json");
		} catch(Exception e){
			System.out.println("This test failed.");
		}
		assertNotNull(j);
	}
	
	@Test
	public void testGetArray(){
		JSONArray jArray = new JSONArray();
		try{
			jArray = rc.getJSONArray("http://apps.tsa.dhs.gov/MyTSAWebService/GetTSOWaitTimes.ashx?ap=PHL&output=json", "WaitTimes");
		} catch(Exception e){
			System.out.println("This test failed.");
		}
		
		assertNotNull(jArray);
	}

	

}
