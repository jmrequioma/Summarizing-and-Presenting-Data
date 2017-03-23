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

	@FXML private TableView<NumericalData> numericalData;
	@FXML private TableColumn<NumericalData, String> classLimit;
	@FXML private TableColumn<NumericalData, String> trueClassLimit;
	@FXML private TableColumn<NumericalData, Float> midpoint;
	@FXML private TableColumn<NumericalData, Integer> frequency;
	@FXML private TableColumn<NumericalData, Float> percentage;
	@FXML private TableColumn<NumericalData, Integer> cumulativeFrequency;
	@FXML private TableColumn<NumericalData, Float> cumulativePercentage;	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ArrayList<Float> sampleDataFloat = MainFields.getSampleDataFloat();
		Collections.sort(sampleDataFloat);
		printSampleDataFloat(sampleDataFloat);
		int mostDecimals = mostDecimals(sampleDataFloat);
		
		float highestVal = sampleDataFloat.get(sampleDataFloat.size() - 1);
		float lowestVal = sampleDataFloat.get(0);
		float range = highestVal - lowestVal;
		int numClasses = sturge(sampleDataFloat.size());
		float width = range / numClasses;
		width = roundUp(width, mostDecimals);
		System.out.println("Number of classes: " + numClasses); // test
		System.out.println("Width: " + width); // test
//		ArrayList<String> classLimitList = classLimit(lowestVal, numClasses, width);
//		ArrayList<String> trueClassLimitList = trueClassLimit(lowestVal, numClasses, width);
//		ArrayList<Float> midpointList = midpoint(lowestVal, numClasses, width);
//		ArrayList<Integer> frequencyList = frequency(sampleDataFloat, trueClassLimitList);
//		ArrayList<Float> percentageList = percentage(frequencyList);
//		ArrayList<Integer> cumulativeFrequencyList = cumulativeFrequency(frequencyList);
//		ArrayList<Float> cumulativePercentageList = cumulativePercentage(percentageList);
		
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
	
	// debugging purposes
	private void printSampleDataFloat(ArrayList<Float> sampleDataFloat) {
		for(Float data : sampleDataFloat) {
			System.out.println("Data: " + String.valueOf(data));
		}
	}
	
	private int mostDecimals (ArrayList<Float> sampleDataFloat) {
		int mostDecimals = 0;
		for(Float data : sampleDataFloat) {
			String dataString = String.valueOf(data);
			int pointLocation = dataString.indexOf('.');
			int decimals = countDecimals(dataString, pointLocation);
			
			if(decimals > mostDecimals) {
				mostDecimals = decimals;
			}
		}
		return mostDecimals;
	}
	
	private int countDecimals(String floatingString, int pointLocation) {
		int decimals = (floatingString.length() - 1) - pointLocation;
		if(decimals == 1) {
			if(floatingString.charAt(pointLocation + 1) == '0') {
				decimals = 0;
			} 
		}
		return decimals;
	}
	
	private int sturge(float range) {
		return (int) Math.ceil(1 + (3.322 * Math.log10(range)));
	}
	
	private float roundUp(float width, int mostDecimals) {
		String widthString = String.valueOf(width);
		int pointLocation = widthString.indexOf('.'); 
		int decimals = countDecimals(widthString, pointLocation);
		
		if(decimals > mostDecimals) {
			float roundUpCoefficient = coefficient(mostDecimals);
			width += roundUpCoefficient;
			widthString = String.valueOf(width);
			widthString = removeExcessDecimals(widthString, mostDecimals);
			width = Float.valueOf(widthString);
		}
		
		return width;
	}
	
	private float coefficient(int mostDecimals) {
		float coefficient = 1;
		
		for(int i = 0 ; i < mostDecimals; i++) {
			coefficient /= 10;
		}
		
		return coefficient;
	}
	
	private String removeExcessDecimals(String floatString, int mostDecimals) {
		int pointLocation = floatString.indexOf('.');
		floatString = floatString.substring(0, pointLocation + mostDecimals + 1);
		
		return floatString;
	}
	
//	private ArrayList<String> classLimit(float lowestVal, int numClasses, float width) {
//		ArrayList<String> classLimitList = new ArrayList<String>();
//		ArrayList<Float> lowerClassLimitList = new ArrayList<Float>();
//		ArrayList<Float> upperClassLimitList = new ArrayList<Float>();
//		
//		for(int i = 0; i < numClasses; i++) {
//			lowerClassLimitList.add(lowestVal + (width * i));
//		}
//		
//	}
}
