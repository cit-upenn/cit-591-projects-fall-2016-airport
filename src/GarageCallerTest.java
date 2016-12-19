package src;

import static org.junit.Assert.*;

import java.io.IOException;

import org.json.JSONException;
import org.junit.Test;

public class GarageCallerTest {

	GarageCaller gc = new GarageCaller();
	
	@Test
	public void testRestCallerNotNull() {
		
		assertNotNull("GarageCaller cannot be null", gc);
	}
	
	@Test public void testGetGarages() throws JSONException, IOException{
		Exception e = new Exception();
		assertNotNull(gc.getGarages());
	}
}
