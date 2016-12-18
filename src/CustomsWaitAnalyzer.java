package src;

import java.util.ArrayList;

/**
 * Analyzes and outputs data based on an input table of customs wait time information.
 * @author Brian
 *
 */
public class CustomsWaitAnalyzer {
	
	private ArrayList<Integer> waitTimes;
	private double avgWait;
	private double waitCount;
	
	/**
	 * Constructor. Uses input list to set the instance list and the waitCount.
	 * @param waitTimes ArrayList of customs wait times.
	 */
	public CustomsWaitAnalyzer(ArrayList<Integer> waitTimes){
		this.waitTimes = waitTimes;
		waitCount = waitTimes.size();
	}
	
	/**
	 * Calculates the average wait time in minutes on a particular day at Philadelphia International Airport
	 * @return double average wait time (in minutes) at Customs and Border Patrol
	 */
	public double averageWait(){
		int totalWait = 0;
		
		for(int i = 0; i < waitCount; i++){
			totalWait = totalWait + waitTimes.get(i);
		}
		
		avgWait = totalWait / waitCount ;
		avgWait = Math.round(avgWait) ;
		return avgWait;
	}
}
