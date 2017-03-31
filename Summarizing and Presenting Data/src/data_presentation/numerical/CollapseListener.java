package data_presentation.numerical;

import com.google.common.eventbus.Subscribe;

import data_presentation.CollapseLastEvent;
import javafx.scene.control.CheckBox;

public class CollapseListener {

	private CbCollapse checkBoxFirst;
	private CbCollapse checkBoxLast;
	
	public CollapseListener(CbCollapse checkBoxFirst, CbCollapse checkBoxLast) {
		this.checkBoxFirst = checkBoxFirst;
		this.checkBoxLast = checkBoxLast;
	}
	
	@Subscribe
	public void listenFirst(CollapseFirstEvent event) {
		CheckBox src = event.getSource();
		boolean selected = src.isSelected();
		
		if(selected) {
			checkBoxFirst.fireCollapseEvent();
		} else {
			checkBoxFirst.fireRecoverEvent();
		}
	}
	
	@Subscribe
	public void listenLast(CollapseLastEvent event) {
		CheckBox src = event.getSource();
		boolean selected = src.isSelected();
		
		if(selected) {
			checkBoxLast.fireCollapseEvent();
		} else {
			checkBoxLast.fireRecoverEvent();
		}
	}
}
