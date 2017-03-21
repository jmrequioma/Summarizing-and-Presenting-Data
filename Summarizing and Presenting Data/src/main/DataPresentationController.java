package main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DataPresentationController implements Initializable {

	@FXML Button btnSampleData;
	@FXML Button btnGenerateTable;
	@FXML Button btnGenerateGraph;
	@FXML Label lblTitle;
	@FXML Pane paneTable;
	
	private ObservableList<PieChart.Data> details = FXCollections.observableArrayList();
	private PieChart pChart;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblTitle.setText(MainFields.getTitle());
	}
	
	@FXML
	public void sampleDataClick(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("SampleData.fxml"));
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add("/main/bloodcrimson.css");
		stage.setTitle("Sample Data");
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(btnSampleData.getScene().getWindow());
		stage.showAndWait();
	}
	
	@FXML
	public void generateTableClick(ActionEvent event) throws IOException {
		Pane paneTypeTable;
		String type = MainFields.getType();
		
		if(type.equals("Categorical")) {
			paneTypeTable = FXMLLoader.load(getClass().getResource("/main/CategoricalTable.fxml"));
		} else { // type.equals("Numerical")
			paneTypeTable = FXMLLoader.load(getClass().getResource("/main/NumericalTable.fxml"));
		}
		
		paneTable.getChildren().add(paneTypeTable);
	}
	
	@FXML
	public void generateGraphClick(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		
		Parent root = FXMLLoader.load(getClass().getResource("PieChart.fxml"));
		for (int i = 0; i < MainFields.getSampleData().size(); i++) {
			details.add(new PieChart.Data(MainFields.getSampleData().get(i), 1));
		}
		Scene scene = new Scene(root);
		scene.getStylesheets().add("/main/bloodcrimson.css");
		pChart = new PieChart();
		pChart.setData(details);
		pChart.setTitle(MainFields.getTitle());
		pChart.setLegendSide(Side.BOTTOM);
		pChart.setLabelsVisible(true);
		//scene.getRoot().getChildrenUnmodifiable().add(pChart);
		stage.setTitle("Pie Chart");
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(btnGenerateGraph.getScene().getWindow());
		stage.showAndWait();
	}
}
