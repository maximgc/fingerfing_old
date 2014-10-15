package com.trifle.fingerfing.client.old;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Label;
import com.trifle.fingerfing.client.widget.progressbar.ProgressBar;

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
		case ExerciseControllerOld1.TYPE_COUNT:
			typeName = "Count";
			break;

		case ExerciseControllerOld1.TYPE_SCORE:
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

	public void setFinalValue(int finalValue) {
		this.finalValue = finalValue;
		exProgress.setRangeEndValue(finalValue);
		text.setText(typeName + ": " + curValue + "/" + finalValue);
	}

	public long getCurValue() {
		return curValue;
	}

	public void setCurValue(int curValue) {
		this.curValue = curValue;
		exProgress.setValue(curValue);
		exProgress.setTitle(typeName + ": " + curValue + "/" + finalValue);
		text.setText(typeName + ": " + curValue + "/" + finalValue);
		
	}


	
	
}
