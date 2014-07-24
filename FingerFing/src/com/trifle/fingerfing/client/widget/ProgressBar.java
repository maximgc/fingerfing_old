package com.trifle.fingerfing.client.widget;

import com.google.gwt.user.client.ui.IsWidget;

public interface ProgressBar extends IsWidget{

	public abstract long getRange();

	public abstract void setRange(long range);
	
	public abstract void setRangeInt(int range);

	public abstract long getPosition();

	public abstract void setPosition(long position);

	public abstract void setPositionInt(int position);
	
	public abstract long getMaxPosition();

	public abstract void setMaxPosition(long maxPosition);

	public abstract void setMaxPositionInt(int maxPosition);
}