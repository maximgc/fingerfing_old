package com.trifle.fingerfing.client.widget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.xml.stream.events.Characters;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.TextArea;
import com.trifle.fingerfing.client.NativeKey;

public class ExerciseTextW extends Composite implements ExercisePassingW {

	private static ExerciseTextWUiBinder uiBinder = GWT
			.create(ExerciseTextWUiBinder.class);

	@UiField
	TextArea textArea;

	private Collection<NativeKey> area;

	private List<NativeKey> seq;
	
	private int[] evals;

	private List<NativeKey> curBlock;

	private NativeKey curElem;

	private int curPos;

	interface ExerciseTextWUiBinder extends UiBinder<Widget, ExerciseTextW> {
	}

	public ExerciseTextW() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setWorkArea(Collection<NativeKey> area) {
		this.area = area;
		showAll();
	}

	private void showAll() {
		String all = "";
		all += "Area: " + area + (char)13;
		all += "Sequence: " + seq + (char)13;
		all += "Cur Block: " + curBlock + (char)13;
		all += "Cur Element(" + curPos + "): " + curElem + (char)13;
		all += "Evals: " + Arrays.toString(evals) + (char)13;
		textArea.setText(all);
	}

	@Override
	public void setWorkSequence(List<NativeKey> seq) {
		this.seq = seq;
		evals = new int[seq.size()];
		showAll();
	}

	@Override
	public void setCurrentBlock(int pos, int lenght) {
		curBlock = seq.subList(pos, pos + lenght);
		showAll();
	}

	@Override
	public void setCurrentElement(int pos) {
		this.curPos = pos;
		curElem = seq.get(pos);
		showAll();
	}

	@Override
	public void setEvaluate(int eval) {
		evals[curPos] = eval;
		showAll();
	}

	@Override
	public void setEvaluate(int pos, int eval) {
		evals[pos] = eval;
		showAll();
	}

}
