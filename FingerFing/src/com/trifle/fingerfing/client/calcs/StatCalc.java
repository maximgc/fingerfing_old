package com.trifle.fingerfing.client.calcs;

public interface StatCalc {

	public abstract void addRecord(long timestamp, int eval);

	
	public abstract double getStepSpeed();

	public abstract String toDebugString();
	
	
	
	public abstract double getLastMeanSpeed();
	
	public abstract double getLastMeanInTempo();


	
	public abstract long getFullCount();

	public abstract long getFullTimeMillis();

	public abstract double getFullMeanSpeed();
	
	public abstract double getFullSuccessDensity();

	
}