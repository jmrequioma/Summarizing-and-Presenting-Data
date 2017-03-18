package main;

import java.io.IOException;

import javax.xml.transform.Source;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuController {
	
	@FXML private Button btnCategorical;
	@FXML private Button btnNumerical;
	@FXML private Button btnQuit;

	@FXML
	private void buttonClick(ActionEvent event) throws IOException {
		Button src = (Button) event.getSource();
		String type;
		
		if(src == btnCategorical) {
			type = "Categorical";
		} else if(src == btnNumerical) {
			type = "Numerical";
		} else {
			System.exit(0);
		}
		
		// TODO
		
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("InputTitle.fxml"));
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(src.getScene().getWindow());
		stage.showAndWait();
	}
}
