package com.trifle.fingerfing.client.widget.newage;

import java.util.ArrayList;
import java.util.Collections;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Widget;


public class VerticalProgressBar extends ProgressBarBase {
	
	public VerticalProgressBar() {
		super();
	}
	
	public VerticalProgressBar(int segmentCount){
		super(segmentCount);
	}
	
	public VerticalProgressBar(int segmentCount, int segmentWidthPx, int segmentHeightPx, double value, String text){
		super(segmentCount, segmentWidthPx, segmentHeightPx, value, text);
		valueLabel.setAutoHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		topLabel.setAutoHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	}
	

	@Override
	protected void initSegments() {
		int completed = (int) (value * segmentCount);
		listElem = new ArrayList<Widget>();
		elementBar.resize(segmentCount, 1);
		elementBar.setStyleName(style.progressbarInner());
		elementBar.setCellPadding(0);
		elementBar.setCellSpacing(0);
		for (int loop = 0; loop < segmentCount; loop++) {
			HTML newElem = new HTML("");
			listElem.add(newElem);
			if (segmentCount - loop -1 < completed) {
				applyStyleOn(newElem);
			} else {
				applyStyleOff(newElem);
			}
			newElem.setHeight(segmentHeightPx+"px");
			newElem.setWidth(segmentWidthPx+"px");
			newElem.addStyleName(style.progressbarBar());
			elementBar.setWidget(loop, 0, newElem);
		}
		Collections.reverse(listElem);
	}

}

