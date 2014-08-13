package com.trifle.fingerfing.client.widget.newage;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Label;

public abstract class ProgressBarBase extends Composite implements ProgressBar {

	protected static ProgressBarBaseUiBinder uiBinder = GWT
			.create(ProgressBarBaseUiBinder.class);

	protected interface ProgressBarBaseUiBinder extends
			UiBinder<Widget, ProgressBarBase> {
	}

	@UiField
	protected Grid elementBar;

	@UiField
	Label topLabel;
	@UiField
	Label valueLabel;

	public ProgressBarBase() {
		this(SEGMENT_DEFAULT);
	}

	public ProgressBarBase(int segmentCount) {
		this(segmentCount, SEGMENT_WIDTH_DEFAULT, SEGMENT_HEIGHT_DEFAULT,
				MIN_VALUE, "");
	}

	public ProgressBarBase(int segmentCount, int segmentWidthPx,
			int segmentHeightPx, double value, String text) {
		initWidget(uiBinder.createAndBindUi(this));
		this.segmentCount = segmentCount;
		this.value = value;
		this.segmentWidthPx = segmentWidthPx;
		this.segmentHeightPx = segmentHeightPx;
		this.topLabel.setText(text);
		initView();
	}

	public static final double MIN_VALUE = 0.0;

	public static final double MAX_VALUE = 1.0;

	public static final int SEGMENT_DEFAULT = 20;

	public static final int SEGMENT_WIDTH_DEFAULT = 10;

	public static final int SEGMENT_HEIGHT_DEFAULT = 10;

	protected double value = MIN_VALUE;

	protected int segmentCount = SEGMENT_DEFAULT;

	protected List<Widget> listElem;

	protected int segmentWidthPx;

	protected int segmentHeightPx;

	protected void updateView(double oldValue) {
		valueLabel.setText(Double.toString(value));

		int oldCompleted = (int) (oldValue * segmentCount);
		int completed = (int) (value * segmentCount);

		for (int loop = oldCompleted; loop < completed; loop++) {
			applyStyleOn(listElem.get(loop));
		}
		for (int loop = completed; loop < oldCompleted; loop++) {
			applyStyleOff(listElem.get(loop));
		}
	}

	/* (non-Javadoc)
	 * @see com.trifle.fingerfing.client.widget.newage.ProgressBar#setSegmentSizePx(int, int)
	 */
	@Override
	public void setSegmentSizePx(int width, int height) {
		this.segmentWidthPx = width;
		this.segmentHeightPx = height;
		for (Widget w : listElem) {
			w.setHeight(segmentHeightPx + "px");
			w.setWidth(segmentWidthPx + "px");
		}
	}
	
	/* (non-Javadoc)
	 * @see com.trifle.fingerfing.client.widget.newage.ProgressBar#setSegmentWidthPx(int)
	 */
	@Override
	public void setSegmentWidthPx(int width) {
		this.segmentWidthPx = width;
		for (Widget w : listElem) {
			w.setWidth(segmentWidthPx + "px");
		}
	}
	
	/* (non-Javadoc)
	 * @see com.trifle.fingerfing.client.widget.newage.ProgressBar#setSegmentHeightPx(int)
	 */
	@Override
	public void setSegmentHeightPx(int height) {
		this.segmentHeightPx = height;
		for (Widget w : listElem) {
			w.setHeight(segmentHeightPx + "px");
		}
	}

	/* (non-Javadoc)
	 * @see com.trifle.fingerfing.client.widget.newage.ProgressBar#getSegmentWidthPx()
	 */
	@Override
	public int getSegmentWidthPx() {
		return segmentWidthPx;
	}

	/* (non-Javadoc)
	 * @see com.trifle.fingerfing.client.widget.newage.ProgressBar#getSegmentHeightPx()
	 */
	@Override
	public int getSegmentHeightPx() {
		return segmentHeightPx;
	}

	/* (non-Javadoc)
	 * @see com.trifle.fingerfing.client.widget.newage.ProgressBar#getValue()
	 */
	@Override
	public double getValue() {
		return value;
	}

	/* (non-Javadoc)
	 * @see com.trifle.fingerfing.client.widget.newage.ProgressBar#setValue(double)
	 */
	@Override
	public void setValue(double value) {
		if (value < MIN_VALUE)
			value = MIN_VALUE;
		if (value > MAX_VALUE)
			value = MAX_VALUE;
		double oldValue = this.value;
		this.value = value;
		updateView(oldValue);
	}

	/* (non-Javadoc)
	 * @see com.trifle.fingerfing.client.widget.newage.ProgressBar#getSegmentCount()
	 */
	@Override
	public int getSegmentCount() {
		return segmentCount;
	}

	/* (non-Javadoc)
	 * @see com.trifle.fingerfing.client.widget.newage.ProgressBar#setSegmentCount(int)
	 */
	@Override
	public void setSegmentCount(int n) {
		if (n < 0)
			n = SEGMENT_DEFAULT;
		this.segmentCount = n;
		initSegments();
	}

	/* (non-Javadoc)
	 * @see com.trifle.fingerfing.client.widget.newage.ProgressBar#getTopLabelText()
	 */
	@Override
	public String getTopLabelText() {
		return topLabel.getText();
	}

	/* (non-Javadoc)
	 * @see com.trifle.fingerfing.client.widget.newage.ProgressBar#setTopLabelText(java.lang.String)
	 */
	@Override
	public void setTopLabelText(String text) {
		this.topLabel.setText(text);
	}

	@UiField
	protected Styles style;

	protected interface Styles extends CssResource {
		String progressbarBlankbar();

		String progressbarBar();

		String progressbarFullbar();

		String progressbarInner();
	}

	protected void applyStyleOff(Widget elm) {
		elm.setStyleName(style.progressbarBlankbar());
		// elm.addStyleName(style.progressbarBar());
	}

	protected void applyStyleOn(Widget elm) {
		elm.setStyleName(style.progressbarFullbar());
		// elm.addStyleName(style.progressbarBar());
	}

	protected void initView() {
		valueLabel.setText(Double.toString(value));
		initSegments();
	}

	protected abstract void initSegments();

}