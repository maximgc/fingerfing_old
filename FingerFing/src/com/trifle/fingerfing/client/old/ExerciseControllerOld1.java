package com.trifle.fingerfing.client.old;

import java.util.List;

import com.trifle.fingerfing.client.Evaluator;
import com.trifle.fingerfing.client.NativeKey;
import com.trifle.fingerfing.client.calcs.BonusMultiplier;
import com.trifle.fingerfing.client.calcs.StatCalc;
import com.trifle.fingerfing.client.lesson.MethodKeySelect;
import com.trifle.fingerfing.client.lesson.MethodKeySelectOrder;
import com.trifle.fingerfing.client.lesson.MethodKeySelectRandom;

public class ExerciseControllerOld1 {

	
	private StatCalc statCalc;
	private BonusMultiplier bonusCalc;
	
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


	public ExerciseControllerOld1(WorkingSets wSets, Evaluator ev, StatCalc sc, BonusMultiplier bm) {
		this.wSets = wSets;
		this.statCalc = sc;
		this.bonusCalc = bm;
		this.ev = ev;
		init();
	}
	
	@SuppressWarnings("unused")
	private ExerciseControllerOld1() {
	}

	private void init() {
		goNextWorkingSet();
		goNextExerciseKey();
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

		statCalc.addStep(System.currentTimeMillis() - lastTimestamp, lastEvaluate);
		bonusCalc.nextDate(statCalc.getLastMeanSpeed(), statCalc.getLastMeanInTempo(),
				statCalc.getFullSuccessDensity(), lastEvaluate);
		simpleScore = 10; //(long) sc.getStepSpeed();
		awardedScore = (long) (bonusCalc.getMultiplier() * simpleScore);
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
		return statCalc;
	}

	public void setSc(StatCalc sc) {
		this.statCalc = sc;
	}

	public BonusMultiplier getBm() {
		return bonusCalc;
	}

	public void setBm(BonusMultiplier bm) {
		this.bonusCalc = bm;
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
	}
	
	public int getType() {
		return type;
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