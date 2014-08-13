package com.trifle.fingerfing.client.widget.newage;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Widget;

public class HorizontalProgressBar extends ProgressBarBase{

	public HorizontalProgressBar() {
		super();
	}
	
	public HorizontalProgressBar(int segmentCount){
		super(segmentCount);
	}
	
	public HorizontalProgressBar(int segmentCount, int segmentWidthPx, int segmentHeightPx, double value, String text){
		super(segmentCount, segmentWidthPx, segmentHeightPx, value, text);
		valueLabel.setAutoHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		topLabel.setAutoHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
	}

	protected void initSegments() {
		int completed = (int) (value * segmentCount);
		listElem = new ArrayList<Widget>();
		elementBar.resize(1, segmentCount);
		elementBar.setStyleName(style.progressbarInner());
		elementBar.setCellPadding(0);
		elementBar.setCellSpacing(0);

		for (int loop = 0; loop < segmentCount; loop++) {
			HTML newElem = new HTML("");
			listElem.add(newElem);
			newElem.setHeight(segmentHeightPx+"px");
			newElem.setWidth(segmentWidthPx+"px");
			if (loop < completed) {
				applyStyleOn(newElem);
			} else {
				applyStyleOff(newElem);
			}
			elementBar.setWidget(0, loop, newElem);
		}
	}

	
}
