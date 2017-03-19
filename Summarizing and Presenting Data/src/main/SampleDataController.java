package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SampleDataController {
	
	@FXML TextField txtFSampleData;
	@FXML TextArea txtASampleData;
	@FXML Button btnAddItem;
	@FXML Button btnDeleteSampleData;
	@FXML Button btnClearSampleData;
	@FXML Button btnConfirmSampleData;
	ArrayList<String> sampleDatas = new ArrayList<String>();   // this container is supposed to be dynamic, i was thinking of using the Data class like before
	private String input;
	int ctr;
	
	@FXML
	private void addItemClick(ActionEvent event) throws IOException {
		input = txtFSampleData.getText();
		txtASampleData.appendText(input + " ");
		sampleDatas.add(input);
		//System.out.println(sampleDatas.get(ctr++));   // testing purposes
		txtFSampleData.clear();
		ctr++;
	}
	
	@FXML
	private void deleteSampleDataClick(ActionEvent event) throws IOException {
		if (ctr > 0) {
			System.out.println(sampleDatas.remove(sampleDatas.size() - 1));
			txtASampleData.clear();
			for (int i = 0; i < sampleDatas.size(); i++) {
				txtASampleData.appendText(sampleDatas.get(i) + " ");
			}
			ctr--;
		}
	}
	
	@FXML
	private void clearSampleDataClick(ActionEvent event) throws IOException {
		txtASampleData.clear();
		sampleDatas.clear();
		ctr = 0;
	}
}
