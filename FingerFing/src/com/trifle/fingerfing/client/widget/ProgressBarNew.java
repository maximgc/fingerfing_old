package com.trifle.fingerfing.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class ProgressBarNew extends Composite implements ProgressBar{

	private static ProgressBarNewUiBinder uiBinder = GWT
			.create(ProgressBarNewUiBinder.class);
	@UiField HorizontalPanel hPanel;

	interface ProgressBarNewUiBinder extends UiBinder<Widget, ProgressBarNew> {
	}

	private Button[] buts = new Button[10];
	
	public ProgressBarNew() {
		initWidget(uiBinder.createAndBindUi(this));
		for (int i = 0; i<10; i++){
			buts[i] = new Button();
			hPanel.add(buts[i]);
			buts[i].setWidth("10px");
			buts[i].setHeight("10px");
			buts[i].setStyleName("keyboardKey");
			Effects.effectDisable.apply(buts[i]);
		}
	}

	
	private int range;
	private int position;
	private int maxPosition;

	@Override
	public int getRange() {
		return range;
	}
	
	@Override
	public void setRange(int range) {
		this.range = range;
	}

	@Override
	public int getPosition() {
		return position;
	}

	@Override
	public void setPosition(int position) {
		this.position = (position>range)?range:position;
		if (maxPosition<position) {
			maxPosition = position;
		}
		for (int i = 0; i<(position*10+5)/range; i++){
			Effects.effectEnable.apply(buts[i]);
		}
		for (int i = (position*10+5)/range; i<10 ; i++){
			Effects.effectDisable.apply(buts[i]);
		}
//		imgPos.setWidth(position*100/range+"%");
//		imgMaxPos.setWidth((maxPosition-position)*100/range+"%");
	}

	@Override
	public int getMaxPosition() {
		return maxPosition;
	}

	@Override
	public void setMaxPosition(int maxPosition) {
		this.maxPosition = maxPosition;
		if (position>maxPosition) {
			position = maxPosition;
		}
//		imgPos.setWidth(position*100/range+"%");
//		imgMaxPos.setWidth((maxPosition-position)*100/range+"%");
	}
	
	
}
