package com.trifle.fingerfing.client.json;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.trifle.fingerfing.client.AlternativeKeyLayout.LabelTextMap;
import com.trifle.fingerfing.client.lesson.ExerciseController.Keys;
import com.trifle.fingerfing.client.lesson.ExerciseController.WorkingSets;

public interface BeanFactory extends AutoBeanFactory {

	AutoBean<LabelTextMap> createLabelTextMap();

	AutoBean<WorkingSets> makeList();

	AutoBean<Keys> makeSet();
}
