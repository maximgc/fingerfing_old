package com.trifle.fingerfing.client.calcs;

public interface StatCalc {

	public abstract void addRecord(long timestamp, int eval);

	public abstract long calcMeanTime();
	
	public abstract long calcCurrentMeanSpeed();
	
	public abstract long calcFullMeanSpeed();
	
	public abstract long calcAllTime();
	
	public abstract double calcDensityError();
	
	public abstract long calcCount();

	public abstract String toDebugString();

}