package com.trifle.fingerfing.client.widget_old.newage;

public interface ProgressBar {

	public abstract int getValue();

	public abstract void setValue(int value);
	
	public abstract void addValueSegment(int startSegment, int endSegment, String colorON, String colorOFF);

	public abstract void setRangeEndValue(int rangeEndValue);

	public abstract int getRangeEndValue();

	public abstract void setRangeStartValue(int rangeStartValue);

	public abstract int getRangeStartValue();

	public abstract String getTitle();

	public abstract void setTitle(String title);
	
}