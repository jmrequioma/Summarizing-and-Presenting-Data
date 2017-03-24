package data_presentation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.MainFields;

public class DataPresentationController implements Initializable {

	@FXML private Button btnSampleData;
	@FXML private Button btnGenerateTable;
	@FXML private Button btnGenerateGraph;
	@FXML private Button btnFinish;
	@FXML private Label lblTitle;
	@FXML private Pane paneTable;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblTitle.setText(MainFields.getTitle());
	}
	
	@FXML
	public void sampleDataClick(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/sample_data/SampleData.fxml"));
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add("/themes/bloodcrimson.css");
		stage.setTitle("Sample Data");
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(btnSampleData.getScene().getWindow());
		stage.showAndWait();
	}
	
	@FXML
	public void generateTableClick(ActionEvent event) throws IOException {
		if (MainFields.getValid()) {
			Pane paneTypeTable;
			String type = MainFields.getType();
			
			if(type.equals("Categorical")) {
				paneTypeTable = FXMLLoader.load(getClass().getResource("/data_presentation/categorical/CategoricalTable.fxml"));
			} else { // type.equals("Numerical")
				paneTypeTable = FXMLLoader.load(getClass().getResource("/data_presentation/numerical/NumericalTable.fxml"));
			}
			
			paneTable.getChildren().add(paneTypeTable);
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Critical Error");
			alert.setHeaderText("Invalid Click!!!");
			alert.setContentText("Ooops, clicking this is not allowed. Please enter sample data first.");
			alert.showAndWait();
		}
	}
	
	@FXML
	public void generateGraphClick(ActionEvent event) throws IOException {
		if (MainFields.getValid()) {
			Parent root;
			Stage stage = new Stage();
			if (MainFields.getType().equals("Categorical")) {
				root = FXMLLoader.load(getClass().getResource("/data_presentation/categorical/PieChart.fxml"));
				stage.setTitle("Pie Chart");
			} else {
				root = FXMLLoader.load(getClass().getResource("/data_presentation/numerical/Histogram.fxml"));
				stage.setTitle("Histogram");
			}
			Scene scene = new Scene(root);
			scene.getStylesheets().add("/themes/bloodcrimson.css");
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(btnGenerateGraph.getScene().getWindow());
			stage.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Critical Error");
			alert.setHeaderText("Invalid Click!!!");
			alert.setContentText("Ooops, clicking this is not allowed. Please enter sample data first.");
			alert.showAndWait();
		}
	}
	
	@FXML
	public void finishClick() throws IOException {
		Stage stage = (Stage) btnFinish.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/menu/Menu.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add("/themes/bloodcrimson.css");
		stage.setScene(scene);
		stage.show();
		MainFields.reset(); 
	}
}
