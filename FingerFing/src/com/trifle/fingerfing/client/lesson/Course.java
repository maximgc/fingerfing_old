package com.trifle.fingerfing.client.lesson;

import java.util.List;

import com.trifle.fingerfing.client.NativeKey;

public interface Course {

	public abstract CourseDescriptor getDescriptor();

	public static interface Descriptor {
		
		String getName();
		
		void setName(String name);
		
	}
	
	public static interface CourseDescriptor extends Descriptor{
		
		String getName();
		
		void setName(String name);

		List<LevelDescriptor> getLevels();

		void setLevels(List<LevelDescriptor> e);

	}

	public static interface LevelDescriptor extends Descriptor {
		
		String getName();
		
		void setName(String name);
		
		List<LessonDescriptor> getLessons();

		void setLessons(List<LessonDescriptor> e);

	}

	public static interface LessonDescriptor extends Descriptor {
		
		String getName();
		
		void setName(String name);
		
		List<ExerciseDescriptor> getExercises();

		void setExercises(List<ExerciseDescriptor> e);

	}

	public static interface ExerciseDescriptor extends Descriptor {
		
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