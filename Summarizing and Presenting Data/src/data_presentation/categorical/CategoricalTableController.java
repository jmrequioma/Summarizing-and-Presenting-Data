package data_presentation.categorical;

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

public class CategoricalTableController implements Initializable {

	
	@FXML private TableView<CategoricalData> categoricalData;
	@FXML private TableColumn<CategoricalData, String> valueLabels;
	@FXML private TableColumn<CategoricalData, Float> percentage;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ArrayList<String> sampleData = MainFields.getSampleDataString();		
		Collections.sort(sampleData);
		
		ArrayList<Integer> dataCount = countData(sampleData);
		MainFields.setDataCount(dataCount);
		
		ArrayList<Float> dataPercentage = convertPercentage(dataCount);
		MainFields.setDataPercentage(dataPercentage);
		
		sampleData = fuseData(sampleData);
		MainFields.setFusedData(sampleData);
		
		presentData(sampleData, dataPercentage);
		
		valueLabels.setCellValueFactory(new PropertyValueFactory
				<CategoricalData, String>("valueLabels"));
		percentage.setCellValueFactory(new PropertyValueFactory
				<CategoricalData, Float>("percentage"));
	}
	
	private ArrayList<Integer> countData(ArrayList<String> sampleData) {
		ArrayList<Integer> dataCount = new ArrayList<Integer>();
		
		String prevData = sampleData.get(0);
		int count = 1;
		
		for(int i = 1; i < sampleData.size(); i++) {
			String currData = sampleData.get(i);
			
			if(prevData.equals(currData)) {
				count++;
			} else {
				prevData = currData;
				dataCount.add(count);
				count = 1;
			}
		}
		dataCount.add(count);
		
		return dataCount;
	}
	
	// debugging purposes
	private void printDataCount(ArrayList<Integer> dataCount) {
		for(Integer count : dataCount) {
			System.out.println(count);
		}
	}
	
	private ArrayList<Float> convertPercentage(ArrayList<Integer> dataCount) {
		int total = countTotal(dataCount);
		ArrayList<Float> dataPercentage = new ArrayList<Float>();
		
		for(int i = 0; i < dataCount.size(); i++) {
			int count = dataCount.get(i);
			float percentage =  (count / (float) total) * 100;
			dataPercentage.add(percentage);
		}
		
		return dataPercentage;
	}
	
	private int countTotal(ArrayList<Integer> dataCount) {
		int total = 0;
		for(Integer count : dataCount) {
			total += count;
		}
		return total;
	}
	
	// debugging purposes
	private void printDataPercentage(ArrayList<Float> dataPercentage) {
		for(Float percentage: dataPercentage) {
			System.out.println(percentage);
		}
	}
	// debugging purposes
	private void printData(ArrayList<String> sampleData) {
		for(String data : sampleData) {
			System.out.println(data);
		}
	}
	
	private ArrayList<String> fuseData(ArrayList<String> sampleData) {
		ArrayList<String> fusedData = new ArrayList<String>();
		fusedData.add(sampleData.get(0));
		
		String prevData = sampleData.get(0);
		for(int i = 1; i < sampleData.size(); i++) {
			String currData = sampleData.get(i);
			
			if(prevData.equals(currData)) {
				// do nothing
			} else {
				fusedData.add(currData);
				prevData = currData;
			}
		}
		
		return fusedData;
	}
	
	private void presentData(ArrayList<String> sampleData, ArrayList<Float> dataPercentage) {
		for(int i = 0; i < sampleData.size(); i++) {
			categoricalData.getItems().add(
					new CategoricalData(sampleData.get(i), dataPercentage.get(i)));
		}
	}
}
