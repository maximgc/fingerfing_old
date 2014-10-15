package com.trifle.fingerfing.client.lesson;

import java.util.Collection;
import java.util.List;

import com.trifle.fingerfing.client.NativeKey;

public interface Exercise {

	interface Block {

		void getStartPos();

		void getEndPos();

	}

	// Получить зону
	public Collection<NativeKey> getArea();

	// Получить последовательность
	public List<NativeKey> getSequence();

	// Получить блок
	public Block getBlock();

	public void hasNextBlock();

	// Получить следующий блок
	public Block getNextBlock();

	// Получить текущую позицию
	public int getPos();

	public boolean hasNextPos();

	// Получить следующую позицию
	public int getNextPos();

	// Получить оценку для текущей позиции
	public int getPosEval();

	// Получить оценку для позиции
	public int getPosEval(int pos);

	// Получение показателей
	public void getIndicators();

	// Получение прогресса
	public void getProgress();

	// Сброс на начало (задания)
	public void resetExercise();

	// Ввод попытки
	public void inputAttempt(NativeKey elem);
	
}
