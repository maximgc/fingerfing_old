package com.trifle.fingerfing.client;

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
import com.trifle.fingerfing.client.lesson.ExerciseControllerOld1;
import com.trifle.fingerfing.client.resources.FFResources;
import com.trifle.fingerfing.client.widget.Effects;
import com.trifle.fingerfing.client.widget.ExcerciseProgress;
import com.trifle.fingerfing.client.widget.KeyboardWidget;
import com.trifle.fingerfing.client.widget.TrainerView;
import com.trifle.fingerfing.client.widget.KeyboardWidget.KeyBlock;
import com.trifle.fingerfing.client.widget.constructor.CourseConstructorWidget;
import com.trifle.fingerfing.client.widget.constructor.JSONConstructorWidget;
import com.trifle.fingerfing.client.widget.newage.HorizontalProgressBar;
import com.trifle.fingerfing.client.widget.newage.ProgressBarBase;
import com.trifle.fingerfing.client.widget.newage.VerticalProgressBar;
import com.trifle.fingerfing.client.widget.KeyboardWidgetUI;
import com.trifle.fingerfing.client.widget.SensorIndicator;

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

	
	public void onModuleLoad() {
	
		CourseConstructorWidget ccw = new CourseConstructorWidget(new BeanManager(). new CourseDescriptorBeans());
		RootPanel.get("mainWidgetField").add(ccw);
		
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
