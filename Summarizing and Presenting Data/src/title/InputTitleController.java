package title;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.MainFields;

public class InputTitleController {
	
	@FXML private TextField txtFInputTitle;
	@FXML private Button btnBack;
	
	@FXML
	private void confirm(ActionEvent event) throws IOException {
		Stage stage = (Stage) btnBack.getScene().getWindow();
		
		String title = txtFInputTitle.getText();
		if (title.equals("")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Critical Error");
			alert.setHeaderText("Invalid Input!!!");
			alert.setContentText("Ooops, input is not allowed! Please change the input.");
			alert.showAndWait();
		} else {
			MainFields.setTitle(title);
			
			Stage ownerStage = (Stage) stage.getOwner();
			Parent root = FXMLLoader.load(getClass().getResource("/data_presentation/DataPresentation.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add("/themes/bloodcrimson.css");
			ownerStage.setScene(scene);
			ownerStage.show();
			stage.close();
		}
	}
	
	@FXML
	private void back(ActionEvent event) {
		Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.close();
	}
}