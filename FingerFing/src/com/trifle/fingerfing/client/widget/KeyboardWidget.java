package com.trifle.fingerfing.client.widget;

import java.util.List;
import java.util.Set;

import com.google.gwt.user.client.ui.IsWidget;
import com.trifle.fingerfing.client.NativeKey;
import com.trifle.fingerfing.client.widget.effect.Effect;

public interface KeyboardWidget extends IsWidget{

	public abstract void setEffect(NativeKey n, Effect e);

	public abstract void setEffectAll(Effect e);

	public abstract void setEffectAll(List<NativeKey> sn, Effect e);

	public abstract void setEffectAll(Set<NativeKey> sn, Effect e);

}