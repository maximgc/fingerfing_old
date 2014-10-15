package com.trifle.fingerfing.client.json;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.trifle.fingerfing.client.AlternativeKeyLayout;
import com.trifle.fingerfing.client.lesson.CourseDescriptor;
import com.trifle.fingerfing.client.old.ExerciseControllerOld1;
import com.trifle.fingerfing.client.old.KeyboardWidget;

public interface BeanFactory extends AutoBeanFactory {

	AutoBean<AlternativeKeyLayout.LabelTextMap> createLabelTextMap();

	AutoBean<ExerciseControllerOld1.WorkingSets> makeList();

	AutoBean<ExerciseControllerOld1.Keys> makeSet();
	
	
	AutoBean<KeyboardWidget.KeyBlock> createKeyBlock();
	
	AutoBean<KeyboardWidget.KeyRow> createKeyRow();
	
	AutoBean<KeyboardWidget.Key> createKey();
	
	
	
	AutoBean<CourseDescriptor> createCourseDescriptor();
	
	AutoBean<CourseDescriptor.LevelDescriptor> createLevelDescriptor();
	
	AutoBean<CourseDescriptor.LessonDescriptor> createLessonDescriptor();
	
	AutoBean<CourseDescriptor.ExerciseDescriptor> createExerciseDescriptor();
	
}
