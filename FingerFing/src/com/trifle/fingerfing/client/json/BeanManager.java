package com.trifle.fingerfing.client.json;

import com.google.gwt.core.shared.GWT;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import com.trifle.fingerfing.client.AlternativeKeyLayout;
import com.trifle.fingerfing.client.lesson.ExerciseController;
import com.trifle.fingerfing.client.widget.KeyboardWidget;

public class BeanManager {

	private BeanFactory f = GWT.create(BeanFactory.class);
	
//	public <T> String encodeLabelTextMap(T b) {
//		return AutoBeanCodex.encode(AutoBeanUtils.getAutoBean(b))
//				.getPayload();
//	}
	
	public class AlternativeKeyLayoutBeans {
		public AlternativeKeyLayout.LabelTextMap createLabelTextMap() {
			return f.createLabelTextMap().as();
		}

		public AlternativeKeyLayout.LabelTextMap decodeLabelTextMap(String json) {
			return AutoBeanCodex.decode(f,
					AlternativeKeyLayout.LabelTextMap.class, json).as();
		}

		public String encodeLabelTextMap(AlternativeKeyLayout.LabelTextMap b) {
			return AutoBeanCodex.encode(AutoBeanUtils.getAutoBean(b))
					.getPayload();
		}
	}
	
	public class WorkingSetsBeans {
		public ExerciseController.WorkingSets createWorkingSets() {
			return f.makeList().as();
		}

		public String encodeWorkingSets(ExerciseController.WorkingSets b) {
			return AutoBeanCodex.encode(AutoBeanUtils.getAutoBean(b))
					.getPayload();
		}

		public ExerciseController.WorkingSets decodeWorkingSets(String json) {
			return AutoBeanCodex.decode(f, ExerciseController.WorkingSets.class, json).as();
		}
	}
	
	public class KeyboardWidgetBeans {
		
		public KeyboardWidget.KeyBlock createKeyBlock() {
			return f.createKeyBlock().as();
		}

		public String encodeKeyBlock(KeyboardWidget.KeyBlock b) {
			return AutoBeanCodex.encode(AutoBeanUtils.getAutoBean(b))
					.getPayload();
		}

		public KeyboardWidget.KeyBlock decodeKeyBlock(String json) {
			return AutoBeanCodex.decode(f, KeyboardWidget.KeyBlock.class, json).as();
		}
		
		public KeyboardWidget.KeyRow createKeyRow() {
			return f.createKeyRow().as();
		}

//		public String encodeKeyBlock(KeyboardWidget.KeyRow b) {
//			return AutoBeanCodex.encode(AutoBeanUtils.getAutoBean(b))
//					.getPayload();
//		}

//		public KeyboardWidget.KeyRow decodeKeyRow(String json) {
//			return AutoBeanCodex.decode(f, KeyboardWidget.KeyRow.class, json).as();
//		}
		
		public KeyboardWidget.Key createKey() {
			return f.createKey().as();
		}

//		public String encodeKey(KeyboardWidget.Key b) {
//			return AutoBeanCodex.encode(AutoBeanUtils.getAutoBean(b))
//					.getPayload();
//		}
//
//		public KeyboardWidget.Key decodeKey(String json) {
//			return AutoBeanCodex.decode(f, KeyboardWidget.Key.class, json).as();
//		}

	}	
	
}
