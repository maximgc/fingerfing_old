package com.trifle.fingerfing.client.calcs;

public interface StatCalc {

	public abstract void addRecord(long timestamp, int eval);

	public abstract long calcConvergentMeanTime();
	
	public abstract long calcConvergentMeanSpeed();
	
	public abstract long calcFullMeanSpeed();
	
	public abstract long calcAllTime();
	
	public abstract double calcFullDensityError();
	
	public abstract long calcCount();

	public abstract String toDebugString();

}