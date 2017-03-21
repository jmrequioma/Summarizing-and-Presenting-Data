package main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PieChartController implements Initializable{
	private ObservableList<PieChart.Data> details = FXCollections.observableArrayList();
	@FXML PieChart pieChart;
	@FXML Button btnConfirmPieChart;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for (int i = 0; i < MainFields.getFusedData().size(); i++) {
			details.add(new PieChart.Data(MainFields.getFusedData().get(i), MainFields.getDataPercentage().get(i)));
		}
		
		pieChart.setData(details);
		pieChart.setTitle(MainFields.getTitle());
		pieChart.setLegendSide(Side.BOTTOM);
		pieChart.setLabelsVisible(true);
	}
	
	@FXML
	private void confirmPieChartClick(ActionEvent event) {
		Stage stage = (Stage) btnConfirmPieChart.getScene().getWindow();
		stage.close();
	}
}
