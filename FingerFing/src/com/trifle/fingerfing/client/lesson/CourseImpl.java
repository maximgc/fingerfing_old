package com.trifle.fingerfing.client.lesson;

import java.util.ListIterator;

import com.trifle.fingerfing.client.lesson.CourseDescriptor.ExerciseDescriptor;
import com.trifle.fingerfing.client.lesson.CourseDescriptor.LessonDescriptor;
import com.trifle.fingerfing.client.lesson.CourseDescriptor.LevelDescriptor;

public class CourseImpl implements Course {

	private CourseDescriptor descriptor;

	private Cursor cur;
	
	private Exercise curExercise;

	public CourseImpl(CourseDescriptor descriptor) {
		this.descriptor = descriptor;
		cur = new Cursor();
	}

	@Override
	public CourseDescriptor getDescriptor() {
		return descriptor;
	}

	@Override
	public void resetAll() {
		cur = new Cursor();
	}

//	@Override
//	public Exercise getExercise() {
//		return curExercise;
//	}

	@Override
	public Exercise getNextExercise() {
		curExercise = new ExerciseImpl(cur.nextExerciseDescritor());
		return curExercise;
	}

	@Override
	public boolean hasNextExercise() {
		return cur.hasNextExerciseDescritor();
	}

	private class Cursor {

		ListIterator<LevelDescriptor> levelCursor;
		ListIterator<LessonDescriptor> lessonCursor;
		ListIterator<ExerciseDescriptor> exerciseCursor;

		public Cursor() {
			levelCursor = descriptor.getLevels().listIterator();
		}

		public boolean hasNextExerciseDescritor() {
			for (; exerciseCursor == null || !exerciseCursor.hasNext(); exerciseCursor = lessonCursor
					.next().getExercises().listIterator())
				for (; lessonCursor == null || !lessonCursor.hasNext(); lessonCursor = levelCursor
						.next().getLessons().listIterator())
					for (; levelCursor == null || !levelCursor.hasNext();)
						return false;
			return true;
		}

		private ExerciseDescriptor nextExerciseDescritor() {
			for (; exerciseCursor == null || !exerciseCursor.hasNext(); exerciseCursor = lessonCursor
					.next().getExercises().listIterator())
				for (; lessonCursor == null || !lessonCursor.hasNext(); lessonCursor = levelCursor
						.next().getLessons().listIterator())
					for (; levelCursor == null || !levelCursor.hasNext();)
						return null;
			return exerciseCursor.next();
		}

		@Override
		public String toString() {
			return levelCursor.toString() + " " + lessonCursor + " "
					+ exerciseCursor;
		}
	}

}
