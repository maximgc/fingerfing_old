package com.trifle.fingerfing.client.lesson;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.trifle.fingerfing.client.NativeKey;
import com.trifle.fingerfing.client.lesson.CourseDescriptor.ExerciseDescriptor;


public class ExerciseImpl implements Exercise{

	private ExerciseDescriptor descriptor;
	
	private int curPos;
	
	private List<Element> attempts;
	
	private Map<Integer, Element> sequence;
	
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
	public List<Element> getAttemptSequence() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getIndicators() {
		// TODO Auto-generated method stub
		
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
	public void getProgress() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<NativeKey> getSequence() {
		return descriptor.getKeySequence();
	}

	@Override
	public boolean hasNextPos() {
		return curPos + 1 < descriptor.getKeySequence().size();
	}

	@Override
	public void inputAttempt(NativeKey elem) {
		if (elem == descriptor.getKeySequence().get(curPos)){
			evals[curPos] = 1;
		} else {
			evals[curPos] = 2;
		}
	}

	@Override
	public Element nextElement() {
		return new Element(++curPos, descriptor.getKeySequence().get(curPos));
	}

	@Override
	public void resetAll() {
		curPos = -1;
		evals = new int[descriptor.getKeySequence().size()];
	}


}
