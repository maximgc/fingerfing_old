package com.trifle.fingerfing.client.widget;

import java.util.Collection;
import java.util.List;
import com.trifle.fingerfing.client.NativeKey;

public interface ExercisePassingW {

	// Отображение зоны.
	void setWorkArea(Collection<NativeKey> area);

	// Отображение всей последовательности задания.
	void setWorkSequence(List<NativeKey> seq);

	// Отображение текущего блока задания.
	void setCurrentBlock(int pos, int lenght);

	// Отображение текущего элемента.
	void setCurrentElement(int pos);

	// Отображение оценки для текущей позиции.
	void setEvaluate(int eval);

	// Отображение оценки для любой пройденной позиции.
	void setEvaluate(int pos, int eval);

	// Отображение оценки для элемента зоны.
	// void setAreaEvaluate(NativeKey elem, int eval);

}
