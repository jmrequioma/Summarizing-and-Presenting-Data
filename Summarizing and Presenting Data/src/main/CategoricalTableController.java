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

public class CategoricalTableController implements Initializable {

	
	@FXML TableView<CategoricalData> categoricalData;
	@FXML TableColumn<CategoricalData, String> valueLabels;
	@FXML TableColumn<CategoricalData, Integer> percentage;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ArrayList<String> sampleData = MainFields.getSampleData();		
		Collections.sort(sampleData);
		
		ArrayList<Integer> dataCount = countData(sampleData);
		fuseData(sampleData);
		presentData(sampleData, dataCount);
		
		categoricalData.getItems().add(
				new CategoricalData("a", 1));
		
		valueLabels.setCellValueFactory(new PropertyValueFactory
				<CategoricalData, String>("valueLabels"));
		percentage.setCellValueFactory(new PropertyValueFactory
				<CategoricalData, Integer>("percentage"));
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
	
	private void fuseData(ArrayList<String> sampleData) {
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
		
		sampleData = fusedData;
	}
	
	private void presentData(ArrayList<String> sampleData, ArrayList<Integer> dataCount) {
		System.out.println(sampleData.get(0));
		for(int i = 0; i < sampleData.size() - 1; i++) {
			categoricalData.getItems().add(
					new CategoricalData(sampleData.get(i), 1));
		}
	}
}
