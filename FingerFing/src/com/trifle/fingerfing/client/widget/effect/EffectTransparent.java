package com.trifle.fingerfing.client.widget.effect;

import com.google.gwt.user.client.ui.Widget;

public class EffectTransparent implements Effect {

	double op;
	
	@SuppressWarnings("unused")
	private EffectTransparent() {
	}
	
	public EffectTransparent(double op) {
		this.op = op;
	}
	
	@Override
	public void apply(Widget w) {
		w.getElement().getStyle().setOpacity(op);
	}

}
