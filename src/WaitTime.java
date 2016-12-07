package src;
/**
 * This class represents a WaitTime from the most recent 25.
 * @author huongvu
 *
 */
public class WaitTime {
	
	private String checkPoint;
	private String createdTime;
	private int wait;
	
	public WaitTime(String checkPoint, String createdTime, int wait) {
		this.checkPoint = checkPoint;
		this.createdTime = createdTime;
		this.wait = wait;
	}

	
	/**
	 * @return the checkPoint
	 */
	public String getCheckPoint() {
		return checkPoint;
	}

	/**
	 * @return the createdTime
	 */
	public String getCreatedTime() {
		return createdTime;
	}

	/**
	 * @return the waitTimeType
	 */
	public int getWait() {
		return wait;
	}
	
	

}
