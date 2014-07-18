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
import com.trifle.fingerfing.client.calcs.StatCalcConvergent;
import com.trifle.fingerfing.client.lesson.ExerciseController;
import com.trifle.fingerfing.client.widget.Effects;
import com.trifle.fingerfing.client.widget.KeyboardWidget;
import com.trifle.fingerfing.client.widget.KeyboardWidgetUIFlex;
import com.trifle.fingerfing.client.widget.SensorData;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */

public class FingerFing implements EntryPoint {

	public void onModuleLoad() {
		
		final Label errorLabel = new Label();
		final KeyboardWidget keyWidget = new KeyboardWidgetUIFlex();
		final FocusPanel fp = new FocusPanel();
		final SensorData sd = new SensorData();

		fp.add(keyWidget);

		RootPanel.get("mainWidgetField").add(sd);
		RootPanel.get("mainWidgetField").add(fp);
		RootPanel.get("errorWidgetField").add(errorLabel);

		final ExerciseController ex = new ExerciseController();

		fp.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				fp.setFocus(true);
			}
		});

		keyWidget.setEffectAll(Effects.effectDisable);
		keyWidget.setEffectAll(ex.getWorkingSet(), Effects.effectEnable);
		keyWidget.setEffect(ex.getExerciseKey(), Effects.effectExpect);

		final StatCalcConvergent statCalc = new StatCalcConvergent();

		fp.addKeyDownHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent event) {
				NativeKey keyDown = NativeKey.getByNativeCode(event
						.getNativeKeyCode());
				keyWidget.setEffect(ex.getExerciseKey(), Effects.effectUp); 
				ex.setAnswerKey(keyDown);
				keyWidget.setEffect(keyDown, Effects.effectPress);
				statCalc.addRecord(System.currentTimeMillis(),
						ex.getLastEvaluate());

				sd.setData(statCalc.calcAllTime(),
						statCalc.calcConvergentMeanSpeed(),
						statCalc.calcFullDensityError(),
						statCalc.calcFullMeanSpeed(), statCalc.calcCount(),
						statCalc.calcConvergentMeanDeviation(), statCalc.calcStepDeviation());
				
				keyWidget.setEffectAll(Effects.effectDisable);
				keyWidget.setEffectAll(ex.getWorkingSet(), Effects.effectEnable); //FIXME Очень неэффективно
			}
		});

		fp.addKeyUpHandler(new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				NativeKey keyUp = NativeKey.getByNativeCode(event
						.getNativeKeyCode());
				keyWidget.setEffect(keyUp, Effects.effectUp);
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
