package com.trifle.fingerfing.client.calcs;

public class BonusMultiplier {

	public static final int STEP_CORRECT = 35;
	public static final int STEP_IN_TEMP = 55;
	private double forSpeed;
	private double forTemp;
	private int lastInTemp;
	private double forCorrect;
	private int lastCorrect;
	private double forSuccess;
	private int curCorrect;
	
	private double round001 (double d) {
		return Math.round(d*100.0)/100.0;
	}

	public void nextDate(double speed, double temp, double success, int correct) {
		curCorrect = correct;
		if (correct == 0) {
			lastCorrect = lastInTemp = 0;
		} else {
			lastCorrect++;
			if (temp > 0.7) {
				lastInTemp++;
			} else {
				lastInTemp = 0;
			}
		}

		forSpeed = (speed <= 2000) ? 1.0 + Math.round(speed / 50) * 0.5
				: 1.0 + Math.round(2000 / 50) * 0.5;
		forTemp = (lastInTemp <= 4 * STEP_IN_TEMP) ? 1.0 + Math.round(lastInTemp / STEP_IN_TEMP)
				: 1.0 + Math.round(4 * STEP_IN_TEMP / STEP_IN_TEMP);
		forCorrect = (lastCorrect <= 4 * STEP_CORRECT * 10) ? 1.0 + Math.round(lastCorrect / STEP_CORRECT) * 0.1 
				: 1.0 + Math.round(4 * STEP_CORRECT * 10 / STEP_CORRECT) * 0.1;
		forSuccess = 1.0 + success;
	}

	public int getLastInTemp() {
		return lastInTemp % STEP_IN_TEMP;
	}

	public int getLastCorrect() {
		return lastCorrect % STEP_CORRECT;
	}

	public double getMultiplier() {
		return round001(forSpeed * forTemp * forCorrect * forSuccess * curCorrect);
	}

	public double getForSpeed() {
		return round001(forSpeed);
	}

	public double getForTemp() {
		return round001(forTemp);
	}

	public double getForCorrect() {
		return round001(forCorrect);
	}

	public double getForSuccess() {
		return round001(forSuccess);
	}
}
