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
import com.trifle.fingerfing.client.calcs.BonusMultiplier;
import com.trifle.fingerfing.client.calcs.StatCalc;
import com.trifle.fingerfing.client.resources.FFResources;

public class ExerciseController {

	
	private StatCalc sc;
	private BonusMultiplier bm;
	


	private int curStep = -1;
	private int fullCount = -1;
	private WorkingSets wSets;
	private List<NativeKey> curKeys;
	private NativeKey exerciseKey;
	private NativeKey answeredKey;
	private Evaluator ev;
	private int lastEvaluate;
	private MethodKeySelect curMethodSelect;
	private long fullScore;
	private long simpleScore;
	private long awardedScore;

	private long finalCount;
	private long finalScore;


	public ExerciseController(Evaluator ev, StatCalc sc, BonusMultiplier bm) {
		this.sc = sc;
		this.bm = bm;
		this.ev = ev;
		init();
	}
	
	@SuppressWarnings("unused")
	private ExerciseController() {
		// TODO Auto-generated constructor stub
	}

	private void init() {
		initWorkingSets(FFResources.INST.getWorkingSets().getText());
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

//		System.out.println(json);
	}

	private void goNextExerciseKey() {
		if ((finalCount != 0 && ++fullCount >= finalCount)
				|| (finalScore != 0 && fullScore >= finalScore)) {
			fullCount = 0;
			fullScore = 0;
			goNextWorkingSet();
		}
		exerciseKey = curMethodSelect.select(curKeys);
	}
	
	
	public static final int TYPE_COUNT = 0;
	public static final int TYPE_SCORE = 1;

	private int type;

	private void goNextWorkingSet() {
		if (curStep < wSets.getWorkingSets().size() - 1) {
			curStep++;
			Keys keys = wSets.getWorkingSets().get(curStep);

			curKeys = keys.getKeys();
			finalCount = 0;
			finalScore = 0;
			
			
			if (keys.getFinalScore() == null && keys.getFinalCount() == null) {
				finalCount = keys.getKeys().size();
				type = TYPE_COUNT;
			} else {
				if (keys.getFinalCount() != null) {
					finalCount = Integer.parseInt(keys.getFinalCount());
					type = TYPE_COUNT;
				}
				if (keys.getFinalScore() != null) {
					finalScore = Integer.parseInt(keys.getFinalScore());
					finalCount = 0;
					type = TYPE_SCORE;
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

	private long lastTimestamp = System.currentTimeMillis();

	public void setAnswerKey(NativeKey key) {
		answeredKey = key;
		lastEvaluate = ev.check(exerciseKey, answeredKey);

		sc.addRecord(System.currentTimeMillis() - lastTimestamp, lastEvaluate);
		bm.nextDate(sc.getLastMeanSpeed(), sc.getLastMeanInTempo(),
				sc.getFullSuccessDensity(), lastEvaluate);
		simpleScore = (long) sc.getStepSpeed();
		awardedScore = (long) (bm.getMultiplier() * simpleScore);
		fullScore += awardedScore;
		goNextExerciseKey();
		if (lastEvaluate > 0) {		
			lastTimestamp = System.currentTimeMillis();
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

	
	public int getLastEvaluate() {
		return lastEvaluate;
	}

	
	public StatCalc getSc() {
		return sc;
	}

	public void setSc(StatCalc sc) {
		this.sc = sc;
	}

	public BonusMultiplier getBm() {
		return bm;
	}

	public void setBm(BonusMultiplier bm) {
		this.bm = bm;
	}

	public long getFullScore() {
		return fullScore;
	}
	
	public long getSimpleScore() {
		return simpleScore;
	}

	public long getAwardedScore() {
		return awardedScore;
	}
	
	public long getCurValue() {
		switch (type) {
		case TYPE_COUNT:
			return fullCount;
		case TYPE_SCORE:
			return fullScore;
		default:
			return fullCount;
		}
		// return finalCount;
	}

	public long getFinalValue() {
		switch (type) {
		case TYPE_COUNT:
			return finalCount;
		case TYPE_SCORE:
			return finalScore;
		default:
			return finalCount;
		}
		// return finalScore;
	}
	
	public int getType() {
		return type;
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
