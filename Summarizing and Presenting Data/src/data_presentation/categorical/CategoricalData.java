package data_presentation.categorical;

public class CategoricalData {
	
	private String valueLabels;
	private String percentage;
	
	public CategoricalData(String valueLabels, String percentage) {
		this.valueLabels = valueLabels;
		this.percentage = percentage;
	}
	
	public String getValueLabels() {
		return valueLabels;
	}
	
	public void setValueLabels(String valueLabels) {
		this.valueLabels = valueLabels;
	}
	
	public String getPercentage() {
		return percentage;
	}
	
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
}
