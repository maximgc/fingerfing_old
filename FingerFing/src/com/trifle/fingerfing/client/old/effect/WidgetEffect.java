package com.trifle.fingerfing.client.old.effect;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.user.client.ui.Widget;


//TODO ������������ �� ������� �������, ����� ���������
@Deprecated
public class WidgetEffect {

	private WidgetEffect() {
	}
	
	private static final int ANIM_TIME = 2000;
	private static Map<Widget, Animation> animationsBgColor = new HashMap<Widget, Animation>();
	
	public static <T extends Widget> void changeColor(T w, EColor newColor){
		w.getElement().getStyle().setColor(newColor.toStringRgbFomat());
	}
	
	public static <T extends Widget> void changeBackgroundColor(T w, EColor newColor){
		if (animationsBgColor.get(w)!=null) {
			animationsBgColor.get(w).cancel();
		}
		w.getElement().getStyle().setBackgroundColor(newColor.toStringRgbFomat());
	}
	
	public static <T extends Widget> void animationBackgroundColor(T w, EColor start, EColor end) {
		if (animationsBgColor.get(w)!=null) {
			animationsBgColor.get(w).cancel();
		}
		Animation anim = new AnimationBgColor<T>(w, start, end);
		animationsBgColor.put(w, anim);
		anim.run(ANIM_TIME);
	}
}



class AnimationBgColor<T extends Widget> extends Animation{

	static final double ANIM_POROG_START = 0;
	static final double ANIM_POROG_RANGE = 0.5;

	final int sRed, sGreen, sBlue, eRed, eGreen, eBlue;
	final T widget;
	
	public  AnimationBgColor(T widget, EColor start, EColor end) {
		this.widget = widget;
		this.sRed = start.getRed();
		this.sGreen = start.getGreen();
		this.sBlue = start.getBlue();
		this.eRed = end.getRed();
		this.eGreen = end.getGreen();
		this.eBlue = end.getBlue();
	}
	
	@Override
	protected void onUpdate(double progress) {
		String nextColor = "rgb("
				+ (int) (sRed + (eRed - sRed)
						* (progress * (1 - ANIM_POROG_RANGE)) + ((progress > ANIM_POROG_START) ? ((eRed - sRed) * (ANIM_POROG_RANGE))
						: 0))
				+ ", "
				+ (int) (sGreen + (eGreen - sGreen)
						* (progress * (1 - ANIM_POROG_RANGE)) + ((progress > ANIM_POROG_START) ? ((eGreen - sGreen) * (ANIM_POROG_RANGE))
						: 0))
				+ ", "
				+ (int) (sBlue + (eBlue - sBlue)
						* (progress * (1 - ANIM_POROG_RANGE)) + ((progress > ANIM_POROG_START) ? ((eBlue - sBlue) * (ANIM_POROG_RANGE))
						: 0)) + ")";
//		KRLogger.getLogger().fine("onUpdate: setBackgroundColor: "+nextColor);
		widget.getElement().getStyle().setBackgroundColor(nextColor);
	}
}