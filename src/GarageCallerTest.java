package src;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

public class GarageCallerTest {

	GarageCaller caller;
	
	@Test
	public void testRestCallerNotNull() {
		GarageCaller gc = new GarageCaller();
		assertNotNull("GarageCaller cannot be null", gc);
	}
	
	
	@Test
	public void testGetGarages(){
		ArrayList<Garage> testList = null;
		try{
			testList = caller.getGarages();
		} catch(Exception e){
			System.out.println("Test fails");
		}
		assertNotNull(testList);
	}
}
