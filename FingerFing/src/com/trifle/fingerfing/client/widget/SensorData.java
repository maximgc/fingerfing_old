package com.trifle.fingerfing.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Label;

public class SensorData extends Composite{

	private static SensorDataUiBinder uiBinder = GWT
			.create(SensorDataUiBinder.class);
	@UiField Label errorDensity;
	@UiField Label meanSpeed;
	@UiField Label allTime;

	interface SensorDataUiBinder extends UiBinder<Widget, SensorData> {
	}

	public SensorData() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public void setData(String allTime, String meanSpeed, String errorDensity){
		this.allTime.setText(" AT:"+allTime);
		this.meanSpeed.setText(" MS:"+meanSpeed);
		this.errorDensity.setText(" ED:"+errorDensity);
	}

	public void setData(long calcAllTime, long calcMeanSpeed,
			double calcDensityError) {
		setData(String.valueOf(calcAllTime), String.valueOf(calcMeanSpeed), String.valueOf(calcDensityError));
	}


}
