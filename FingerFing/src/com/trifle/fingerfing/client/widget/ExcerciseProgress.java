package com.trifle.fingerfing.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Label;
import com.trifle.fingerfing.client.lesson.ExerciseController;

public class ExcerciseProgress extends Composite{

	private static ExcerciseProgressUiBinder uiBinder = GWT
			.create(ExcerciseProgressUiBinder.class);
	
	@UiField ProgressBar exProgress;
	@UiField Label text;
	
	interface ExcerciseProgressUiBinder extends
			UiBinder<Widget, ExcerciseProgress> {
	}
	
	private int type;
	private String typeName;
	private long finalValue;
	private long curValue;

	public ExcerciseProgress() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
		switch (type) {
		case ExerciseController.TYPE_COUNT:
			typeName = "Count";
			break;

		case ExerciseController.TYPE_SCORE:
			typeName = "Score";
			break;
		default:
			typeName = "";
			break;
		}
	}

	public long getFinalValue() {
		return finalValue;
	}

	public void setFinalValue(long finalValue) {
		this.finalValue = finalValue;
		exProgress.setRange(finalValue);
		text.setText(typeName + ": " + curValue + "/" + finalValue);
	}

	public long getCurValue() {
		return curValue;
	}

	public void setCurValue(long curValue) {
		this.curValue = curValue;
		exProgress.setPosition(curValue);
		text.setText(typeName + ": " + curValue + "/" + finalValue);
	}


	
	
}
