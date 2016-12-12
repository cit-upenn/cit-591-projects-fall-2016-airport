package src;

import java.io.IOException;

import org.json.JSONException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class UserInterface extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {	
		
		primaryStage.setTitle("PHL Airport Planner");
		
        Button btn = new Button();
        btn.setText("Search");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	TSACaller tsa = new TSACaller();
                try {
					System.out.println(tsa.getAllCheckPoint());
				} catch (JSONException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        StackPane root = new StackPane();
        
		SplitPane splitPane1 = new SplitPane();
		SplitPane.setResizableWithParent(splitPane1, true);
		splitPane1.setOrientation(Orientation.HORIZONTAL);
		splitPane1.setPrefSize(500, 500);
		final Button l1 = new Button("TSA Wait Times");
		final Button r1 = new Button("TSA Output");
		splitPane1.getItems().addAll(l1, r1);
		root.getChildren().add(splitPane1);
		
		SplitPane splitPane2 = new SplitPane();
		splitPane2.setOrientation(Orientation.VERTICAL);
		splitPane2.setPrefSize(300, 200);
		final Button c2 = new Button("Center Button");
		final Button r2 = new Button("Right Button");
		splitPane2.getItems().addAll(splitPane1, c2, r2);
		root.getChildren().add(splitPane2);
	
		
		
//		splitPane1.getChildrenUnmodifiable().add(btn);
//        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}

}


//SplitPane splitPane1 = new SplitPane();
//splitPane1.setOrientation(Orientation.VERTICAL);
//splitPane1.setPrefSize(200, 200);
//final Button l1 = new Button("Top Button");
//final Button r1 = new Button("Bottom Button");
//splitPane1.getItems().addAll(l1, r1);
// 
//SplitPane splitPane2 = new SplitPane();
//splitPane2.setOrientation(Orientation.HORIZONTAL);
//splitPane2.setPrefSize(300, 200);
//final Button c2 = new Button("Center Button");
//final Button r2 = new Button("Right Button");
//splitPane2.getItems().addAll(splitPane1, c2, r2);
//hbox.getChildren().add(splitPane2);
