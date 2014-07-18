package com.trifle.fingerfing.client.widget;

import com.trifle.fingerfing.client.widget.effect.EColor;
import com.trifle.fingerfing.client.widget.effect.Effect;
import com.trifle.fingerfing.client.widget.effect.EffectBgColor;
import com.trifle.fingerfing.client.widget.effect.EffectTransparent;

public interface Effects {
	
	final Effect effectPress = new EffectBgColor(new EColor(10, 10, 10));
	final Effect effectUp = new EffectBgColor(new EColor(240, 240, 240));
	final Effect effectExpect = new EffectBgColor(new EColor(140, 240, 140));

	final Effect effectDisable = new EffectTransparent(0.2);
	final Effect effectEnable = new EffectTransparent(1);
	
}
