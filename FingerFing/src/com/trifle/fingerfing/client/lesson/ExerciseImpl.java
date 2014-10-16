package com.trifle.fingerfing.client.lesson;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import com.trifle.fingerfing.client.NativeKey;
import com.trifle.fingerfing.client.lesson.CourseDescriptor.ExerciseDescriptor;


public class ExerciseImpl implements Exercise{

	private ExerciseDescriptor descriptor;
	
	private int curPos;
	private int[] evals;

	public ExerciseImpl(ExerciseDescriptor descriptor) {
		this.descriptor = descriptor;
		curPos = -1;
		evals = new int[descriptor.getKeySequence().size()];
	}

	@Override
	public Collection<NativeKey> getArea() {
		return new HashSet<NativeKey>(descriptor.getKeySequence());
	}

	@Override
	public List<NativeKey> getSequence() {
		return descriptor.getKeySequence();
	}

	@Override
	public Block getBlock() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void hasNextBlock() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Block getNextBlock() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasNextPos() {
		return curPos + 1 < descriptor.getKeySequence().size();
	}

	@Override
	public int getNextPos() {
		return ++curPos;
	}

	@Override
	public int getPrevEval() {
		return evals[curPos-1];
	}

	@Override
	public int getPrevEval(int pos) {
		return evals[pos];
	}

	@Override
	public void getIndicators() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getProgress() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetAll() {
		curPos = -1;
		evals = new int[descriptor.getKeySequence().size()];
	}

	@Override
	public void inputAttempt(NativeKey elem) {
		if (elem == descriptor.getKeySequence().get(curPos)){
			evals[curPos] = 1;
		} else {
			evals[curPos] = 2;
		}
	}


}
