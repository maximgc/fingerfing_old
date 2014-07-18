package com.trifle.fingerfing.client.widget;

import com.google.gwt.user.client.ui.IsWidget;

public interface ProgressBar extends IsWidget{

	public abstract int getRange();

	public abstract void setRange(int range);

	public abstract int getPosition();

	public abstract void setPosition(int position);

	public abstract int getMaxPosition();

	public abstract void setMaxPosition(int maxPosition);

}