package controller_project;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;
import listeners_project.SystemEventsListener;
import listeners_project.SystemUIEventsListener;
import model_project.Answer;
import model_project.Exam;
import model_project.Manager;
import model_project.Set;
import view_project.AbstractView;

public class Controller implements SystemEventsListener, SystemUIEventsListener, Serializable {

	private Manager systemModel;
	private AbstractView systemView;

	public Controller(Manager theModel, AbstractView theView) {
		systemModel = theModel;
		systemView = theView;

		systemModel.registerListener(this);
		systemView.registerListener(this);
	}

	@Override
	public void addQuestionUIEvent(String question, boolean open) {
		try {
			systemModel.addQuestion(question, open);
			systemView.exceptionMessage("Question are successfully added to the storage");
		} catch (Exception e) {
			systemView.exceptionMessage(e.getMessage());
		}
	}

	@Override
	public void updateQuestionUIEvent(int serialNumber, String question) {
		try {
			systemModel.updateQuestion(serialNumber, question);
			systemView.exceptionMessage("Question are successfully update");
		} catch (Exception e) {
			systemView.exceptionMessage(e.getMessage());
		}

	}

	@Override
	public void addAnswerUIEvent(String textQuestion, String text) {
		try {
			systemModel.addAnswer(textQuestion, text);
			systemView.exceptionMessage("Answer are successfully added to the storage");
		} catch (Exception e) {
			systemView.exceptionMessage(e.getMessage());
		}

	}

	@Override
	public void saveViewEvent() {
		try {
			ObjectOutputStream outputStorage = new ObjectOutputStream(new FileOutputStream("StorageQuestion.txt"));
			outputStorage.writeObject(systemModel.getQuestions());
			systemView.exceptionMessage("Data are successfully saved in the file storage");
		} catch (IOException e) {
			systemView.exceptionMessage(e.getMessage());
		}

	}

	@Override
	public void addAnswerUIEvent(String textQuestion, String answer, boolean correct) {
		try {
			systemModel.addAnswer(textQuestion, answer, correct);
			systemView.exceptionMessage("Answer are successfully added to the storage");
		} catch (Exception e) {
			systemView.exceptionMessage(e.getMessage());
		}
	}

	@Override
	public void openOrCloseUIEvent(Integer value) {

		try {
			systemModel.checkOpenOrClose(value);
		} catch (Exception e) {
			systemView.exceptionMessage(e.getMessage());
		}

	}

	@Override
	public void createAutomaticallyExamViewEvent(int numOfQuestion) {
		try {
			systemModel.printExamAutomatically(numOfQuestion, systemModel);
			systemView.exceptionMessage("Test created successfully ");
		} catch (Exception e) {
			systemView.exceptionMessage(e.getMessage());
		}

	}

	@Override
	public void allAnswersUIEvent(Integer serial) {
		try {
			systemModel.allAnswers(serial);
			systemView.exceptionMessage("choose Answer to delete");
		} catch (Exception e) {
			systemView.exceptionMessage(e.getMessage());
		}

	}

	@Override
	public void updateAnswerUIEvent(int id, String newAnswer, boolean correct, int index) {
		try {
			systemModel.updateAnswer(id, newAnswer, correct, index);
			systemView.exceptionMessage("Update Answer Successfully ");
		} catch (Exception e) {
			systemView.exceptionMessage(e.getMessage());
		}
	}

	@Override
	public void deleteAnswersUIEvent(int serial, Answer answer) {
		try {
			systemModel.deleteAnswers(serial, answer);
			systemView.exceptionMessage("delete Successfully ");
		} catch (Exception e) {
			systemView.exceptionMessage(e.getMessage());
		}
	}

	@Override
	public void copyExamUIEvent() {
		try {
			systemModel.copyExam();
		} catch (Exception e) {
			systemView.exceptionMessage(e.getMessage());
		}
	}

	Vector<Integer> serialOfQuestion = new Vector<Integer>();
	Vector<Integer> AnswersForCloseQuestion = new Vector<Integer>();

	@Override
	public void updateQuestionInExamUIEvent(int serialNumber) {
		try {
			serialOfQuestion.add(serialNumber);
			systemModel.allAnswersForExam(serialNumber);
			systemView.exceptionMessage("Question additional successfully");
		} catch (Exception e) {
			systemView.exceptionMessage(e.getMessage());
		}
	}

	@Override
	public void createManuallyExamUIEvent() {
		try {
			systemModel.createExamManually(serialOfQuestion, AnswersForCloseQuestion);
			systemView.exceptionMessage("Exam Created Successfully");
		} catch (Exception e) {
			systemView.exceptionMessage(e.getMessage());
		}
	}

	@Override
	public void getIndexAnswerUIEvent(Integer id, Answer answer) {
		try {
			systemModel.getIndexOfAnswer(id, answer);
			systemView.exceptionMessage("Answer adding successfully");
		} catch (Exception e) {
			systemView.exceptionMessage(e.getMessage());
		}
	}

	@Override
	public void addOpenQuestionModelEvent(boolean trueQuestion) {
		systemView.addQuestionViewEvent(trueQuestion);
	}

	@Override
	public void addOpenAnswerModelEvent() {
		systemView.addAnswerViewEvent(systemModel.toString());
	}

	@Override
	public void updateQuestionModelEvent() {
		systemView.updateQuestionViewEvent(systemModel.toString());
	}

	@Override
	public void addCloseAnswerModelEvent() {
		systemView.addAnswerCloseViewEvent(systemModel.toString());
	}

	@Override
	public void updateAnswerModelEvent() {
		systemView.updateAnswerViewEvent(systemModel.toString());
	}

	@Override
	public void deleteAnswerModelEvent(int id, int index) {
		systemView.deleteAnswerViewEvent(id, index, systemModel);
	}

	@Override
	public void openOrCloseModelEvent(int openOrclose) {
		systemView.openOrCloseViewEvent(openOrclose);
	}

	@Override
	public void createExamModelEvent(String exam) {
		systemView.createExamViewEvent(exam);
	}

	@Override
	public void AnswerModelEvent(Set<Answer> answerQuestion) {
		systemView.AnswerViewEvent(answerQuestion);
	}

	@Override
	public void AnswerDeleteModelEvent(Answer answer) {
		systemView.DeleteViewEvent(answer, systemModel.toString());
	}

	@Override
	public void showAllDataViewEvent() {
		systemModel.numOfQuestionInStorage(systemModel);
	}

	@Override
	public void copyExamModelEvent(Exam clone) {
		systemView.copyExamEvent(clone);
	}

	@Override
	public void numOfQuestionModelEvent(String allQuestionAndAnswers, int size) {
		systemView.updateAllQuestionAndAnswersEvent(allQuestionAndAnswers, size);
	}

	@Override
	public void updateCloseQuestionModelEvent(Set<Answer> answerQuestion) {
		systemView.updateCloseQuestionAnswersEvent(answerQuestion);
	}

	@Override
	public void numOfAnswerModelEvent(int currentSize, Answer answer) {
		AnswersForCloseQuestion.add(currentSize + 1);
		systemView.numOfAnswerupdate(answer);
	}

	@Override
	public void updateAnswersInControllerUIEvent(Integer num) {
		AnswersForCloseQuestion.add(num);
		systemView.numOfAnswers(num);
	}

	@Override
	public void createManuallyExamModelEvent(String exam) {
		systemView.createManuallyExamViewEvent(exam);
		serialOfQuestion.removeAllElements();
		AnswersForCloseQuestion.removeAllElements();
	}

	@Override
	public void openQuestionEvent(int serial) {
		systemView.answerOpenQuestioViewEvent(serial);
	}

	@Override
	public void addCloseQuestionModelEvent(boolean falseQuestion) {
		systemView.addQuestionViewEvent(falseQuestion);
	}

}
