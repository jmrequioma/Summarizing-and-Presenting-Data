package main;

public class CategoricalData {
	
	private String valueLabels;
	private float percentage;
	
	public CategoricalData(String valueLabels, float percentage) {
		this.valueLabels = valueLabels;
		this.percentage = percentage;
	}
	
	public String getValueLabels() {
		return valueLabels;
	}
	
	public void setValueLabels(String valueLabels) {
		this.valueLabels = valueLabels;
	}
	
	public float getPercentage() {
		return percentage;
	}
	
	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}
}
