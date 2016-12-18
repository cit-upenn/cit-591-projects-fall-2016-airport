package src;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WaitTimeTest {
	
	WaitTime wt;
	
	@Before
	public void setup() { 
		wt = new WaitTime("Checkpoint F", "2016-12-15T01:41:33-05:00", 15);
	}

	@Test
	public void testCheckpointValue() {
		String checkpoint = wt.getCheckPoint();
		
		assertEquals("Checkpoint should be Checkpoint F", "Checkpoint F", checkpoint);
		
	}
	
	@Test
	public void testWaitValue() {
		int wait = wt.getWait();
		
		assertEquals("Wait should be 15", 15, wait);
		
	}
	
	@Test
	public void testCreatedTimeValue() {
		String createdTime = wt.getCreatedTime();
		
		assertEquals("Created time should be 2016-12-15T01:41:33-05:0", "2016-12-15T01:41:33-05:00", createdTime);
		
	}

}
