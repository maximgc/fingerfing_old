package com.trifle.fingerfing.client.lesson;

import java.util.List;

import com.trifle.fingerfing.client.NativeKey;

public class MethodKeySelectOrder implements MethodKeySelect {

	List<NativeKey> cws;
	int curNumber = 0;

	@Override
	public NativeKey select(List<NativeKey> ws) {
		if (cws == ws) {
			if (curNumber < ws.size() - 1) {
				curNumber++;
			} else {
				curNumber = 0;
			}

		} else {
			cws = ws;
			curNumber = 0;
		}
		return cws.get(curNumber);
	}

}
