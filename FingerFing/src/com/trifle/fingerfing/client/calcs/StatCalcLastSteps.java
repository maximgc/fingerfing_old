package com.trifle.fingerfing.client.calcs;

import java.util.LinkedList;
import java.util.Queue;

public class StatCalcLastSteps implements StatCalc {

	private int count;
	private int countErr;
	private long sumTime;
	private long sumObservTime;
	private long lastTimestamp;
	private long lastStepTime;
	private Queue<Long> histStepTime;

	public StatCalcLastSteps() {
		this(10);
	}

	public StatCalcLastSteps(int stepCount) {
		this.count = stepCount;
		histStepTime = new LinkedList<Long>();
	}

	@Override
	public void addRecord(long timestamp, int eval) {
		lastStepTime = lastTimestamp == 0 ? 1000 : timestamp - lastTimestamp;
		if (histStepTime.size() >= count) {
			long l = histStepTime.remove();
			sumObservTime -= l;
		}
		histStepTime.add(lastStepTime);
		sumTime += lastStepTime;
		sumObservTime += lastStepTime;
		lastTimestamp = timestamp;
	}

	@Override
	public long calcConvergentMeanTime() {
		return sumObservTime / count;
	}

	@Override
	public long calcAllTime() {
		return sumTime;
	}

	@Override
	public double calcFullDensityError() {
		return 0.0;
	}

	@Override
	public String toDebugString() {
		return " stepCount="+count+
				" sumTime="+sumTime+
				" sumObservTime="+sumObservTime+
				" lastTimestamp="+lastTimestamp+
				" lastStepTime="+lastStepTime+
				" histStepTime.size="+histStepTime.size();
	}

	@Override
	public long calcConvergentMeanSpeed() {
		return 60000/calcConvergentMeanTime();
	}
	
	@Override
	public long calcFullMeanSpeed() {
		return 60000/(sumTime/count);
	}

	@Override
	public long calcCount() {
		return count;
	}

}
