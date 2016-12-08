package database;

import java.util.ArrayList;

public class CustomsWaitAnalyzer {
	
	private ArrayList<Integer> waitTimes;
	private double avgWait;
	private double waitCount;
	private Database testDB = new Database();
	
	public CustomsWaitAnalyzer(){
		this.waitTimes = testDB.queryCustomsData();
		waitCount = waitTimes.size();
	}
	
	public double averageWait(){
		int totalWait = 0;
		
		for(int i = 0; i < waitCount; i++){
			totalWait = totalWait + waitTimes.get(i);
		}
		
		avgWait = totalWait / waitCount ;
		return avgWait;
	}
}
