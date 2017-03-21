package main;

import java.util.ArrayList;

public class MainFields {
	private static String type;
	private static String title;
	private static ArrayList<String> sampleDataString;
	private static ArrayList<String> fusedData;
	private static ArrayList<Integer> dataCount;
	private static ArrayList<Float> dataPercentage;
	private static ArrayList<Float> sampleDataFloat;
	
	public static String getType() {
		return type;
	}
	
	public static void setType(String type) {
		MainFields.type = type;
	}
	
	public static String getTitle() {
		return title;
	}
	
	public static void setTitle(String title) {
		MainFields.title = title;
	}
	
	public static ArrayList<String> getSampleDataString() {
		return sampleDataString;
	}
	
	public static void setSampleDataString(ArrayList<String> sampleDataString) {
		MainFields.sampleDataString = sampleDataString;
	}

	public static ArrayList<String> getFusedData() {
		return fusedData;
	}

	public static void setFusedData(ArrayList<String> fusedData) {
		MainFields.fusedData = fusedData;
	}

	public static ArrayList<Integer> getDataCount() {
		return dataCount;
	}

	public static void setDataCount(ArrayList<Integer> dataCount) {
		MainFields.dataCount = dataCount;
	}

	public static ArrayList<Float> getDataPercentage() {
		return dataPercentage;
	}

	public static void setDataPercentage(ArrayList<Float> dataPercentage) {
		MainFields.dataPercentage = dataPercentage;
	}

	public static ArrayList<Float> getSampleDataFloat() {
		return sampleDataFloat;
	}

	public static void setSampleDataFloat(ArrayList<Float> sampleDataFloat) {
		MainFields.sampleDataFloat = sampleDataFloat;
	}
	
	
}
