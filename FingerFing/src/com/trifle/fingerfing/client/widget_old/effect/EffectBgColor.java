package com.trifle.fingerfing.client.widget_old.effect;

import com.google.gwt.user.client.ui.IsWidget;

public class EffectBgColor implements Effect {

	@SuppressWarnings("unused")
	private EffectBgColor(){
		
	}
	
	private EColor color;
	
	public EffectBgColor(EColor c){
		color = c;
	}
	
	@Override
	public void apply(IsWidget w) {
		w.asWidget().getElement().getStyle().setBackgroundColor(color.toStringRgbFomat());
	}

}
