package com.trifle.fingerfing.client.widget.progressbar;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class HorizontalProgressBar extends ProgressBarBase {

	public HorizontalProgressBar() {
		super();
	}

	public HorizontalProgressBar(int elementCount) {
		super(elementCount);
	}

	public HorizontalProgressBar(int elementCount, int value) {
		super(elementCount, value);
	}

	protected void initElementsView() {
		elements = new ArrayList<Widget>();
		elementBar.setStyleName(style.progressbarInner());
		elementBar.setCellPadding(0);
		elementBar.setCellSpacing(0);
		elementBar.resize(1, elementCount);
		for (int loop = 0; loop < elementCount; loop++) {
			HTML newElement = new HTML("");
			elements.add(newElement);
			newElement.setHeight(elementHeightPx + "px");
			newElement.setWidth(elementWidthPx + "px");
			newElement.setStyleName(style.progressbarBlankbar());
			elementBar.setWidget(0, loop, newElement);
		}
	}

}
