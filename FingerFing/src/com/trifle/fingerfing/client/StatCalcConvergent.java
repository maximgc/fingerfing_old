package com.trifle.fingerfing.client;

public class StatCalcConvergent implements StatCalc{
    //на базе сходимого ряда 1/2^n, сходится к 1.
	private int count;
	private long sumTime;
	private long convergentTime; //(по сути сумма ряда * с, где с - скорость)
	private long lastTimestamp;
	private long lastStepTime;

	@Override
	public void addRecord(long timestamp, int eval) {
		lastStepTime = lastTimestamp == 0 ? 1000 : timestamp - lastTimestamp;
		sumTime += lastStepTime;
		convergentTime = (convergentTime + lastStepTime)/2; 
		lastTimestamp = timestamp;		
	}

	@Override
	public long calcMeanTime() {
		return convergentTime;
	}

	@Override
	public long calcAllTime() {
		return sumTime;
	}

	@Override
	public double calcDensityError() {
		return 0;
	}

	@Override
	public String toDebugString() {

		return "count="+count+
				" sumTime="+sumTime+
				" convergentTime="+convergentTime+
				" lastTimestamp="+lastTimestamp+
				" lastStepTime="+lastStepTime;
	}

	@Override
	public long calcMeanSpeed() {
		return 60000/calcMeanTime();
	}

}
