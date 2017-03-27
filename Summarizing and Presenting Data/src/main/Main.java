package main;
	
import com.google.common.eventbus.EventBus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			EventBus eventBus = new EventBus();
			MainFields.setEventBus(eventBus);
			
			Parent root = FXMLLoader.load(getClass().getResource("/menu/Menu.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add("/themes/bloodcrimson.css");
			
			primaryStage.setTitle("Summarizing and Presenting Data");   // title
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
