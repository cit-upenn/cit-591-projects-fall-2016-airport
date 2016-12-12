package src;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;

import org.json.JSONException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class GUI extends Application {
	
	String text = "";

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Philadelphia Airport Planner");
		BorderPane root = new BorderPane();
		
		VBox tsa = new VBox();
		VBox wait = new VBox();
		VBox garage = new VBox();
		
		root.setLeft(tsa);
		root.setRight(wait);
		root.setBottom(garage);
		
		//TSA VBox on the left
		tsa.setPadding(new Insets(10));
	    tsa.setSpacing(8);
		Text tsaText = new Text();
		tsaText.setText("Check TSA Security Wait Time at:");
		
		ObservableList<String> options = FXCollections.observableArrayList(
				"All Checkpoints",
				"Checkpoint A-East",
	    		"Checkpoint A-West Sec 5",
	    		"Checkpoint A-West Sec 7",
	    		"Checkpoint B",
	    		"Checkpoint C",
	    		"Checkpoint D/E",
	    		"Checkpoint F"
		);
		ComboBox<String> checkpointSelection = new ComboBox<String>(options);
		
		Button checkTSA = new Button();
		Text tsaOutput = new Text();
		
		checkTSA.setText("Check now");
		checkTSA.setOnAction(new EventHandler<ActionEvent>() {
			 
            @Override
            public void handle(ActionEvent event) {
            	TSACaller caller = new TSACaller();
                try {
                	if (caller.getAllCheckPoint().containsKey(checkpointSelection.getValue())) {
                		text = Integer.toString(caller.getWaitTime(checkpointSelection.getValue())) + " minutes";
					} else {
						text = "Not available";
					}
                
            		tsaOutput.setText(text);
 
                	
					System.out.println(text);
				} catch (JSONException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
		
		
		tsa.getChildren().addAll(tsaText, checkpointSelection, checkTSA, tsaOutput);
		
		//Delay/Customs wait VBox on the right
		wait.setPadding(new Insets(10));
	    wait.setSpacing(8);
	    
	    //Setup the departing/arriving radio buttons
		Text waitText = new Text();
		waitText.setText("Are you arriving or departing from Philadelphia Airport?");
		
		ToggleGroup toggle = new ToggleGroup();
		
		RadioButton radioDepart = new RadioButton();
		radioDepart.setText("Departing");
		
		RadioButton radioArrive = new RadioButton();
		radioArrive.setText("Arriving");
		
		radioDepart.setToggleGroup(toggle);
		radioArrive.setToggleGroup(toggle);
		
		//Put in the datetime object
		Text datePrompt = new Text();
		datePrompt.setText("Please select the date and time of your flight: ");
		
		DatePicker calendar = new DatePicker();
		calendar.setValue(LocalDate.now());
		
		HBox enterTime = new HBox();
		
		final ComboBox flightHour = new ComboBox();
		flightHour.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12") ;
		
		final ComboBox flightMin = new ComboBox();
		String comboMin = "";
		
		DecimalFormat dec = new DecimalFormat("00");
		for(int i = 0; i < 60; i++){
			
			
			
			/*String tempMin = "";
			if (i < 10){
				tempMin = dec.format(i) + ", ";
			}
			else if(i == 59){tempMin = Integer.toString(i);}
			else{tempMin = i + ", ";}
			
			comboMin = comboMin + tempMin ;*/
		}
		
		flightMin.getItems().addAll(comboMin) ;
		
		final ComboBox flightAMPM = new ComboBox();
		
		flightAMPM.getItems().addAll("AM", "PM") ;
		enterTime.getChildren().addAll(flightHour, flightMin, flightAMPM);
		
		
		//Insert button for searching flight information
		Button checkWait = new Button();
		checkWait.setText("Check Wait");
		
		Text response = new Text();
		checkWait.setOnAction((click)->{
			LocalDate date = calendar.getValue();
			int month = date.getMonthValue();
			int day = date.getDayOfMonth();
			int year = date.getYear();
			int dayOfWeek = date.getDayOfWeek().getValue();
			
			Database db = new Database(dayOfWeek, month, 1244, day);

			
			if(radioDepart.isSelected()){
				FlightDelayAnalyzer delays = new FlightDelayAnalyzer(db.pullFlightDelayData());
				double flightDelay = delays.calculateAverageDelay();
				if(flightDelay > 0){
					response.setText("Expected flight delay: " + flightDelay + " min");
				}
				else if(flightDelay < 0){
					response.setText("Flight expected to arrive" + flightDelay + " min early.");
				}
				else{response.setText("No delay expected.");}
				
			}
			
			else if(radioArrive.isSelected()){
				CustomsWaitAnalyzer customs = new CustomsWaitAnalyzer(db.queryCustomsData());
				double customsWait = customs.averageWait();
				if(customsWait > 0){
					response.setText("Average expected wait at Customs: " + customs.averageWait() + " min");
				}
				else{response.setText("No wait expected at Customs.");}
			}
			
		});

		wait.getChildren().addAll(waitText, radioDepart, radioArrive, datePrompt, calendar, enterTime, checkWait, response) ;
		
		
		
		//Garage VBox at the bottom
		garage.setPadding(new Insets(10));
	    garage.setSpacing(8);
	    
	    Text garageText = new Text();
		garageText.setText("Parking Availability at Philadelphia Airport");
		
		garage.getChildren().add(garageText);
		
		primaryStage.setScene(new Scene(root, 600, 600));
	    primaryStage.show();
		

	}

	public static void main(String[] args) {
		launch(args);

	}

}
