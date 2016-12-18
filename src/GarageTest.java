package src;

import static org.junit.Assert.*;

import org.junit.Test;

public class GarageTest {
	final static double EPSILON = 0.0;
	
	@Test
	public void testN() {
		Garage garage = new Garage("A", 41.2, -22.4, 30, 100, 1200);
		String name = garage.getName();
		
		assertEquals("Garage name should be A", "A", name);
		
	}

	@Test
	public void testLat() {
		Garage garage = new Garage("A", 41.2, -22.4, 30, 100, 1200);
		double lat = garage.getLatitude();
		
		assertEquals(41.2, lat, EPSILON);
		
	}
	
	@Test
	public void testLon() {
		Garage garage = new Garage("A", 41.2, -22.4, 30, 100, 1200);
		double lon = garage.getLongitude();
		
		assertEquals(-22.4, lon, EPSILON);
		
	}
	
	@Test
	public void testADAValue() {
		Garage garage = new Garage("A", 41.2, -22.4, 30, 100, 1200);
		int ada = garage.getAdaSpaces();
		
		assertEquals("Ada spaces should be 30", 30, ada);
		
	}
	
	@Test
	public void testAvailValue() {
		Garage garage = new Garage("A", 41.2, -22.4, 30, 100, 1200);
		int avail = garage.getAvailSpaces();
		
		assertEquals("Available spaces should be 100", 100, avail);
		
	}
	
	@Test
	public void testTotalValue() {
		Garage garage = new Garage("A", 41.2, -22.4, 30, 100, 1200);
		int total = garage.getTotalSpaces();
		
		assertEquals("Total spaces should be 1200", 1200, total);
		
	}
	
	@Test
	public void testUsedValue() {
		Garage garage = new Garage("A", 41.2, -22.4, 30, 100, 1200);
		int used = garage.getTotalSpaces() - garage.getAvailSpaces() - garage.getAdaSpaces();
		
		assertEquals("Total spaces should be 1200", 1070, used);
		
	}
}
