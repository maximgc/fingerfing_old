package com.trifle.fingerfing.client.widget.newage;

public interface ProgressBar {

	public abstract void setSegmentSizePx(int width, int height);

	public abstract void setSegmentWidthPx(int width);

	public abstract void setSegmentHeightPx(int height);

	public abstract int getSegmentWidthPx();

	public abstract int getSegmentHeightPx();

	public abstract double getValue();

	public abstract void setValue(double value);

	public abstract int getSegmentCount();

	public abstract void setSegmentCount(int n);

	public abstract String getTopLabelText();

	public abstract void setTopLabelText(String text);

}