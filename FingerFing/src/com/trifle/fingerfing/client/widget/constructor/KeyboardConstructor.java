package com.trifle.fingerfing.client.widget.constructor;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.trifle.fingerfing.client.NativeKey;
import com.trifle.fingerfing.client.json.BeanManager;
import com.trifle.fingerfing.client.widget.KeyboardWidget;

public class KeyboardConstructor extends Composite {

	private static KeyboardConstructorUiBinder uiBinder = GWT
			.create(KeyboardConstructorUiBinder.class);
	
	@UiField Button nextRow;
	@UiField FocusPanel focusPanel;
	@UiField Label lbInfo;
	@UiField Button start;

	interface KeyboardConstructorUiBinder extends
			UiBinder<Widget, KeyboardConstructor> {
	}

	public KeyboardConstructor() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	
	
	private BeanManager.KeyboardWidgetBeans kbm;
	private KeyboardWidget.KeyBlock kBlock;
	private KeyboardWidget.KeyRow kRow;
	private KeyboardWidget.Key kKey;
	
	private List<KeyboardWidget.KeyRow> curRowList;
	private List<KeyboardWidget.Key> curKeyList;

	public BeanManager.KeyboardWidgetBeans getKbm() {
		return kbm;
	}

	public void setKbm(BeanManager.KeyboardWidgetBeans kbm) {
		this.kbm = kbm;
	}

	@UiHandler("focusPanel")
	void onFocusPanelClick(ClickEvent event) {
		focusPanel.setFocus(true);
	}
	
	@UiHandler("start")
	void onStartClick(ClickEvent event) {
		kBlock = kbm.createKeyBlock();
		curRowList = new ArrayList<KeyboardWidget.KeyRow>();
		kBlock.setBlock(curRowList);
		
		kRow = kbm.createKeyRow();
		curKeyList = new ArrayList<KeyboardWidget.Key>();
		kRow.setRow(curKeyList);
		curRowList.add(kRow);
		
		
		focusPanel.setFocus(true);
	}

	@UiHandler("nextRow")
	void onNextRowClick(ClickEvent event) {

		kRow = kbm.createKeyRow();
		curKeyList = new ArrayList<KeyboardWidget.Key>();
		kRow.setRow(curKeyList);
		curRowList.add(kRow);
		
		focusPanel.setFocus(true);
	}
	
	@UiHandler("focusPanel")
	void onFocusPanelKeyDown(KeyDownEvent event) {
		kKey = kbm.createKey();
		kKey.setNativeKey(NativeKey.getByNativeCode(event.getNativeKeyCode()));
		curKeyList.add(kKey);
	}
	
	public String generateJSON(){
		return kbm.encodeKeyBlock(kBlock);
	}
}
