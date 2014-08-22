package com.trifle.fingerfing.client.calcs;

public class StatCalcConvergent implements StatCalc {

	// на базе сходящегося ряда 1/2^n, сходится к 1.
	private long stepTime;
	private long fullCount;
	private long fullCountSuccess;
	private long fullTime;
	private double fullSuccessDensity;
	private double fullMeanSpeed ;
	
	private double stepSpeed;
	
	private double lastMaxMeanSpeed = 0;
	private double lastMeanSpeed;
	private double lastMeanInTempo;
	
	private double sumQuadDeviation;
	private void calcLastMeanInTempo(double lastMeanSpeed) {
		// на базе средне квадратичного отклонения, нормализованного по текущему среднему, и увеличена чувствительность как 1/x^5
		sumQuadDeviation = (sumQuadDeviation + Math.pow(2.0*stepSpeed/(stepSpeed+lastMeanSpeed)-1, 2.0))/2; 
		lastMeanInTempo = Math.pow(1.0 - Math.pow(sumQuadDeviation, 0.5), 5.0);
	}

	private void calcLastMeanSpeed() {
		// на базе сходящегося ряда 1/2^n, сходится к 1.
		lastMeanSpeed = lastMeanSpeed*0.75 + stepSpeed*0.25;
		if (lastMeanSpeed > lastMaxMeanSpeed) lastMaxMeanSpeed = lastMeanSpeed;

//		вариант попытки стабилизировать от разовых скачков, 
//		плохо переносит разовые падения скорости, например можно набирать по 3 быстрых нажатия с перерывами - средняя будет высокой
//		double cof = 1.0/Math.pow(Math.abs(stepSpeed - lastMeanSpeed)/lastMeanSpeed+1, 2.0);
//		lastMeanSpeed = (lastMeanSpeed==0)?stepSpeed:(1-cof)*lastMeanSpeed+cof*stepSpeed;
	}

	private double round001 (double d) {
		return Math.round(d*100.0)/100.0;
	}

	@Override
	public void addRecord(long stepTime, int eval) {
		this.stepTime = stepTime;
		fullTime += stepTime;
		fullCount++;
		fullCountSuccess += (eval > 0)? 1 : 0;
		
		stepSpeed = (double) 60000 / stepTime;
		fullMeanSpeed = (double) 60000 / (fullTime / fullCount);
	
		fullSuccessDensity = (double) fullCountSuccess / fullCount;
		
		if (eval > 0) {
			calcLastMeanSpeed(); 
			calcLastMeanInTempo(lastMeanSpeed);
		}
	}

	@Override
	public double getLastMeanInTempo(){
		return round001(lastMeanInTempo);
	}

	@Override
	public long getFullTimeMillis() {
		return fullTime;
	}

	@Override
	public double getFullSuccessDensity() {
		return round001(fullSuccessDensity);
	}

	@Override
	public double getLastMeanSpeed() {
		return round001(lastMeanSpeed);
	}

	@Override
	public double getFullMeanSpeed() {
		return round001(fullMeanSpeed);
	}

	@Override
	public long getFullCount() {
		return fullCount;
	}
	
	@Override
	public double getStepSpeed() {
		return round001(stepSpeed);
	}

	@Override
	public String toDebugString() {
		return "count=" + fullCount + "countSuccess=" + fullCountSuccess + " sumTime="
				+ fullTime + " lastStepTime="	+ stepTime;
	}

	@Override
	public double getLastMaxMeanSpeed() {
		return lastMaxMeanSpeed;
	}

}
