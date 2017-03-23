package main;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HistogramController implements Initializable {
	private ObservableList<String> details = FXCollections.observableArrayList();
	 private int DATA_SIZE = 1000;
	 private int data[] = new int[DATA_SIZE];
	 private int group[] = new int[10];
	 @FXML private CategoryAxis xAxis;
     @FXML private NumberAxis yAxis;
     @FXML private BarChart<String,Number> histogram;
	 @FXML private Button btnConfirmHistogram;
		
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	prepareData();
    	groupData();
    	histogram.setCategoryGap(0);
    	histogram.setBarGap(0);
    	histogram.setTitle(MainFields.getTitle());
          
    	xAxis.setLabel("Range");
    	yAxis.setLabel("Frequency");
    	xAxis.setVisible(true);
    	yAxis.setVisible(true);
    	XYChart.Series series1 = new XYChart.Series();
    	series1.setName("Histogram");  
    	for (int i = 0; i < 9; i++) {
    		series1.getData().add(new XYChart.Data("group" + "[" + Integer.toString(i) + "]", group[i]));
    	}
    	histogram.getData().addAll(series1);
    }
    
    @FXML
	private void confirmHistogramClick(ActionEvent event) {
		Stage stage = (Stage) btnConfirmHistogram.getScene().getWindow();
		stage.close();
	}
    
    private void prepareData() {
        Random random = new Random();
        for(int i = 0; i < DATA_SIZE; i++){
            data[i] = random.nextInt(100);
        }
    }
    
  //count data population in groups
    private void groupData(){
        for (int i = 0; i < 10; i++){
            group[i]=0;
        }
        for (int i = 0; i < DATA_SIZE; i++){
            if(data[i]<=10){
                group[0]++;
            }else if(data[i]<=20){
                group[1]++;
            }else if(data[i]<=30){
                group[2]++;
            }else if(data[i]<=40){
                group[3]++;
            }else if(data[i]<=50){
                group[4]++;
            }else if(data[i]<=60){
                group[5]++;
            }else if(data[i]<=70){
                group[6]++;
            }else if(data[i]<=80){
                group[7]++;
            }else if(data[i]<=90){
                group[8]++;
            }else if(data[i]<=100){
                group[9]++;
            }
        }
    }
}
