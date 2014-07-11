package com.trifle.fingerfing.client.calcs;

public class StatCalcConvergent implements StatCalc {
	// на базе сходимого ряда 1/2^n, сходится к 1.
	private int count;
	private int countSuccess;
	private long sumTime;
	private long convergentTime; // (по сути сумма ряда * с, где с - скорость)
	private double convergentDeviation; // сходящийся средний процент от отклонения, от среднего
	private long lastTimestamp;
	private long lastStepTime;

	@Override
	public void addRecord(long timestamp, int eval) {
		lastStepTime = lastTimestamp == 0 ? 1000 : timestamp - lastTimestamp;
		sumTime += lastStepTime;
		countSuccess += eval;
		convergentTime = (convergentTime + lastStepTime) / 2;
		convergentDeviation = (convergentDeviation + calcDeviation()) / 2;
		lastTimestamp = timestamp;
		count++;
	}

	@Override
	public long calcMeanTime() {
		return convergentTime;
	}
	
	public double calcMeanDeviation(){
		return convergentDeviation;
	}

	public double calcDeviation(){
		return Math.abs((double)convergentTime-lastStepTime)/convergentTime;
	}

	@Override
	public long calcAllTime() {
		return sumTime;
	}

	@Override
	public double calcDensityError() {
		return (double) countSuccess / count;
	}

	@Override
	public String toDebugString() {
		return "count=" + count + "countSuccess=" + countSuccess + " sumTime="
				+ sumTime + " convergentTime=" + convergentTime
				+ " lastTimestamp=" + lastTimestamp + " lastStepTime="
				+ lastStepTime;
	}

	@Override
	public long calcCurrentMeanSpeed() {
		return 60000 / calcMeanTime();
	}

	@Override
	public long calcFullMeanSpeed() {
		return 60000 / (sumTime / count);
	}

	@Override
	public long calcCount() {
		return count;
	}

}
