package data_presentation.numerical;

public class NumericalData {
	String classLimit;
	String trueClassLimit;
	String midpoint;
	String frequency;
	String percentage;
	String cumulativeFrequency;
	String cumulativePercentage;
	
	public NumericalData(String classLimit, String trueClassLimit, String midpoint, 
			String frequency, String percentage, String cumulativeFrequency, 
			String cumulativePercentage) 
	{
		this.classLimit = classLimit;
		this.trueClassLimit = trueClassLimit;
		this.midpoint = midpoint;
		this.frequency = frequency;
		this.percentage = percentage;
		this.cumulativeFrequency = cumulativeFrequency;
		this.cumulativePercentage = cumulativePercentage;
	}

	public String getClassLimit() {
		return classLimit;
	}
	
	public void setClassLimit(String classLimit) {
		this.classLimit = classLimit;
	}
	
	public String getTrueClassLimit() {
		return trueClassLimit;
	}
	
	public void setTrueClassLimit(String trueClassLimit) {
		this.trueClassLimit = trueClassLimit;
	}
	
	public String getMidpoint() {
		return midpoint;
	}
	
	public void setMidpoint(String midpoint) {
		this.midpoint = midpoint;
	}
	
	public String getFrequency() {
		return frequency;
	}
	
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	
	public String getPercentage() {
		return percentage;
	}
	
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	
	public String getCumulativeFrequency() {
		return cumulativeFrequency;
	}
	
	public void setCumulativeFrequency(String cumulativeFrequency) {
		this.cumulativeFrequency = cumulativeFrequency;
	}
	
	public String getCumulativePercentage() {
		return cumulativePercentage;
	}
	
	public void setCumulativePercentage(String cumulativePercentage) {
		this.cumulativePercentage = cumulativePercentage;
	}	
}
