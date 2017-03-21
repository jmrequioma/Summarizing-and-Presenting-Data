package main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
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
		
		final Label caption = new Label("");
		caption.setTextFill(Color.DARKORANGE);
		caption.setStyle("-fx-font: 24 arial;");

		for (final PieChart.Data data : pieChart.getData()) {
		    data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
		        new EventHandler<MouseEvent>() {
		            @Override public void handle(MouseEvent e) {
		                caption.setTranslateX(e.getSceneX());
		                caption.setTranslateY(e.getSceneY());
		                caption.setText(String.valueOf(data.getPieValue()) + "%");
		             }
		        });
		}
		
	}
	
	@FXML
	private void confirmPieChartClick(ActionEvent event) {
		Stage stage = (Stage) btnConfirmPieChart.getScene().getWindow();
		stage.close();
	}
}
