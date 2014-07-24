package com.trifle.fingerfing.client;

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
import com.trifle.fingerfing.client.calcs.BonusMultiplier;
import com.trifle.fingerfing.client.calcs.StatCalcConvergent;
import com.trifle.fingerfing.client.lesson.ExerciseController;
import com.trifle.fingerfing.client.widget.Effects;
import com.trifle.fingerfing.client.widget.ExcerciseProgress;
import com.trifle.fingerfing.client.widget.KeyboardWidget;
import com.trifle.fingerfing.client.widget.KeyboardWidgetUIFlex;
import com.trifle.fingerfing.client.widget.SensorIndicator;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */

public class FingerFing implements EntryPoint {

	public void onModuleLoad() {
		
		final Label errorLabel = new Label();
		final KeyboardWidget keyWidget = new KeyboardWidgetUIFlex();
		final FocusPanel fp = new FocusPanel();
		final SensorIndicator sensorIndicator = new SensorIndicator();
		final ExcerciseProgress ep = new ExcerciseProgress();

		fp.add(keyWidget);

		RootPanel.get("mainWidgetField").add(ep);
		RootPanel.get("mainWidgetField").add(sensorIndicator);
		RootPanel.get("mainWidgetField").add(fp);
		RootPanel.get("errorWidgetField").add(errorLabel);

		final ExerciseController ex = new ExerciseController(new Evaluator(), new StatCalcConvergent(), new BonusMultiplier());

		fp.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				fp.setFocus(true);
			}
		});

		keyWidget.setEffectAll(Effects.effectDisable);
		keyWidget.setEffectAll(ex.getWorkingSet(), Effects.effectEnable);
		keyWidget.setEffect(ex.getExerciseKey(), Effects.effectExpect);

		fp.addKeyDownHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent event) {
				
				NativeKey keyDown = NativeKey.getByNativeCode(event
						.getNativeKeyCode());
				
				keyWidget.setEffect(ex.getExerciseKey(), Effects.effectDefault); 
				
				ex.setAnswerKey(keyDown); //FIXME баг при finalCount=1 или 2, новый workingSet почемуто не отображется
				
				
				sensorIndicator.setData(ex.getSc().getFullCount(),
						ex.getSc().getFullTimeMillis(),
						ex.getSc().getFullSuccessDensity(),
						ex.getSc().getFullMeanSpeed(), 
						ex.getSc().getLastMeanSpeed(),
						ex.getSc().getLastMeanInTempo());
				
				sensorIndicator.setMultiplers(ex.getFullScore(), ex.getAwardedScore(), ex.getSimpleScore(), ex.getBm().getMultiplier(), ex.getBm().getForSpeed(), ex.getBm().getForTemp(), ex.getBm().getForCorrect(), ex.getBm().getForSuccess());
				
				ep.setType(ex.getType());
				ep.setFinalValue(ex.getFinalValue());
				ep.setCurValue(ex.getCurValue());
				
				keyWidget.setEffect(keyDown, Effects.effectPress);
				keyWidget.setEffectAll(Effects.effectDisable);
				keyWidget.setEffectAll(ex.getWorkingSet(), Effects.effectEnable); //FIXME Очень неэффективно
			}
		});

		fp.addKeyUpHandler(new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				NativeKey keyUp = NativeKey.getByNativeCode(event
						.getNativeKeyCode());
				keyWidget.setEffect(keyUp, Effects.effectDefault);
				keyWidget.setEffect(ex.getExerciseKey(), Effects.effectExpect);
			}
		});
		
		fp.setFocus(true);
	}

	// fp.addKeyDownHandler(new KeyDownHandler() {
	//
	// @Override
	// public void onKeyDown(KeyDownEvent event) {
	// System.out.println("NativeKey."+NativeKey.getByNativeCode(event.getNativeKeyCode()).toString()+", ");
	//
	// }
	// });

}
