package com.trifle.fingerfing.client.json;

import com.google.gwt.core.shared.GWT;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import com.trifle.fingerfing.client.AlternativeKeyLayout.LabelTextMap;
import com.trifle.fingerfing.client.lesson.ExerciseController.WorkingSets;

public class BeanManager {

	BeanFactory f = GWT.create(BeanFactory.class);
	
	public LabelTextMap createLabelTextMap(){
		return f.createLabelTextMap().as();
	}
	
	public LabelTextMap decodeLabelTextMap(String json){
		return AutoBeanCodex.decode(f, LabelTextMap.class, json).as();
	}
	
	public String encodeLabelTextMap(LabelTextMap b){
		return AutoBeanCodex.encode(AutoBeanUtils.getAutoBean(b)).getPayload();
	}
	
	public WorkingSets createWorkingSets() {
		return f.makeList().as();
	}
	
	public String encodeWorkingSets(WorkingSets b) {
		return AutoBeanCodex.encode(AutoBeanUtils.getAutoBean(b)).getPayload();
	}
	
	public WorkingSets decodeWorkingSets(String json) {
		return AutoBeanCodex.decode(f, WorkingSets.class, json).as();
	}
	
}
