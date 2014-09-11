package com.trifle.fingerfing.client.calcs;

public interface StatCalc {

//	public abstract void addStep(long stepTime, int eval);

	
	public abstract double getStepSpeed();

	
	public abstract double getLastMeanSpeed();
	
	public abstract double getLastMeanInTempo();
	
	public abstract double getLastMaxMeanSpeed();


	
	public abstract long getFullCount();

	public abstract long getFullTimeMillis();

	public abstract double getFullMeanSpeed();
	
	public abstract double getFullSuccessDensity();
	
	
	
	public abstract void addStep(long duration, int env);


}