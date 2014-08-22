package com.trifle.fingerfing.client.widget;

//import java.util.EnumMap;
//import java.util.EnumSet;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import com.google.gwt.core.client.GWT;
//import com.google.gwt.uibinder.client.UiBinder;
//import com.google.gwt.uibinder.client.UiField;
//import com.google.gwt.user.client.ui.AbsolutePanel;
//import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
//import com.google.gwt.user.client.ui.Widget;
//import com.trifle.fingerfing.client.IllegalNativeKey;
//import com.trifle.fingerfing.client.NativeKey;
//import com.trifle.fingerfing.client.widget.effect.Effect;

public class KeyboardWidgetUIHard extends Composite { // implements KeyboardWidget  {
//
//	private static KeyboardWidgetUiBinder uiBinder = GWT
//			.create(KeyboardWidgetUiBinder.class);
//
//	interface KeyboardWidgetUiBinder extends UiBinder<Widget, KeyboardWidgetUIHard> {
//	}
//
//	public KeyboardWidgetUIHard() {
//		initWidget(uiBinder.createAndBindUi(this));
//		nMap = new NativeMap();
//	}
//
//	@UiField AbsolutePanel keysPanel;
//	
//	@UiField Button keyGraveAccent;
//	@UiField Button key1;
//	@UiField Button key2;
//	@UiField Button key3;
//	@UiField Button key4;
//	@UiField Button key5;
//	@UiField Button key6;
//	@UiField Button key7;
//	@UiField Button key8;
//	@UiField Button key9;
//	@UiField Button key0;
//	@UiField Button keyMinus;
//	@UiField Button keyEqual;
//	@UiField Button keyBackspace;
//	@UiField Button keyTab;
//	@UiField Button keyQ;
//	@UiField Button keyW;
//	@UiField Button keyE;
//	@UiField Button keyR;
//	@UiField Button keyT;
//	@UiField Button keyY;
//	@UiField Button keyU;
//	@UiField Button keyI;
//	@UiField Button keyO;
//	@UiField Button keyP;
//	@UiField Button keySqBracketOpen;
//	@UiField Button keySqBracketClose;
//	@UiField Button keyBackslash;
//	@UiField Button keyCapsLock;
//	@UiField Button keyA;
//	@UiField Button keyS;
//	@UiField Button keyD;
//	@UiField Button keyF;
//	@UiField Button keyG;
//	@UiField Button keyH;
//	@UiField Button keyJ;
//	@UiField Button keyK;
//	@UiField Button keyL;
//	@UiField Button keySemicolon;
//	@UiField Button keyApostrophe;
//	@UiField Button keyEnter;
//	@UiField Button keyLShift;
//	@UiField Button keyZ;
//	@UiField Button keyX;
//	@UiField Button keyC;
//	@UiField Button keyV;
//	@UiField Button keyB;
//	@UiField Button keyN;
//	@UiField Button keyM;
//	@UiField Button keyComma;
//	@UiField Button keyFullstop;
//	@UiField Button keySlash;
//	@UiField Button keyRShift;
//	@UiField Button keyLCtrl;
//	@UiField Button keyFn;  //havn't Native code
//	@UiField Button keyWin;
//	@UiField Button keyLAlt;
//	@UiField Button keySpace;
//	@UiField Button keyRAlt;
//	@UiField Button keyMenu;
//	@UiField Button keyRCtrl;
//	
//	double fontSize = 14;
//	
//	/* (non-Javadoc)
//	 * @see com.trifle.fingerfing.client.widget.KeyboardWidget#setEffect(com.trifle.fingerfing.client.NativeKey, com.trifle.fingerfing.client.widget.effect.Effect)
//	 */
//	@Override
//	public void setEffect(NativeKey n, Effect e){
//		for (Widget w: nMap.getButtons(n)){
//			e.apply(w);
//		}
//	}
//	
//	/* (non-Javadoc)
//	 * @see com.trifle.fingerfing.client.widget.KeyboardWidget#setEffectAll(com.trifle.fingerfing.client.widget.effect.Effect)
//	 */
//	@Override
//	public void setEffectAll(Effect e){
//		for (Set<Button> s: nMap.m.values()){
//			for (Widget w: s){
//				e.apply(w);
//			}
//		}
//	}
//	
//	/* (non-Javadoc)
//	 * @see com.trifle.fingerfing.client.widget.KeyboardWidget#setEffectAll(java.util.List, com.trifle.fingerfing.client.widget.effect.Effect)
//	 */
//	@Override
//	public void setEffectAll(List<NativeKey> sn, Effect e){
//		setEffectAll(EnumSet.copyOf(sn), e);
//	}
//
//	/* (non-Javadoc)
//	 * @see com.trifle.fingerfing.client.widget.KeyboardWidget#setEffectAll(java.util.Set, com.trifle.fingerfing.client.widget.effect.Effect)
//	 */
//	@Override
//	public void setEffectAll(Set<NativeKey> sn, Effect e){
//		for (NativeKey n: sn){
//			for (Widget w: nMap.getButtons(n)){
//				e.apply(w);
//			}
//		}
//	}
//	
//
//	
//	private NativeMap nMap;
//	
//	private class NativeMap {
//		@SuppressWarnings("serial")
//		EnumMap<NativeKey, Set<Button>> m = new EnumMap<NativeKey, Set<Button>>(NativeKey.class)
//		{{
//			put(NativeKey.KEY_BACKSPACE, new HashSet<Button>() {{add(keyBackspace);}});
//			put(NativeKey.KEY_SHIFT, new HashSet<Button>() {{add(keyLShift); add(keyRShift);}});
//			put(NativeKey.KEY_CTRL, new HashSet<Button>() {{add(keyLCtrl); add(keyRCtrl);}});
//			put(NativeKey.KEY_ALT, new HashSet<Button>() {{add(keyLAlt); add(keyRAlt);}});
//			put(NativeKey.KEY_CAPS_LOCK, new HashSet<Button>() {{add(keyCapsLock);}});
////			put(NativeKey.KEY_PAGE_UP, new HashSet<Button>() {{add(keyPageUp);}});
////			put(NativeKey.KEY_PAGE_DOWN, new HashSet<Button>() {{add(keyPageDown);}});
////			put(NativeKey.KEY_END, new HashSet<Button>() {{add(keyEnd);}});
////			put(NativeKey.KEY_HOME, new HashSet<Button>() {{add(keyHome);}});
////			put(NativeKey.KEY_ARROW_LEFT, new HashSet<Button>() {{add(keyArrowLeft);}});
////			put(NativeKey.KEY_ARROW_UP, new HashSet<Button>() {{add(keyArrowUp);}});
////			put(NativeKey.KEY_ARROW_RIGHT, new HashSet<Button>() {{add(keyArrowRight);}});
////			put(NativeKey.KEY_ARROW_DOWN, new HashSet<Button>() {{add(keyArrowDown);}});
////			put(NativeKey.KEY_PRT_SCR, new HashSet<Button>() {{add(keyPrtScr);}});
////			put(NativeKey.KEY_INS, new HashSet<Button>() {{add(keyIns);}});
////			put(NativeKey.KEY_DEL, new HashSet<Button>() {{add(keyDel);}});
//			put(NativeKey.KEY_WIN, new HashSet<Button>() {{add(keyWin);}});
//			put(NativeKey.KEY_MENU, new HashSet<Button>() {{add(keyMenu);}});
////			put(NativeKey.KEY_F1, new HashSet<Button>() {{add(keyF1);}});
////			put(NativeKey.KEY_F2, new HashSet<Button>() {{add(keyF2);}});
////			put(NativeKey.KEY_F3, new HashSet<Button>() {{add(keyF3);}});
////			put(NativeKey.KEY_F4, new HashSet<Button>() {{add(keyF4);}});
////			put(NativeKey.KEY_F5, new HashSet<Button>() {{add(keyF5);}});
////			put(NativeKey.KEY_F6, new HashSet<Button>() {{add(keyF6);}});
////			put(NativeKey.KEY_F7, new HashSet<Button>() {{add(keyF7);}});
////			put(NativeKey.KEY_F8, new HashSet<Button>() {{add(keyF8);}});
////			put(NativeKey.KEY_F9, new HashSet<Button>() {{add(keyF9);}});
////			put(NativeKey.KEY_F10, new HashSet<Button>() {{add(keyF10);}});
////			put(NativeKey.KEY_F11, new HashSet<Button>() {{add(keyF11);}});
////			put(NativeKey.KEY_F12, new HashSet<Button>() {{add(keyF12);}});
////			put(NativeKey.KEY_SCR_LOCK, new HashSet<Button>() {{add(keyScrLock);}});
//			put(NativeKey.KEY_TAB, new HashSet<Button>() {{add(keyTab);}});
//			put(NativeKey.KEY_ENTER, new HashSet<Button>() {{add(keyEnter);}});
////			put(NativeKey.KEY_ESC, new HashSet<Button>() {{add(keyEsc);}});
//			put(NativeKey.KEY_SPACE, new HashSet<Button>() {{add(keySpace);}});
//			put(NativeKey.KEY_0, new HashSet<Button>() {{add(key0);}});
//			put(NativeKey.KEY_1, new HashSet<Button>() {{add(key1);}});
//			put(NativeKey.KEY_2, new HashSet<Button>() {{add(key2);}});
//			put(NativeKey.KEY_3, new HashSet<Button>() {{add(key3);}});
//			put(NativeKey.KEY_4, new HashSet<Button>() {{add(key4);}});
//			put(NativeKey.KEY_5, new HashSet<Button>() {{add(key5);}});
//			put(NativeKey.KEY_6, new HashSet<Button>() {{add(key6);}});
//			put(NativeKey.KEY_7, new HashSet<Button>() {{add(key7);}});
//			put(NativeKey.KEY_8, new HashSet<Button>() {{add(key8);}});
//			put(NativeKey.KEY_9, new HashSet<Button>() {{add(key9);}});
//			put(NativeKey.KEY_F, new HashSet<Button>() {{add(keyF);}});
//			put(NativeKey.KEY_G, new HashSet<Button>() {{add(keyG);}});
//			put(NativeKey.KEY_H, new HashSet<Button>() {{add(keyH);}});
//			put(NativeKey.KEY_I, new HashSet<Button>() {{add(keyI);}});
//			put(NativeKey.KEY_J, new HashSet<Button>() {{add(keyJ);}});
//			put(NativeKey.KEY_K, new HashSet<Button>() {{add(keyK);}});
//			put(NativeKey.KEY_L, new HashSet<Button>() {{add(keyL);}});
//			put(NativeKey.KEY_M, new HashSet<Button>() {{add(keyM);}});
//			put(NativeKey.KEY_N, new HashSet<Button>() {{add(keyN);}});
//			put(NativeKey.KEY_O, new HashSet<Button>() {{add(keyO);}});
//			put(NativeKey.KEY_P, new HashSet<Button>() {{add(keyP);}});
//			put(NativeKey.KEY_Q, new HashSet<Button>() {{add(keyQ);}});
//			put(NativeKey.KEY_R, new HashSet<Button>() {{add(keyR);}});
//			put(NativeKey.KEY_S, new HashSet<Button>() {{add(keyS);}});
//			put(NativeKey.KEY_T, new HashSet<Button>() {{add(keyT);}});
//			put(NativeKey.KEY_U, new HashSet<Button>() {{add(keyU);}});
//			put(NativeKey.KEY_V, new HashSet<Button>() {{add(keyV);}});
//			put(NativeKey.KEY_W, new HashSet<Button>() {{add(keyW);}});
//			put(NativeKey.KEY_X, new HashSet<Button>() {{add(keyX);}});
//			put(NativeKey.KEY_Y, new HashSet<Button>() {{add(keyY);}});
//			put(NativeKey.KEY_Z, new HashSet<Button>() {{add(keyZ);}});
//			put(NativeKey.KEY_SEMICOLON, new HashSet<Button>() {{add(keySemicolon);}});
//			put(NativeKey.KEY_EQUAL, new HashSet<Button>() {{add(keyEqual);}});
//			put(NativeKey.KEY_COMMA, new HashSet<Button>() {{add(keyComma);}});
//			put(NativeKey.KEY_MINUS, new HashSet<Button>() {{add(keyMinus);}});
//			put(NativeKey.KEY_FULLSTOP, new HashSet<Button>() {{add(keyFullstop);}});
//			put(NativeKey.KEY_SLASH, new HashSet<Button>() {{add(keySlash);}});
//			put(NativeKey.KEY_GRAVE_ACCENT, new HashSet<Button>() {{add(keyGraveAccent);}});
//			put(NativeKey.KEY_SQ_BRACKET_OPEN, new HashSet<Button>() {{add(keySqBracketOpen);}});
//			put(NativeKey.KEY_BACKSLASH, new HashSet<Button>() {{add(keyBackslash);}});
//			put(NativeKey.KEY_SQ_BRACKET_CLOSE, new HashSet<Button>() {{add(keySqBracketClose);}});
//			put(NativeKey.KEY_APOSTROPHE, new HashSet<Button>() {{add(keyApostrophe);}});
//			put(NativeKey.KEY_A, new HashSet<Button>() {{add(keyA);}});
//			put(NativeKey.KEY_B, new HashSet<Button>() {{add(keyB);}});
//			put(NativeKey.KEY_C, new HashSet<Button>() {{add(keyC);}});
//			put(NativeKey.KEY_D, new HashSet<Button>() {{add(keyD);}});
//			put(NativeKey.KEY_E, new HashSet<Button>() {{add(keyE);}});
////			put(NativeKey.KEY_NUM_LOCK, new HashSet<Button>() {{add(keyNumLock);}});
////			put(NativeKey.KEY_NUM_0, new HashSet<Button>() {{add(keyNum0);}});
////			put(NativeKey.KEY_NUM_1, new HashSet<Button>() {{add(keyNum1);}});
////			put(NativeKey.KEY_NUM_2, new HashSet<Button>() {{add(keyNum2);}});
////			put(NativeKey.KEY_NUM_3, new HashSet<Button>() {{add(keyNum3);}});
////			put(NativeKey.KEY_NUM_4, new HashSet<Button>() {{add(keyNum4);}});
////			put(NativeKey.KEY_NUM_5, new HashSet<Button>() {{add(keyNum5);}});
////			put(NativeKey.KEY_NUM_6, new HashSet<Button>() {{add(keyNum6);}});
////			put(NativeKey.KEY_NUM_7, new HashSet<Button>() {{add(keyNum7);}});
////			put(NativeKey.KEY_NUM_8, new HashSet<Button>() {{add(keyNum8);}});
////			put(NativeKey.KEY_NUM_9, new HashSet<Button>() {{add(keyNum9);}});
////			put(NativeKey.KEY_NUM_MULT, new HashSet<Button>() {{add(keyNumMult);}});
////			put(NativeKey.KEY_NUM_PLUS, new HashSet<Button>() {{add(keyNumPlus);}});
////			put(NativeKey.KEY_NUM_MINUS, new HashSet<Button>() {{add(keyNumMinus);}});
////			put(NativeKey.KEY_NUM_DOT, new HashSet<Button>() {{add(keyNumDot);}});
////			put(NativeKey.KEY_NUM_DIV, new HashSet<Button>() {{add(keyNumDiv);}});
//		}};
//		
//		public Set<Button> getButtons(NativeKey nk){
//			Set<Button> s = m.get(nk);
//			if (s==null) {throw new IllegalNativeKey();}
//			return s;
//		}
//		
//	}
//	
//	
}
