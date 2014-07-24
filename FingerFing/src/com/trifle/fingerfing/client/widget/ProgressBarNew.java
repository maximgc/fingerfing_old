package com.trifle.fingerfing.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class ProgressBarNew extends Composite implements ProgressBar{

	private static final String SEGMENT_WIDTH = "10px";
	private static final String SEGMENT_HEIGHT = "2px";
	private static final int SEGMENT_COUNT = 10;
	private int segmentCount;
	
	public int getSegmentCount() {
		return segmentCount;
	}

	public void setSegmentCount(int segmentCount) {
		this.segmentCount = (segmentCount < SEGMENT_COUNT) ? SEGMENT_COUNT : segmentCount;
		removeSegments();
		initSegments();
	}


	private static ProgressBarNewUiBinder uiBinder = GWT
			.create(ProgressBarNewUiBinder.class);
	@UiField HorizontalPanel hPanel;

	interface ProgressBarNewUiBinder extends UiBinder<Widget, ProgressBarNew> {
	}

	
	private Button[] buts = new Button[SEGMENT_COUNT];
	
	public ProgressBarNew() {
		this(SEGMENT_COUNT);
	}
	
	public ProgressBarNew(int segmentCount) {
		this.segmentCount = (segmentCount < SEGMENT_COUNT) ? SEGMENT_COUNT : segmentCount;
		initWidget(uiBinder.createAndBindUi(this));
		initSegments();
	}

	private void initSegments() {
		buts = new Button[segmentCount];
		for (int i = 0; i<segmentCount; i++){
			buts[i] = new Button();
			hPanel.add(buts[i]);
			buts[i].setStyleName("keyboardKey");
			buts[i].setWidth(SEGMENT_WIDTH);
			buts[i].setHeight(SEGMENT_HEIGHT);
			Effects.effectDisable.apply(buts[i]);
		}
	}
	
	private void removeSegments() {
		for (int i = 0; i < buts.length; i++){
			hPanel.remove(buts[i]);
		}
	}

	
	private long range;
	private long position;
	private long maxPosition;

	@Override
	public long getRange() {
		return range;
	}
	
	
	@Override
	public void setRangeInt(int range) {
		setRange((long)range);
	}
	
	@Override
	public void setRange(long range) {
		this.range = range;
	}

	@Override
	public long getPosition() {
		return position;
	}

	@Override
	public void setPositionInt(int position) {
		setPosition((long)position);
	}
	
	@Override
	public void setPosition(long ps) {
		position = ps;
		position = (position>range)?range:position;
		position = (position<0)?0:position;
		
		if (maxPosition<position) {
			maxPosition = position;
		}
		
		drawPosition();
	}

	private void drawPosition() {
		for (int i = 0; i<(position*segmentCount+5)/(range); i++){
			Effects.effectEnable.apply(buts[i]);
		}
		for (int i = (int)((position*segmentCount+5)/(range)); i<segmentCount ; i++){
			Effects.effectDisable.apply(buts[i]);
		}
	}

	@Override
	public long getMaxPosition() {
		return maxPosition;
	}

	@Override
	public void setMaxPositionInt(int maxPosition) {
		setMaxPosition((long) maxPosition);
	}
	
	@Override
	public void setMaxPosition(long maxPosition) {
		this.maxPosition = maxPosition;
		if (position>maxPosition) {
			position = maxPosition;
		}
	}
	
	
}
