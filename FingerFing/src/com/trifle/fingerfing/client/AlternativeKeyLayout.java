package com.trifle.fingerfing.client;

import java.util.Map;


public class AlternativeKeyLayout {

	private LabelTextMap mapLabel;

	public AlternativeKeyLayout() {
	}
	
	public AlternativeKeyLayout(LabelTextMap map ) {
		this.mapLabel = map;
	}
	
	public boolean isDefineText(NativeKey nk) {
		if (mapLabel.getLabelTextMap().containsKey(nk)) {
			return true;
		} else {
			return false;
		}
	}

	public String getLabelText(NativeKey nk) {
		return mapLabel.getLabelTextMap().get(nk);
	}
	
	public static interface LabelTextMap {

		Map<NativeKey, String> getLabelTextMap();

		void setLabelTextMap(Map<NativeKey, String> m);
	}

}
