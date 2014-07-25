package com.trifle.fingerfing.client.widget;

import java.util.List;
import java.util.Set;

import com.google.gwt.user.client.ui.IsWidget;
import com.trifle.fingerfing.client.AlternativeKeyLayout;
import com.trifle.fingerfing.client.NativeKey;
import com.trifle.fingerfing.client.widget.effect.Effect;

public interface KeyboardWidget extends IsWidget{

	public abstract void setEffect(NativeKey n, Effect e);

	public abstract void setEffectAll(Effect e);

	public abstract void setEffectAll(List<NativeKey> sn, Effect e);

	public abstract void setEffectAll(Set<NativeKey> sn, Effect e);

	public abstract AlternativeKeyLayout getAlternateveKeyLayout();

	public abstract void setAlternateveKeyLayout(AlternativeKeyLayout alternateveKeyLayout);
	
	public abstract void setKeyBlock(KeyBlock keyBlock);

	public abstract KeyBlock getKeyBlock();

	public static interface KeyBlock {
		public List<KeyRow> getBlock();

		public void setBlock(List<KeyRow> block);
	}

	public static interface KeyRow {
		public List<Key> getRow();

		public void setRow(List<Key> row);
	}

	public static interface Key {
		public NativeKey getNativeKey();

		public void setNativeKey(NativeKey nativeKey);

		public String getWidth();

		public void setWidth(String width);
	}

}