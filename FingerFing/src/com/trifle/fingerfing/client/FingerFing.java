package com.trifle.fingerfing.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.trifle.fingerfing.client.calcs.BonusMultiplier;
import com.trifle.fingerfing.client.calcs.StatCalcConvergentOld1;
import com.trifle.fingerfing.client.json.BeanManager;
import com.trifle.fingerfing.client.json.BeanManager.CourseDescriptorBeans;
import com.trifle.fingerfing.client.lesson.Course;
import com.trifle.fingerfing.client.lesson.CourseDescriptor;
import com.trifle.fingerfing.client.lesson.CourseImpl;
import com.trifle.fingerfing.client.old.Effects;
import com.trifle.fingerfing.client.old.ExcerciseProgress;
import com.trifle.fingerfing.client.old.ExerciseControllerOld1;
import com.trifle.fingerfing.client.old.KeyboardWidget;
import com.trifle.fingerfing.client.old.KeyboardWidgetUI;
import com.trifle.fingerfing.client.old.SensorIndicator;
import com.trifle.fingerfing.client.old.TrainerView;
import com.trifle.fingerfing.client.old.KeyboardWidget.KeyBlock;
import com.trifle.fingerfing.client.resources.FFResources;
import com.trifle.fingerfing.client.widget.CoursePassingW;
import com.trifle.fingerfing.client.widget.construct.CourseConstructorWidget;
import com.trifle.fingerfing.client.widget.construct.JSONConstructorWidget;
import com.trifle.fingerfing.client.widget.progressbar.HorizontalProgressBar;
import com.trifle.fingerfing.client.widget.progressbar.ProgressBarBase;
import com.trifle.fingerfing.client.widget.progressbar.VerticalProgressBar;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */

public class FingerFing implements EntryPoint {

//	 int i = 100;
	
//	@Override
//	public void onModuleLoad() {
//		
//		final ProgressBarBase b = new VerticalProgressBar(30, 35, 1, 0.0, "12");
//		final ProgressBarBase bh = new HorizontalProgressBar(60, 1, 5, 0.0, "14");
////		Label l = new Label("HI!");
//		
////		RootPanel.get("mainWidgetField").add(l);
//		RootPanel.get("mainWidgetField").add(b);
//		RootPanel.get("mainWidgetField").add(bh);
//		
//		final Timer t = new Timer() {
//			
//			@Override
//			public void run() {
//				
//				if (i--<1) {b.setValue(0.5); b.setSegmentCount(70);
//				bh.setValue(0.5);this.cancel(); } else {
//				
//				if (i==50) {
//					b.setSegmentCount(40);
//					bh.setSegmentCount(100);
//				}
//				
//					b.setValue((double)i/100);
//					bh.setValue((double)i/100);
//				}
//			}
//		};
//
//		t.scheduleRepeating(50);
//	}

	
//	public void onModuleLoad() {
//	
//		CourseConstructorWidget ccw = new CourseConstructorWidget(new BeanManager(). new CourseDescriptorBeans());
//		RootPanel.get("mainWidgetField").add(ccw);
//		
//	}
	
	public void onModuleLoad() {
		//init logic and content
		String courseJson = FFResources.INST.getCourseDescriptorJSON().getText();
		CourseDescriptorBeans cdb = new BeanManager(). new CourseDescriptorBeans();
		CourseDescriptor cd = cdb.decodeCourseDescriptor(courseJson);
		Course course = new CourseImpl(cd);
		
		//init view
		CoursePassingW courseW = new CoursePassingW();
		RootPanel.get("mainWidgetField").add(courseW);
		
		
		List<NativeKey> seq = course.getDescriptor().getLevels().get(0).getLessons().get(0).getExercises().get(0).getKeySequence();
		courseW.getExercisePassingW().setWorkSequence(seq);
		courseW.getExercisePassingW().setWorkArea(seq);
		courseW.getExercisePassingW().setCurrentBlock(0, 3);
		courseW.getExercisePassingW().setCurrentElement(0);
	}

	
	
//	public void onModuleLoad() {
//		
//
//
//		final TrainerView tv = new TrainerView();
//		RootPanel.get("mainWidgetField").add(tv);
//		
//		BeanManager bm = new BeanManager();
//		final JSONConstructorWidget jsonC = new JSONConstructorWidget(bm);
//
//		tv.start();
//	}

	// fp.addKeyDownHandler(new KeyDownHandler() {
	//
	// @Override
	// public void onKeyDown(KeyDownEvent event) {
	// System.out.println("NativeKey."+NativeKey.getByNativeCode(event.getNativeKeyCode()).toString()+", ");
	//
	// }
	// });

}
