package com.trifle.fingerfing.client.calcs;

public class StatCalcImpl implements StatCalcNew{

	static final int DEFAULT_ENV_CARDINALITY = 2;
	
	// скорость схождения  для last (должна быть >0, значение 1 - полная замена последним значением)
	static final double SPEED_CONVERGENT1 = 0.25;
	static final double SPEED_CONVERGENT2 = 0.5;
	
	final int envCardinality;
	
	int stepEnv;
	long stepDuration;
	double stepSpeed;

	int fullStepCount;
	int[] fullStepEnvCount;
	double[] fullStepEnvRatio;
	long fullDuration;
	double fullAverageStepSpeed;

	// не расчетная без сохранения всего ряда скоростей, т.к. fullAverageStepSpeed за ранее не известна
	// а когда станет известна нужен полный перерасчет всего ряда.
	// double fullAverageStepSpeedDeviation;

	double[] lastStepEnvRatio;
	double lastAverageStepSpeed;
	double lastAverageStepSpeedDeviation;

	
	public StatCalcImpl() {
		this(DEFAULT_ENV_CARDINALITY);
	}
	
	public StatCalcImpl(int envCardinality) {
		this.envCardinality = envCardinality;
	}
	
	double sumDeviation;
	
	/* (non-Javadoc)
	 * @see com.trifle.fingerfing.client.calcs.StatCalcNew#addStep(long, int)
	 */
	@Override
	public void addStep(long duration, int env) {
		stepEnv = env;
		stepDuration = duration;
		stepSpeed = 60000 / duration;

		fullStepCount++;
		fullStepEnvCount[env]++;
		fullStepEnvRatio[env] = (double) fullStepEnvCount[env] / fullStepCount;
		fullDuration += duration;
		
		//TODO Нужно чтобы некоторые показатели улучшались только при положительной оценке
		fullAverageStepSpeed = (double) fullDuration / fullStepCount;
		
		lastStepEnvRatio[env] = lastStepEnvRatio[env] * (1-SPEED_CONVERGENT1) + SPEED_CONVERGENT1;
		lastAverageStepSpeed = lastAverageStepSpeed * (1-SPEED_CONVERGENT1) + stepSpeed * SPEED_CONVERGENT1;
		sumDeviation = sumDeviation * (1-SPEED_CONVERGENT2) + Math.pow(2.0 * stepSpeed / (stepSpeed + lastAverageStepSpeed) - 1, 2.0) * SPEED_CONVERGENT2;
		lastAverageStepSpeedDeviation = Math.pow(sumDeviation, 0.5);

	}

	/* (non-Javadoc)
	 * @see com.trifle.fingerfing.client.calcs.StatCalcNew#getStepEnv()
	 */
	@Override
	public int getStepEnv() {
		return stepEnv;
	}

	/* (non-Javadoc)
	 * @see com.trifle.fingerfing.client.calcs.StatCalcNew#getStepDuration()
	 */
	@Override
	public long getStepDuration() {
		return stepDuration;
	}

	/* (non-Javadoc)
	 * @see com.trifle.fingerfing.client.calcs.StatCalcNew#getStepSpeed()
	 */
	@Override
	public double getStepSpeed() {
		return stepSpeed;
	}

	/* (non-Javadoc)
	 * @see com.trifle.fingerfing.client.calcs.StatCalcNew#getFullStepCount()
	 */
	@Override
	public int getFullStepCount() {
		return fullStepCount;
	}

	/* (non-Javadoc)
	 * @see com.trifle.fingerfing.client.calcs.StatCalcNew#getFullStepEnvCount(int)
	 */
	@Override
	public int getFullStepEnvCount(int env) {
		return fullStepEnvCount[env];
	}

	/* (non-Javadoc)
	 * @see com.trifle.fingerfing.client.calcs.StatCalcNew#getFullStepEnvRatio(int)
	 */
	@Override
	public double getFullStepEnvRatio(int env) {
		return fullStepEnvRatio[env];
	}

	/* (non-Javadoc)
	 * @see com.trifle.fingerfing.client.calcs.StatCalcNew#getFullDuration()
	 */
	@Override
	public long getFullDuration() {
		return fullDuration;
	}

	/* (non-Javadoc)
	 * @see com.trifle.fingerfing.client.calcs.StatCalcNew#getFullAverageStepSpeed()
	 */
	@Override
	public double getFullAverageStepSpeed() {
		return fullAverageStepSpeed;
	}

	/* (non-Javadoc)
	 * @see com.trifle.fingerfing.client.calcs.StatCalcNew#getLastStepEnvRatio(int)
	 */
	@Override
	public double getLastStepEnvRatio(int env) {
		return lastStepEnvRatio[env];
	}

	/* (non-Javadoc)
	 * @see com.trifle.fingerfing.client.calcs.StatCalcNew#getLastAverageStepSpeed()
	 */
	@Override
	public double getLastAverageStepSpeed() {
		return lastAverageStepSpeed;
	}

	/* (non-Javadoc)
	 * @see com.trifle.fingerfing.client.calcs.StatCalcNew#getLastAverageStepSpeedDeviation()
	 */
	@Override
	public double getLastAverageStepSpeedDeviation() {
		return lastAverageStepSpeedDeviation;
	}

	


	
}
