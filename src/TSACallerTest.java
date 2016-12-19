package src;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONException;
import org.junit.Test;

public class TSACallerTest {
	
	TSACaller caller ;

	@Test
	public void constructorTest(){
		Exception exception = null;
		try{
			caller = new TSACaller();
		} catch(Exception ex){
			exception = ex;
			System.out.printf("Received error %s", exception.toString());
		}
		assertEquals(null, exception) ;
	}
	
	@Test
	public void testGetCheckpoints() throws JSONException, IOException{
		assertNotNull(caller.getAllCheckPoint());
	}
	
	@Test
	public void testWaitA() throws JSONException, IOException{
		
		assertNotNull(caller.getWaitTime("Checkpoint A-East"));
	}

}

