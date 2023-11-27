package view_project;

import java.io.Serializable;
import java.util.Vector;
import javax.swing.JOptionPane;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import listeners_project.SystemUIEventsListener;
import model_project.Answer;
import model_project.Exam;
import model_project.Manager;
import model_project.Set;

public class SystemJavaFx implements AbstractView, Serializable {

	private Vector<SystemUIEventsListener> allListeners = new Vector<SystemUIEventsListener>();
	private Label lblNewCloseQuestion = new Label("Enter the question:");
	private TextField tfNewTextCloseQuestion = new TextField();

	private Label lblNewAnswer = new Label("Enter Answer:");
	private TextField tfNewAnswer = new TextField();

	private GridPane gpMenu = new GridPane();
	private BorderPane BPExamTable = new BorderPane();
	private VBox gpRightMenu = new VBox();
	private VBox gpLeftMenu = new VBox();
	private GridPane gpOpenQuestion = new GridPane();
	private GridPane gpupdateQuestion = new GridPane();
	private GridPane gpupdateAnswer = new GridPane();
	private GridPane gpcloseQuestion = new GridPane();
	private GridPane gpCloneExam = new GridPane();
	private GridPane gpAutomaticallyExam = new GridPane();
	private GridPane gpDeleteAnswer = new GridPane();
	private ComboBox<Integer> cmbNumOfAnswers = new ComboBox<Integer>();
	private ComboBox<Integer> cmbSerialNumber = new ComboBox<Integer>();
	private RadioButton trueAnswer = new RadioButton("true");
	private RadioButton falseAnswer = new RadioButton("false");
	private ToggleGroup tgTrueOrFalse = new ToggleGroup();
	private TextField tfTextNewQuestion = new TextField();
	private Button btnStorageQuestionAndAnswers = new Button("The Storage");
	private Button btnAddOpenQuestion = new Button("Add Open Question");
	private Button btnAddNewCloseQuestion = new Button("update");
	private Button btnAddCloseQuestion = new Button("Add close Question");
	private Button btnUpdateQuestion = new Button("Update Question");
	private Button save = new Button("Save");
	private Button btnUpdateAnswer = new Button("Update Answer");
	private Button btnUpdateQuestions = new Button("Update");
	private Button btnAdd = new Button("Adding");
	private Button btnDeleteAnswer = new Button("Delete Answer");
	private Button btnExamManually = new Button("Create Exam Manually");
	private Button btnExamAutomatically = new Button("Create Exam Automatically");
	private Button btnClone = new Button("Copy Exam");
	private BackgroundFill background_fill_system = new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY);
	private Label lblNewQuestion = new Label("Enter New Question:");
	private Label lblNumOFSerial = new Label("Enter Serial Number:");
	private Label lblAnswerToUpdate = new Label("Enter New Answer:");
	private Label lblNewOpenQuestion = new Label("Enter the question:");
	private Label lblNewOfAnswers = new Label("How many options of answers:");
	private Label lblNewOpenAnswer = new Label("Enter the answer:");
	private Button btnToUpdateAnswer = new Button("Update Answer");
	private ComboBox<Integer> cmbSerialNumberToAnswer = new ComboBox<Integer>();
	private ComboBox<Integer> cmbAnswer = new ComboBox<Integer>();
	private Label lblNumOFSerialToUpdate = new Label("Enter Serial Number:");
	private Label lblNumOFAnswer = new Label("Enter number of answers to add:");
	private TextField tfNewTextAnswer = new TextField();
	private TextField tfNewTextOpenQuestion = new TextField();
	private TextField tfNewTextOpenAnswer = new TextField();
	private Label lblChooseNumOfQuestions = new Label("Choose number of questions: ");
	private Label lblChooseAnswers = new Label("Choose Answers: ");
	private Spinner<Integer> spNumOfQuestions = new Spinner<Integer>();
	private Button btnCreateAutomatically = new Button("Create exam automatically");
	private RadioButton trueAnswerUpDate = new RadioButton("true");
	private RadioButton falseAnswerUpDate = new RadioButton("false");
	private ToggleGroup gpUpdateAnswers = new ToggleGroup();
	private ListView<String> lvStorage = new ListView<String>();
	private ListView<String> lvAutoExam = new ListView<String>();
	private ListView<String> lvManuallyExam = new ListView<String>();
	private ListView<String> lvcopyExam = new ListView<String>();
	private Label lblNumSerialToExam = new Label("Enter Serial Number:");
	private Label lblDeleteSerial = new Label("choose serial number: ");
	private Label lblDeleteAnswer = new Label("choose Answer to delete: ");
	private ComboBox<Integer> cmballSerial = new ComboBox<Integer>();
	private Button btnDelete = new Button("Delete");
	private Label lbIndex = new Label("Enter index :");
	private ComboBox<Answer> cmbAllAnswersOfQuestion = new ComboBox<Answer>();
	private GridPane gpExamManually = new GridPane();
	private Button btnAddQuestionToExam = new Button("Add Question");
	private Button btnAddAnswerToExam = new Button("Add Answer");
	private Button btnCreateExam = new Button("Create Exam");
	private ComboBox<Integer> cmbAllQuestion = new ComboBox<Integer>();
	private ComboBox<Integer> cmbNumOFAnswerToAdd = new ComboBox<Integer>();
	private ComboBox<Answer> cmbAnswersClose = new ComboBox<Answer>();
	private int numOfQuestion = 0;
	private int countAnswer = 0;
	private int numOfAnswers = 0;

	public SystemJavaFx(Stage theStage) {

		theStage.setTitle("My Exams");
		gpMenu.setBackground(new Background(background_fill_system));
		gpRightMenu.setBackground(new Background(background_fill_system));

		// *****TopMenu*****
		BPExamTable.setTop(gpMenu);
		gpMenu.add(btnStorageQuestionAndAnswers, 0, 0);
		gpMenu.add(btnAddOpenQuestion, 1, 0);
		gpMenu.add(btnUpdateQuestion, 3, 0);
		gpMenu.add(btnUpdateAnswer, 4, 0);
		gpMenu.add(btnDeleteAnswer, 5, 0);
		gpMenu.add(btnAddCloseQuestion, 2, 0);
		gpMenu.setPadding(new Insets(10));
		gpMenu.setHgap(5);

		// ******************

		// *******LeftMenu*******

		gpLeftMenu.setPadding(new Insets(10));
		gpLeftMenu.setSpacing(5);
		gpLeftMenu.getChildren().add(lvStorage);
		lvStorage.setMaxWidth(220);
		gpLeftMenu.setVisible(false);

		// ********************

		// ******RightMenu*******
		gpRightMenu.setPadding(new Insets(10));
		gpRightMenu.getChildren().addAll(btnExamManually, btnExamAutomatically, btnClone);
		gpRightMenu.setAlignment(Pos.CENTER);
		gpRightMenu.setSpacing(5);
		BPExamTable.setRight(gpRightMenu);

		// ***********************

		trueAnswer.setToggleGroup(tgTrueOrFalse);
		falseAnswer.setToggleGroup(tgTrueOrFalse);
		tgTrueOrFalse.selectToggle(trueAnswer);

		// *****buttonOpenQuestion***

		gpOpenQuestion.setPadding(new Insets(10));
		gpOpenQuestion.add(lblNewOpenQuestion, 0, 0);
		gpOpenQuestion.add(tfNewTextOpenQuestion, 1, 0);
		gpOpenQuestion.add(lblNewOpenAnswer, 0, 1);
		gpOpenQuestion.add(tfNewTextOpenAnswer, 1, 1);
		gpOpenQuestion.add(btnAdd, 0, 2);
		gpOpenQuestion.setVgap(10);
		gpOpenQuestion.setHgap(5);
		gpOpenQuestion.setVisible(false);

		// ************************

		// *****buttonUpdateAnswer

		trueAnswerUpDate.setToggleGroup(gpUpdateAnswers);
		falseAnswerUpDate.setToggleGroup(gpUpdateAnswers);
		gpUpdateAnswers.selectToggle(trueAnswerUpDate);
		gpupdateAnswer.setVisible(false);
		gpupdateAnswer.setPadding(new Insets(10));
		gpupdateAnswer.add(lblAnswerToUpdate, 0, 1);
		gpupdateAnswer.add(tfNewTextAnswer, 1, 1);
		gpupdateAnswer.add(trueAnswerUpDate, 2, 1);
		gpupdateAnswer.add(falseAnswerUpDate, 3, 1);
		gpupdateAnswer.add(cmbSerialNumberToAnswer, 1, 0);
		gpupdateAnswer.add(lblNumOFSerialToUpdate, 0, 0);
		gpupdateAnswer.add(btnToUpdateAnswer, 0, 2);
		gpupdateAnswer.add(cmbAnswer, 3, 0);
		gpupdateAnswer.add(lbIndex, 2, 0);
		cmbAnswer.setDisable(true);
		lbIndex.setDisable(true);
		trueAnswerUpDate.setDisable(true);
		falseAnswerUpDate.setDisable(true);
		gpupdateAnswer.setVgap(10);
		gpupdateAnswer.setHgap(5);
		cmbAnswer.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		cmbAnswer.getSelectionModel().select(0);

		// *********************

		// *****buttonCloseQuestion***
		gpcloseQuestion.setVisible(false);
		gpcloseQuestion.setPadding(new Insets(10));
		gpcloseQuestion.add(lblNewCloseQuestion, 0, 0);
		gpcloseQuestion.add(tfNewTextCloseQuestion, 1, 0);
		gpcloseQuestion.add(cmbNumOfAnswers, 1, 1);
		gpcloseQuestion.add(lblNewOfAnswers, 0, 1);
		gpcloseQuestion.setVgap(10);
		gpcloseQuestion.setHgap(5);
		gpcloseQuestion.add(lblNewAnswer, 0, 3);
		gpcloseQuestion.add(tfNewAnswer, 1, 3);
		gpcloseQuestion.add(trueAnswer, 2, 3);
		gpcloseQuestion.add(falseAnswer, 3, 3);
		gpcloseQuestion.add(btnAddNewCloseQuestion, 0, 2);
		gpcloseQuestion.add(save, 0, 4);
		cmbNumOfAnswers.getItems().addAll(2, 3, 4, 5, 6, 7, 8, 9, 10);

		lblNewAnswer.setDisable(true);
		tfNewAnswer.setDisable(true);
		trueAnswer.setDisable(true);
		falseAnswer.setDisable(true);
		save.setDisable(true);

		// *****************

		// *****buttonUpdateQuestion***
		BPExamTable.setCenter(gpupdateQuestion);
		gpupdateQuestion.setPadding(new Insets(10));
		gpupdateQuestion.add(lblNewQuestion, 0, 1);
		gpupdateQuestion.add(tfTextNewQuestion, 1, 1);
		gpupdateQuestion.add(cmbSerialNumber, 1, 0);
		gpupdateQuestion.add(lblNumOFSerial, 0, 0);
		gpupdateQuestion.add(btnUpdateQuestions, 0, 2);
		gpupdateQuestion.setVgap(10);
		gpupdateQuestion.setHgap(5);
		gpupdateQuestion.setVisible(false);
		// *******************

		// ****buttonDeleteAnswer*****
		gpDeleteAnswer.setPadding(new Insets(10));
		gpDeleteAnswer.add(lblDeleteSerial, 0, 0);
		gpDeleteAnswer.add(cmballSerial, 1, 0);
		gpDeleteAnswer.add(lblDeleteAnswer, 0, 1);
		gpDeleteAnswer.add(btnDelete, 0, 2);
		gpDeleteAnswer.setVgap(10);
		gpDeleteAnswer.setHgap(5);
		gpDeleteAnswer.setVisible(false);
		gpDeleteAnswer.add(cmbAllAnswersOfQuestion, 1, 1);

		// *******************

		// *******AutomaticallyExam*******
		SpinnerValueFactory<Integer> vfNumOfQuestion = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 0);
		spNumOfQuestions.setValueFactory(vfNumOfQuestion);

		gpAutomaticallyExam.setPadding(new Insets(20));
		gpAutomaticallyExam.setHgap(20);
		gpAutomaticallyExam.setVgap(20);
		gpAutomaticallyExam.setVisible(false);
		gpAutomaticallyExam.add(lblChooseNumOfQuestions, 0, 0);
		gpAutomaticallyExam.add(spNumOfQuestions, 1, 0);
		gpAutomaticallyExam.add(btnCreateAutomatically, 0, 2);
		gpAutomaticallyExam.add(lvAutoExam, 0, 3);

		// *************************

		// ******ExamManually**********
		gpExamManually.setPadding(new Insets(20));
		gpExamManually.setHgap(20);
		gpExamManually.setVgap(20);
		gpExamManually.setVisible(false);
		gpExamManually.add(cmbAllQuestion, 1, 0);
		gpExamManually.add(lblNumSerialToExam, 0, 0);
		gpExamManually.add(btnAddQuestionToExam, 2, 0);
		gpExamManually.add(lblNumOFAnswer, 0, 1);
		gpExamManually.add(cmbNumOFAnswerToAdd, 1, 1);
		gpExamManually.add(lblChooseAnswers, 0, 2);
		gpExamManually.add(cmbAnswersClose, 1, 2);
		gpExamManually.add(btnAddAnswerToExam, 2, 2);
		gpExamManually.add(btnCreateExam, 0, 3);
		gpExamManually.add(lvManuallyExam, 0, 4);
		cmbAnswersClose.setDisable(true);
		btnAddAnswerToExam.setDisable(true);
		cmbNumOFAnswerToAdd.setDisable(true);

		// *************************

		// ****cloneExam***
		gpCloneExam.setPadding(new Insets(10));
		gpCloneExam.setHgap(10);
		gpCloneExam.setVgap(10);
		gpCloneExam.add(lvcopyExam, 0, 0);
		gpCloneExam.setVisible(false);

		// ******************

		
		
		// *** StorageView ***
		btnStorageQuestionAndAnswers.setOnAction(new EventHandler<ActionEvent>() { // show all the Question and Answer
			@Override
			public void handle(ActionEvent action) {
				BPExamTable.setLeft(gpLeftMenu);
				if (gpLeftMenu.isVisible()) {
					gpLeftMenu.setVisible(false);
				} else {
					gpLeftMenu.setVisible(true);
				}
				if (lvStorage.getItems().isEmpty()) {
					updateStorage();
				}
			}
		});
		// *********
		
		// ** ExamManuallyView***

		btnExamManually.setOnAction(new EventHandler<ActionEvent>() { // create manually exam
			@Override
			public void handle(ActionEvent action) {
				lvManuallyExam.getItems().clear();
				BPExamTable.setCenter(gpExamManually);
				gpExamManually.setVisible(true);
				if (lvStorage.getItems().isEmpty()) {
					updateStorage();
				}
			}

		});

		btnAddQuestionToExam.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				
				if (cmbAllQuestion.getValue() != null) {
					for (SystemUIEventsListener l : allListeners) {
						l.updateQuestionInExamUIEvent(cmbAllQuestion.getValue());
						btnUpdateQuestion.setDisable(true);
						btnUpdateAnswer.setDisable(true);
						btnDeleteAnswer.setDisable(true);
					}

				} else {
					JOptionPane.showMessageDialog(null, "It does not have an empty entry value option");
				}
			}

		});

		cmbNumOFAnswerToAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {

				if (cmbNumOFAnswerToAdd.getValue() != null) {
					for (SystemUIEventsListener l : allListeners) {
						l.updateAnswersInControllerUIEvent(cmbNumOFAnswerToAdd.getValue());
					}
				}
			}

		});

		btnAddAnswerToExam.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {

				if (cmbAnswersClose.getValue() != null) {
					for (SystemUIEventsListener l : allListeners) {
						l.getIndexAnswerUIEvent(cmbAllQuestion.getValue(), cmbAnswersClose.getValue());
					}

				}
				if (numOfAnswers == cmbNumOFAnswerToAdd.getValue()) {
					answerOpenQuestioViewEvent(cmbAllQuestion.getValue());
					cmbNumOFAnswerToAdd.getItems().clear();
					cmbAnswersClose.getItems().clear();
					cmbNumOFAnswerToAdd.setDisable(true);
					cmbAllQuestion.setDisable(false);
					btnAddQuestionToExam.setDisable(false);
					btnCreateExam.setDisable(false);
					cmbAnswersClose.setDisable(true);
					btnAddAnswerToExam.setDisable(true);
					numOfAnswers = 0;
				}

			}

		});

		btnCreateExam.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				lvManuallyExam.getItems().clear();
				for (SystemUIEventsListener l : allListeners) {
					l.createManuallyExamUIEvent();
				}
				cmbNumOFAnswerToAdd.getItems().clear(); // clear button in view
				cmbAnswersClose.getItems().clear();
				cmbAllQuestion.getItems().clear();
				updateNumOfQuestion();
				btnUpdateQuestion.setDisable(false);
				btnUpdateAnswer.setDisable(false);
				btnDeleteAnswer.setDisable(false);
			}
		});
		// ******
		
		// ** AddOpenQuestionView****

		btnAddOpenQuestion.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				BPExamTable.setCenter(gpOpenQuestion);
				gpOpenQuestion.setVisible(true);

				if (lvStorage.getItems().isEmpty()) {
					updateStorage();
				}

				btnAdd.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent action) {
						if (!(tfNewTextOpenQuestion.getText().isBlank() || (tfNewTextOpenAnswer.getText().isBlank()))) {
							for (SystemUIEventsListener l : allListeners) {
								l.addQuestionUIEvent(tfNewTextOpenQuestion.getText(), true);
								l.addAnswerUIEvent(tfNewTextOpenQuestion.getText(), tfNewTextOpenAnswer.getText());
							}
						} else {
							JOptionPane.showMessageDialog(null, "It does not have an empty entry value option");
						}
						tfNewTextOpenQuestion.clear();
						tfNewTextOpenAnswer.clear();
					}
				});
			}
		});
		
		// *****
		
		// ** updateQuestionView***

		btnUpdateQuestion.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				BPExamTable.setCenter(gpupdateQuestion);
				gpupdateQuestion.setVisible(true);
				if (lvStorage.getItems().isEmpty()) {
					updateStorage();
				}
			}
		});

		btnUpdateQuestions.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				if (!(tfTextNewQuestion.getText().isBlank() ) && cmbSerialNumber.getValue() != null) {
					for (SystemUIEventsListener l : allListeners) {
						l.updateQuestionUIEvent(cmbSerialNumber.getValue(), tfTextNewQuestion.getText());
					}
					cmbSerialNumberToAnswer.setDisable(false);
					cmbAnswer.setDisable(true);
					lbIndex.setDisable(true);
					trueAnswerUpDate.setDisable(true);
					falseAnswerUpDate.setDisable(true);
				} else {
					JOptionPane.showMessageDialog(null, "It does not have an empty entry value option");
			}
				tfTextNewQuestion.clear();
			}
		});
		
		// ******
		
		// ** AddCloseQuestionView***

		btnAddCloseQuestion.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				BPExamTable.setCenter(gpcloseQuestion);
				gpcloseQuestion.setVisible(true);

				if (lvStorage.getItems().isEmpty()) {
					updateStorage();
				}

				btnAddNewCloseQuestion.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent action) {
						if ((!(tfNewTextCloseQuestion.getText().isBlank() ) && (cmbNumOfAnswers.getValue() != null))) {
							for (SystemUIEventsListener l : allListeners) {
								l.addQuestionUIEvent(tfNewTextCloseQuestion.getText(), false);

							}
						} else {
							JOptionPane.showMessageDialog(null, "It does not have an empty entry value option");
						}

					}
				});

			}
		});

		save.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				for (SystemUIEventsListener l : allListeners) {
					if (!(tfNewAnswer.getText().isBlank())) {
						l.addAnswerUIEvent(tfNewTextCloseQuestion.getText(), tfNewAnswer.getText(),
								tgTrueOrFalse.getSelectedToggle().equals(trueAnswer));
						tfNewAnswer.clear();
					} else {
						JOptionPane.showMessageDialog(null, "It does not have an empty entry value option");
					}
				}
				if (cmbNumOfAnswers.getValue() == countAnswer) {
					enabledType();
					tfNewTextCloseQuestion.clear();
					tfNewAnswer.clear();
					countAnswer = 0;
					cmbNumOfAnswers.setValue(null);
				}
			}

		});
		
		// ******
		
		
		// ** UpdateAnswerView****
		
		btnUpdateAnswer.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent action) {
				BPExamTable.setCenter(gpupdateAnswer);
				gpupdateAnswer.setVisible(true);

				if (lvStorage.getItems().isEmpty()) {
					updateStorage();
				}

				cmbSerialNumberToAnswer.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent action) {
						if (cmbSerialNumberToAnswer.getValue() != null)
							for (SystemUIEventsListener l : allListeners) {
								l.openOrCloseUIEvent(cmbSerialNumberToAnswer.getValue());
							}
					}

				});

				btnToUpdateAnswer.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent action) {
						if (!(tfNewTextAnswer.getText().isBlank()) && cmbSerialNumberToAnswer.getValue() != null) {
							for (SystemUIEventsListener s : allListeners) {
								s.updateAnswerUIEvent(cmbSerialNumberToAnswer.getValue(), tfNewTextAnswer.getText(),
										gpUpdateAnswers.getSelectedToggle().equals(trueAnswerUpDate),
										cmbAnswer.getValue());
								update();
								tfNewTextAnswer.clear();
							}

						} else {
							JOptionPane.showMessageDialog(null, "It does not have an empty entry value option");
						}

					}

				});
			}

		});
		
		// *****
		
		// ** ExamAutomaticallyView****

		btnExamAutomatically.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				BPExamTable.setCenter(gpAutomaticallyExam);
				gpAutomaticallyExam.setVisible(true);

				if (lvStorage.getItems().isEmpty()) {
					updateStorage();
				}

				btnCreateAutomatically.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent action) {
						lvAutoExam.getItems().clear();
						for (SystemUIEventsListener l : allListeners) {
							l.createAutomaticallyExamViewEvent(spNumOfQuestions.getValue());
						}
					}
				});

			}
		});
		
		// ******
		
		// ** DeleteAnswerView****

		btnDeleteAnswer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				BPExamTable.setCenter(gpDeleteAnswer);
				gpDeleteAnswer.setVisible(true);
				if (lvStorage.getItems().isEmpty()) {
					updateStorage();
				}
				cmbAllAnswersOfQuestion.getItems().clear();
			}
		});

		cmballSerial.setOnAction(new EventHandler<ActionEvent>() { //Show answers by serial
			@Override
			public void handle(ActionEvent action) {
				cmbAllAnswersOfQuestion.getItems().clear();
				if (cmballSerial.getValue() != null) {
					for (SystemUIEventsListener l : allListeners) {
						l.allAnswersUIEvent(cmballSerial.getValue());
					}
				}

			}
		});

		btnDelete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				if (cmballSerial.getValue() != null && cmbAllAnswersOfQuestion.getValue() != null) {
					for (SystemUIEventsListener s : allListeners) {
						s.deleteAnswersUIEvent(cmballSerial.getValue(), cmbAllAnswersOfQuestion.getValue());
						cmbAllAnswersOfQuestion.getItems().clear();
					}

				} else {
					JOptionPane.showMessageDialog(null, "It does not have an empty entry value option");
				}
			}
		});
		
		// *******
		
		
		// ** CloneView****
		btnClone.setOnAction(new EventHandler<ActionEvent>() { //copy last exam in program 
			@Override
			public void handle(ActionEvent action) {
				BPExamTable.setCenter(gpCloneExam);
				gpCloneExam.setVisible(true);
				for (SystemUIEventsListener l : allListeners) {
					l.copyExamUIEvent();
				}

			}
		});
		
		// ********

		theStage.setScene(new Scene(BPExamTable, 897, 480));
		theStage.show();
		theStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent action) {
				for (SystemUIEventsListener l : allListeners)
					l.saveViewEvent();
			}
		});

	}

	@Override
	public void registerListener(SystemUIEventsListener newListener) {
		allListeners.add(newListener);
	}

	@Override
	public void addQuestionViewEvent(boolean falseQuestion) {
		if(falseQuestion)
			DisableType();
		numOfQuestion++;
		cmbSerialNumber.getItems().add(numOfQuestion);
		cmbSerialNumberToAnswer.getItems().add(numOfQuestion);
		cmballSerial.getItems().add(numOfQuestion);
		cmbAllQuestion.getItems().add(numOfQuestion);
	}

	@Override
	public void updateQuestionViewEvent(String question) {
		lvStorage.getItems().clear();
		lvStorage.getItems().addAll(question);
	}

	@Override
	public void addAnswerCloseViewEvent(String question) {
		lvStorage.getItems().clear();
		lvStorage.getItems().addAll(question);
		countAnswer++;
	}

	@Override
	public void deleteAnswerViewEvent(int id, int index, Manager manager) {

		lvStorage.getItems().clear();
		lvStorage.getItems().addAll(manager.toString());
	}

	@Override
	public void exceptionMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	@Override
	public void addAnswerViewEvent(String manager) {
		lvStorage.getItems().clear();
		lvStorage.getItems().addAll(manager);
	}

	public void DisableType() {  //disable button
		lblNewAnswer.setDisable(false);
		tfNewAnswer.setDisable(false);
		save.setDisable(false);
		trueAnswer.setDisable(false);
		falseAnswer.setDisable(false);
		lblNewCloseQuestion.setDisable(true);
		tfNewTextCloseQuestion.setDisable(true);
		cmbNumOfAnswers.setDisable(true);
		lblNewOfAnswers.setDisable(true);
		btnAddNewCloseQuestion.setDisable(true);
		btnAddOpenQuestion.setDisable(true);
		btnUpdateQuestion.setDisable(true);
		btnDeleteAnswer.setDisable(true);
		btnUpdateAnswer.setDisable(true);
		btnExamAutomatically.setDisable(true);
		btnExamManually.setDisable(true);
		btnClone.setDisable(true);	
	}

	public void enabledType() { //enable button
		lblNewAnswer.setDisable(true);
		tfNewAnswer.setDisable(true);
		save.setDisable(true);
		trueAnswer.setDisable(true);
		falseAnswer.setDisable(true);
		lblNewCloseQuestion.setDisable(false);
		tfNewTextCloseQuestion.setDisable(false);
		cmbNumOfAnswers.setDisable(false);
		lblNewOfAnswers.setDisable(false);
		btnAddNewCloseQuestion.setDisable(false);
		btnAddOpenQuestion.setDisable(false);
		btnUpdateQuestion.setDisable(false);
		btnDeleteAnswer.setDisable(false);
		btnUpdateAnswer.setDisable(false);
		btnExamAutomatically.setDisable(false);
		btnExamManually.setDisable(false);
		btnClone.setDisable(false);
	}

	@Override
	public void openOrCloseViewEvent(int openOrclose) {  
		if (openOrclose == 2) {
			cmbAnswer.setDisable(false);
			lbIndex.setDisable(false);
			trueAnswerUpDate.setDisable(false);
			falseAnswerUpDate.setDisable(false);
			cmbSerialNumberToAnswer.setDisable(true);
		}

	}

	public void update() {
		cmbAnswer.setDisable(true);
		lbIndex.setDisable(true);
		trueAnswerUpDate.setDisable(true);
		falseAnswerUpDate.setDisable(true);
		cmbSerialNumberToAnswer.setDisable(false);
		gpUpdateAnswers.selectToggle(trueAnswerUpDate);
		cmbAnswer.getSelectionModel().select(0);
	}

	@Override
	public void AnswerViewEvent(Set<Answer> answerQuestion) {
		if (answerQuestion == null)
			cmbAllAnswersOfQuestion.getItems().add(null);
		for (int i = 0; i < answerQuestion.getCurrentSize(); i++) {
			cmbAllAnswersOfQuestion.getItems().add(answerQuestion.get(i));
		}

	}

	@Override
	public void DeleteViewEvent(Answer answer, String manager) {

		ObservableList<Answer> allLines = cmbAllAnswersOfQuestion.getItems();
		for (int i = 0; i < allLines.size(); i++) {
			if (cmbAllAnswersOfQuestion.getItems().get(i).equals(answer)) {
				cmbAllAnswersOfQuestion.getItems().remove(i);
			}
		}
		lvStorage.getItems().clear();
		lvStorage.getItems().addAll(manager);

	}

	public void updateStorage() {
		for (SystemUIEventsListener l : allListeners) {
			l.showAllDataViewEvent();
		}
	}

	@Override
	public void updateAllQuestionAndAnswersEvent(String allQuestionAnswers, int size) {
		lvStorage.getItems().clear();
		lvStorage.getItems().addAll(allQuestionAnswers);
		numOfQuestion = size;
		cmbSerialNumber.getItems().clear();
		cmbSerialNumberToAnswer.getItems().clear();
		cmbAllQuestion.getItems().clear();
		cmballSerial.getItems().clear();
		for (int i = 0; i < numOfQuestion; i++) {
			cmbSerialNumber.getItems().add(i + 1);
			cmbSerialNumberToAnswer.getItems().add(i + 1);
			cmballSerial.getItems().add(i + 1);
			cmbAllQuestion.getItems().add(i + 1);
		}
	}

	@Override
	public void copyExamEvent(Exam clone) {
		lvcopyExam.getItems().clear();
		lvcopyExam.getItems().addAll(clone.toString());
	}

	@Override
	public void updateCloseQuestionAnswersEvent(Set<Answer> answerQuestion) {
		for (int i = 0; i < answerQuestion.getCurrentSize(); i++) {
			cmbAnswersClose.getItems().add(answerQuestion.get(i));
			if (i != 0) {
				cmbNumOFAnswerToAdd.getItems().add(i + 1);
			}
		}
		cmbNumOFAnswerToAdd.setDisable(false);
		btnAddQuestionToExam.setDisable(true);
		cmbAllQuestion.setDisable(true);
		btnCreateExam.setDisable(true);
	}

	@Override
	public void numOfAnswers(int currentSize) {
		cmbAnswersClose.setDisable(false);
		btnAddAnswerToExam.setDisable(false);
		cmbNumOFAnswerToAdd.setDisable(true);

	}

	@Override
	public void numOfAnswerupdate(Answer answer) {
		ObservableList<Answer> allLines = cmbAnswersClose.getItems();
		for (int i = 0; i < allLines.size(); i++) {
			if (cmbAnswersClose.getItems().get(i).equals(answer)) {
				cmbAnswersClose.getItems().remove(i);
			}
		}
		numOfAnswers++;
	}

	@Override
	public void answerOpenQuestioViewEvent(int serial) {
		for (int i = 0; i < cmbAllQuestion.getItems().size(); i++) {
			if (cmbAllQuestion.getItems().get(i) == serial)
				cmbAllQuestion.getItems().remove(i);
		}
	}

	@Override
	public void updateAnswerViewEvent(String manager) {
		lvStorage.getItems().clear();
		lvStorage.getItems().addAll(manager);
	}

	public void updateNumOfQuestion() {
		for (int i = 1; i <= numOfQuestion; i++)
			cmbAllQuestion.getItems().add(i);
	}

	@Override
	public void createExamViewEvent(String exam) { // for Exam Auto Add list of Exam
		lvAutoExam.getItems().addAll(exam);
	}

	@Override
	public void createManuallyExamViewEvent(String exam) { // for Exam manually
		lvManuallyExam.getItems().addAll(exam);
	}

}
