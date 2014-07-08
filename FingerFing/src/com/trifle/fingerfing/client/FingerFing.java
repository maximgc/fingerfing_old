package com.trifle.fingerfing.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.trifle.fingerfing.client.widget.KeyboardWidget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class FingerFing implements EntryPoint {
	public void onModuleLoad() {
		final Label errorLabel = new Label();
		final KeyboardWidget keyWidget = new KeyboardWidget();

		RootPanel.get("mainWidgetField").add(keyWidget);
		RootPanel.get("errorWidgetField").add(errorLabel);

	}
}
