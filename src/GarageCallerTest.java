package src;

import static org.junit.Assert.*;

import org.junit.Test;

public class GarageCallerTest {

	@Test
	public void testRestCallerNotNull() {
		GarageCaller gc = new GarageCaller();
		assertNotNull("GarageCaller cannot be null", gc);
	}
}
