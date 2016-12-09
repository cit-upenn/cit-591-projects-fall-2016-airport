package src;

import java.io.IOException;

import org.json.JSONException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}

}
