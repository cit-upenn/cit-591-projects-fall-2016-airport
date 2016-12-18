package src;

import static org.junit.Assert.*;

import org.junit.Test;

import javafx.stage.Stage;

public class GUITest {
	
	Exception exception;

	@Test
	public void isException() {
		GUI gui = new GUI();
		Stage primaryStage = new Stage();
		
		try{
			gui.start(primaryStage);
		} catch(Exception e){
			exception = e;
		}
		
		assertEquals(null, exception);
	}

}
