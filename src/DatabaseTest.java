package src;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class DatabaseTest {
	
	private Database db;
	private ArrayList<Integer> flightDelays = new ArrayList<Integer>();
	private ArrayList<Integer> customsDelays = new ArrayList<Integer>();
	
	@Before
	public void setup() { 
		db = new Database(1, 2, 910, 23);
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
		assertEquals(840, lowerTimeValue);
	}
	
	
	@Test
	public void testUpperTimeBound() {
		db.convertTime();
		int upperTimeValue = db.getUpperTime();
		assertEquals(940, upperTimeValue);
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
