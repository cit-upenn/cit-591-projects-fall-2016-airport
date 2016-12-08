package src;
import java.util.ArrayList;
import database.Database;

/**
 * Predicts the flight delay time of a flight.
 * @author veronikaalex
 *
 */
public class FlightDelayAnalyzer {
	private ArrayList<Integer> delays = new ArrayList<Integer>();

	
	/**
	 * Constructor.
	 */
	public FlightDelayAnalyzer() {
		Database db = new Database();
		delays = db.pullFlightDelayData();
		

//		for (int delay : delays) {
//			System.out.println(delay);
//		}
	}
	
	
	
	/**
	 * Calculates the average delay of all flights leaving in the same month, on the same day of the week, and within the same hour
	 * @return the average flight delay
	 */
	public double calculateAverageDelay() {
		double sum = 0;
		double average = 0;
		for (int i = 0; i < delays.size(); i++) {
			sum += delays.get(i);
		}
		average = sum / (delays.size());
		
		System.out.println(sum/(delays.size()));
		average = Math.round(average);
		System.out.println(average);
		return average;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
