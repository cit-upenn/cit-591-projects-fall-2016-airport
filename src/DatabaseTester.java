package src;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Tester class for testing queried data. Precursor to our GUI.
 * @author Brian Z. Sokas
 */
public class DatabaseTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//CustomsWaitAnalyzer customs = new CustomsWaitAnalyzer();

		Scanner in = new Scanner(System.in);

		System.out.println("Please enter day of week(integer): ");
		int day = in.nextInt();
		System.out.println("Please enter the number month: ");
		int month = in.nextInt();
		System.out.println("Please enter day of month.");
		int dayOfMonth = in.nextInt();
		System.out.println("Please enter time in 24-clock period");
		int time = in.nextInt();

		System.out.println("Enter 0 if you're on a departing flight or 1 if you're on an arriving flight: ");
		int status = in.nextInt();
		Database db = new Database(day, month, time, dayOfMonth);

		if(status == 0){
			FlightDelayAnalyzer delays = new FlightDelayAnalyzer(db.pullFlightDelayData());
			
			System.out.printf("Your expected flight delay is: %f minutes", delays.calculateAverageDelay());
			}
		else if(status == 1){
			CustomsWaitAnalyzer customs = new CustomsWaitAnalyzer(db.queryCustomsData());
			System.out.printf("Your expected wait at Customs is: %f minutes", customs.averageWait());
		}

	}
}