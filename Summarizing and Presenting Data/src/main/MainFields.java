package main;

import java.util.ArrayList;

import com.google.common.eventbus.EventBus;

import data_presentation.numerical.CollapseListener;

public class MainFields {
	private static EventBus eventBus;
	private static CollapseListener collapseListener;
	private static String type;
	private static String title;
	private static boolean valid;
	private static boolean stringExists;
	private static boolean floatExists;
	private static ArrayList<String> sampleDataString;
	private static ArrayList<String> fusedData;
	private static ArrayList<Integer> dataCount;
	private static ArrayList<Float> dataPercentage;
	private static ArrayList<Float> sampleDataFloat;
	private static ArrayList<String> classLimits;
	private static ArrayList<String> trueClassLimits;
	private static ArrayList<String> midpoints;
	private static ArrayList<Integer> frequencies;
	private static ArrayList<Float> percentages;
	
	public static EventBus getEventBus() {
		return eventBus;
	}

	public static void setEventBus(EventBus eventBus) {
		MainFields.eventBus = eventBus;
	}

	public static CollapseListener getCollapseListener() {
		return collapseListener;
	}

	public static void setCollapseListener(CollapseListener collapseListener) {
		MainFields.collapseListener = collapseListener;
	}

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
	
	public static boolean getValid() {
		return valid;
	}
	
	public static void setValid(boolean valid) {
		MainFields.valid = valid;
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

	public static ArrayList<String> getClassLimits() {
		return classLimits;
	}

	public static void setClassLimits(ArrayList<String> classLimits) {
		MainFields.classLimits = classLimits;
	}

	public static ArrayList<String> getTrueClassLimits() {
		return trueClassLimits;
	}

	public static void setTrueClassLimits(ArrayList<String> trueClassLimits) {
		MainFields.trueClassLimits = trueClassLimits;
	}

	public static ArrayList<String> getMidpoints() {
		return midpoints;
	}

	public static void setMidpoints(ArrayList<String> midpoints) {
		MainFields.midpoints = midpoints;
	}

	public static ArrayList<Integer> getFrequencies() {
		return frequencies;
	}

	public static void setFrequencies(ArrayList<Integer> frequencies) {
		MainFields.frequencies = frequencies;
	}

	public static ArrayList<Float> getPercentages() {
		return percentages;
	}

	public static void setPercentages(ArrayList<Float> percentages) {
		MainFields.percentages = percentages;
	}

	public static boolean getStringExists() {
		return stringExists;
	}

	public static void setStringExists(boolean stringExists) {
		MainFields.stringExists = stringExists;
	}

	public static boolean getFloatExists() {
		return floatExists;
	}

	public static void setFloatExists(boolean floatExists) {
		MainFields.floatExists = floatExists;
	}
	
	public static void reset() {
		eventBus.unregister(collapseListener);
		type = "";
		title = "";
		valid = false;
		sampleDataString.clear();
		fusedData.clear();
		dataCount.clear();
		dataPercentage.clear();
		sampleDataFloat.clear();
		classLimits.clear();
		trueClassLimits.clear();
		midpoints.clear();
		frequencies.clear();
		percentages.clear();
	}
}
