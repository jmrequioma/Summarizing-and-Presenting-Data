package main;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class InputTitleController {
	
	@FXML
	private TextField txtFieldInputTitle;
	@FXML
	private Button btnConfirm;
	@FXML
	private Button btnBack;
	
	@FXML
	private void buttonClick(ActionEvent event) throws IOException {
		Button src = (Button) event.getSource();
		System.out.println("hello");
		if (src == btnConfirm) {
			System.out.println("inside");
		}
	}
}
