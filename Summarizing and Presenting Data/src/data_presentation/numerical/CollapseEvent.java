package data_presentation.numerical;

public class CollapseEvent {
	
	private boolean selected;
	
	public CollapseEvent(boolean selected) {
		this.selected = selected;
	}
	
	public boolean isSelected() {
		return selected;
	}
}
