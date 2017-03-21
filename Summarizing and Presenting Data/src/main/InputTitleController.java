package main;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InputTitleController {
	
	@FXML private TextField txtFInputTitle;
	@FXML private Button btnBack;
	
	@FXML
	private void confirm(ActionEvent event) throws IOException {
		Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.close();
		
		String title = txtFInputTitle.getText();
		MainFields.setTitle(title);
		
		Stage ownerStage = (Stage) stage.getOwner();
		Parent root = FXMLLoader.load(getClass().getResource("DataPresentation.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add("/main/bloodcrimson.css");
		ownerStage.setScene(scene);
		ownerStage.show();
	}
	
	@FXML
	private void back(ActionEvent event) {
		Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.close();
	}
}