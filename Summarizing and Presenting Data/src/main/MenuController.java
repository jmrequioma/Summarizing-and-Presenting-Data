package main;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuController {
	
	@FXML
	private Button btnCategorical;
	@FXML
	private Button btnNumerical;
	@FXML
	private Button btnQuit;

	@FXML
	public void categoricalClick(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("InputTitle.fxml"));
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(btnCategorical.getScene().getWindow());
		stage.showAndWait();
	}
	
	public void numericalClick(ActionEvent event) {
		
	}
	
	public void quitClick(ActionEvent event) {
		
	}
}
