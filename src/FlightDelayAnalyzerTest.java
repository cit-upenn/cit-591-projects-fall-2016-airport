package src;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class FlightDelayAnalyzerTest {

	private Database db;
	private ArrayList<Integer> delays = new ArrayList<Integer>();
	
	@Before
	public void setup() { 
		db = new Database(1, 2, 900, 23);
		delays = db.pullFlightDelayData();
	}
	
	
	@Test
	public void testAverageCalculationNotNull() {
		FlightDelayAnalyzer fda = new FlightDelayAnalyzer(delays);
		double delayAverage = fda.calculateAverageDelay();
		assertNotNull("Average cannot be null", delayAverage);	
	}

}
