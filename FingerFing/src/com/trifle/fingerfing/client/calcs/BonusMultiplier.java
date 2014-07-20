package com.trifle.fingerfing.client.calcs;

public class BonusMultiplier {

	double forSpeed;
	double forTemp;
	long lastInTemp;
	double forCorrect;
	long lastCorrect;
	double forSuccess;

	public void nextDate(long speed, double temp, double success, int correct) {
		if (correct == 0) {
			lastCorrect = lastInTemp = 0;
		} else {
			lastCorrect++;
			if (temp > 0.9) {
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
		return forSpeed * forTemp * forCorrect * forSuccess;
	}

	public double getForSpeed() {
		return forSpeed;
	}

	public double getForTemp() {
		return forTemp;
	}

	public double getForCorrect() {
		return forCorrect;
	}

	public double getForSuccess() {
		return forSuccess;
	}
}
