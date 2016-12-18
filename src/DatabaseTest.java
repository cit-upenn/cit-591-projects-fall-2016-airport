package src;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class DatabaseTest {
	
	private Database db;
	private Database db2;
	private ArrayList<Integer> flightDelays = new ArrayList<Integer>();
	private ArrayList<Integer> customsDelays = new ArrayList<Integer>();
	
	@Before
	public void setup() { 
		db = new Database(1, 2, 1010, 23);
		db2 = new Database(1, 2, 950, 23);
	}

	
	@Test
	public void testMonthConversion() {
		db.convertMonth();
		String monthValue = db.getMonth();
		assertEquals("FEB", monthValue);
	}
	
	
	@Test
	public void testLowerTimeBound() {
		db.convertTime();
		int lowerTimeValue = db.getLowerTime();
		assertEquals(940, lowerTimeValue);
		
		db2.convertTime();
		int lowerTimeValue2 = db2.getLowerTime();
		assertEquals(920, lowerTimeValue2);
	}
	
	
	@Test
	public void testUpperTimeBound() {
		db.convertTime();
		int upperTimeValue = db.getUpperTime();
		assertEquals(1040, upperTimeValue);
		
		db2.convertTime();
		int upperTimeValue2 = db2.getUpperTime();
		assertEquals(1020, upperTimeValue2);
	}
	
	
	@Test
	public void testFlightDelayDataNotNull() {
		flightDelays = db.pullFlightDelayData();
		assertNotNull("Flight delay data cannot be null", flightDelays);	
	}
	
	
	@Test
	public void testCustomsDelayDataNotNull() {
		customsDelays = db.queryCustomsData();
		assertNotNull("Customs delay data cannot be null", customsDelays);	
	}

}
