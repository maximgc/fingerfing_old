package com.trifle.fingerfing.client.lesson;


public interface Course {

	public CourseDescriptor getDescriptor();

	// Сброс на начало (курса)
	public 	void resetAll();

	// Навигация (переход к заданию)
	public void jumpToExercise();

	// Получение задания (зона, блок, элемент)
	public Exercise getExercise();



}