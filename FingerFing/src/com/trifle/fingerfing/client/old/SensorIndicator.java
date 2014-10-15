package com.trifle.fingerfing.client.old;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Label;
import com.trifle.fingerfing.client.widget.progressbar.ProgressBar;
import com.trifle.fingerfing.client.widget.progressbar.VerticalProgressBar;

public class SensorIndicator extends Composite {

	private static SensorIndicatorUiBinder uiBinder = GWT
			.create(SensorIndicatorUiBinder.class);

	@UiField
	Label lFullCount;
	@UiField
	Label lFullSuccessDensity;
	@UiField
	Label lFullTime;
	@UiField
	Label lLastMeanSpeed;
	@UiField
	Label lLastMeanDeviation;

	@UiField
	ProgressBar pgSuccessDensity;
	@UiField
	ProgressBar pgMeanSpeed;
	@UiField
	ProgressBar pgTemp;
	@UiField
	Label mForTemp;
	@UiField
	Label mForSpeed;
	@UiField
	Label mForSuccess;
	@UiField
	Label mForCorrect;
	@UiField
	Label mScore;
	@UiField
	ProgressBar pgInSpeed;
	@UiField
	ProgressBar pgInTemp;
	@UiField
	ProgressBar pgInDensity;

	interface SensorIndicatorUiBinder extends UiBinder<Widget, SensorIndicator> {
	}

	public SensorIndicator() {
		initWidget(uiBinder.createAndBindUi(this));

		pgInSpeed.addValueSegment(0, 10, "#ded", "#5a5");
		pgInTemp.addValueSegment(0, 55, "#ded", "#5a5");
		pgInDensity.addValueSegment(0, 35, "#ded", "#5a5");

		pgSuccessDensity.addValueSegment(90, 100, "#ded", "#5a5");
		pgSuccessDensity.addValueSegment(40, 90, "#eec", "#ec0");
		// pgSuccessDensity.addValueSegment(40, 90, "#dde", "#55a");
		// pgSuccessDensity.addValueSegment(0, 40, "#edd", "#a55");

		pgMeanSpeed.addValueSegment(90, 100, "#ded", "#5a5");
		pgMeanSpeed.addValueSegment(40, 90, "#eec", "#ec0");
		// pgMeanSpeed.addValueSegment(40, 90, "#dde", "#55a");
		// pgMeanSpeed.addValueSegment(0, 40, "#edd", "#a55");

		pgTemp.addValueSegment(70, 100, "#ded", "#5a5");
		pgTemp.addValueSegment(40, 70, "#eec", "#ec0");
		// pgTemp.addValueSegment(40, 90, "#dde", "#55a");
		// pgTemp.addValueSegment(0, 40, "#edd", "#a55");
	}

	public void setMultiplers(long fullScore, long awardedScore,
			long simpleScore, double forAll, double forSpeed, double forTemp,
			double forCorrect, double forSuccess) {
		mForSpeed.setText(String.valueOf(forSpeed));
		mForTemp.setText(String.valueOf(forTemp));
		mForCorrect.setText(String.valueOf(forCorrect));
		mForSuccess.setText(String.valueOf(forSuccess));
		mScore.setText(String.valueOf(fullScore) + "(+"
				+ String.valueOf(awardedScore) + " = "
				+ String.valueOf(simpleScore) + " * " + String.valueOf(forAll)
				+ ")");
	}

	public void setCount(long count) {
		this.lFullCount.setText(String.valueOf(count));
	}

	public void setAllTime(long allTime) {
		this.lFullTime.setText(String.valueOf(allTime));
	}

	public void setFullDensitySuccess(double fullDensitySuccess) {
		this.lFullSuccessDensity.setText(String.valueOf(fullDensitySuccess));
		pgSuccessDensity.setValue((int) (fullDensitySuccess * 100));
	}

	public void setFullMeanSpeed(double fullMeanSpeed) {
	}

	public void setLastMeanSpeed(double lastMeanSpeed) {
		this.lLastMeanSpeed.setText(String.valueOf(lastMeanSpeed));
		pgMeanSpeed
				.setValue((int) (lastMeanSpeed * 100 / lastMaxMeanSpeed - 5));
	}

	public void setLastMeanDeviation(double lastMeanDeviation) {
		this.lLastMeanDeviation.setText(String.valueOf(lastMeanDeviation));
		pgTemp.setValue((int) (lastMeanDeviation * 100));
	}

	private double lastMaxMeanSpeed;

	public void setLastMaxMeanSpeed(double lastMaxMeanSpeed) {
		this.lastMaxMeanSpeed = lastMaxMeanSpeed;
	}

	public void setInSpeed(int value) {
		pgInSpeed.setValue(value);
	}

	public void setInTemp(int value) {
		pgInTemp.setValue(value);
	}

	public void setInDensity(int value) {
		pgInDensity.setValue(value);
	}

}
