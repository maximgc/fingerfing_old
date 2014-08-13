package com.trifle.fingerfing.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Label;
import com.trifle.fingerfing.client.widget.newage.ProgressBar;

public class SensorIndicator extends Composite {

	private static SensorIndicatorUiBinder uiBinder = GWT
			.create(SensorIndicatorUiBinder.class);

	@UiField
	Label fullCount;
	@UiField
	Label fullSuccessDensity;
	@UiField
	Label fullTime;
	@UiField
	Label lastMeanSpeed;
	@UiField
	Label lastMeanDeviation;
	
	@UiField ProgressBar pgSuccessDensity;
	@UiField ProgressBar pgFullTime;
	@UiField ProgressBar pgFullCount;
	@UiField ProgressBar pgMeanSpeed;
	@UiField ProgressBar pgTemp;
	@UiField Label mForTemp;
	@UiField Label mForSpeed;
	@UiField Label mForSuccess;
	@UiField Label mForCorrect;
	@UiField Label mScore;

	interface SensorIndicatorUiBinder extends UiBinder<Widget, SensorIndicator> {
	}

	public SensorIndicator() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setData(long count, long allTime,
			double fullDensitySuccess, double fullMeanSpeed, double lastMeanSpeed,
			double lastMeanDeviation) {
		this.fullTime.setText(String.valueOf(allTime));
		this.lastMeanSpeed.setText(String.valueOf(lastMeanSpeed));
		this.fullSuccessDensity.setText(String.valueOf(fullDensitySuccess));
		this.fullCount.setText(String.valueOf(count));
		this.lastMeanDeviation.setText(String.valueOf(lastMeanDeviation));
		
		pgSuccessDensity.setValue(fullDensitySuccess);
		
		pgMeanSpeed.setValue((double)lastMeanSpeed/2000);
		pgTemp.setValue(lastMeanDeviation);
//		pgSuccessDensity.setValueInt((int)(fullDensitySuccess*10));
//		pgMeanSpeed.setValueInt((int)(lastMeanSpeed));
//		pgTemp.setValueInt((int)(lastMeanDeviation*10));
	}
	
	public void setMultiplers(long fullScore, long awardedScore, long simpleScore, double forAll, double forSpeed, double forTemp, double forCorrect, double forSuccess){
		mForSpeed.setText(String.valueOf(forSpeed));
		mForTemp.setText(String.valueOf(forTemp));
		mForCorrect.setText(String.valueOf(forCorrect));
		mForSuccess.setText(String.valueOf(forSuccess));
		mScore.setText(String.valueOf(fullScore) + "(+" + String.valueOf(awardedScore) + " = " + String.valueOf(simpleScore) +" * " + String.valueOf(forAll) + ")");
	}

}
