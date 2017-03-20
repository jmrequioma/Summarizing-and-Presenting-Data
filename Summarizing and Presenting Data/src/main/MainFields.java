package main;

import java.util.ArrayList;

public class MainFields {
	private static String type;
	private static String title;
	private static ArrayList<String> sampleData;
	
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
}
