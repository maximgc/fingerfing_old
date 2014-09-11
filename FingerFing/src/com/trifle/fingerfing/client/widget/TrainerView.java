package com.trifle.fingerfing.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FocusPanel;
import com.trifle.fingerfing.client.AlternativeKeyLayout;
import com.trifle.fingerfing.client.Evaluator;
import com.trifle.fingerfing.client.NativeKey;
import com.trifle.fingerfing.client.calcs.BonusMultiplier;
import com.trifle.fingerfing.client.calcs.StatCalcConvergentOld1;
import com.trifle.fingerfing.client.json.BeanManager;
import com.trifle.fingerfing.client.lesson.ExerciseControllerOld1;
import com.trifle.fingerfing.client.resources.FFResources;
import com.trifle.fingerfing.client.widget.KeyboardWidget.KeyBlock;
import com.google.gwt.uibinder.client.UiHandler;

public class TrainerView extends Composite {

	private static TrainerViewUiBinder uiBinder = GWT
			.create(TrainerViewUiBinder.class);
	@UiField
	SensorIndicator indicators;
	@UiField
	ExcerciseProgress progress;
	@UiField
	KeyboardWidgetUI keyboard;
	@UiField
	FocusPanel focus;

	interface TrainerViewUiBinder extends UiBinder<Widget, TrainerView> {
	}

	public TrainerView() {
		initWidget(uiBinder.createAndBindUi(this));
		myInit();
	}

	ExerciseControllerOld1 ex;

	private void myInit() {
		BeanManager beanManger = new BeanManager();

		KeyBlock keyBlock = beanManger.new KeyboardWidgetBeans()
				.decodeKeyBlock(FFResources.INST.getKeyBlockBase().getText());
		AlternativeKeyLayout alternateveKeyLayoutRU = new AlternativeKeyLayout(
				beanManger.new AlternativeKeyLayoutBeans()
						.decodeLabelTextMap(FFResources.INST
								.getAlternativeKeyLayoutRU().getText()));

		keyboard.setKeyBlock(keyBlock);
		keyboard.setAlternateveKeyLayout(alternateveKeyLayoutRU);

		ex = new ExerciseControllerOld1(
				beanManger.new WorkingSetsBeans().decodeWorkingSets(FFResources.INST
						.getWorkingSets().getText()), new Evaluator(),
				new StatCalcConvergentOld1(), new BonusMultiplier());

		keyboard.setEffectAll(Effects.effectDisable);
		keyboard.setEffect(ex.getWorkingSet(), Effects.effectEnable);
		keyboard.setEffect(ex.getExerciseKey(), Effects.effectExpect);
		keyboard.setEffectAll(new KeyboardWidgetUI.EffectCenterCard());

	}

	@UiHandler("focus")
	void onFocusClick(ClickEvent event) {
		focus.setFocus(true);
	}

	@UiHandler("focus")
	void onFocusKeyDown(KeyDownEvent event) {
		NativeKey keyDown = NativeKey.getByNativeCode(event.getNativeKeyCode());

		keyboard.setEffect(ex.getExerciseKey(), Effects.effectDefault);

		ex.setAnswerKey(keyDown); // FIXME баг при finalCount=1 или 2,
									// новый workingSet почемуто не
									// отображется

		indicators.setLastMeanDeviation(ex.getSc().getLastMeanInTempo());
		indicators.setFullDensitySuccess(ex.getSc().getFullSuccessDensity());
		indicators.setFullMeanSpeed(ex.getSc().getFullMeanSpeed());
		indicators.setLastMaxMeanSpeed(ex.getSc().getLastMaxMeanSpeed());
		indicators.setLastMeanSpeed(ex.getSc().getLastMeanSpeed());
		indicators.setAllTime(ex.getSc().getFullTimeMillis());
		indicators.setCount(ex.getSc().getFullCount());
		// indicators.setInSpeed(ex.getBm().getLastCorrect());
		indicators.setInDensity(ex.getBm().getLastCorrect());
		indicators.setInTemp(ex.getBm().getLastInTemp());

		indicators.setMultiplers(ex.getFullScore(), ex.getAwardedScore(), ex
				.getSimpleScore(), ex.getBm().getMultiplier(), ex.getBm()
				.getForSpeed(), ex.getBm().getForTemp(), ex.getBm()
				.getForCorrect(), ex.getBm().getForSuccess());

		progress.setType(ex.getType());

		// FIXME сужение типов long
		progress.setFinalValue((int) ex.getFinalValue());
		progress.setCurValue((int) ex.getCurValue());
		keyboard.setEffect(keyDown, Effects.effectPress);

		// FIXME Очень неэффективно это же при каждом нажатии!
		// keyboard.setEffectAll(Effects.effectDisable);
		// keyboard.setEffect(ex.getWorkingSet(), Effects.effectEnable);
	}

	@UiHandler("focus")
	void onFocusKeyUp(KeyUpEvent event) {
		NativeKey keyUp = NativeKey.getByNativeCode(event.getNativeKeyCode());
		keyboard.setEffect(keyUp, Effects.effectDefault);
		keyboard.setEffect(ex.getExerciseKey(), Effects.effectExpect);
	}

	public void start() {
		focus.setFocus(true);

	}
}
