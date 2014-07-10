package com.trifle.fingerfing.client;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.trifle.fingerfing.client.widget.KeyboardWidget;
import com.trifle.fingerfing.client.widget.SensorData;
import com.trifle.fingerfing.client.widget.effect.EColor;
import com.trifle.fingerfing.client.widget.effect.Effect;
import com.trifle.fingerfing.client.widget.effect.EffectBgColor;
import com.trifle.fingerfing.client.widget.effect.EffectTransparent;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */


public class FingerFing implements EntryPoint {

	int step;
	int cpress;
	List<NativeKey> curList;
	NativeKey curKey;
	int curCount;
	Random rnd;
	
	public void onModuleLoad() {
		final Label errorLabel = new Label();
		final KeyboardWidget keyWidget = new KeyboardWidget();
		final FocusPanel fp = new FocusPanel();
		final SensorData sd = new SensorData();
		
		fp.add(keyWidget);

		RootPanel.get("mainWidgetField").add(sd);
		RootPanel.get("mainWidgetField").add(fp);
		RootPanel.get("errorWidgetField").add(errorLabel);

		final Effect effectPress = new EffectBgColor(new EColor(10,10,10));
		final Effect effectUp = new EffectBgColor(new EColor(240,240,240));
		final Effect effectExpect = new EffectBgColor(new EColor(140,240,140));
		
		final Effect effectDisable = new EffectTransparent(0.2);
		final Effect effectEnable = new EffectTransparent(1);
		
		
		@SuppressWarnings("serial")
		final List<Set<NativeKey>> setNk = new ArrayList<Set<NativeKey>>(){
			{
				add(EnumSet.of(NativeKey.KEY_F, NativeKey.KEY_J, NativeKey.KEY_G, NativeKey.KEY_H));
				add(EnumSet.of(NativeKey.KEY_R, NativeKey.KEY_T, NativeKey.KEY_Y, NativeKey.KEY_U));
				add(EnumSet.of(NativeKey.KEY_V, NativeKey.KEY_B, NativeKey.KEY_N, NativeKey.KEY_M));
			}
		};
		
		keyWidget.setEffectAll(effectDisable);
		rnd = new Random();
		step = 0;
		cpress = 0;
		curList = new ArrayList<NativeKey>(setNk.get(step));
		curCount = curList.size();
		curKey = curList.get(rnd.nextInt(curCount));
		keyWidget.setEffectAll(curList, effectEnable);
		keyWidget.setEffect(curKey, effectExpect);
		
		fp.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				fp.setFocus(true);
			}
		});
		
		
		final StatCalc statCalc = new StatCalcConvergent(); 
		
		fp.addKeyDownHandler(new KeyDownHandler() {
			
			@Override
			public void onKeyDown(KeyDownEvent event) {
				keyWidget.setEffect(curKey, effectUp);
				keyWidget.setEffect(NativeKey.getByNativeCode(event.getNativeKeyCode()), effectPress);
				statCalc.addRecord(System.currentTimeMillis(), 1);
				sd.setData(statCalc.calcAllTime(), statCalc.calcMeanSpeed(), statCalc.calcDensityError());
				System.out.println(60000/statCalc.calcMeanTime());
				System.out.println(statCalc.toDebugString());
				if (cpress++ > 20 && step<setNk.size()-1) {
					cpress = 0;
					step++;
					curList.addAll(setNk.get(step));
					curCount = curList.size();
					keyWidget.setEffectAll(setNk.get(step), effectEnable);
				}
			}
		});
		
		fp.addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				keyWidget.setEffect(NativeKey.getByNativeCode(event.getNativeKeyCode()), effectUp);
				curKey = curList.get(rnd.nextInt(curCount));
				keyWidget.setEffect(curKey, effectExpect);
			}
		});
		
		fp.setFocus(true);
		
	}
}
