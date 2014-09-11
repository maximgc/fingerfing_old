package com.trifle.fingerfing.client.calcs;

public interface StatCalcNew {

	public abstract void addStep(long duration, int env);

	public abstract int getStepEnv();

	public abstract long getStepDuration();

	public abstract double getStepSpeed();

	public abstract int getFullStepCount();

	public abstract int getFullStepEnvCount(int env);

	public abstract double getFullStepEnvRatio(int env);

	public abstract long getFullDuration();

	public abstract double getFullAverageStepSpeed();

	public abstract double getLastStepEnvRatio(int env);

	public abstract double getLastAverageStepSpeed();

	public abstract double getLastAverageStepSpeedDeviation();

}