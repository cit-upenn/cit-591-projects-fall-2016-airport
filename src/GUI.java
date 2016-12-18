package src;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Iterator;

import org.json.JSONException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
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

/**
 * Graphical User Interface for 591 Project.
 * Displays a user interface with 3 different functionalities:
 * 1) Checking TSA airport security wait times at PHL
 * 2) Finding expected flight delay time or expected wait at Customs and Border Patrol
 * 3) Paring availability @ PHL
 * @author Brian Sokas, Huong Vu, Veronika Alex
 *
 */
public class GUI extends Application {

	String text = "";
	final static String parkingA = "Garage A";
	final static String parkingB = "Garage B";
	final static String parkingC = "Garage C";
	final static String parkingD = "Garage D";
	final static String parkingEF = "Garage EF";
	final static String parkingEconomy = "Economy";
	final CategoryAxis xAxis = new CategoryAxis();
	final NumberAxis yAxis = new NumberAxis();
	final StackedBarChart<String, Number> sbc = new StackedBarChart<String, Number> (xAxis, yAxis);
	final XYChart.Series<String, Number> series1 =
			new XYChart.Series<String, Number>();
	final XYChart.Series<String, Number> series2 =
			new XYChart.Series<String, Number>();
	final XYChart.Series<String, Number> series3 =
			new XYChart.Series<String, Number>();


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

		tsaText.setText("Check Current TSA Security Wait Time at:");

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
		checkpointSelection.getSelectionModel().selectFirst();

		Button checkTSA = new Button();
		Text tsaOutput = new Text();

		checkTSA.setText("Check now");
		checkTSA.setOnAction(new EventHandler<ActionEvent>() {

			/**
			 * Event path when the 'Check Now' button for the first panel is pressed
			 */
			@Override
			public void handle(ActionEvent event) {
				TSACaller caller = new TSACaller();
				try {
					if (caller.getAllCheckPoint().containsKey(checkpointSelection.getValue())) {
						text = checkpointSelection.getValue() + ": " + Integer.toString(caller.getWaitTime(checkpointSelection.getValue())) + " minutes";
					} else if (checkpointSelection.getValue().equals("All Checkpoints")) {
						StringBuilder all = new StringBuilder();
						Iterator<String> it = caller.getAllCheckPoint().keySet().iterator(); 
						while(it.hasNext()) {
							String key = it.next();
							if (key == "N/A") continue;
							int wait = caller.getAllCheckPoint().get(key);
							text = key + ": " + Integer.toString(wait) + "minutes\n";
							all.append(text);
						}

						text = all.toString();
					} else {
						text = "Not available";
					}

					tsaOutput.setText(text);

				} catch (JSONException | IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Could not call API! Connection problem!");
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

		final ComboBox<String>flightHour = new ComboBox();
		flightHour.getItems().addAll("Hr","1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12") ;
		flightHour.getSelectionModel().selectFirst();

		final ComboBox<String> flightMin = new ComboBox();
		flightMin.getItems().addAll("Min", "00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17", "18", "19", "20",
				"21","22","23","24","25","26","27","28","29", "30","31","32","33","34","35","36","37","38","39", "40","41","42","43","44","45",
				"46", "47", "48", "49", "50","51","52","53","54","55","56","57","58","59") ;
		flightMin.getSelectionModel().selectFirst();

		final ComboBox<String> flightAMPM = new ComboBox();
		flightAMPM.getItems().addAll("AM", "PM") ;
		flightAMPM.getSelectionModel().selectFirst();

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
			int hour = Integer.parseInt(flightHour.getValue());
			int min = Integer.parseInt(flightMin.getValue());
			if(flightAMPM.getValue() == "PM"){
				hour = hour + 12;
			}
			int time = (hour * 100) + min ;

			Database db = new Database(dayOfWeek, month, time, day);


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


        primaryStage.setTitle("Current PHL Airport Parking Availability");
        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();
        final BarChart<Number, String> bc = new BarChart<Number, String> (xAxis, yAxis);
        bc.setTitle("Current PHL Airport Parking Availability");
        
        GarageCaller gc = new GarageCaller();
        
        //sbc.setTitle("Current PHL Airport Parking Availability");
        xAxis.setLabel("Parking Spaces");
        xAxis.setTickLabelRotation(90);
//        xAxis.setCategories(FXCollections.<String>observableArrayList(
//                Arrays.asList(parkingA, parkingB, parkingC, parkingD, parkingEF)));
        yAxis.setLabel("Garage Name");
        
        series1.setName("Used Spaces");
        series1.getData().add(new XYChart.Data<String, Number>(parkingA, gc.getGarages().get(0).getUsedSpaces()));
        series1.getData().add(new XYChart.Data<String, Number>(parkingB, gc.getGarages().get(1).getUsedSpaces()));
        series1.getData().add(new XYChart.Data<String, Number>(parkingC, gc.getGarages().get(2).getUsedSpaces()));
        series1.getData().add(new XYChart.Data<String, Number>(parkingD, gc.getGarages().get(3).getUsedSpaces()));
        series1.getData().add(new XYChart.Data<String, Number>(parkingEF, gc.getGarages().get(4).getUsedSpaces()));
        series1.getData().add(new XYChart.Data<String, Number>(parkingEconomy, gc.getGarages().get(5).getUsedSpaces()));
        
        series2.setName("Handicap Available                     ");
        series2.getData().add(new XYChart.Data<String, Number>(parkingA, gc.getGarages().get(0).getAdaSpaces()));
        series2.getData().add(new XYChart.Data<String, Number>(parkingB, gc.getGarages().get(1).getAdaSpaces()));
        series2.getData().add(new XYChart.Data<String, Number>(parkingC, gc.getGarages().get(2).getAdaSpaces()));
        series2.getData().add(new XYChart.Data<String, Number>(parkingD, gc.getGarages().get(3).getAdaSpaces()));
        series2.getData().add(new XYChart.Data<String, Number>(parkingEF, gc.getGarages().get(4).getAdaSpaces()));
        series2.getData().add(new XYChart.Data<String, Number>(parkingEconomy, gc.getGarages().get(5).getAdaSpaces()));
        
        series3.setName("Regular Available");
        series3.getData().add(new XYChart.Data<String, Number>(parkingA, gc.getGarages().get(0).getAvailSpaces()));
        series3.getData().add(new XYChart.Data<String, Number>(parkingB, gc.getGarages().get(1).getAvailSpaces()));
        series3.getData().add(new XYChart.Data<String, Number>(parkingC, gc.getGarages().get(2).getAvailSpaces()));
        series3.getData().add(new XYChart.Data<String, Number>(parkingD, gc.getGarages().get(3).getAvailSpaces()));
        series3.getData().add(new XYChart.Data<String, Number>(parkingEF, gc.getGarages().get(4).getAvailSpaces()));
        series3.getData().add(new XYChart.Data<String, Number>(parkingEconomy, gc.getGarages().get(5).getAvailSpaces()));
        

        sbc.getData().addAll(series1, series2, series3);
        garage.getChildren().add(sbc);
        
        
        
		primaryStage.setScene(new Scene(root, 700, 700));
	    primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
