package main;

public class CategoricalData {
	
	String valueLabels;
	int percentage;
	
	public CategoricalData(String valueLabels, int percentage) {
		this.valueLabels = valueLabels;
		this.percentage = percentage;
	}
	
	public String getValueLabels() {
		return valueLabels;
	}
	
	public void setValueLabels(String valueLabels) {
		this.valueLabels = valueLabels;
	}
	
	public int getPercentage() {
		return percentage;
	}
	
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
}
