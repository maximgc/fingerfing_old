package com.trifle.fingerfing.client;

import java.util.LinkedList;
import java.util.Queue;

public class StatCalcLastSteps implements StatCalc {

	private int stepCount;
	private long sumTime;
	private long sumObservTime;
	private long lastTimestamp;
	private long lastStepTime;
	private Queue<Long> histStepTime;

	public StatCalcLastSteps() {
		this(10);
	}

	public StatCalcLastSteps(int stepCount) {
		this.stepCount = stepCount;
		histStepTime = new LinkedList<Long>();
	}

	@Override
	public void addRecord(long timestamp, int eval) {
		lastStepTime = lastTimestamp == 0 ? 1000 : timestamp - lastTimestamp;
		if (histStepTime.size() >= stepCount) {
			long l = histStepTime.remove();
			sumObservTime -= l;
		}
		histStepTime.add(lastStepTime);
		sumTime += lastStepTime;
		sumObservTime += lastStepTime;
		lastTimestamp = timestamp;
	}

	@Override
	public long calcMeanTime() {
		return sumObservTime / stepCount;
	}

	@Override
	public long calcAllTime() {
		return sumTime;
	}

	@Override
	public double calcDensityError() {
		return 0.0;
	}

	@Override
	public String toDebugString() {
		return " stepCount="+stepCount+
				" sumTime="+sumTime+
				" sumObservTime="+sumObservTime+
				" lastTimestamp="+lastTimestamp+
				" lastStepTime="+lastStepTime+
				" histStepTime.size="+histStepTime.size();
	}

	@Override
	public long calcMeanSpeed() {
		return 60000/calcMeanTime();
	}
}
