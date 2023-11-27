package model_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;
import java.util.Vector;

import listeners_project.SystemEventsListener;

public class Manager implements Serializable {

	private Vector<Question> questions;
	private Vector<SystemEventsListener> listeners;
	private Exam test;

	public Manager() {
		listeners = new Vector<SystemEventsListener>();
		questions = new Vector<Question>();
	}

	public void registerListener(SystemEventsListener listener) {
		listeners.add(listener);
	}

	public int getNumOfQuestion() {
		return questions.size();
	}

	public Vector<Question> getQuestions() {
		return questions;
	}

	public boolean setQuestions(Vector<Question> questions) {
		this.questions = questions;
		return true;
	}

	public boolean addQuestion(String textQuestion, boolean openOrClose) throws Exception {
		if (!(emptyString(textQuestion)) || ((openOrClose != false && openOrClose != true))) {
			if (openOrClose) {
				if (equalsOpenQuestion(textQuestion) == -1) {
					OpenQuestion newQuestion = new OpenQuestion(textQuestion, null);
					questions.add(newQuestion);
					fireAddOpenQuestionEvent(false);
					return true;
				} else {
					throw new Exception("There is already question ");
				}
			} else {
				if (equalsCloseQuestion(textQuestion) == -1) {
					CloseQuestion newQuestion = new CloseQuestion(textQuestion);
					questions.add(newQuestion);
					fireAddCloseQuestionEvent(true);
					return true;
				} else {
					fireAddCloseQuestionEvent(false);
					throw new Exception("There is already question ");
				}
			}
		}
		throw new Exception("empty Question ");
		// return false;
	}

	private void fireAddOpenQuestionEvent(boolean trueQuestion) {
		for (SystemEventsListener l : listeners) {
			l.addOpenQuestionModelEvent(trueQuestion);
		}
	}

	private void fireAddCloseQuestionEvent(boolean falseQuestion) {
		for (SystemEventsListener l : listeners) {
			l.addCloseQuestionModelEvent(falseQuestion);
		}
	}

	public int equalsCloseQuestion(String textQustion) {
		for (int i = 0; i < questions.size(); i++) {
			if (questions.get(i) != null && textQustion.equals(questions.get(i).textQuestion)
					&& questions.get(i) instanceof CloseQuestion)
				return i + 1;
		}
		return -1;
	}

	public Vector<Boolean> newQuestionVector(Manager test) {
		Vector<Boolean> newQuestion = new Vector<Boolean>();
		for (int i = 0; i < test.getQuestions().size(); i++) {
			if (test.getQuestions().get(i) != null) {
				newQuestion.add(true);
			}
		}
		return newQuestion;
	}

	// Create exam automatically
	public boolean createExamAutomatically(int numOfQuestion, Manager storageQuestions) throws Exception {

		Vector<Boolean> allQuestion = new Vector<Boolean>();
		allQuestion = newQuestionVector(storageQuestions);
		Vector<Question> newQuestion = new Vector<Question>();

		for (int question = 0; question < numOfQuestion; question++) {
			int random = (int) (Math.random() * (storageQuestions.getNumOfQuestion()));
			while (allQuestion.get(random) == null) {
				random = (int) (Math.random() * (storageQuestions.getNumOfQuestion()));
			}
			allQuestion.set(random, null);
			if (storageQuestions.getQuestions().get(random) != null) {
				if (storageQuestions.getQuestions().get(random) instanceof OpenQuestion) {
					OpenQuestion open = (OpenQuestion) storageQuestions.getQuestions().get(random);
					newQuestion.add(open.clone());
				} else if ((storageQuestions.getQuestions().get(random) instanceof CloseQuestion)
						&& ((CloseQuestion) storageQuestions.getQuestions().get(random)).getAnswerQuestion()
								.getCurrentSize() >= 4
						&& ((CloseQuestion) storageQuestions.getQuestions().get(random)).numOfFalseAnswers() >= 3) {
					newQuestion.add(question, ((CloseQuestion) storageQuestions.getQuestions().get(random)).clone());
					CloseQuestion closeQuestion = (CloseQuestion) newQuestion.get(question);
					closeQuestion.AutoAnswers();
					newQuestion.remove(question);
					newQuestion.add(closeQuestion);
				} else {
					question--; // If he fails and does not take the question then we keep the number of
								// questions that will not change
				}
			}
			if (arrIsFull(allQuestion)) { // If it's full we'll get out
				question = numOfQuestion;
			}
		}
		if (newQuestion.size() == numOfQuestion) {
			test = new Exam(numOfQuestion);
			test.setQuestions(newQuestion);
		} else {
			throw new Exception("Failed - storage is not contains enough questions for automatically exam \n ");
		}
		return true;
	}

	public void printExamAutomatically(int numOfQuestion, Manager storageQuestions)
			throws FileNotFoundException, Exception {

		if (createExamAutomatically(numOfQuestion, storageQuestions)) {
			sortByCharAmountAnswer(test);
			test.save();
			fireExamAutomaticallyEvent(test.toString());
		}
	}

	public void copyExam() throws Exception {
		if (test != null) {
			if (test.clone() != null) {
				fireCopyExamEvent(test.clone());
			}
		} else
			throw new Exception("there is no Exam in this program \n");
	}

	public void createExamManually(Vector<Integer> question, Vector<Integer> answers) throws Exception {
		int countForIndexInAnswers = 0;
		test = new Exam(question.size());
		if (question.size() != 0) {
			for (int i = 0; i < question.size(); i++) {
				if (checkOpenOrClose(question.get(i)) == 1) {
					test.addQuestionInTest(findQuestionByID(question.get(i)));
				} else if (checkOpenOrClose(question.get(i)) == 2) {
					test.addQuestionWithousAnswers((CloseQuestion) findQuestionByID(question.get(i)));
					int index = answers.get(countForIndexInAnswers);
					countForIndexInAnswers++;
					while (index > 0) {
						Question close = findQuestionByID(question.get(i));
						int indexInAnswer = answers.get(countForIndexInAnswers++);
						Answer textQuestion2 = ((CloseQuestion) close).getAnswerQuestion().get(indexInAnswer - 1);
						test.addAnswerToTest(question.get(i), textQuestion2.getText(), textQuestion2.isCorrect());
						index--;
					}

					CloseQuestion castingToCloseQuestion = (CloseQuestion) test.findQuestionByID(question.get(i));
					castingToCloseQuestion.ManuallyAnswers(castingToCloseQuestion.getAnswerQuestion());
				}

			}
			sortByCharAmountAnswer(test);
			test.save();
			fireExamManuallyEvent(test.toString());
		} else
			throw new Exception("cant create exam with 0 question");

	}

	private void fireExamManuallyEvent(String exam) {

		for (SystemEventsListener l : listeners) {
			l.createManuallyExamModelEvent(exam);
		}
	}

	public boolean arrIsFull(Vector<Boolean> randomArr) {
		for (int i = 0; i < randomArr.size(); i++) {
			if (randomArr.get(i) != null) {
				return false;
			}
		}
		return true;
	}

// Add answer to Open question	
	public boolean addAnswer(String textQuestion, String text) throws Exception {
		if (!((emptyString(text)) || (emptyString(textQuestion)))) {
			int id = equalsOpenQuestion(textQuestion);
			int index = findIndexQuestionById(id);
			if (index == -1) {
				return false;
			}
			if (questions.get(index) instanceof OpenQuestion) {
				OpenQuestion open = (OpenQuestion) questions.get(index);
				if (open.getTextAnswer() == null) {
					Answer answerToopen = new Answer(text, true);
					open.setTextAnswer(answerToopen);
					fireAddOpenAnswerEvent();
					return true;
				}
			}
			throw new Exception("There is already answer to this question");
		}
		throw new Exception("empty answer");
	}

	public int equalsOpenQuestion(String textQustion) {
		for (int i = 0; i < questions.size(); i++) {
			if (questions.get(i) != null && textQustion.equals(questions.get(i).textQuestion)
					&& questions.get(i) instanceof OpenQuestion)
				return i + 1;
		}
		return -1;
	}

	public void numOfQuestionInStorage(Manager storageQuestionAndAnswers) {
		if (this.questions.size() > 0) {
			storageQuestionAndAnswers.getQuestions().get(this.questions.size() - 1)
					.Updatecounter(this.questions.size());
			fireNumOfQuestion(storageQuestionAndAnswers.toString(), questions.size());
		}
	}

// Add answer to Close question
	public boolean addAnswer(String textQuestion, String text, boolean correct) throws Exception {
		if (!(emptyString(text)) || (emptyString(textQuestion))) { // add to correct
			int id = equalsCloseQuestion(textQuestion);
			int index = findIndexQuestionById(id);
			if (index != -1) {
				if (questions.get(index) instanceof CloseQuestion) {
					CloseQuestion close = (CloseQuestion) questions.get(index);
					Answer newAnswer = new Answer(text, correct);
					if (close.addAnswer(newAnswer)) {
						fireAddCloseAnswerEvent();
						return true;
					}
					throw new Exception("error");
				}

			}
		}

		throw new Exception("empty answer");
	}

	public void updateAnswer(int id, String newAnswer, boolean correct, int index) throws Exception {
		if (!(emptyString(newAnswer))) {
			int indexQuestion = findIndexQuestionById(id);
			Answer answer = new Answer(newAnswer, correct);

			if (questions.get(indexQuestion) instanceof CloseQuestion) {
				if (questions.get(indexQuestion).equals(newAnswer) != true) {
					CloseQuestion close = (CloseQuestion) questions.get(indexQuestion);
					if (close.updateAnswer(answer, index)) {
						fireUpdateAnswerEvent();
					}
				} else {
					throw new Exception("This answer already exists \n");
				}

			} else if (questions.get(indexQuestion) instanceof OpenQuestion) {
				OpenQuestion open = (OpenQuestion) questions.get(indexQuestion);
				open.setTextAnswer(answer);
				fireUpdateAnswerEvent();
			}
		}
	}

	public boolean deleteAnswer(int id, int index) throws Exception {
		int indexQuestion = findIndexQuestionById(id);
		if (indexQuestion != -1 && checkOpenOrClose(id) != 1) {
			if (questions.get(indexQuestion) instanceof CloseQuestion) {
				CloseQuestion close = (CloseQuestion) questions.get(indexQuestion);
				if (close.deleteAnswer(index)) {
					fireDeleteAnswerEvent(id, index);
					return true;
				}
				throw new Exception("Index does not exist");
			}
		}

		throw new Exception("no option to delete open answer");
	}

	public void updateQuestion(int serialNumber, String question) throws Exception {
		if (!(emptyString(question))) {
			int index = findIndexQuestionById(serialNumber);
			if (index != -1) {
				if (checkOpenOrClose(serialNumber) == 1) {
					if (equalsOpenQuestion(question) == -1) {
						questions.get(index).setTextQuestion(question);
						fireUpdateQuestionEvent();
					} else {
						throw new Exception("The question already exists \n");
					}
				} else {
					if (equalsCloseQuestion(question) == -1) {
						questions.get(index).setTextQuestion(question);
						fireUpdateQuestionEvent();
					} else {
						throw new Exception("The question already exists ");
					}
				}
			}
		}
	}

// Check if question is open or close by ID
	public int checkOpenOrClose(int id) throws Exception {
		int i = findIndexQuestionById(id);
		if (i != -1) {
			if (questions.get(i) instanceof OpenQuestion) {
				int open = 1;
				fireOpenOrCloseEvent(open);
				return 1;
			}
			if (questions.get(i) instanceof CloseQuestion) {
				int close = 2;
				fireOpenOrCloseEvent(close);
				return 2;
			}
		}
		return 0;
	}

	public Question findQuestionByID(int serialNumber) throws Exception {
		int index = findIndexQuestionById(serialNumber);
		if (index == -1)
			return null;
		return questions.get(index);
	}

// Find the index of question in the array (return Exception if the serial number isn't exists)
	public int findIndexQuestionById(int serialNumber) throws Exception {
		for (int index = 0; index < questions.size(); index++) {
			if (questions.get(index) != null) {
				if (questions.get(index).getSerialNumber() == serialNumber) {
					return index;
				}
			}
		}
		throw new Exception("This serial question is not exist \n");
	}

	public boolean emptyString(String str) throws Exception {
		if (str.trim().isEmpty()) {
			return true;
		}
		return false;
	}

	public Vector<Question> sortByCharAmountAnswer(Exam exam) {
		Vector<Question> QuestionSort = exam.getQuestions();
		Question tempquestion;
		for (int i = QuestionSort.size() - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (QuestionSort.get(j) != null && QuestionSort.get(j + 1) != null) {
					if (QuestionSort.get(j).compareTo(QuestionSort.get(j + 1)) > 0) {
						tempquestion = QuestionSort.get(j);
						QuestionSort.set(j, QuestionSort.get(j + 1));
						QuestionSort.set(j + 1, tempquestion);
					}
				}
			}
		}
		return QuestionSort;
	}

	public int numOfAnswers(int id) throws Exception { // check number of answer in close question.
		Question q = findQuestionByID(id);
		CloseQuestion close = (CloseQuestion) q;
		int count = 0;
		for (int i = 0; i < close.getAnswerQuestion().getCurrentSize(); i++) {
			if (close.getAnswerQuestion().get(i) == null) {
				count++;
			}
		}
		return (close.getAnswerQuestion().getCurrentSize() - count);
	}

	public void getIndexOfAnswer(Integer id, Answer answer) throws Exception {
		int indexQuestion = findIndexQuestionById(id);

		if (questions.get(indexQuestion) instanceof CloseQuestion) {
			CloseQuestion close = (CloseQuestion) questions.get(indexQuestion);
			for (int i = 0; i < close.getAnswerQuestion().getCurrentSize(); i++) {
				if (close.getAnswerQuestion().get(i).equals(answer)) {
					fireNumberOfAnswer(i, answer);
					return;
				}
			}
		}

	}

	private void fireNumberOfAnswer(int currentSize, Answer answer) {
		for (SystemEventsListener l : listeners) {
			l.numOfAnswerModelEvent(currentSize, answer);
		}

	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Manager))
			return false;
		Manager managerObject = (Manager) object;
		return (managerObject.equals(this.questions));
	}

	public void WriteExamAndSolution(File file, File fileCopy) throws FileNotFoundException, Exception {
		if (file.exists()) {
			PrintWriter writer = new PrintWriter(fileCopy);
			Scanner reader = new Scanner(file);
			while (reader.hasNext()) {
				writer.println(reader.nextLine());
			}
			writer.close();
		} else {
			throw new Exception("The Exam Not exist");
		}

	}

	private void fireUpdateQuestionEvent() {
		for (SystemEventsListener l : listeners) {
			l.updateQuestionModelEvent();
		}
	}

	private void fireAddOpenAnswerEvent() {
		for (SystemEventsListener l : listeners) {
			l.addOpenAnswerModelEvent();
		}
	}

	private void fireAddCloseAnswerEvent() {
		for (SystemEventsListener l : listeners) {
			l.addCloseAnswerModelEvent();
		}
	}

	private void fireUpdateAnswerEvent() {
		for (SystemEventsListener l : listeners) {
			l.updateAnswerModelEvent();
		}
	}

	private void fireDeleteAnswerEvent(int id, int index) {
		for (SystemEventsListener l : listeners) {
			l.deleteAnswerModelEvent(id, index);
		}
	}

	private void fireExamAutomaticallyEvent(String exam) {
		for (SystemEventsListener l : listeners) {
			l.createExamModelEvent(exam);
		}

	}

	private void fireOpenOrCloseEvent(int openOrclose) {

		for (SystemEventsListener l : listeners) {
			l.openOrCloseModelEvent(openOrclose);
		}
	}

	@Override
	public String toString() {
		StringBuffer question = new StringBuffer("The storage:\n");
		if (questions != null)
			for (int i = 0; i < questions.size(); i++) {
				if (questions.get(i) != null) {
					question.append(questions.get(i).toString() + "\n");
				}

			}
		return question.toString();
	}

	public void allAnswers(Integer value) throws Exception {

		for (int i = 0; i < questions.size(); i++) {
			if (questions.get(i).getSerialNumber() == value) {
				if (questions.get(i) instanceof CloseQuestion) {
					CloseQuestion close = (CloseQuestion) questions.get(i);
					close.getAnswerQuestion();
					// fireTypeQuestionEvent(close.getAnswerQuestion());
					fireAnswerEvent(close.getAnswerQuestion());
				}
				if (questions.get(i) instanceof OpenQuestion) {
					throw new Exception("no option delete from open question ");
				}
			}
		}
	}

	private void fireAnswerEvent(Set<Answer> answerQuestion) {
		for (SystemEventsListener l : listeners) {
			l.AnswerModelEvent(answerQuestion);
		}
	}

	public void allAnswersForExam(int serialNumber) throws Exception {

		for (int i = 0; i < questions.size(); i++) {
			if (questions.get(i).getSerialNumber() == serialNumber) {
				if (questions.get(i) instanceof CloseQuestion) {
					CloseQuestion close = (CloseQuestion) questions.get(i);
					close.getAnswerQuestion();
					fireCloseQuestionUpdateEvent(close.getAnswerQuestion());
				}
				if (questions.get(i) instanceof OpenQuestion) {
					fireOpenQuestionExam(serialNumber);
				}
			}
		}
	}

	private void fireOpenQuestionExam(int serial) {
		for (SystemEventsListener l : listeners) {
			l.openQuestionEvent(serial);
		}

	}

	private void fireCloseQuestionUpdateEvent(Set<Answer> answerQuestion) {
		for (SystemEventsListener l : listeners) {
			l.updateCloseQuestionModelEvent(answerQuestion);
		}

	}

	public void deleteAnswers(int serial, Answer answer) throws Exception {
		int indexQuestion = findIndexQuestionById(serial);

		if (questions.get(indexQuestion) instanceof CloseQuestion) {
			CloseQuestion close = (CloseQuestion) questions.get(indexQuestion);
			if (close.deleteAnswer(answer)) {
				fireDeleteAnswerEvent(answer);
			}

		}
	}

	private void fireDeleteAnswerEvent(Answer answer) {
		for (SystemEventsListener l : listeners) {
			l.AnswerDeleteModelEvent(answer);
		}
	}

	private void fireCopyExamEvent(Exam clone) {
		for (SystemEventsListener l : listeners) {
			l.copyExamModelEvent(clone);
		}

	}

	private void fireNumOfQuestion(String allQuestionANdAnswer, int size) {
		for (SystemEventsListener l : listeners) {
			l.numOfQuestionModelEvent(allQuestionANdAnswer, size);
		}

	}

}
