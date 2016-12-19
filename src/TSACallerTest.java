package src;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

public class TSACallerTest {
	
	private TSACaller caller ;
	private final static String checkpoint = "Checkpoint B";
	
	@Before
	public void setup() {
		caller = new TSACaller();
	}
	
	@Test
	public void testRestCallerNotNull() {
		
		assertNotNull("TSACaller cannot be null", caller);
	}
	
	@Test public void testGetWaitTime() throws JSONException, IOException{
		Exception e = new Exception();
		assertNotNull(caller.getWaitTime(checkpoint));
	}

}

