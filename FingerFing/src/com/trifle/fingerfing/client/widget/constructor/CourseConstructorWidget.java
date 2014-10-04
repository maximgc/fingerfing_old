package com.trifle.fingerfing.client.widget.constructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.trifle.fingerfing.client.NativeKey;
import com.trifle.fingerfing.client.json.BeanManager;
import com.trifle.fingerfing.client.lesson.Course;
import com.trifle.fingerfing.client.lesson.Course.CourseDescriptor;
import com.trifle.fingerfing.client.lesson.Course.ExerciseDescriptor;
import com.trifle.fingerfing.client.lesson.Course.LessonDescriptor;
import com.trifle.fingerfing.client.lesson.Course.LevelDescriptor;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.event.dom.client.ChangeEvent;

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

	public CourseConstructorWidget(BeanManager bm) {
		initWidget(uiBinder.createAndBindUi(this));
		cb = bm.new CourseDescriptorBeans();
		initSelections();
	}

	private void initSelections() {

		levelSelection = new SingleSelectionModel<Course.LevelDescriptor>();
		lessonSelection = new SingleSelectionModel<Course.LessonDescriptor>();
		exerciseSelection = new SingleSelectionModel<Course.ExerciseDescriptor>();

		levelSelection
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					@Override
					public void onSelectionChange(SelectionChangeEvent event) {
						curLevelDescriptor = levelSelection.getSelectedObject();
					}
				});

		lessonSelection
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					@Override
					public void onSelectionChange(SelectionChangeEvent event) {
						curLessonDescriptor = lessonSelection
								.getSelectedObject();
					}
				});

		exerciseSelection
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					@Override
					public void onSelectionChange(SelectionChangeEvent event) {
						curExerciseDescriptor = exerciseSelection
								.getSelectedObject();
					}
				});

	}

	class CourseTreeModel implements TreeViewModel {

		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public <T> NodeInfo<?> getNodeInfo(T value) {
			if (value == null && curCourseDescriptor != null) {

				Cell<LevelDescriptor> levelTreeCell = new AbstractCell<Course.LevelDescriptor>() {
					@Override
					public void render(
							com.google.gwt.cell.client.Cell.Context context,
							LevelDescriptor value, SafeHtmlBuilder sb) {
						sb.appendEscaped(value.getName());
					}
				};

				return new DefaultNodeInfo<LevelDescriptor>(levelProviders.get(curCourseDescriptor.getLevels()),
						levelTreeCell, levelSelection, null);
			}

			if (value instanceof LevelDescriptor) {
				Cell<LessonDescriptor> lessonTreeCell = new AbstractCell<Course.LessonDescriptor>() {
					@Override
					public void render(
							com.google.gwt.cell.client.Cell.Context context,
							LessonDescriptor value, SafeHtmlBuilder sb) {
						sb.appendEscaped(value.getName());
					}
				};

				return new DefaultNodeInfo<LessonDescriptor>(lessonProviders.get(((LevelDescriptor) value).getLessons()),
						lessonTreeCell, lessonSelection, null);
			}

			if (value instanceof LessonDescriptor) {
				Cell<ExerciseDescriptor> exerciseTreeCell = new AbstractCell<Course.ExerciseDescriptor>() {
					@Override
					public void render(
							com.google.gwt.cell.client.Cell.Context context,
							ExerciseDescriptor value, SafeHtmlBuilder sb) {
						sb.appendEscaped(value.getName());
					}
				};

				return new DefaultNodeInfo<ExerciseDescriptor>(
						exerciseProviders.get(((LessonDescriptor) value).getExercises()), exerciseTreeCell, exerciseSelection,
						null);
			}

			return null;
		}

		@Override
		public boolean isLeaf(Object value) {
			return (value instanceof ExerciseDescriptor);
		}

	}

	private void initNavTree() {
		TreeViewModel model = new CourseTreeModel();

		navTree = new CellTree(model, null);

		navPanel.add(navTree);
	}

	private BeanManager.CourseDescriptorBeans cb;

	// Current selected Descriptors
	private CourseDescriptor curCourseDescriptor;

	private LevelDescriptor curLevelDescriptor;

	private LessonDescriptor curLessonDescriptor;

	private ExerciseDescriptor curExerciseDescriptor;

	// DataProviders
	private ListDataProvider<LevelDescriptor> curLevelsProvider;

	private ListDataProvider<LessonDescriptor> curLessonsProvider;

	private ListDataProvider<ExerciseDescriptor> curExercisesProvider;
	
	//Provider's Map
	
	private Map<List<LevelDescriptor>, ListDataProvider<LevelDescriptor>> levelProviders;

	private Map<List<LessonDescriptor> , ListDataProvider<LessonDescriptor>> lessonProviders;

	private Map<List<ExerciseDescriptor> , ListDataProvider<ExerciseDescriptor>> exerciseProviders;

	// Navigation tree
	CellTree navTree;

	// Selectors
	private SingleSelectionModel<LevelDescriptor> levelSelection;

	private SingleSelectionModel<LessonDescriptor> lessonSelection;

	private SingleSelectionModel<ExerciseDescriptor> exerciseSelection;

	private void createCourse() {
		curCourseDescriptor = cb.createCourseDescriptor();
		curCourseDescriptor.setName("Noname Course");
		List<LevelDescriptor> levelList = new ArrayList<LevelDescriptor>();
		curCourseDescriptor.setLevels(levelList);
		curLevelsProvider = new ListDataProvider<Course.LevelDescriptor>(
				levelList);
		levelProviders.put(levelList, curLevelsProvider);
	}

	private void createLevel() {
		curLevelDescriptor = cb.createLevelDescriptor();
		curLevelDescriptor.setName("Level "
				+ (curLevelsProvider.getList().size() + 1));
		List<LessonDescriptor> lessonList = new ArrayList<LessonDescriptor>();
		curLevelDescriptor.setLessons(lessonList);
		curLessonsProvider = new ListDataProvider<Course.LessonDescriptor>(
				lessonList);
		curLevelsProvider.getList().add(curLevelDescriptor);
		lessonProviders.put(lessonList, curLessonsProvider);
	}

	private void createLesson() {
		curLessonDescriptor = cb.createLessonDescriptor();
		curLessonDescriptor.setName("Lesson "
				+ (curLessonsProvider.getList().size() + 1));
		List<ExerciseDescriptor> exercisesList = new ArrayList<ExerciseDescriptor>();
		curLessonDescriptor.setExercises(exercisesList);
		curExercisesProvider = new ListDataProvider<Course.ExerciseDescriptor>(
				exercisesList);
		curLessonsProvider.getList().add(curLessonDescriptor);
		exerciseProviders.put(exercisesList, curExercisesProvider);
	}

	private void createExercise() {
		curExerciseDescriptor = cb.createExerciseDescriptor();
		curExerciseDescriptor.setName("Exercise "
				+ (curExercisesProvider.getList().size() + 1));
		List<NativeKey> nkList = new ArrayList<NativeKey>();
		curExerciseDescriptor.setKeySequence(nkList);
		curExerciseDescriptor.setFinalScore(finalScore.getValue().toString());
		curExerciseDescriptor.setMethodSelectName(passMethod
				.getValue(passMethod.getSelectedIndex()));
		curExercisesProvider.getList().add(curExerciseDescriptor);
	}

	@UiHandler("createCourse")
	void onCreateCourseClick(ClickEvent event) {
		createCourse();
		initNavTree();
	}

	@UiHandler("createLevel")
	void onCreateLevelClick(ClickEvent event) {
		createLevel();
		// levelSelection.setSelected(curLevelDescriptor, true);
	}

	@UiHandler("createLesson")
	void onCreateLessonClick(ClickEvent event) {
		createLesson();
		// lessonSelection.setSelected(curLessonDescriptor, true);
	}

	@UiHandler("createExercise")
	void onCreateExerciseClick(ClickEvent event) {
		createExercise();
		// exerciseSelection.setSelected(curExerciseDescriptor, true);
	}

	@UiHandler("inputKeys")
	void onInputKeysKeyUp(KeyUpEvent event) {
		curExerciseDescriptor.getKeySequence().add(
				NativeKey.getByNativeCode(event.getNativeKeyCode()));
	}

	@UiHandler("passMethod")
	void onPassMethodChange(ChangeEvent event) {
		curExerciseDescriptor.setMethodSelectName(passMethod
				.getValue(passMethod.getSelectedIndex()));
	}

	@UiHandler("finalScore")
	void onFinalScoreChange(ChangeEvent event) {
		curExerciseDescriptor.setFinalScore(finalScore.getValue().toString());
	}

	@UiHandler("generateJSON")
	void onGenerateJSONClick(ClickEvent event) {
		jsonTextArea.setText(cb.encodeCourseDescriptor(curCourseDescriptor));
	}
}
