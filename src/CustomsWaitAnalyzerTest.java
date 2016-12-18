package src;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class CustomsWaitAnalyzerTest {

	private Database database;
	private ArrayList<Integer> customsWait = new ArrayList<Integer>();
	private CustomsWaitAnalyzer customs;
	
	
	@Before
	public void setup(){
		database = new Database(2, 12, 1840, 22);
		customsWait = database.queryCustomsData();
	}

	@Test
	public void testAvgCalculation() {
		customs = new CustomsWaitAnalyzer(customsWait);
		double waitAvg = customs.averageWait();
		assertNotNull("Returned average value should not be NULL or negative: ", waitAvg);
	}

}
