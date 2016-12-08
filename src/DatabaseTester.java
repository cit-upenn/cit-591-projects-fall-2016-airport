package src;

import java.util.Scanner;

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
		System.out.println( "SELECT * FROM FLIGHTDELAYS WHERE DAY_OF_WEEK = " + day 
			+ " AND MONTH = " + month + " AND CRS_DEP_TIME > " + time + " AND CRS_DEP_TIME < " + (time+100));
		
		System.out.println("Enter 0 for arriving and 1 for departing flight: ");
		int status = in.nextInt();
		
		
		
		if(status == 0){
			Database db = new Database(day, month, time, dayOfMonth);
			System.out.println(db.pullFlightDelayData().toString());}
		
	}

}
