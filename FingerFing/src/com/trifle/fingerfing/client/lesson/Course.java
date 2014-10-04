package com.trifle.fingerfing.client.lesson;

import java.util.List;

import com.trifle.fingerfing.client.NativeKey;

public interface Course {

	public abstract CourseDescriptor getDescriptor();

	public static interface CourseDescriptor {
		
		String getName();
		
		void setName(String name);

		List<LevelDescriptor> getLevels();

		void setLevels(List<LevelDescriptor> e);

	}

	public static interface LevelDescriptor {
		
		String getName();
		
		void setName(String name);
		
		List<LessonDescriptor> getLessons();

		void setLessons(List<LessonDescriptor> e);

	}

	public static interface LessonDescriptor {
		
		String getName();
		
		void setName(String name);
		
		List<ExerciseDescriptor> getExercises();

		void setExercises(List<ExerciseDescriptor> e);

	}

	public static interface ExerciseDescriptor {
		
		String getName();
		
		void setName(String name);
		
		String getFinalScore();

		void setFinalScore(String c);

		String getMethodSelectName();

		void setMethodSelectName(String m);

		List<NativeKey> getKeySequence();

		void setKeySequence(List<NativeKey> s);
	
	}

}