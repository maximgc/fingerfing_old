package com.trifle.fingerfing.client.widget.construct;

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
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;
import com.trifle.fingerfing.client.NativeKey;
import com.trifle.fingerfing.client.json.BeanManager;
import com.trifle.fingerfing.client.json.BeanManager.CourseDescriptorBeans;
import com.trifle.fingerfing.client.lesson.CourseDescriptor;
import com.trifle.fingerfing.client.lesson.Descriptor;

//самостоятельный виджет для создания курса.
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
	TextArea inputKeys;
	@UiField
	IntegerBox finalScore;
	@UiField
	ListBox passMethod;
	@UiField Button deleteLevel;
	@UiField Button deleteLesson;
	@UiField Button deleteExercise;
	@UiField Button clearKeySequence;

	interface CourseConstructorWidgetUiBinder extends
			UiBinder<Widget, CourseConstructorWidget> {
	}

	public CourseConstructorWidget(CourseDescriptorBeans cb) {
		initWidget(uiBinder.createAndBindUi(this));
		this.cb = cb;

		levelSelection = new SingleSelectionModel<CourseDescriptor.LevelDescriptor>();
		lessonSelection = new SingleSelectionModel<CourseDescriptor.LessonDescriptor>();
		exerciseSelection = new SingleSelectionModel<CourseDescriptor.ExerciseDescriptor>();

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
						inputKeys.setText(exerciseSelection.getSelectedObject().getKeySequence().toString());
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
	private SingleSelectionModel<CourseDescriptor.LevelDescriptor> levelSelection;

	private SingleSelectionModel<CourseDescriptor.LessonDescriptor> lessonSelection;

	private SingleSelectionModel<CourseDescriptor.ExerciseDescriptor> exerciseSelection;

	// Current selected Descriptors
	private CourseDescriptor curCourseDescriptor;

	// Provider's Map
	// private Map<Descriptor, ListDataProvider<Descriptor>> providers = new
	// HashMap<Descriptor, ListDataProvider<Descriptor>>();

	private Map<CourseDescriptor, ListDataProvider<CourseDescriptor.LevelDescriptor>> levelProviders = new HashMap<CourseDescriptor, ListDataProvider<CourseDescriptor.LevelDescriptor>>();

	private Map<CourseDescriptor.LevelDescriptor, ListDataProvider<CourseDescriptor.LessonDescriptor>> lessonProviders = new HashMap<CourseDescriptor.LevelDescriptor, ListDataProvider<CourseDescriptor.LessonDescriptor>>();;

	private Map<CourseDescriptor.LessonDescriptor, ListDataProvider<CourseDescriptor.ExerciseDescriptor>> exerciseProviders = new HashMap<CourseDescriptor.LessonDescriptor, ListDataProvider<CourseDescriptor.ExerciseDescriptor>>();;

	@UiHandler("createCourse")
	void onCreateCourseClick(ClickEvent event) {
		if (navTree != null)
			navTree.removeFromParent();
		CourseDescriptor cd = cb.createCourseDescriptor();
		cd.setName("Noname Course");
		cd.setLevels(new ArrayList<CourseDescriptor.LevelDescriptor>());

		levelProviders.put(cd,
				new ListDataProvider<CourseDescriptor.LevelDescriptor>(cd.getLevels()));
		curCourseDescriptor = cd;
		navTree = new CellTree(new CourseTreeModel(), cd);
		navPanel.add(navTree);
	}

	@UiHandler("createLevel")
	void onCreateLevelClick(ClickEvent event) {
		List<CourseDescriptor.LevelDescriptor> levelList = levelProviders.get(
				curCourseDescriptor).getList();

		CourseDescriptor.LevelDescriptor ld = cb.createLevelDescriptor();
		ld.setName("Level " + (levelList.size() + 1));
		ld.setLessons(new ArrayList<CourseDescriptor.LessonDescriptor>());
		levelList.add(ld);

		lessonProviders.put(ld, new ListDataProvider<CourseDescriptor.LessonDescriptor>(
				ld.getLessons()));
		levelSelection.setSelected(ld, true);
	}

	@UiHandler("deleteLevel")
	void onDeleteLevelClick(ClickEvent event) {
		List<CourseDescriptor.LevelDescriptor> levelList = levelProviders.get(
				curCourseDescriptor).getList();
		levelList.remove(levelSelection.getSelectedObject());
	}

	@UiHandler("createLesson")
	void onCreateLessonClick(ClickEvent event) {
		List<CourseDescriptor.LessonDescriptor> lessonList = lessonProviders.get(
				levelSelection.getSelectedObject()).getList();

		CourseDescriptor.LessonDescriptor ld = cb.createLessonDescriptor();
		ld.setName("Lesson " + (lessonList.size() + 1));
		ld.setExercises(new ArrayList<CourseDescriptor.ExerciseDescriptor>());
		lessonList.add(ld);

		exerciseProviders.put(
				ld,
				new ListDataProvider<CourseDescriptor.ExerciseDescriptor>(ld
						.getExercises()));
		lessonSelection.setSelected(ld, true);
	}

	@UiHandler("deleteLesson")
	void onDeleteLessonClick(ClickEvent event) {
		List<CourseDescriptor.LessonDescriptor> lessonList = lessonProviders.get(
				levelSelection.getSelectedObject()).getList();
		lessonList.remove(lessonSelection.getSelectedObject());
	}

	@UiHandler("createExercise")
	void onCreateExerciseClick(ClickEvent event) {
		List<CourseDescriptor.ExerciseDescriptor> exList = exerciseProviders.get(
				lessonSelection.getSelectedObject()).getList();

		CourseDescriptor.ExerciseDescriptor ed = cb.createExerciseDescriptor();
		ed.setName("Exercise " + (exList.size() + 1));
		ed.setKeySequence(new ArrayList<NativeKey>());
		ed.setFinalScore(finalScore.getText());
		ed.setMethodSelectName(passMethod.getValue(passMethod
				.getSelectedIndex()));
		exList.add(ed);

		exerciseSelection.setSelected(ed, true);
	}

	@UiHandler("deleteExercise")
	void onDeleteExerciseClick(ClickEvent event) {
		List<CourseDescriptor.ExerciseDescriptor> exList = exerciseProviders.get(
				lessonSelection.getSelectedObject()).getList();
		exList.remove(exerciseSelection.getSelectedObject());
	}

	@UiHandler("inputKeys")
	void onInputKeysKeyUp(KeyUpEvent event) {
		if (exerciseSelection.getSelectedObject() != null) {
			exerciseSelection.getSelectedObject().getKeySequence()
					.add(NativeKey.getByNativeCode(event.getNativeKeyCode()));
			inputKeys.setText(exerciseSelection.getSelectedObject().getKeySequence().toString());
		}
	}

	@UiHandler("clearKeySequence")
	void onClearKeySequenceClick(ClickEvent event) {
		exerciseSelection.getSelectedObject().getKeySequence().clear();
		inputKeys.setText(exerciseSelection.getSelectedObject().getKeySequence().toString());
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
				return new DefaultNodeInfo<CourseDescriptor.LevelDescriptor>(
						levelProviders.get(value),
						new CellCourse<CourseDescriptor.LevelDescriptor>(), levelSelection, null);
			} else if (value instanceof CourseDescriptor.LevelDescriptor) {
				return new DefaultNodeInfo<CourseDescriptor.LessonDescriptor>(
						lessonProviders.get(value),
						new CellCourse<CourseDescriptor.LessonDescriptor>(), lessonSelection,
						null);
			} else if (value instanceof CourseDescriptor.LessonDescriptor) {
				return new DefaultNodeInfo<CourseDescriptor.ExerciseDescriptor>(
						exerciseProviders.get(value),
						new CellCourse<CourseDescriptor.ExerciseDescriptor>(),
						exerciseSelection, null);
			}

			return null;
		}

		@Override
		public boolean isLeaf(Object value) {
			return (value instanceof CourseDescriptor.ExerciseDescriptor);
		}

	}
}
