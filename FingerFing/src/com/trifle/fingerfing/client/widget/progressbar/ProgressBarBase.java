package com.trifle.fingerfing.client.widget.progressbar;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Widget;

public abstract class ProgressBarBase extends Composite implements ProgressBar {

	protected static ProgressBarBaseUiBinder uiBinder = GWT
			.create(ProgressBarBaseUiBinder.class);

	protected interface ProgressBarBaseUiBinder extends
			UiBinder<Widget, ProgressBarBase> {
	}

	public ProgressBarBase() {
		this(ELEMENT_DEFAULT);
	}

	public ProgressBarBase(int elementCount) {
		this(elementCount, DEFAULT_START_VALUE);
	}

	public ProgressBarBase(int elementCount, int value) {
		initWidget(uiBinder.createAndBindUi(this));
		this.elementCount = elementCount;
		this.value = value;
		initElementsView();
		initSegments();
	}

	public static final int DEFAULT_START_VALUE = 0;

	public static final int DEFAULT_END_VALUE = 50;

	protected int value = DEFAULT_START_VALUE;
	
	protected int rangeStartValue = DEFAULT_START_VALUE;

	protected int rangeEndValue = DEFAULT_END_VALUE;

	protected int range = rangeEndValue - rangeStartValue;
	
	@Override
	public int getRangeStartValue() {
		return rangeStartValue;
	}

	@Override
	public void setRangeStartValue(int rangeStartValue) {
		if (value < rangeStartValue) setValue(rangeStartValue);
		int oldElementsON = calcValueElements(value);

		this.rangeStartValue = rangeStartValue;
		range = rangeEndValue - rangeStartValue;
		
		int newElementsON = calcValueElements(value);
		updateElementsView(oldElementsON, newElementsON);
	}

	@Override
	public int getRangeEndValue() {
		return rangeEndValue;
	}

	@Override
	public void setRangeEndValue(int rangeEndValue) {
		if (value > rangeEndValue) setValue(rangeEndValue);
		int oldElementsON = calcValueElements(value);

		this.rangeEndValue = rangeEndValue;
		range = rangeEndValue - rangeStartValue;
		
		int newElementsON = calcValueElements(value);
		updateElementsView(oldElementsON, newElementsON);
	}

	@SuppressWarnings("serial")
	public static class OutOfRange extends RuntimeException {
		public OutOfRange() {
			super();
		}
		
		public OutOfRange(int arg) {
			super("value: " + arg);
		}
	}

	public int getValue() {
		return value;
	}

	@Override
	public void setValue(int value) {
		if (value < rangeStartValue) {
			throw new OutOfRange();
		}
		if (value > rangeEndValue) {
			throw new OutOfRange();
		}
		int oldValue = this.value;
		this.value = value;
		int oldElementsON = calcValueElements(oldValue);
		int newElementsON = calcValueElements(value);

		updateElementsView(oldElementsON, newElementsON);
	}

	@Override
	public void setTitle(String title){
		elementBar.setTitle(title);
	}
	
	@Override
	public String getTitle(){
		return elementBar.getTitle();
	}

	
	public static final int ELEMENT_DEFAULT = 20;
	public static final int ELEMENT_WIDTH_DEFAULT = 10;
	public static final int ELEMENT_HEIGHT_DEFAULT = 10;

	protected List<Widget> elements;
	protected int elementCount = ELEMENT_DEFAULT;
	protected int elementWidthPx = ELEMENT_WIDTH_DEFAULT;
	protected int elementHeightPx = ELEMENT_HEIGHT_DEFAULT;

	public int getElementCount() {
		return elementCount;
	}

	public void setElementCount(int n) {
		if (n < 1)
			n = ELEMENT_DEFAULT;
		this.elementCount = n;
		initElementsView();
		initSegments();
	}

	private static final String DEFAULT_COLOR_ON = "#55a";
	private static final String DEFAULT_COLOR_OFF = "#eee";

	String[] segmentColorON;
	String[] segmentColorOFF;

	protected void initSegments() {
		segmentColorON = new String[elementCount];
		segmentColorOFF = new String[elementCount];
		addValueSegment(rangeStartValue, rangeEndValue, DEFAULT_COLOR_OFF, DEFAULT_COLOR_ON);
	}

	@UiField
	protected Styles style;
	@UiField
	protected Grid elementBar;

	protected interface Styles extends CssResource {
		String progressbarBlankbar();

		String progressbarBar();

		String progressbarFullbar();

		String progressbarInner();
	}

	protected void applyStyleOff(int n) {
		elements.get(n).getElement().getStyle()
				.setBackgroundColor(segmentColorOFF[n]);
	}

	protected void applyStyleOn(int n) {
		elements.get(n).getElement().getStyle()
				.setBackgroundColor(segmentColorON[n]);
	}

	protected abstract void initElementsView();

	@Override
	public void addValueSegment(int startValueSegment, int endValueSegment,
			String colorOFF, String colorON) {
		if (startValueSegment < rangeStartValue
				|| startValueSegment > rangeEndValue){
			throw new OutOfRange(startValueSegment);
		}
		if( endValueSegment < rangeStartValue
				|| endValueSegment > rangeEndValue) {
			throw new OutOfRange(endValueSegment);
		}
		
		int startElement = calcValueElements(startValueSegment);
		int endElement = calcValueElements(endValueSegment);
		int elemValue = calcValueElements(value);
		for (int i = startElement; i < endElement; i++) {
			segmentColorON[i] = colorON;
			segmentColorOFF[i] = colorOFF;
			if (i < elemValue) {
				applyStyleOn(i);
			} else {
				applyStyleOff(i);
			}
		}
	}

	protected int calcValueElements(int val) {
		// TODO Не надежно, возможно переполнение
		return (val - rangeStartValue) * elementCount
				/ range;
	}

	protected void updateElementsView(int startElement, int endElement) {

		for (int loop = startElement; loop < endElement; loop++) {
			applyStyleOn(loop);
		}
		for (int loop = endElement; loop < startElement; loop++) {
			applyStyleOff(loop);
		}
	}

	public void setElementWidthPx(int width) {
		this.elementWidthPx = width;
		for (Widget w : elements) {
			w.setWidth(elementWidthPx + "px");
		}
	}

	public void setElementHeightPx(int height) {
		this.elementHeightPx = height;
		for (Widget w : elements) {
			w.setHeight(elementHeightPx + "px");
		}
	}

	public int getElementWidthPx() {
		return elementWidthPx;
	}

	public int getElementHeightPx() {
		return elementHeightPx;
	}
	

}