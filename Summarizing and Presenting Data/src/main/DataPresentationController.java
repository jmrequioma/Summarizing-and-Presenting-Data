package main;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DataPresentationController {

	@FXML Label lblTitle;
	
	public void setTitle(String title) {
		lblTitle.setText(title);
	}
}
