package com.trifle.fingerfing.client.widget_old;

import com.trifle.fingerfing.client.widget_old.effect.EColor;
import com.trifle.fingerfing.client.widget_old.effect.Effect;
import com.trifle.fingerfing.client.widget_old.effect.EffectBgColor;
import com.trifle.fingerfing.client.widget_old.effect.EffectTransparent;

public interface Effects {
	
	final Effect effectPress = new EffectBgColor(new EColor(10, 10, 10));
	final Effect effectDefault = new EffectBgColor(new EColor(240, 240, 240));
	final Effect effectExpect = new EffectBgColor(new EColor(140, 240, 140));

	final Effect effectDisable = new EffectTransparent(0.2);
	final Effect effectEnable = new EffectTransparent(1);
	
}
