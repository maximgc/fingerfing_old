package com.trifle.fingerfing.client.widget.newage;

import java.util.ArrayList;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class VerticalProgressBar extends ProgressBarBase {

	public VerticalProgressBar() {
		super();
	}

	public VerticalProgressBar(int elementCount) {
		super(elementCount);
	}

	public VerticalProgressBar(int elementCount, int value) {
		super(elementCount, value);
		// valueLabel.setAutoHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		// topLabel.setAutoHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	}

	@Override
	protected void initElementsView() {
		elements = new ArrayList<Widget>();
		elementBar.setStyleName(style.progressbarInner());
		elementBar.setCellPadding(0);
		elementBar.setCellSpacing(0);
		elementBar.resize(elementCount, 1);
		for (int loop = 0; loop < elementCount; loop++) {
			HTML newElement = new HTML("");
			elements.add(newElement);
			newElement.setHeight(elementHeightPx + "px");
			newElement.setWidth(elementWidthPx + "px");
			newElement.setStyleName(style.progressbarBlankbar());
			elementBar.setWidget(elementCount - loop - 1, 0, newElement);
		}
	}

}
