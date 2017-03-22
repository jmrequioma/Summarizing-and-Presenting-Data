package main;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SampleDataController {
	
	@FXML private TextField txtFSampleData;
	@FXML private TextArea txtASampleData;
	@FXML private Button btnAddItem;
	@FXML private Button btnDeleteSampleData;
	@FXML private Button btnClearSampleData;
	@FXML private Button btnConfirmSampleData;
	private ArrayList<String> sampleDatasString = new ArrayList<String>();   // this container is supposed to be dynamic, i was thinking of using the Data class like before
	private ArrayList<Float> sampleDatasFloat = new ArrayList<Float>();
	private int ctr;
	
	@FXML
	private void addItemClick(ActionEvent event) throws IOException {
		String type = MainFields.getType();
		String input = txtFSampleData.getText();
		
		if(type.equals("Categorical")) {
			txtASampleData.appendText(input + " ");
			sampleDatasString.add(input);
			//System.out.println(sampleDatas.get(ctr++));   // testing purposes
		} else { // type.equals("Numerical")
			float inputFloat = Float.valueOf(input);
			txtASampleData.appendText(inputFloat + " ");
			sampleDatasFloat.add(inputFloat);
		}
		
		txtFSampleData.clear();
		ctr++;
	}
	
	@FXML
	private void deleteSampleDataClick(ActionEvent event) throws IOException {
		String type = MainFields.getType();
		
		if (ctr > 0) {
			if(type.equals("Categorical")) {
				updateTxtACategorical();
			} else { // type.equals("Numerical")
				updateTxtANumerical();
			}
			ctr--;
		}
	}
	
	private void updateTxtACategorical() {
		System.out.println(sampleDatasString.remove(sampleDatasString.size() - 1));
		txtASampleData.clear();
		for (int i = 0; i < sampleDatasString.size(); i++) {
			txtASampleData.appendText(sampleDatasString.get(i) + " ");
		}
	}
	
	private void updateTxtANumerical() {
		System.out.println(sampleDatasFloat.remove(sampleDatasFloat.size() - 1));
		txtASampleData.clear();
		for (int i = 0; i < sampleDatasFloat.size(); i++) {
			txtASampleData.appendText(sampleDatasFloat.get(i) + " ");
		}
	}
	
	@FXML
	private void clearSampleDataClick(ActionEvent event) throws IOException {
		String type = MainFields.getType();
		
		if(type.equals("Categorical")) {
			sampleDatasString.clear();
		} else { // type.equals("Numerical")
			sampleDatasFloat.clear();
		}
		
		txtASampleData.clear();
		ctr = 0;
	}
	
	@FXML
	private void confirmSampleDataClick(ActionEvent event) {
		Stage stage = (Stage) btnConfirmSampleData.getScene().getWindow();
		stage.close();
		
		String type = MainFields.getType();
		if(type.equals("Categorical")) {
			MainFields.setSampleDataString(sampleDatasString);
		} else { // type.equals("Numerical")
			MainFields.setSampleDataFloat(sampleDatasFloat);
		}
	}
}
