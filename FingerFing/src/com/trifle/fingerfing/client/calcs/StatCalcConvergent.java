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
		convergentTime = (convergentTime*75 + lastStepTime*25)/100; // / 2;
		//FIXME при коэф. .75 .25 и .75 .25 темп может выйти в минус (задержка после скорости 1800 например:-1.2)
		convergentDeviation = (convergentDeviation*0.25 + calcStepDeviation()*0.75) ; /// 2; 
		lastTimestamp = timestamp;
		count++;
	}

	@Override
	public long calcConvergentMeanTime() {
		return convergentTime;
	}
	
	public double calcConvergentMeanDeviation(){
		return 1.0 - convergentDeviation;
	}

	public double calcStepDeviation(){
		return Math.abs((double)convergentTime-lastStepTime)/convergentTime;
	}

	@Override
	public long calcAllTime() {
		return sumTime;
	}

	@Override
	public double calcFullDensitySuccess() {
		return (double)countSuccess / count;
	}

	@Override
	public String toDebugString() {
		return "count=" + count + "countSuccess=" + countSuccess + " sumTime="
				+ sumTime + " convergentTime=" + convergentTime
				+ " lastTimestamp=" + lastTimestamp + " lastStepTime="
				+ lastStepTime;
	}

	@Override
	public long calcConvergentMeanSpeed() {
		return 60000 / calcConvergentMeanTime();
	}

	@Override
	public long calcFullMeanSpeed() {
		return 60000 / (sumTime / count);
	}

	@Override
	public long calcCount() {
		return count;
	}
	@Override
	public long calcLastSpeed() {
		return 60000 / lastStepTime;
	}

}
