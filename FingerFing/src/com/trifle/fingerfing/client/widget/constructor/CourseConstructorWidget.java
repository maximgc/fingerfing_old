package com.trifle.fingerfing.client.widget.constructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;
import com.trifle.fingerfing.client.NativeKey;
import com.trifle.fingerfing.client.json.BeanManager;
import com.trifle.fingerfing.client.json.BeanManager.CourseDescriptorBeans;
import com.trifle.fingerfing.client.lesson.Course;
import com.trifle.fingerfing.client.lesson.Course.Descriptor;
import com.trifle.fingerfing.client.lesson.Course.CourseDescriptor;
import com.trifle.fingerfing.client.lesson.Course.ExerciseDescriptor;
import com.trifle.fingerfing.client.lesson.Course.LessonDescriptor;
import com.trifle.fingerfing.client.lesson.Course.LevelDescriptor;

public class CourseConstructorWidget extends Composite {

	private static CourseConstructorWidgetUiBinder uiBinder = GWT
			.create(CourseConstructorWidgetUiBinder.class);
	@UiField
	VerticalPanel actPanel;
	@UiField
	Button createLevel;
	@UiField
	VerticalPanel jsonPanel;
	@UiField
	TextArea jsonTextArea;
	@UiField
	Button generateJSON;
	@UiField
	Button createLesson;
	@UiField
	Button createExercise;
	@UiField
	Button createCourse;
	@UiField
	HorizontalPanel navPanel;
	@UiField
	TextBox inputKeys;
	@UiField
	IntegerBox finalScore;
	@UiField
	ListBox passMethod;

	interface CourseConstructorWidgetUiBinder extends
			UiBinder<Widget, CourseConstructorWidget> {
	}

	public CourseConstructorWidget(CourseDescriptorBeans cb) {
		initWidget(uiBinder.createAndBindUi(this));
		this.cb = cb;

		levelSelection = new SingleSelectionModel<Course.LevelDescriptor>();
		lessonSelection = new SingleSelectionModel<Course.LessonDescriptor>();
		exerciseSelection = new SingleSelectionModel<Course.ExerciseDescriptor>();

		levelSelection
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					@Override
					public void onSelectionChange(SelectionChangeEvent event) {
						// lessonSelection.clear();
						// exerciseSelection.clear();
					}
				});
		lessonSelection
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					@Override
					public void onSelectionChange(SelectionChangeEvent event) {
						// levelSelection.clear();
						// exerciseSelection.clear();
					}
				});
		exerciseSelection
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					@Override
					public void onSelectionChange(SelectionChangeEvent event) {
						// levelSelection.clear();
						// lessonSelection.clear();
						finalScore.setText(exerciseSelection
								.getSelectedObject().getFinalScore());
						switch (exerciseSelection.getSelectedObject()
								.getMethodSelectName()) {
						case "order":
							passMethod.setSelectedIndex(0);
							break;
						case "random":
							passMethod.setSelectedIndex(1);
							break;

						default:
							passMethod.setSelectedIndex(0);
							break;
						}
					}
				});

	}

	private BeanManager.CourseDescriptorBeans cb;

	// Navigation tree
	// @UiField
	CellTree navTree;

	// Selectors
	private SingleSelectionModel<LevelDescriptor> levelSelection;

	private SingleSelectionModel<LessonDescriptor> lessonSelection;

	private SingleSelectionModel<ExerciseDescriptor> exerciseSelection;

	// Current selected Descriptors
	private CourseDescriptor curCourseDescriptor;

	// Provider's Map
	// private Map<Descriptor, ListDataProvider<Descriptor>> providers = new
	// HashMap<Descriptor, ListDataProvider<Descriptor>>();

	private Map<CourseDescriptor, ListDataProvider<LevelDescriptor>> levelProviders = new HashMap<CourseDescriptor, ListDataProvider<LevelDescriptor>>();

	private Map<LevelDescriptor, ListDataProvider<LessonDescriptor>> lessonProviders = new HashMap<LevelDescriptor, ListDataProvider<LessonDescriptor>>();;

	private Map<LessonDescriptor, ListDataProvider<ExerciseDescriptor>> exerciseProviders = new HashMap<LessonDescriptor, ListDataProvider<ExerciseDescriptor>>();;

	@UiHandler("createCourse")
	void onCreateCourseClick(ClickEvent event) {
		if (navTree != null)
			navTree.removeFromParent();
		CourseDescriptor cd = cb.createCourseDescriptor();
		cd.setName("Noname Course");
		cd.setLevels(new ArrayList<Course.LevelDescriptor>());

		levelProviders.put(cd,
				new ListDataProvider<LevelDescriptor>(cd.getLevels()));
		curCourseDescriptor = cd;
		navTree = new CellTree(new CourseTreeModel(), cd);
		navPanel.add(navTree);
	}

	@UiHandler("createLevel")
	void onCreateLevelClick(ClickEvent event) {
		List<LevelDescriptor> levelList = levelProviders.get(
				curCourseDescriptor).getList();

		LevelDescriptor ld = cb.createLevelDescriptor();
		ld.setName("Level " + (levelList.size() + 1));
		ld.setLessons(new ArrayList<LessonDescriptor>());
		levelList.add(ld);

		lessonProviders.put(ld, new ListDataProvider<Course.LessonDescriptor>(
				ld.getLessons()));
		levelSelection.setSelected(ld, true);
	}

	@UiHandler("createLesson")
	void onCreateLessonClick(ClickEvent event) {
		List<LessonDescriptor> lessonList = lessonProviders.get(
				levelSelection.getSelectedObject()).getList();

		LessonDescriptor ld = cb.createLessonDescriptor();
		ld.setName("Lesson " + (lessonList.size() + 1));
		ld.setExercises(new ArrayList<Course.ExerciseDescriptor>());
		lessonList.add(ld);

		exerciseProviders.put(
				ld,
				new ListDataProvider<Course.ExerciseDescriptor>(ld
						.getExercises()));
		lessonSelection.setSelected(ld, true);
	}

	@UiHandler("createExercise")
	void onCreateExerciseClick(ClickEvent event) {
		List<ExerciseDescriptor> exList = exerciseProviders.get(
				lessonSelection.getSelectedObject()).getList();

		ExerciseDescriptor ed = cb.createExerciseDescriptor();
		ed.setName("Exercise " + (exList.size() + 1));
		ed.setKeySequence(new ArrayList<NativeKey>());
		ed.setFinalScore(finalScore.getText());
		ed.setMethodSelectName(passMethod.getValue(passMethod
				.getSelectedIndex()));
		exList.add(ed);

		exerciseSelection.setSelected(ed, true);
	}

	@UiHandler("inputKeys")
	void onInputKeysKeyUp(KeyUpEvent event) {
		if (exerciseSelection.getSelectedObject() != null) {
			exerciseSelection.getSelectedObject().getKeySequence()
					.add(NativeKey.getByNativeCode(event.getNativeKeyCode()));
		}
	}

	@UiHandler("passMethod")
	void onPassMethodChange(ChangeEvent event) {
		if (exerciseSelection.getSelectedObject() != null) {
			exerciseSelection.getSelectedObject().setMethodSelectName(
					passMethod.getValue(passMethod.getSelectedIndex()));
		}
	}

	@UiHandler("finalScore")
	void onFinalScoreChange(ChangeEvent event) {
		if (exerciseSelection.getSelectedObject() != null) {
			exerciseSelection.getSelectedObject().setFinalScore(
					finalScore.getText());
		}
	}

	@UiHandler("generateJSON")
	void onGenerateJSONClick(ClickEvent event) {
		jsonTextArea.setText(cb.encodeCourseDescriptor(curCourseDescriptor));
	}

	private class CourseTreeModel implements TreeViewModel {

		class CellCourse<T extends Descriptor> extends AbstractCell<T> {
			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Descriptor value, SafeHtmlBuilder sb) {
				sb.appendEscaped(value.getName());
			}
		};

		@Override
		public <T> NodeInfo<?> getNodeInfo(T value) {

			if (value instanceof CourseDescriptor) {
				return new DefaultNodeInfo<LevelDescriptor>(
						levelProviders.get(value),
						new CellCourse<LevelDescriptor>(), levelSelection, null);
			} else if (value instanceof LevelDescriptor) {
				return new DefaultNodeInfo<LessonDescriptor>(
						lessonProviders.get(value),
						new CellCourse<LessonDescriptor>(), lessonSelection,
						null);
			} else if (value instanceof LessonDescriptor) {
				return new DefaultNodeInfo<ExerciseDescriptor>(
						exerciseProviders.get(value),
						new CellCourse<ExerciseDescriptor>(),
						exerciseSelection, null);
			}

			return null;
		}

		@Override
		public boolean isLeaf(Object value) {
			return (value instanceof ExerciseDescriptor);
		}

	}

}
