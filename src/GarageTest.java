package src;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GarageTest {
	final static double EPSILON = 0.0;
	private Garage garage;
	
	@Before
	public void setup() { 
		garage = new Garage("A", 41.2, -22.4, 30, 100, 1200);
	}
	
	@Test
	public void testN() {
		String name = garage.getName();
		
		assertEquals("Garage name should be A", "A", name);
		
	}

	@Test
	public void testLat() {
		double lat = garage.getLatitude();
		
		assertEquals(41.2, lat, EPSILON);
		
	}
	
	@Test
	public void testLon() {
		double lon = garage.getLongitude();
		
		assertEquals(-22.4, lon, EPSILON);
		
	}
	
	@Test
	public void testADAValue() {
		int ada = garage.getAdaSpaces();
		
		assertEquals("Ada spaces should be 30", 30, ada);
		
	}
	
	@Test
	public void testAvailValue() {
		int avail = garage.getAvailSpaces();
		
		assertEquals("Available spaces should be 100", 100, avail);
		
	}
	
	@Test
	public void testTotalValue() {
		int total = garage.getTotalSpaces();
		
		assertEquals("Total spaces should be 1200", 1200, total);
		
	}
	
	@Test
	public void testUsedValue() {
		int used = garage.getUsedSpaces();
		assertEquals("Used spaces should be 1070", 1070, used);
		
	}
}
