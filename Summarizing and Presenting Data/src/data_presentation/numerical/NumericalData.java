package data_presentation.numerical;

public class NumericalData {
	String classLimit;
	String trueClassLimit;
	float midpoint;
	int frequency;
	float percentage;
	int cumulativeFrequency;
	float cumulativePercentage;
	
	public NumericalData(String classLimit, String trueClassLimit, float midpoint, 
			int frequency, float percentage, int cumulativeFrequency, float cumulativePercentage) 
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
	
	public float getMidpoint() {
		return midpoint;
	}
	
	public void setMidpoint(float midpoint) {
		this.midpoint = midpoint;
	}
	
	public int getFrequency() {
		return frequency;
	}
	
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	public float getPercentage() {
		return percentage;
	}
	
	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}
	
	public int getCumulativeFrequency() {
		return cumulativeFrequency;
	}
	
	public void setCumulativeFrequency(int cumulativeFrequency) {
		this.cumulativeFrequency = cumulativeFrequency;
	}
	
	public float getCumulativePercentage() {
		return cumulativePercentage;
	}
	
	public void setCumulativePercentage(float cumulativePercentage) {
		this.cumulativePercentage = cumulativePercentage;
	}
	
	
}
