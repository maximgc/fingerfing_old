package com.trifle.fingerfing.client.widget;

import java.util.EnumMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.TextArea;
import com.trifle.fingerfing.client.AlternativeKeyLayout;
import com.trifle.fingerfing.client.NativeKey;
import com.trifle.fingerfing.client.AlternativeKeyLayout.LabelTextMap;
import com.trifle.fingerfing.client.json.BeanManager;
import com.google.gwt.user.client.ui.FocusPanel;

public class JSONConstructorWidget extends Composite {

	private static JSONConstructorUiBinder uiBinder = GWT
			.create(JSONConstructorUiBinder.class);
	
	@UiField TextArea jsonTextOut;
	@UiField Button button;
	@UiField FocusPanel focusPanel;

	interface JSONConstructorUiBinder extends UiBinder<Widget, JSONConstructorWidget> {
	}

	private BeanManager bm;
	public JSONConstructorWidget(BeanManager bm) {
		initWidget(uiBinder.createAndBindUi(this));
		this.bm = bm;
	}

	@UiHandler("focusPanel")
	void onFocusPanelClick(ClickEvent event) {
//		focusPanel.setFocus(true);
//		init();
	}
	
	@UiHandler("jsonTextOut")
	void onTextAreaClick(ClickEvent event) {
		focusPanel.setFocus(true);
		init();
	}
	
	private LabelTextMap mapL;
	private NativeKey lastNk;
	
	public void init() {
		mapL = bm.createLabelTextMap();
		mapL.setLabelTextMap(new EnumMap<NativeKey, String>(NativeKey.class));
	}

	@UiHandler("focusPanel")
	void onFocusPanelKeyDown(KeyDownEvent event) {
		lastNk = NativeKey.getByNativeCode(event.getNativeKeyCode());
		System.out.println(lastNk.toString());
	}
	
	@UiHandler("focusPanel")
	void onFocusPanelKeyPress(KeyPressEvent event) {
		mapL.getLabelTextMap().put(lastNk, String.valueOf(event.getCharCode()));
	}
	
	
	@UiHandler("button")
	void onButtonClick(ClickEvent event) {
		jsonTextOut.setText(bm.encodeLabelTextMap(mapL));
	}
}
