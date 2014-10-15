package com.trifle.fingerfing.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CoursePassingW extends Composite {

	private static CoursePassingWUiBinder uiBinder = GWT
			.create(CoursePassingWUiBinder.class);
	
	@UiField VerticalPanel pProgress;
	@UiField VerticalPanel pIndicators;
	@UiField VerticalPanel pActive;

	ExerciseTextW ext;
	
	interface CoursePassingWUiBinder extends UiBinder<Widget, CoursePassingW> {
	}

	public CoursePassingW() {
		initWidget(uiBinder.createAndBindUi(this));
		ext = new ExerciseTextW();
		pActive.add(ext);
	}

	public ExerciseTextW getExercisePassingW(){
		return ext;
	}

}
