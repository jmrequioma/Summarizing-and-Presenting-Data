package main;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class NumericalTableController implements Initializable {

	@FXML TableView<NumericalData> numericalData;
	@FXML TableColumn<NumericalData, String> classLimit;
	@FXML TableColumn<NumericalData, String> trueClassLimit;
	@FXML TableColumn<NumericalData, Float> midpoint;
	@FXML TableColumn<NumericalData, Integer> frequency;
	@FXML TableColumn<NumericalData, Float> percentage;
	@FXML TableColumn<NumericalData, Integer> cumulativeFrequency;
	@FXML TableColumn<NumericalData, Float> cumulativePercentage;	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ArrayList<Float> sampleDataFloat = MainFields.getSampleDataFloat();
		Collections.sort(sampleDataFloat);
		
		float highestVal = sampleDataFloat.get(sampleDataFloat.size() - 1);
		float lowestVal = sampleDataFloat.get(0);
		float range = highestVal - lowestVal;
		int numClasses = sturge(range);
		float width = range / numClasses;
		ArrayList<String> classLimitList = classLimit(lowestVal, numClasses, width);
		ArrayList<String> trueClassLimitList = trueClassLimit(lowestVal, numClasses, width);
		ArrayList<Float> midpointList = midpoint(lowestVal, numClasses, width);
		ArrayList<Integer> frequencyList = frequency(sampleDataFloat, trueClassLimitList);
		ArrayList<Float> percentageList = percentage(frequencyList);
		ArrayList<Integer> cumulativeFrequencyList = cumulativeFrequency(frequencyList);
		ArrayList<Float> cumulativePercentageList = cumulativePercentage(percentageList);
		
		classLimit.setCellValueFactory(new PropertyValueFactory
				<NumericalData, String>("classLimit"));
		trueClassLimit.setCellValueFactory(new PropertyValueFactory
				<NumericalData, String>("trueClassLimit"));
		midpoint.setCellValueFactory(new PropertyValueFactory
				<NumericalData, Float>("midpoint"));
		frequency.setCellValueFactory(new PropertyValueFactory
				<NumericalData, Integer>("frequency"));
		percentage.setCellValueFactory(new PropertyValueFactory
				<NumericalData, Float>("percentage"));
		cumulativeFrequency.setCellValueFactory(new PropertyValueFactory
				<NumericalData, Integer>("cumulativeFrequency"));
		cumulativePercentage.setCellValueFactory(new PropertyValueFactory
				<NumericalData, Float>("cumulativePercentage"));
	}
	
	private int sturge(float range) {
		return (int) Math.ceil(1 + (3.322 * Math.log10(range)));
	}
	
	private ArrayList<String> classLimit(float lowestVal, int numClasses, float width) {
		ArrayList<String> classLimitList = new ArrayList<String>();
		
		for(int i = 0; i < numClasses; i++) {
			
		}
	}
}
