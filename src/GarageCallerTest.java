package src;
import static org.junit.Assert.*;
import java.io.IOException;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

public class GarageCallerTest {

	private GarageCaller gc; 
	
	@Before
	public void setup() {
		gc = new GarageCaller();
	}
	@Test
	public void testRestCallerNotNull() {
		
		assertNotNull("GarageCaller cannot be null", gc);
	}
	

	@Test public void testGetGarages() throws JSONException, IOException{
		Exception e = new Exception();
		assertNotNull(gc.getGarages());
	}
}
