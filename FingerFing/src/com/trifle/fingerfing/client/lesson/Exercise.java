package com.trifle.fingerfing.client.lesson;

import java.util.Collection;
import java.util.List;

import com.trifle.fingerfing.client.NativeKey;

public interface Exercise {

	class Element {

		public int pos;
		public NativeKey key;
		public int eval;

		public Element(int pos, NativeKey key) {
			this(pos, key, 0);
		}

		public Element(int pos, NativeKey key, int eval) {
			this.pos = pos;
			this.key = key;
			this.eval = eval;
		}

	}

	// Получить зону
	public Collection<NativeKey> getArea();

	// Получить последовательность
	public List<NativeKey> getSequence();

	// Получить введеную последовательность
	public List<Element> getAttemptSequence();

	public boolean hasNextPos();

	// Получить следующую позицию
	public Element nextElement();

	// Получить оценку для текущей позиции
	public int getPrevEval();

	// Получить оценку для позиции
	public int getPrevEval(int pos);

	// Получение показателей
	public void getIndicators();

	// Получение прогресса
	public void getProgress();

	// Сброс на начало (задания)
	public void resetAll();

	// Ввод попытки
	public void inputAttempt(NativeKey elem);

}
