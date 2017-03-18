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
	
	@FXML TextField txtFInputTitle;
	@FXML Button btnBack;
	
	@FXML
	private void confirm(ActionEvent event) throws IOException {
		Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.close();
		
		String title = txtFInputTitle.getText();
		// TODO
		
		Stage ownerStage = (Stage) stage.getOwner();
		Parent root = FXMLLoader.load(getClass().getResource("DataPresentation.fxml"));
		Scene scene = new Scene(root);
		ownerStage.setScene(scene);
		ownerStage.show();
	}
	
	@FXML
	private void back(ActionEvent event) {
		Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.close();
	}
}