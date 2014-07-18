package com.trifle.fingerfing.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Image;

public class ProgressBarImg extends Composite implements ProgressBar{

	private static ProgressBarUiBinder uiBinder = GWT
			.create(ProgressBarUiBinder.class);
	@UiField Image imgPos;
	@UiField Image imgBlank;
//	@UiField Image imgMaxPos;

	interface ProgressBarUiBinder extends UiBinder<Widget, ProgressBarImg> {
	}

	public ProgressBarImg() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public ProgressBarImg(int range) {
		this.range = range;
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	private int range;
	private int position;
	private int maxPosition;

	/* (non-Javadoc)
	 * @see com.trifle.fingerfing.client.widget.ProgressBar#getRange()
	 */
	@Override
	public int getRange() {
		return range;
	}
	
	/* (non-Javadoc)
	 * @see com.trifle.fingerfing.client.widget.ProgressBar#setRange(int)
	 */
	@Override
	public void setRange(int range) {
		this.range = range;
	}

	/* (non-Javadoc)
	 * @see com.trifle.fingerfing.client.widget.ProgressBar#getPosition()
	 */
	@Override
	public int getPosition() {
		return position;
	}

	/* (non-Javadoc)
	 * @see com.trifle.fingerfing.client.widget.ProgressBar#setPosition(int)
	 */
	@Override
	public void setPosition(int position) {
		this.position = (position>range)?range:position;
		if (maxPosition<position) {
			maxPosition = position;
		}
		imgPos.setWidth(position*100/range+"%");
//		imgMaxPos.setWidth((maxPosition-position)*100/range+"%");
	}

	/* (non-Javadoc)
	 * @see com.trifle.fingerfing.client.widget.ProgressBar#getMaxPosition()
	 */
	@Override
	public int getMaxPosition() {
		return maxPosition;
	}

	/* (non-Javadoc)
	 * @see com.trifle.fingerfing.client.widget.ProgressBar#setMaxPosition(int)
	 */
	@Override
	public void setMaxPosition(int maxPosition) {
		this.maxPosition = maxPosition;
		if (position>maxPosition) {
			position = maxPosition;
		}
		imgPos.setWidth(position*100/range+"%");
//		imgMaxPos.setWidth((maxPosition-position)*100/range+"%");
	}


}
