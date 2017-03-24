package sample_data;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import main.MainFields;

public class SampleDataController implements Initializable {
	
	@FXML private TextField txtFSampleData;
	@FXML private TextArea txtASampleData;
	@FXML private Button btnAddItem;
	@FXML private Button btnDeleteSampleData;
	@FXML private Button btnClearSampleData;
	@FXML private Button btnConfirmSampleData;
	private ArrayList<String> sampleDatasString = new ArrayList<String>();   // this container is supposed to be dynamic, i was thinking of using the Data class like before
	private ArrayList<Float> sampleDatasFloat = new ArrayList<Float>();
	private int ctr;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (MainFields.getType().equals("Categorical")) {
			if (MainFields.getStringExists()) {
				for (int i = 0; i < MainFields.getSampleDataString().size(); i++) {
					txtASampleData.appendText(MainFields.getSampleDataString().get(i) + " ");
					sampleDatasString.add(MainFields.getSampleDataString().get(i));
				}
				MainFields.setSampleDataString(sampleDatasString);
				ctr = MainFields.getSampleDataString().size();
			} else {
				System.out.println("empty");
			}
		} else {
			if (MainFields.getFloatExists()) {
				for (int i = 0; i < MainFields.getSampleDataFloat().size(); i++) {
					txtASampleData.appendText(MainFields.getSampleDataFloat().get(i) + " ");
					sampleDatasFloat.add(MainFields.getSampleDataFloat().get(i));
				}
				ctr = MainFields.getSampleDataFloat().size();
			} else {
				System.out.println("empty");
			}
		}
	}
	
	@FXML
	private void addItemClick(ActionEvent event) throws IOException {
		String type = MainFields.getType();
		String input = txtFSampleData.getText();
		if (input.equals("") || input.startsWith(" ")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Critical Error");
			alert.setHeaderText("Invalid Input!!!");
			alert.setContentText("Ooops, input is not allowed! Please change the input.");
			alert.showAndWait();
		} else {
			if(type.equals("Categorical")) {
				txtASampleData.appendText(input + " ");
				sampleDatasString.add(input);
				//System.out.println(sampleDatas.get(ctr++));   // testing purposes
			} else { // type.equals("Numerical")
				try {
					float inputFloat = Float.valueOf(input);
					txtASampleData.appendText(inputFloat + " ");
					sampleDatasFloat.add(inputFloat);
				} catch ( java.lang.RuntimeException re) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Critical Error");
					alert.setHeaderText("Invalid Input!!!");
					alert.setContentText("Ooops, input is not allowed! Please change the input.");
					alert.showAndWait();
				}
			}	
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
		if (MainFields.getStringExists()) {
			System.out.println(MainFields.getSampleDataString().remove(MainFields.getSampleDataString().size() - 1));
			txtASampleData.clear();
			for (int i = 0; i < MainFields.getSampleDataString().size(); i++) {
				txtASampleData.appendText(MainFields.getSampleDataString().get(i) + " ");
			}
		} else {
			System.out.println(sampleDatasString.remove(sampleDatasString.size() - 1));
			txtASampleData.clear();
			for (int i = 0; i < sampleDatasString.size(); i++) {
				txtASampleData.appendText(sampleDatasString.get(i) + " ");
			}
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
			MainFields.setStringExists(true);
		} else { // type.equals("Numerical")
			MainFields.setSampleDataFloat(sampleDatasFloat);
			MainFields.setFloatExists(true);
		}
		MainFields.setValid(true);
	}
}
