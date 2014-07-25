package com.trifle.fingerfing.client.json;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.trifle.fingerfing.client.AlternativeKeyLayout;
import com.trifle.fingerfing.client.lesson.ExerciseController;
import com.trifle.fingerfing.client.widget.KeyboardWidget;

public interface BeanFactory extends AutoBeanFactory {

	AutoBean<AlternativeKeyLayout.LabelTextMap> createLabelTextMap();

	AutoBean<ExerciseController.WorkingSets> makeList();

	AutoBean<ExerciseController.Keys> makeSet();
	
	
	AutoBean<KeyboardWidget.KeyBlock> createKeyBlock();
	
	AutoBean<KeyboardWidget.KeyRow> createKeyRow();
	
	AutoBean<KeyboardWidget.Key> createKey();
}
