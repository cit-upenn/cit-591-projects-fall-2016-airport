package src;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

public class RestCallerTest {
	
	@Test
	public void testRestCallerNotNull() {
		RestCaller rc = new RestCaller();
		assertNotNull("RestCaller cannot be null", rc);
	}
}
