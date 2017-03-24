package data_presentation.numerical;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.MainFields;

public class NumericalTableController implements Initializable {

	@FXML private TableView<NumericalData> numericalData;
	@FXML private TableColumn<NumericalData, String> classLimit;
	@FXML private TableColumn<NumericalData, String> trueClassLimit;
	@FXML private TableColumn<NumericalData, String> midpoint;
	@FXML private TableColumn<NumericalData, Integer> frequency;
	@FXML private TableColumn<NumericalData, Float> percentage;
	@FXML private TableColumn<NumericalData, Integer> cumulativeFrequency;
	@FXML private TableColumn<NumericalData, Float> cumulativePercentage;	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ArrayList<Float> sampleDataFloat = MainFields.getSampleDataFloat();
		Collections.sort(sampleDataFloat);
		
		int mostDecimals = mostDecimals(sampleDataFloat);
		
		float highestVal = sampleDataFloat.get(sampleDataFloat.size() - 1);
		float lowestVal = sampleDataFloat.get(0);
		float range = filterInaccuracy(highestVal - lowestVal, mostDecimals);
		
		int numClasses = sturge(sampleDataFloat.size());
		
		float width = range / numClasses;
		width = roundUp(width, mostDecimals);
		
		ArrayList<Float> lowerClassLimitList = lowerClassLimit(lowestVal, numClasses, 
				width, mostDecimals);
		ArrayList<Float> upperClassLimitList = upperClassLimit(lowestVal, numClasses, 
				width, mostDecimals);
		ArrayList<String> classLimitList = classLimit(lowerClassLimitList, upperClassLimitList);
		MainFields.setClassLimits(classLimitList);
		ArrayList<String> trueClassLimitList = trueClassLimit(lowerClassLimitList, 
				upperClassLimitList, mostDecimals);
		
		ArrayList<String> midpointList = midpoint(lowerClassLimitList, upperClassLimitList, 
				width, mostDecimals);
		
		ArrayList<Integer> frequencyList = frequency(sampleDataFloat, lowerClassLimitList, 
				upperClassLimitList);
		
		ArrayList<Float> percentageList = percentage(frequencyList);
		
		ArrayList<Integer> cumulativeFrequencyList = cumulativeFrequency(frequencyList);
		
		ArrayList<Float> cumulativePercentageList = cumulativePercentage(percentageList);
		
		populateTable(classLimitList, trueClassLimitList, midpointList, frequencyList, 
					  percentageList, cumulativeFrequencyList, cumulativePercentageList);
		
		MainFields.setFrequencies(frequencyList);
		MainFields.setPercentages(percentageList);
		
		initCellValues();
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
	
	private float filterInaccuracy(float floatingNumber, int mostDecimals) {
		String floatingString = String.valueOf(floatingNumber);
		int pointLocation = floatingString.indexOf('.');
		int decimals = countDecimals(floatingString, pointLocation);
		
		if(decimals > mostDecimals) {
			String mostSignificantExcess = floatingString.substring(
					pointLocation + mostDecimals + 1, pointLocation + mostDecimals + 2);
			int mostSignificantExcessInt = Integer.valueOf(mostSignificantExcess);
			
			if(mostSignificantExcessInt < 5) {
				floatingString = filterRoundDown(floatingString, pointLocation + mostDecimals + 1);
			} else {
				floatingString = filterRoundUp(floatingString, pointLocation + mostDecimals + 1);
			}
		}
		
		floatingNumber = Float.valueOf(floatingString);
		return floatingNumber;
	}
	
	private String filterRoundDown(String floatingString, int roundDownLocation) {
		return floatingString.substring(0, roundDownLocation);
	}
	
	private String filterRoundUp(String floatingString, int roundUpLocation) {
		floatingString = filterRoundDown(floatingString, roundUpLocation);
		floatingString = incrementLastDecimal(floatingString);
		return floatingString;
	}
	
	private String incrementLastDecimal(String floatingString) {
		String lastDecimal = floatingString.substring(floatingString.length() - 1);
		int lastDecimalInt = Integer.valueOf(lastDecimal);
		
		lastDecimalInt++;
		lastDecimal = String.valueOf(lastDecimalInt);
		
		floatingString = floatingString.substring(0, floatingString.length() - 1);
		floatingString += lastDecimal;
		
		return floatingString;
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
	
	private ArrayList<Float> lowerClassLimit(float lowestVal, int numClasses, 
											 float width, int mostDecimals) 
	{
		ArrayList<Float> lowerClassLimitList = new ArrayList<Float>();
		
		for(int i = 0; i < numClasses; i++) {
			lowerClassLimitList.add(filterInaccuracy(lowestVal + (width * i), mostDecimals));
		}
		
		return lowerClassLimitList;
	}
	
	private ArrayList<Float> upperClassLimit(float lowestVal, int numClasses, 
			float width, int mostDecimals) 
	{
		ArrayList<Float> upperClassLimitList = new ArrayList<Float>();
		
		float subtrahendCoefficient = coefficient(mostDecimals);
		for(int i = 0; i < numClasses; i++) {
			upperClassLimitList.add(filterInaccuracy(
					(lowestVal + (width * (i + 1))) - subtrahendCoefficient, mostDecimals));
		}
		
		return upperClassLimitList;
	}
	
	private ArrayList<String> classLimit(ArrayList<Float> lowerClassLimitList,
			ArrayList<Float> upperClassLimitList) 
	{
		
		ArrayList<String> classLimitList = new ArrayList<String>();
		int numClasses = lowerClassLimitList.size();
		
		for(int i = 0; i < numClasses; i++) {
			String classLimit = lowerClassLimitList.get(i) + " - " + upperClassLimitList.get(i);
			classLimitList.add(classLimit);
		}
		
		return classLimitList;
	}
	
	private ArrayList<String> trueClassLimit(ArrayList<Float> lowerClassLimitList, 
			ArrayList<Float> upperClassLimitList, int mostDecimals) 
	{
		ArrayList<String> trueClassLimitList = new ArrayList<String>();
		int numClasses = lowerClassLimitList.size();
		float trueClassLimitCoefficient = trueClassLimitCoefficient(mostDecimals);
		
		for(int i = 0; i < numClasses; i++) {
			float trueLowerClassLimit = filterInaccuracy(lowerClassLimitList.get(i) - 
					trueClassLimitCoefficient, mostDecimals + 1);
			float trueUpperClassLimit = filterInaccuracy(upperClassLimitList.get(i) + 
					trueClassLimitCoefficient, mostDecimals + 1);
			
			String trueClassLimit = trueLowerClassLimit + " - " +trueUpperClassLimit;
			trueClassLimitList.add(trueClassLimit);
		}
		
		return trueClassLimitList;
	}
	
	private float trueClassLimitCoefficient(int mostDecimals) {
		float trueClassLimitCoefficient = 0.5f;
		
		for(int i = 0; i < mostDecimals; i++) {
			trueClassLimitCoefficient /= 10;
		}
		
		return trueClassLimitCoefficient;
	}
	
	private ArrayList<String> midpoint(ArrayList<Float> lowerClassLimitList,
			ArrayList<Float> upperClassLimitList, float width, int mostDecimals) 
	{
		ArrayList<String> midpointList = new ArrayList<String>();
		int numClasses = lowerClassLimitList.size();
		float lowerUpperDiff = upperClassLimitList.get(0) - lowerClassLimitList.get(0);
		float firstMidpoint = (lowerUpperDiff / 2) + lowerClassLimitList.get(0);
		
		for(int i = 0 ; i < numClasses; i++) {
			float midpoint = filterInaccuracy(firstMidpoint + (width * i), mostDecimals + 1);
			String midpointString = String.valueOf(midpoint);
			midpointList.add(midpointString);
		}
		
		return midpointList;
	}
	
	private ArrayList<Integer> frequency(ArrayList<Float> sampleDataFloat, 
			ArrayList<Float> lowerClassLimitList, ArrayList<Float> upperClassLimitList) 
	{
		int size = lowerClassLimitList.size();
		ArrayList<Integer> frequencyList = initFrequencyList(size);
		
		int classRow = 0;
		for(int i = 0; i < sampleDataFloat.size() && classRow < lowerClassLimitList.size(); i++) {
			float data = sampleDataFloat.get(i);
			float lowerClassLimit = lowerClassLimitList.get(classRow);
			float upperClassLimit = upperClassLimitList.get(classRow);
			
			if(data >= lowerClassLimit && data <= upperClassLimit) {
				frequencyList.set(classRow, frequencyList.get(classRow) + 1);
			} else {
				i--;
				classRow++;
			}
		}
		
		return frequencyList;
	}
	
	private ArrayList<Integer> initFrequencyList(int size) {
		ArrayList<Integer> frequencyList = new ArrayList<Integer>(size);
		
		for(int i = 0; i < size; i++) {
			frequencyList.add(0);
		}
		
		return frequencyList;
	}
	
	private ArrayList<Float> percentage(ArrayList<Integer> frequencyList) {
		ArrayList<Float> percentageList = new ArrayList<Float>();
		int total = sumFrequencies(frequencyList);
		
		for(Integer frequency : frequencyList) {
			float percentage = (frequency / (float) total) * 100;
			percentageList.add(percentage);
		}
		
		return percentageList;
	}
	
	private int sumFrequencies(ArrayList<Integer> frequencyList) {
		int total = 0;
		
		for(Integer frequency : frequencyList) {
			total += frequency;
		}
		
		return total;
	}
	
	private ArrayList<Integer> cumulativeFrequency(ArrayList<Integer> frequencyList) {
		ArrayList<Integer> cumulativeFrequencyList = new ArrayList<Integer>();
		
		int cumulativeFrequency = 0;
		for(Integer frequency : frequencyList) {
			cumulativeFrequency += frequency;
			cumulativeFrequencyList.add(cumulativeFrequency);
		}
		
		return cumulativeFrequencyList;
	}
	
	private ArrayList<Float> cumulativePercentage(ArrayList<Float> percentageList) {
		ArrayList<Float> cumulativePercentageList = new ArrayList<Float>();
		
		float cumulativePercentage = 0;
		for(Float percentage : percentageList) {
			cumulativePercentage += percentage;
			cumulativePercentageList.add(cumulativePercentage);
		}
		
		return cumulativePercentageList;
	}
	
	private void populateTable(ArrayList<String> classLimits, ArrayList<String> trueClassLimits,
			ArrayList<String> midpoints, ArrayList<Integer> frequencies,
			ArrayList<Float> percentages, ArrayList<Integer> cumulativeFrequencies, 
			ArrayList<Float> cumulativePercentages) 
	{
		for(int i = 0; i < classLimits.size(); i++) {
			numericalData.getItems().add(new NumericalData(classLimits.get(i), 
					trueClassLimits.get(i), midpoints.get(i), frequencies.get(i), 
					percentages.get(i), cumulativeFrequencies.get(i), 
					cumulativePercentages.get(i)));
		}
	}
	
	private void initCellValues() {
		classLimit.setCellValueFactory(new PropertyValueFactory
				<NumericalData, String>("classLimit"));
		trueClassLimit.setCellValueFactory(new PropertyValueFactory
				<NumericalData, String>("trueClassLimit"));
		midpoint.setCellValueFactory(new PropertyValueFactory
				<NumericalData, String>("midpoint"));
		frequency.setCellValueFactory(new PropertyValueFactory
				<NumericalData, Integer>("frequency"));
		percentage.setCellValueFactory(new PropertyValueFactory
				<NumericalData, Float>("percentage"));
		cumulativeFrequency.setCellValueFactory(new PropertyValueFactory
				<NumericalData, Integer>("cumulativeFrequency"));
		cumulativePercentage.setCellValueFactory(new PropertyValueFactory
				<NumericalData, Float>("cumulativePercentage"));
	}
}
