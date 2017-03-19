package main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DataPresentationController implements Initializable {

	@FXML Button btnSampleData;
	@FXML Button btnGenerateTable;
	@FXML Button btnGenerateGraph;
	@FXML Label lblTitle;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblTitle.setText(MainFields.getTitle());
	}
	
	@FXML
	public void sampleDataClick(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("SampleData.fxml"));
		
		Scene scene = new Scene(root);
		stage.setTitle("Sample Data");
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(btnSampleData.getScene().getWindow());
		stage.showAndWait();
	}
}
