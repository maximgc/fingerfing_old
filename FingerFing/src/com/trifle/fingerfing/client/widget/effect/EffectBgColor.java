package com.trifle.fingerfing.client.widget.effect;

import com.google.gwt.user.client.ui.Widget;

public class EffectBgColor implements Effect {

	@SuppressWarnings("unused")
	private EffectBgColor(){
		
	}
	
	private EColor color;
	
	public EffectBgColor(EColor c){
		color = c;
	}
	
	@Override
	public void apply(Widget w) {
		w.getElement().getStyle().setBackgroundColor(color.toStringRgbFomat());
	}

}
