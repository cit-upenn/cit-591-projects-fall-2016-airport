package src;

import java.util.ArrayList;

public class CustomsWaitAnalyzer {
	
	private ArrayList<Integer> waitTimes;
	private double avgWait;
	private double waitCount;
	
	public CustomsWaitAnalyzer(ArrayList<Integer> waitTimes){
		this.waitTimes = waitTimes;
		waitCount = waitTimes.size();
	}
	
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
