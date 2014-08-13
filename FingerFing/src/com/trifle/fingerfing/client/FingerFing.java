package com.trifle.fingerfing.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.trifle.fingerfing.client.calcs.BonusMultiplier;
import com.trifle.fingerfing.client.calcs.StatCalcConvergent;
import com.trifle.fingerfing.client.json.BeanManager;
import com.trifle.fingerfing.client.lesson.ExerciseController;
import com.trifle.fingerfing.client.resources.FFResources;
import com.trifle.fingerfing.client.widget.Effects;
import com.trifle.fingerfing.client.widget.ExcerciseProgress;
import com.trifle.fingerfing.client.widget.KeyboardWidget;
import com.trifle.fingerfing.client.widget.KeyboardWidget.KeyBlock;
import com.trifle.fingerfing.client.widget.constructor.JSONConstructorWidget;
import com.trifle.fingerfing.client.widget.newage.HorizontalProgressBar;
import com.trifle.fingerfing.client.widget.newage.ProgressBarBase;
import com.trifle.fingerfing.client.widget.newage.VerticalProgressBar;
import com.trifle.fingerfing.client.widget.KeyboardWidgetUI;
import com.trifle.fingerfing.client.widget.SensorIndicator;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */

public class FingerFing implements EntryPoint {

	 int i = 100;
	
//	@Override
//	public void onModuleLoad() {
//		
//		final ProgressBarBase b = new VerticalProgressBar(30, 35, 1, 0.0, "12");
//		final ProgressBarBase bh = new HorizontalProgressBar(60, 1, 5, 0.0, "14");
////		Label l = new Label("HI!");
//		
////		RootPanel.get("mainWidgetField").add(l);
//		RootPanel.get("mainWidgetField").add(b);
//		RootPanel.get("mainWidgetField").add(bh);
//		
//		final Timer t = new Timer() {
//			
//			@Override
//			public void run() {
//				
//				if (i--<1) {b.setValue(0.5); b.setSegmentCount(70);
//				bh.setValue(0.5);this.cancel(); } else {
//				
//				if (i==50) {
//					b.setSegmentCount(40);
//					bh.setSegmentCount(100);
//				}
//				
//					b.setValue((double)i/100);
//					bh.setValue((double)i/100);
//				}
//			}
//		};
//
//		t.scheduleRepeating(50);
//	}

	
	
	public void onModuleLoad() {
		
		final Label errorLabel = new Label();
		final KeyboardWidget keyWidget = new KeyboardWidgetUI();
		final FocusPanel fp = new FocusPanel();
		final SensorIndicator sensorIndicator = new SensorIndicator();
		final ExcerciseProgress ep = new ExcerciseProgress();

		BeanManager bm = new BeanManager();
		final JSONConstructorWidget jsonC = new JSONConstructorWidget(bm);
		
		fp.add(keyWidget);
		KeyBlock keyBlock = bm.new KeyboardWidgetBeans().decodeKeyBlock(FFResources.INST.getKeyBlockBase().getText());
		keyWidget.setKeyBlock(keyBlock);
		AlternativeKeyLayout alternateveKeyLayoutRU = new AlternativeKeyLayout(
				bm.new AlternativeKeyLayoutBeans().decodeLabelTextMap(FFResources.INST
						.getAlternativeKeyLayoutRU().getText()));
		keyWidget.setAlternateveKeyLayout(alternateveKeyLayoutRU);
		

		RootPanel.get("mainWidgetField").add(ep);
		RootPanel.get("mainWidgetField").add(sensorIndicator);
		RootPanel.get("mainWidgetField").add(fp);
		RootPanel.get("mainWidgetField").add(jsonC);
		RootPanel.get("errorWidgetField").add(errorLabel);

		
		final ExerciseController ex = new ExerciseController(bm.new WorkingSetsBeans().decodeWorkingSets(FFResources.INST.getWorkingSets().getText()), new Evaluator(), new StatCalcConvergent(), new BonusMultiplier());
		
		fp.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				fp.setFocus(true);
			}
		});
				
		keyWidget.setEffectAll(Effects.effectDisable);
		keyWidget.setEffect(ex.getWorkingSet(), Effects.effectEnable);
		keyWidget.setEffect(ex.getExerciseKey(), Effects.effectExpect);
		keyWidget.setEffectAll(new KeyboardWidgetUI.EffectCenterCard());

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
				keyWidget.setEffect(ex.getWorkingSet(), Effects.effectEnable); //FIXME Очень неэффективно
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
