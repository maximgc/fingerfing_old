package com.trifle.fingerfing.client.lesson;

import java.util.List;
import java.util.Random;

import com.trifle.fingerfing.client.NativeKey;

public class MethodKeySelectRandom implements MethodKeySelect {
	
	private Random rnd = new Random();
	
	@Override
	public NativeKey select(List<NativeKey> ws){
		return ws.get(rnd.nextInt(ws.size()));
	}

}
