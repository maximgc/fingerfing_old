package com.trifle.fingerfing.client.lesson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.gwt.core.shared.GWT;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import com.trifle.fingerfing.client.Evaluator;
import com.trifle.fingerfing.client.NativeKey;
import com.trifle.fingerfing.client.resources.FFResources;

public class ExerciseController {

	private int curStep = -1;
	private int curKeyStep = -1;
	private int curScore;
	private WorkingSets wSets;
	private List<NativeKey> curKeys;
	private NativeKey exerciseKey;
	private NativeKey answeredKey;
	private Evaluator evaluator;
	private int lastEvaluate;
	private MethodKeySelect curMethodSelect;
	private int curFinalCount;
	private long curFinalScore;

	public ExerciseController() {
		init();
	}

	private void init() {
		initWorkingSets(FFResources.INST.getWorkingSets().getText());
		evaluator = new Evaluator();
		goNextWorkingSet();
		goNextExerciseKey();
	}

	private void initWorkingSets(String jsonText) {
		BeanFactory factory = GWT.create(BeanFactory.class);
		wSets = AutoBeanCodex.decode(factory, WorkingSets.class, jsonText).as();
	}

	@SuppressWarnings("unused")
	private void initWorkingSets() {
		// простой пример хардкодного набора заданий
		NativeKey[][] wsArray = {
				{ NativeKey.KEY_F, NativeKey.KEY_J, NativeKey.KEY_G,
						NativeKey.KEY_H },
				{ NativeKey.KEY_R, NativeKey.KEY_T, NativeKey.KEY_Y,
						NativeKey.KEY_U },
				{ NativeKey.KEY_V, NativeKey.KEY_B, NativeKey.KEY_N,
						NativeKey.KEY_M } };

		BeanFactory factory = GWT.create(BeanFactory.class);

		wSets = factory.makeList().as();
		wSets.setWorkingSets(new ArrayList<Keys>());

		for (NativeKey[] s : wsArray) {
			Keys ws = factory.makeSet().as();
			ws.setKeys(Arrays.asList(s));
			wSets.getWorkingSets().add(ws);
		}

		String json = AutoBeanCodex.encode(AutoBeanUtils.getAutoBean(wSets))
				.getPayload();

		System.out.println(json);
	}

	private void goNextExerciseKey() {
		System.out.println("before curFinalCount: " + curFinalCount + ", curKeyStep: "
				+ curKeyStep + ", curFinalScore: " + curFinalScore
				+ ", curFinalScore: " + curFinalScore);
		if ((curFinalCount != 0 && ++curKeyStep >= curFinalCount)
				|| (curFinalScore != 0 && ++curScore >= curFinalScore)) {
			curKeyStep = 0;
			curScore = 0;
			goNextWorkingSet();
		}
		exerciseKey = curMethodSelect.select(curKeys);
		System.out.println("after curFinalCount: " + curFinalCount + ", curKeyStep: "
				+ curKeyStep + ", curFinalScore: " + curFinalScore
				+ ", curScore: " + curScore);
	}

	private void goNextWorkingSet() {
		if (curStep < wSets.getWorkingSets().size() - 1) {
			curStep++;
			Keys keys = wSets.getWorkingSets().get(curStep);

			curKeys = keys.getKeys();
			curFinalCount = 0;
			curFinalScore = 0;
			
			
			if (keys.getFinalScore() == null && keys.getFinalCount() == null) {
				curFinalCount = keys.getKeys().size();
			} else {
				if (keys.getFinalCount() != null) {
					curFinalCount = Integer.parseInt(keys.getFinalCount());
				}
				if (keys.getFinalScore() != null) {
					curFinalScore = Integer.parseInt(keys.getFinalScore());
				}
			}

			switch (keys.getMethodSelectName()) {
			case "order":
				curMethodSelect = new MethodKeySelectOrder();
				break;
			case "random":
				curMethodSelect = new MethodKeySelectRandom();
				break;
			default:
				curMethodSelect = new MethodKeySelectOrder();
			}

			System.out.println(curKeys);
		}
	}

	public List<NativeKey> getWorkingSet() {
		return curKeys;
	}

	public NativeKey getExerciseKey() {
		return exerciseKey;
	}

	public NativeKey getRightAnswerKey() {
		return exerciseKey;
	}

	public void setAnswerKey(NativeKey key) {
		answeredKey = key;
		lastEvaluate = evaluator.check(exerciseKey, answeredKey);
		goNextExerciseKey();
	}

	public int getLastEvaluate() {
		return lastEvaluate;
	}

	public static interface BeanFactory extends AutoBeanFactory {

		AutoBean<WorkingSets> makeList();

		AutoBean<Keys> makeSet();
	}

	public static interface WorkingSets {
		List<Keys> getWorkingSets();

		void setWorkingSets(List<Keys> l);
	}

	public static interface Keys {

		String getFinalScore();

		void setFinalScore(String c);

		String getFinalCount();

		void setFinalCount(String c);

		String getMethodSelectName();

		void setMethodSelectName(String m);

		List<NativeKey> getKeys();

		void setKeys(List<NativeKey> s);
	}

}
