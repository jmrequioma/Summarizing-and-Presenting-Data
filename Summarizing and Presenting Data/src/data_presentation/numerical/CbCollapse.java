package data_presentation.numerical;

import java.util.HashSet;
import java.util.Set;

public class CbCollapse {
	
	private Set<ClickListener> listeners;
	private String details;
	
	public CbCollapse(String details) {
		listeners = new HashSet<ClickListener>();
		this.details = details;
	}
	
	public String getDetails() {
		return details;
	}
	
	public synchronized void addClickListener(ClickListener listener) {
		listeners.add(listener);
	}
	
	public synchronized void removeClickListener(ClickListener listener) {
		listeners.remove(listener);
	}
	
	protected synchronized void fireCollapseEvent() {
		ClickEvent e = new ClickEvent(this);
		for(ClickListener listener : listeners) {
			listener.collapse(e);
		}
	}
	
	protected synchronized void fireRecoverEvent() {
		ClickEvent e = new ClickEvent(this);
		for(ClickListener listener : listeners) {
			listener.recover(e);
		}
	}
}
