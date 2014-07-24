package com.trifle.fingerfing.client.calcs;

public class BonusMultiplier {

	private double forSpeed;
	private double forTemp;
	private long lastInTemp;
	private double forCorrect;
	private long lastCorrect;
	private double forSuccess;
	private int curCorrect;
	
	private double round01 (double d) {
		return Math.round(d*10.0)/10.0;
	}

	public void nextDate(double speed, double temp, double success, int correct) {
		curCorrect = correct;
		if (correct == 0) {
			lastCorrect = lastInTemp = 0;
		} else {
			lastCorrect++;
			if (temp > 0.8) {
				lastInTemp++;
			} else {
				lastInTemp = 0;
			}
		}

		forSpeed = (speed <= 2000) ? 1.0 + Math.round(speed / 50) * 0.5
				: 1.0 + Math.round(2000 / 50) * 0.5;
		forTemp = (lastInTemp <= 4 * 11) ? 1.0 + Math.round(lastInTemp / 11)
				: 1.0 + Math.round(4 * 11 / 11);
		forCorrect = (lastCorrect <= 4 * 70) ? 1.0 + Math.round(lastCorrect / 7) * 0.1 
				: 1.0 + Math.round(4 * 70 / 7) * 0.1;
		forSuccess = 1.0 + success;
	}

	public double getMultiplier() {
		return round01(forSpeed * forTemp * forCorrect * forSuccess * curCorrect);
	}

	public double getForSpeed() {
		return round01(forSpeed);
	}

	public double getForTemp() {
		return round01(forTemp);
	}

	public double getForCorrect() {
		return round01(forCorrect);
	}

	public double getForSuccess() {
		return round01(forSuccess);
	}
}
