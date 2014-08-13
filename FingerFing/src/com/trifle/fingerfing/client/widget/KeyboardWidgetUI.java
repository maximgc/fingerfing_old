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
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.Widget;
import com.trifle.fingerfing.client.AlternativeKeyLayout;
import com.trifle.fingerfing.client.NativeKey;
import com.trifle.fingerfing.client.widget.effect.Effect;

public class KeyboardWidgetUI extends Composite implements KeyboardWidget {

	private static KeyboardWidgetUIFlexUiBinder uiBinder = GWT
			.create(KeyboardWidgetUIFlexUiBinder.class);

	interface KeyboardWidgetUIFlexUiBinder extends
			UiBinder<Widget, KeyboardWidgetUI> {
	}

	public KeyboardWidgetUI() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiField
	AbsolutePanel keysPanel;


	@Override
	public void setEffect(NativeKey n, Effect e) {
		for (KeyWidget w : buttonMap.getWidget(n)) {
			e.apply(w);
		}
		for (KeyWidget w : labelMap.getWidget(n)) {
			e.apply(w);
		}
		for (KeyWidget w : altLabelMap.getWidget(n)) { //WARRNING Возможны ошибки т.к. не для всех n есть лейбл
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
	public void setEffect(List<NativeKey> sn, Effect e) {
		setEffect(EnumSet.copyOf(sn), e);
	}

	@Override
	public void setEffect(Set<NativeKey> sn, Effect e) {
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

	private final int LEFT_MARGIN = 10;
	private final int TOP_MARGIN = 10;
	private final int WIDTH_KEY = 45;
	private final int HEIGHT_KEY = 45;
	private final int VERTICAL_SPACE = 6;
	private final int HORIZONTAL_SPACE = 6;
	private final int HORIZONTAL_SHIFT = 0;
	// private final int HORIZONTAL_SHIFT = 7;

	private final int LABEL_LEFT_MARGIN = 5;
	private final int LABEL_RIGHT_MARGIN = 10;
	private final int LABEL_TOP_MARGIN = 3;
	private final int ALT_LABEL_LEFT_MARGIN = LABEL_LEFT_MARGIN; //20;
	private final int ALT_LABEL_RIGHT_MARGIN = LABEL_RIGHT_MARGIN; //5;
	private final int ALT_LABEL_BOTTOM_MARGIN = 23;

	private NativeMap<KeyButton> buttonMap = new NativeMap<KeyButton>();
	private NativeMap<KeyLabel> labelMap = new NativeMap<KeyLabel>();
	private NativeMap<KeyLabel> altLabelMap = new NativeMap<KeyLabel>();

	
	public static interface KeyWidget extends IsWidget{
		void setPosition(int l, int t);
		int getLeft();
		void setLeft(int l);
		int getTop();
		void setTop(int t);
	}
	
	
	
	public static class KeyButton extends ToggleButton implements KeyWidget{

		int left, top;
		
		public KeyButton() {
			super();
			setDown(false);
		}
		
		public KeyButton(String arg0) {
			super(arg0);
			setDown(false);
		}

		@Override
		public int getLeft() {
			return left;
		}

		@Override
		public void setLeft(int left) {
			this.left = left;
		}

		@Override
		public int getTop() {
			return top;
		}

		@Override
		public void setTop(int top) {
			this.top = top;
		}

		@Override
		public void setPosition(int l, int t) {
			setLeft(l);
			setTop(t);
		}

	}

	public static class KeyLabel extends Label implements KeyWidget{

		int left, top;
		
		public KeyLabel() {
			super();
		}
		
		public KeyLabel(String arg0) {
			super(arg0);
		}

		@Override
		public int getLeft() {
			return left;
		}

		@Override
		public void setLeft(int left) {
			this.left = left;
		}

		@Override
		public int getTop() {
			return top;
		}

		@Override
		public void setTop(int top) {
			this.top = top;
		}
		
		@Override
		public void setPosition(int l, int t) {
			setLeft(l);
			setTop(t);
		}

	}

	
	private AlternativeKeyLayout alternateveKeyLayout;
	
	@Override
	public AlternativeKeyLayout getAlternateveKeyLayout() {
		return alternateveKeyLayout;
	}

	private class NativeMap<T extends KeyWidget> {
		
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
		KeyButton b;
		KeyLabel l;
		
		List<KeyRow> kr = kb.getBlock();
		int lbPos = LEFT_MARGIN;
		int tPos = TOP_MARGIN;
		for (int h = 0; h < kr.size(); h++) {
			List<Key> ks = kr.get(h).getRow();
			int lPos = lbPos;
			for (int v = 0; v < ks.size(); v++) {
				Key k = ks.get(v);
				int width = WIDTH_KEY;
				if (k.getWidth()!=null){
					width = Integer.parseInt(k.getWidth());
				}
				b = new KeyButton();
				l = new KeyLabel(k.getNativeKey().getLabelText());
				l.setWordWrap(true);
				l.setHorizontalAlignment(HorizontalAlignmentConstant.startOf(Direction.LTR));
				l.setWidth(width - LABEL_LEFT_MARGIN - LABEL_RIGHT_MARGIN + "px");
				b.setWidth(width + "px");
				b.setHeight(HEIGHT_KEY + "px");
				b.setStyleName("keyboardKey");
				labelMap.addWidget(k.getNativeKey(), l);
				buttonMap.addWidget(k.getNativeKey(), b);

				
				keysPanel.add(b, lPos, tPos );
				b.setPosition(lPos, tPos);
				keysPanel.add(l, lPos + LABEL_LEFT_MARGIN, tPos + LABEL_TOP_MARGIN);
				l.setPosition(lPos + LABEL_LEFT_MARGIN, tPos + LABEL_TOP_MARGIN);
				lPos += width + HORIZONTAL_SPACE;
			}
			lbPos += HORIZONTAL_SHIFT;
			tPos += HEIGHT_KEY + VERTICAL_SPACE;
		}
	}
	
	@Override
	public void setAlternateveKeyLayout(AlternativeKeyLayout alternateveKeyLayout) {
		this.alternateveKeyLayout = alternateveKeyLayout;
		KeyLabel l;

		List<KeyRow> kr = keyBlock.getBlock();
		int lbPos = LEFT_MARGIN;
		int tPos = TOP_MARGIN;
		for (int h = 0; h < kr.size(); h++) {
			List<Key> ks = kr.get(h).getRow();
			int lPos = lbPos;
			for (int v = 0; v < ks.size(); v++) {
				Key k = ks.get(v);
				int width = WIDTH_KEY;
				if (k.getWidth()!=null){
					width = Integer.parseInt(k.getWidth());
				}
				if (alternateveKeyLayout.isDefineText(k.getNativeKey())) {
					l = new KeyLabel(
							alternateveKeyLayout.getLabelText(k.getNativeKey()));
					l.setWordWrap(true);
					l.setHorizontalAlignment(HorizontalAlignmentConstant.startOf(Direction.RTL));
					l.setWidth(width - ALT_LABEL_RIGHT_MARGIN - ALT_LABEL_LEFT_MARGIN + "px");
					altLabelMap.addWidget(k.getNativeKey(), l);
					keysPanel.add(l, lPos + ALT_LABEL_LEFT_MARGIN, 
							tPos + HEIGHT_KEY - ALT_LABEL_BOTTOM_MARGIN);
					l.setPosition(lPos + ALT_LABEL_LEFT_MARGIN, tPos + HEIGHT_KEY - ALT_LABEL_BOTTOM_MARGIN);
				}
				lPos += width + HORIZONTAL_SPACE;
			}
			lbPos += HORIZONTAL_SHIFT;
			tPos += HEIGHT_KEY + VERTICAL_SPACE;
		}
	}
	
	
	public static class EffectCenterCard implements Effect{

		public void apply(KeyWidget w) {
			System.out.println("KeyWidget");
		}

		@Override
		public void apply(IsWidget w) {
			System.out.println("Widget");
		}

	}

	
}
