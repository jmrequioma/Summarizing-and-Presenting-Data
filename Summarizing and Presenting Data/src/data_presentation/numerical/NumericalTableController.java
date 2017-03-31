package data_presentation.numerical;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import com.google.common.eventbus.EventBus;

import javafx.collections.ObservableList;
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
	@FXML private TableColumn<NumericalData, String> frequency;
	@FXML private TableColumn<NumericalData, String> percentage;
	@FXML private TableColumn<NumericalData, String> cumulativeFrequency;
	@FXML private TableColumn<NumericalData, String> cumulativePercentage;	
	
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
		ArrayList<String> trueClassLimitList = trueClassLimit(lowerClassLimitList, 
				upperClassLimitList, mostDecimals);
		
		ArrayList<String> midpointList = midpoint(lowerClassLimitList, upperClassLimitList, 
				width, mostDecimals);
		
		ArrayList<Integer> frequencyList = frequency(sampleDataFloat, lowerClassLimitList, 
				upperClassLimitList);
		ArrayList<String> frequencyListString = convertIntToString(frequencyList);
		
		ArrayList<Float> percentageList = percentage(frequencyList);
		ArrayList<String> percentageListString = convertFloatToString(percentageList);
		
		ArrayList<String> cumulativeFrequencyList = cumulativeFrequency(frequencyList);
		
		ArrayList<String> cumulativePercentageList = cumulativePercentage(percentageList);
		
		populateTable(classLimitList, trueClassLimitList, midpointList, frequencyListString, 
					  percentageListString, cumulativeFrequencyList, cumulativePercentageList);
		
		MainFields.setMidpoints(midpointList);
		MainFields.setFrequencies(frequencyList);
		MainFields.setPercentages(percentageList);
		
		initCellValues();
		initCollapse();
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
	
	private ArrayList<String> convertIntToString(ArrayList<Integer> integerList) {
		ArrayList<String> stringList = new ArrayList<String>();
		
		for(Integer item : integerList) {
			stringList.add(String.valueOf(item));
		}
		
		return stringList;
	}
	
	private ArrayList<String> convertFloatToString(ArrayList<Float> floatList) {
		ArrayList<String> stringList = new ArrayList<String>();
		
		for(Float item : floatList) {
			stringList.add(String.valueOf(item));
		}
		
		return stringList;
	}
	
	private ArrayList<Float> percentage(ArrayList<Integer> frequencyList) {
		ArrayList<Float> percentageList = new ArrayList<Float>();
		int total = sumFrequencies(frequencyList);
		int mostDecimals = 2;
		
		for(Integer frequency : frequencyList) {
			float percentage = (frequency / (float) total) * 100;
			percentageList.add(filterInaccuracy(percentage, mostDecimals));
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
	
	private ArrayList<String> cumulativeFrequency(ArrayList<Integer> frequencyList) {
		ArrayList<String> cumulativeFrequencyList = new ArrayList<String>();
		
		int cumulativeFrequency = 0;
		for(Integer frequency : frequencyList) {
			cumulativeFrequency += frequency;
			cumulativeFrequencyList.add(String.valueOf(cumulativeFrequency));
		}
		
		return cumulativeFrequencyList;
	}
	
	private ArrayList<String> cumulativePercentage(ArrayList<Float> percentageList) {
		ArrayList<String> cumulativePercentageList = new ArrayList<String>();
		int decimals = 2;
		
		float cumulativePercentage = 0;
		for(Float percentage : percentageList) {
			cumulativePercentage += percentage;
			cumulativePercentage = filterInaccuracy(cumulativePercentage, decimals);
			cumulativePercentageList.add(String.valueOf(cumulativePercentage));
		}
		
		return cumulativePercentageList;
	}
	
	private void populateTable(ArrayList<String> classLimits, ArrayList<String> trueClassLimits,
			ArrayList<String> midpoints, ArrayList<String> frequencies,
			ArrayList<String> percentages, ArrayList<String> cumulativeFrequencies, 
			ArrayList<String> cumulativePercentages) 
	{
		for(int i = 0; i < classLimits.size(); i++) {
			numericalData.getItems().add(new NumericalData(classLimits.get(i), 
					trueClassLimits.get(i), midpoints.get(i), frequencies.get(i), 
					percentages.get(i) + "%", cumulativeFrequencies.get(i), 
					cumulativePercentages.get(i) + "%"));
		}
		
		String totalFrequency = cumulativeFrequencies.get(cumulativeFrequencies.size() - 1);
		String totalPercentage = cumulativePercentages.get(cumulativePercentages.size() - 1);
		
		numericalData.getItems().add(new NumericalData("", "", "", "n = " + totalFrequency,
				totalPercentage + "%", "", ""));
	}
	
	private void initCellValues() {
		classLimit.setCellValueFactory(new PropertyValueFactory
				<NumericalData, String>("classLimit"));
		trueClassLimit.setCellValueFactory(new PropertyValueFactory
				<NumericalData, String>("trueClassLimit"));
		midpoint.setCellValueFactory(new PropertyValueFactory
				<NumericalData, String>("midpoint"));
		frequency.setCellValueFactory(new PropertyValueFactory
				<NumericalData, String>("frequency"));
		percentage.setCellValueFactory(new PropertyValueFactory
				<NumericalData, String>("percentage"));
		cumulativeFrequency.setCellValueFactory(new PropertyValueFactory
				<NumericalData, String>("cumulativeFrequency"));
		cumulativePercentage.setCellValueFactory(new PropertyValueFactory
				<NumericalData, String>("cumulativePercentage"));
	}
	
	private void initCollapse() {
		CbCollapse cbCollapseFirst = new CbCollapse("Collapse first");
		CbCollapse cbCollapseLast = new CbCollapse("Collapse last");
		CollapseListener collapseListener = new CollapseListener(
				cbCollapseFirst, cbCollapseLast);
		EventBus eventBus = MainFields.getEventBus();
		eventBus.register(collapseListener);
		MainFields.setCollapseListenerFirst(collapseListener);
		
		initCheckBoxListeners(cbCollapseFirst, cbCollapseLast);
		
	}
	
	private void initCheckBoxListeners(CbCollapse cbCollapseFirst, CbCollapse cbCollapseLast) {
		cbCollapseFirst.addClickListener(new ClickListener() {

			@Override
			public void collapse(ClickEvent e) {
				collapseRow(0);

			}

			@Override
			public void recover(ClickEvent e) {
				System.out.println("Recover");
				
			}
		});
		
		cbCollapseLast.addClickListener(new ClickListener() {

			@Override
			public void collapse(ClickEvent e) {
				int rowPosition = numericalData.getItems().size() - 2;
				collapseRow(rowPosition);
			}

			@Override
			public void recover(ClickEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void collapseRow(int rowPosition) {
		ObservableList<NumericalData> numericalDataList = numericalData.getItems();
		NumericalData row = numericalDataList.get(rowPosition);
		openEnd(row, rowPosition);
		
		ArrayList<NumericalData> copyList = new ArrayList<NumericalData>();
		copy(copyList, numericalDataList);
		numericalDataList.removeAll(numericalDataList);
		copy(numericalDataList, copyList);
	}
	
	private void openEnd(NumericalData row, int rowPosition) {
		String classLimit = row.getClassLimit();
		String midPoint = row.getMidpoint();
		String position = position(rowPosition);

		classLimit = openEndClassLimit(classLimit, position);
		midPoint = "-";

		row.setClassLimit(classLimit);
		row.setMidpoint(midPoint);
	}
	
	private String position(int rowPosition) {
		String position = "";
		
		if(rowPosition == 0) {
			position = "first";
		} else {
			position = "last";
		}
		
		return position;
	}
	
	private String openEndClassLimit(String classLimit, String position) {
		String classLimitCopy = classLimit;
		int indexOfDash = classLimit.indexOf('-');
		
		if(position.equals("first")) {
			String secondHalf = classLimit.substring(indexOfDash + 1, classLimit.length());
			classLimitCopy = "<" + secondHalf;
		} else {
			String secondHalf = classLimit.substring(0, indexOfDash - 1);
			classLimitCopy = "> " + secondHalf; 
		}
		
		return classLimitCopy;
	}
	
	private void copy(List<NumericalData> destination, 
			List<NumericalData> source) 
	{
		for(NumericalData data : source) {
			destination.add(data);
		}
	}
}
