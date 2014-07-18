package com.trifle.fingerfing.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Label;

public class SensorData extends Composite {

	private static SensorDataUiBinder uiBinder = GWT
			.create(SensorDataUiBinder.class);

	@UiField
	Label fullCount;
	@UiField
	Label fullSuccessDensity;
	@UiField
	Label fullTime;
//	@UiField
//	Label fullMeanSpeed;
	@UiField
	Label lastMeanSpeed;
	@UiField
	Label lastMeanDeviation;
//	@UiField
//	Label curDeviation;
	
	@UiField ProgressBar pgSuccessDensity;
	@UiField ProgressBarNew pgFullTime;
	@UiField ProgressBarNew pgFullCount;
	@UiField ProgressBarNew pgMeanSpeed;
	@UiField ProgressBarNew pgTemp;

	interface SensorDataUiBinder extends UiBinder<Widget, SensorData> {
	}

	public SensorData() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setData(String allTime, String meanSpeed, String errorDensity,
			String fullMeanSpeed, String count, String meanDeviation,
			String curDeviation) {
		this.fullTime.setText(allTime);
		this.lastMeanSpeed.setText(meanSpeed);
		this.fullSuccessDensity.setText(errorDensity);
//		this.fullMeanSpeed.setText(fullMeanSpeed);
		this.fullCount.setText(count);
		this.lastMeanDeviation.setText(meanDeviation);
//		this.curDeviation.setText(curDeviation);
	}

	public void setData(long calcAllTime, long calcMeanSpeed,
			double calcDensityError, long fullMeanSpeed, long count,
			double meanDeviation, double curDeviation) {
		setData(String.valueOf(calcAllTime), String.valueOf(calcMeanSpeed),
				String.valueOf(calcDensityError),
				String.valueOf(fullMeanSpeed), String.valueOf(count),
				String.valueOf(meanDeviation), String.valueOf(curDeviation));
		pgSuccessDensity.setPosition((int)(calcDensityError*10));
		pgMeanSpeed.setPosition((int)(calcMeanSpeed));
		pgTemp.setPosition((int)(meanDeviation*10));
	}

}
