package data_presentation.numerical;

public class ClickEvent {
	
	private CbCollapse source;
	
	public ClickEvent(CbCollapse source) {
		this.source = source;
	}
	
	public CbCollapse getSource() {
		return source;
	}
}
