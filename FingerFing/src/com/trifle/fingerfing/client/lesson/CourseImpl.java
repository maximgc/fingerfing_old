package com.trifle.fingerfing.client.lesson;

public class CourseImpl implements Course {

	private CourseDescriptor descriptor;

	public CourseImpl(CourseDescriptor descriptor) {
		this.descriptor = descriptor;
	}

	@Override
	public CourseDescriptor getDescriptor() {
		return descriptor;
	}

	@Override
	public void resetAll() {
	}

	@Override
	public void jumpToExercise() {
	}

	@Override
	public Exercise getExercise() {
		return null;
	}

}
