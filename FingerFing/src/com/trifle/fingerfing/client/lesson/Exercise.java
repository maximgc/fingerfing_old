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

	public boolean hasNextPos();

	// Получить следующую позицию
	public int getNextPos();

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
