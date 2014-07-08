package com.trifle.fingerfing.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class KeyboardWidget extends Composite {

	private static KeyboardWidgetUiBinder uiBinder = GWT
			.create(KeyboardWidgetUiBinder.class);

	interface KeyboardWidgetUiBinder extends UiBinder<Widget, KeyboardWidget> {
	}

	public KeyboardWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField Button keyGraveAccent;
	@UiField Button key1;
	@UiField Button key2;
	@UiField Button key3;
	@UiField Button key4;
	@UiField Button key5;
	@UiField Button key6;
	@UiField Button key7;
	@UiField Button key8;
	@UiField Button key9;
	@UiField Button key0;
	@UiField Button keyMinus;
	@UiField Button keyEqual;
	@UiField Button keyBackspace;
	@UiField Button keyTab;
	@UiField Button keyQ;
	@UiField Button keyW;
	@UiField Button keyE;
	@UiField Button keyR;
	@UiField Button keyT;
	@UiField Button keyY;
	@UiField Button keyU;
	@UiField Button keyI;
	@UiField Button keyO;
	@UiField Button keyP;
	@UiField Button keySqBracketOpen;
	@UiField Button keySqBracketClose;
	@UiField Button keyBackslash;
	@UiField Button keyCapsLock;
	@UiField Button keyA;
	@UiField Button keyS;
	@UiField Button keyD;
	@UiField Button keyF;
	@UiField Button keyG;
	@UiField Button keyH;
	@UiField Button keyJ;
	@UiField Button keyK;
	@UiField Button keyL;
	@UiField Button keySemicolon;
	@UiField Button keyApostrophe;
	@UiField Button keyEnter;
	@UiField Button keyLShift;
	@UiField Button keyZ;
	@UiField Button keyX;
	@UiField Button keyC;
	@UiField Button keyV;
	@UiField Button keyB;
	@UiField Button keyN;
	@UiField Button keyM;
	@UiField Button keyComma;
	@UiField Button keyFullstop;
	@UiField Button keySlash;
	@UiField Button keyRShift;
	@UiField Button keyLCtrl;
	@UiField Button keyFn;  //havn't Native code
	@UiField Button keyWin;
	@UiField Button keyLAlt;
	@UiField Button keySpace;
	@UiField Button keyRAlt;
	@UiField Button keyMenu;
	@UiField Button keyRCtrl;
	
	public void setEffect(Widget w, Effect e){
		
	}
	

}
