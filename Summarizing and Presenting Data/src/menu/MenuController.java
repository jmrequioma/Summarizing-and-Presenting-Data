package menu;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.MainFields;

public class MenuController {
	
	@FXML private Button btnCategorical;
	@FXML private Button btnNumerical;
	@FXML private Button btnQuit;

	@FXML
	private void buttonClick(ActionEvent event) throws IOException {
		Button src = (Button) event.getSource();
		
		if(src == btnCategorical) {
			MainFields.setType("Categorical");
		} else if(src == btnNumerical) {
			MainFields.setType("Numerical");
		} else {
			System.exit(0);
		}
		
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/title/InputTitle.fxml"));
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add("/themes/bloodcrimson.css");
		stage.setTitle("Input Title");
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(src.getScene().getWindow());
		stage.showAndWait();
	}
}
