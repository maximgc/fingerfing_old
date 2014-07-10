package com.trifle.fingerfing.client;

public interface StatCalc {

	public abstract void addRecord(long timestamp, int eval);

	public abstract long calcMeanTime();
	
	public abstract long calcMeanSpeed();

	public abstract long calcAllTime();

	public abstract double calcDensityError();

	public abstract String toDebugString();

}