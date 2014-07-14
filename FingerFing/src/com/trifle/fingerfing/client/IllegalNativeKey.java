package com.trifle.fingerfing.client;

@SuppressWarnings("serial")
public class IllegalNativeKey extends RuntimeException {

	public IllegalNativeKey() {
	}
	
	public IllegalNativeKey(String msg) {
		super(msg);
	}

}
