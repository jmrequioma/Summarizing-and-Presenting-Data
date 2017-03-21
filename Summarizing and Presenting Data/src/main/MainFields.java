package main;

import java.util.ArrayList;

public class MainFields {
	private static String type;
	private static String title;
	private static ArrayList<String> sampleData;
	private static ArrayList<String> fusedData;
	private static ArrayList<Integer> dataCount;
	private static ArrayList<Float> dataPercentage;
	
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
	
	public static ArrayList<String> getSampleData() {
		return sampleData;
	}
	
	public static void setSampleData(ArrayList<String> sampleData) {
		MainFields.sampleData = sampleData;
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
	
	
}
