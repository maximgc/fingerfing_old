package com.trifle.fingerfing.client;

public class Evaluator {

	public int check(NativeKey expected, NativeKey actual) {
		return expected.equals(actual) ? 1 : 0;
	}

}
