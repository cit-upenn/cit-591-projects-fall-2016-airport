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
		//HashMap<String, Integer> testMap = new HashMap<String, Integer>();
		Exception exception = null;
		try{
			caller.getAllCheckPoint();
		} catch(Exception e){
			exception = e;
		}
		assertNotNull(exception);
	}
	
	@Test
	public void testWaitA(){
		int a = 0;
		int testReturn = 0;
		try{
			testReturn = caller.getWaitTime("Checkpoint A-East");
			a = testReturn;
		} catch (Exception e){}
		
		assertEquals(testReturn, a);
	}

}
