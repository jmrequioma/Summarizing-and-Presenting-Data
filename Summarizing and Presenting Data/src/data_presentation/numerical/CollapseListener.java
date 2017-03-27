package data_presentation.numerical;

import com.google.common.eventbus.Subscribe;

public class CollapseListener {

	private CbCollapse checkBox;
	
	public CollapseListener(CbCollapse checkBox) {
		this.checkBox = checkBox;
	}
	
	@Subscribe
	public void listen(CollapseEvent event) {
		boolean selected = event.isSelected();
		
		if(selected) {
			checkBox.fireCollapseEvent();
		} else {
			checkBox.fireRecoverEvent();
		}
	}
}
