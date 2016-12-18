package src;

import static org.junit.Assert.*;

import java.util.HashMap;

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
	public void testGetCheckpoints(){
		HashMap<String, Integer> testMap = null;
		try{
			testMap = caller.getAllCheckPoint();
		} catch(Exception e){
			System.out.println("Test fails");
		}
		assertNotNull(testMap);
	}
	
	//@Test
	/*public void testWaitA(){
		int a;
		try{
			a = caller.getWaitTime("Checkpoint A-East");
		} catch (Exception e){}
		
		assertEquals(a);
	}*/

}
