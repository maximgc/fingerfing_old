package com.trifle.fingerfing.client.widget;

import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.trifle.fingerfing.client.AlternativeKeyLayout;
import com.trifle.fingerfing.client.NativeKey;
import com.trifle.fingerfing.client.widget.effect.Effect;

public class KeyboardWidgetUIFlex extends Composite implements KeyboardWidget {

	private static KeyboardWidgetUIFlexUiBinder uiBinder = GWT
			.create(KeyboardWidgetUIFlexUiBinder.class);

	interface KeyboardWidgetUIFlexUiBinder extends
			UiBinder<Widget, KeyboardWidgetUIFlex> {
	}

	public KeyboardWidgetUIFlex() {
		initWidget(uiBinder.createAndBindUi(this));
//		placeKeys();
	}
	
	@UiField
	AbsolutePanel keysPanel;


	@Override
	public void setEffect(NativeKey n, Effect e) {
		for (Widget w : buttonMap.getWidget(n)) {
			e.apply(w);
		}
		for (Widget w : labelMap.getWidget(n)) {
			e.apply(w);
		}
		for (Widget w : altLabelMap.getWidget(n)) { //WARRNING Возможны ошибки т.к. не для всех n есть лейбл
			e.apply(w);
		}
	}

	@Override
	public void setEffectAll(Effect e) {
		for (Widget w : buttonMap.getAllWidget()) {
			e.apply(w);
		}
		for (Widget w : labelMap.getAllWidget()) {
			e.apply(w);
		}
		for (Widget w : altLabelMap.getAllWidget()) { 
			e.apply(w);
		}
	}

	@Override
	public void setEffectAll(List<NativeKey> sn, Effect e) {
		setEffectAll(EnumSet.copyOf(sn), e);
	}

	@Override
	public void setEffectAll(Set<NativeKey> sn, Effect e) {
		for (NativeKey n : sn) {
			for (Widget w : buttonMap.getWidget(n)) {
				e.apply(w);
			}
			for (Widget w : labelMap.getWidget(n)) {
				e.apply(w);
			}
			for (Widget w : altLabelMap.getWidget(n)) {
				e.apply(w);
			}
		}
	}

//	private NativeKey[][] placeMap = {
//			{ NativeKey.KEY_GRAVE_ACCENT, NativeKey.KEY_1, NativeKey.KEY_2,
//					NativeKey.KEY_3, NativeKey.KEY_4, NativeKey.KEY_5,
//					NativeKey.KEY_6, NativeKey.KEY_7, NativeKey.KEY_8,
//					NativeKey.KEY_9, NativeKey.KEY_0, NativeKey.KEY_MINUS,
//					NativeKey.KEY_EQUAL, NativeKey.KEY_BACKSPACE },
//
//			{ NativeKey.KEY_TAB, NativeKey.KEY_Q, NativeKey.KEY_W,
//					NativeKey.KEY_E, NativeKey.KEY_R, NativeKey.KEY_T,
//					NativeKey.KEY_Y, NativeKey.KEY_U, NativeKey.KEY_I,
//					NativeKey.KEY_O, NativeKey.KEY_P,
//					NativeKey.KEY_SQ_BRACKET_OPEN,
//					NativeKey.KEY_SQ_BRACKET_CLOSE, NativeKey.KEY_BACKSLASH
//			// NativeKey.KEY_ENTER,
//			},
//
//			{ NativeKey.KEY_CAPS_LOCK, NativeKey.KEY_A, NativeKey.KEY_S,
//					NativeKey.KEY_D, NativeKey.KEY_F, NativeKey.KEY_G,
//					NativeKey.KEY_H, NativeKey.KEY_J, NativeKey.KEY_K,
//					NativeKey.KEY_L, NativeKey.KEY_SEMICOLON,
//					NativeKey.KEY_APOSTROPHE,
//					NativeKey.KEY_ENTER },
//
//			{ NativeKey.KEY_SHIFT, NativeKey.KEY_Z, NativeKey.KEY_X,
//					NativeKey.KEY_C, NativeKey.KEY_V, NativeKey.KEY_B,
//					NativeKey.KEY_N, NativeKey.KEY_M, NativeKey.KEY_COMMA,
//					NativeKey.KEY_FULLSTOP, NativeKey.KEY_SLASH,
//					NativeKey.KEY_SHIFT },
//
//			{ NativeKey.KEY_CTRL, NativeKey.KEY_WIN, NativeKey.KEY_ALT,
//					NativeKey.KEY_SPACE, NativeKey.KEY_ALT, NativeKey.KEY_WIN,
//					NativeKey.KEY_MENU, NativeKey.KEY_CTRL } };

	private final int LEFT_MARGIN = 10;
	private final int TOP_MARGIN = 10;
	private final int WIDTH_KEY = 45;
	private final int HEIGHT_KEY = 45;
	private final int VERTICAL_SPACE = 6;
	private final int HORIZONTAL_SPACE = 6;
	private final int VERTICAL_SHIFT = 12;
	// private final int HORIZONTAL_SHIFT = 7;

	private final int LABEL_LEFT_MARGIN = 5;
	private final int LABEL_RIGHT_MARGIN = 10;
	private final int LABEL_TOP_MARGIN = 3;
	private final int ALT_LABEL_LEFT_MARGIN = LABEL_LEFT_MARGIN; //20;
	private final int ALT_LABEL_RIGHT_MARGIN = LABEL_RIGHT_MARGIN; //5;
	private final int ALT_LABEL_BOTTOM_MARGIN = 23;

	private NativeMap<Button> buttonMap = new NativeMap<Button>();
	private NativeMap<Label> labelMap = new NativeMap<Label>();
	private NativeMap<Label> altLabelMap = new NativeMap<Label>();

//	private void placeKeys() {
//		Button b;
//		Label l;
//
//		for (int h = 0; h < placeMap.length; h++) {
//			for (int v = 0; v < placeMap[h].length; v++) {
//				b = new Button();
//				l = new Label(placeMap[h][v].getLabelText());
//				l.setWordWrap(true);
//				l.setHorizontalAlignment(HorizontalAlignmentConstant.startOf(Direction.LTR));
//				l.setWidth(WIDTH_KEY - LABEL_LEFT_MARGIN - LABEL_RIGHT_MARGIN + "px");
//				b.setWidth(WIDTH_KEY + "px");
//				b.setHeight(HEIGHT_KEY + "px");
//				b.setStyleName("keyboardKey");
//				labelMap.addWidget(placeMap[h][v], l);
//				buttonMap.addWidget(placeMap[h][v], b);
//
//				keysPanel.add(b, LEFT_MARGIN + h * VERTICAL_SHIFT
//						+ (v * (VERTICAL_SPACE + WIDTH_KEY)), TOP_MARGIN
//						+ (h * (HORIZONTAL_SPACE + HEIGHT_KEY)));
//				keysPanel.add(l,
//						LEFT_MARGIN + h * VERTICAL_SHIFT
//								+ (v * (VERTICAL_SPACE + WIDTH_KEY))
//								+ LABEL_LEFT_MARGIN, TOP_MARGIN
//								+ (h * (HORIZONTAL_SPACE + HEIGHT_KEY))
//								+ LABEL_TOP_MARGIN);
//			}
//		}
//	}

	private AlternativeKeyLayout alternateveKeyLayout;
	
	@Override
	public AlternativeKeyLayout getAlternateveKeyLayout() {
		return alternateveKeyLayout;
	}

//	@Override
//	public void setAlternateveKeyLayout(AlternativeKeyLayout alternateveKeyLayout) {
////		this.alternateveKeyLayout = alternateveKeyLayout;
////		Label l;
////
////		for (int h = 0; h < placeMap.length; h++) {
////			for (int v = 0; v < placeMap[h].length; v++) {
////				if (alternateveKeyLayout.isDefineText(placeMap[h][v])) {
////					l = new Label(
////							alternateveKeyLayout.getLabelText(placeMap[h][v]));
////					l.setWordWrap(true);
////					l.setHorizontalAlignment(HorizontalAlignmentConstant.startOf(Direction.RTL));
////					l.setWidth(WIDTH_KEY - ALT_LABEL_RIGHT_MARGIN - ALT_LABEL_LEFT_MARGIN + "px");
////					altLabelMap.addWidget(placeMap[h][v], l);
////					keysPanel.add(l, LEFT_MARGIN + h * VERTICAL_SHIFT
////							+ (v * (VERTICAL_SPACE + WIDTH_KEY)) + ALT_LABEL_LEFT_MARGIN, 
////							TOP_MARGIN + (h * (HORIZONTAL_SPACE + HEIGHT_KEY))
////							+ HEIGHT_KEY - ALT_LABEL_BOTTOM_MARGIN);
////				}
////			}
////		}
//	}

	
	
	private class NativeMap<T extends Widget> {
		
		private EnumMap<NativeKey, Set<T>> wMap = new EnumMap<NativeKey, Set<T>>(
				NativeKey.class);

		private Set<T> wSet = new HashSet<T>();

		public Set<T> getWidget(NativeKey nk) {
			Set<T> s = wMap.get(nk);
			if (s == null) {
//				throw new IllegalNativeKey(nk.name()); //WARRNING правильно ли?
				return Collections.emptySet();
			}
			return s;
		}

		public Set<T> getAllWidget() {
			return wSet;
		}

		public void addWidget(NativeKey nk, final T w) {
			Set<T> s = wMap.get(nk);
			if (s == null) {
				s = new HashSet<T>();
				wMap.put(nk, s);
			}
			s.add(w);
			wSet.add(w);
		}
	}
	
	private KeyBlock keyBlock;
	
	@Override
	public KeyBlock getKeyBlock() {
		return keyBlock;
	}

	@Override
	public void setKeyBlock(KeyBlock keyBlock) {
		this.keyBlock = keyBlock;
		placeKeys(keyBlock);
	}

	private void placeKeys(KeyBlock kb) {
		Button b;
		Label l;
		
		List<KeyRow> kr = kb.getBlock();
		for (int h = 0; h < kr.size(); h++) {
			List<Key> ks = kr.get(h).getRow();
			for (int v = 0; v < ks.size(); v++) {
				Key k = ks.get(v);
				int width = WIDTH_KEY;
				if (k.getWidth()!=null){
					width = Integer.parseInt(k.getWidth());
				}
				b = new Button();
				l = new Label(k.getNativeKey().getLabelText());
				l.setWordWrap(true);
				l.setHorizontalAlignment(HorizontalAlignmentConstant.startOf(Direction.LTR));
				l.setWidth(width - LABEL_LEFT_MARGIN - LABEL_RIGHT_MARGIN + "px");
				b.setWidth(width + "px");
				b.setHeight(HEIGHT_KEY + "px");
				b.setStyleName("keyboardKey");
				labelMap.addWidget(k.getNativeKey(), l);
				buttonMap.addWidget(k.getNativeKey(), b);

				keysPanel.add(b, LEFT_MARGIN + h * VERTICAL_SHIFT
						+ (v * (VERTICAL_SPACE + width)), TOP_MARGIN
						+ (h * (HORIZONTAL_SPACE + HEIGHT_KEY)));
				keysPanel.add(l,
						LEFT_MARGIN + h * VERTICAL_SHIFT
								+ (v * (VERTICAL_SPACE + width))
								+ LABEL_LEFT_MARGIN, TOP_MARGIN
								+ (h * (HORIZONTAL_SPACE + HEIGHT_KEY))
								+ LABEL_TOP_MARGIN);
			}
		}
	}
	
	@Override
	public void setAlternateveKeyLayout(AlternativeKeyLayout alternateveKeyLayout) {
		this.alternateveKeyLayout = alternateveKeyLayout;
		Label l;

		List<KeyRow> kr = keyBlock.getBlock();
		for (int h = 0; h < kr.size(); h++) {
			List<Key> ks = kr.get(h).getRow();
			for (int v = 0; v < ks.size(); v++) {
				Key k = ks.get(v);
				if (alternateveKeyLayout.isDefineText(k.getNativeKey())) {
					l = new Label(
							alternateveKeyLayout.getLabelText(k.getNativeKey()));
					l.setWordWrap(true);
					l.setHorizontalAlignment(HorizontalAlignmentConstant.startOf(Direction.RTL));
					l.setWidth(WIDTH_KEY - ALT_LABEL_RIGHT_MARGIN - ALT_LABEL_LEFT_MARGIN + "px");
					altLabelMap.addWidget(k.getNativeKey(), l);
					keysPanel.add(l, LEFT_MARGIN + h * VERTICAL_SHIFT
							+ (v * (VERTICAL_SPACE + WIDTH_KEY)) + ALT_LABEL_LEFT_MARGIN, 
							TOP_MARGIN + (h * (HORIZONTAL_SPACE + HEIGHT_KEY))
							+ HEIGHT_KEY - ALT_LABEL_BOTTOM_MARGIN);
				}
			}
		}
	}

	
}
