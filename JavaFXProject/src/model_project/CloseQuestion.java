package model_project;

import java.io.Serializable;

public class CloseQuestion extends Question implements Serializable, Cloneable {

	private Set<Answer> answerQuestion = new Set<Answer>();

	public CloseQuestion(String textQuestion) {
		super(textQuestion);
	}

	public CloseQuestion(String textQuestion, int serialOfQuestion) {
		super(textQuestion, serialOfQuestion);
	}

	public CloseQuestion(String textQuestion, Set<Answer> answerQuestion) {
		super(textQuestion);
		this.answerQuestion = answerQuestion;
	}

	public CloseQuestion clone() throws CloneNotSupportedException {
		CloseQuestion temp = (CloseQuestion) super.clone();
		temp.answerQuestion = new Set<Answer>();
		for (int i = 0; i < answerQuestion.getCurrentSize(); i++)
			try {
				temp.answerQuestion.add((Answer) answerQuestion.get(i).clone());
			} catch (Exception e) {
				e.printStackTrace();
			}
		return temp;
	}

	public Set<Answer> getAnswerQuestion() {
		return answerQuestion;
	}

	public boolean setAnswerQuestion(Set<Answer> answerQuestion) {
		this.answerQuestion = answerQuestion;
		return true;
	}

	public int numOfFalseAnswers() { // return numbers of false answers in close question
		int countFalseAnswers = 0;
		for (int i = 0; i < answerQuestion.getCurrentSize(); i++) {
			if (answerQuestion.get(i) != null && !(answerQuestion.get(i).isCorrect()))
				countFalseAnswers++;
		}
		return countFalseAnswers;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof String))
			return false;
		String textQuestion = (String) other;
		for (int i = 0; i < answerQuestion.getCurrentSize(); i++) {
			if (answerQuestion.get(i) != null && answerQuestion.get(i).getText().equals(textQuestion)) {
				
				return true;
			}
		}
		return false;
	}

	public boolean updateAnswer(Answer text, int index) throws Exception {
		if (answerQuestion.getCurrentSize() >= index) {
			answerQuestion.setElementAt(text, index - 1);
			return true;
		}
		throw new Exception("index not found \n");
	}

	public boolean deleteAnswer(int index) throws Exception {
		if (answerQuestion.getCurrentSize() > 2) {
			if (index > answerQuestion.getCurrentSize() || answerQuestion.get(index - 1) == null || index <= 0) {
				return false;
			}
			answerQuestion.removeElementAt(index - 1);
			return true;
		}
		throw new Exception("Unable to delete answer because there are only 2 answers to this question");

	}

	public boolean addAnswer(Answer text) throws Exception {
		if (answerQuestion.getCurrentSize() == 10 && answerQuestion.get(answerQuestion.getCurrentSize() - 1) != null) {
			throw new Exception("The maximum answers is 10");
		}
		if (answerQuestion.add(text))
			return true;
		return false;
	}

// Check how many true answers and add more two options
	public boolean ManuallyAnswers(Set<Answer> answersInCloseQuestion) throws Exception {
		int countNumOfTrueAnswers = 0;
		for (int i = 0; i < answersInCloseQuestion.getCurrentSize(); i++) {
			if (answersInCloseQuestion.get(i) != null && answersInCloseQuestion.get(i).isCorrect()) {
				countNumOfTrueAnswers++;
			}
		}
		if (countNumOfTrueAnswers > 1) {
			changeToFalseAnswers(answerQuestion); // If there is more than one correct answer, make them false
			answerQuestion.add(new Answer("More than one correct answer", true));
			answerQuestion.add(new Answer("No answer is correct", false));
		} else if (countNumOfTrueAnswers == 0) {
			answerQuestion.add(new Answer("More than one correct answer", false));
			answerQuestion.add(new Answer("No answer is correct", true));
		} else if (countNumOfTrueAnswers == 1) {
			answerQuestion.add(new Answer("More than one correct answer", false));
			answerQuestion.add(new Answer("No answer is correct", false));
		}
		return true;
	}

	public void changeToFalseAnswers(Set<Answer> answerThisQuestion) throws Exception {
		for (int i = 0; i < answerThisQuestion.getCurrentSize(); i++) {
			if (answerThisQuestion.get(i) != null && answerThisQuestion.get(i).isCorrect()) {
				answerThisQuestion.setElementAt(new Answer(answerThisQuestion.get(i).getText(), false), i);
			}
		}
	}

// For the auto exam
	public void AutoAnswers() throws Exception {
		int countNewAnswers = 0, countTrueAnswers = 0, counterOldAnswers = 0, counterNewAnswerwithoutTrue = 0;
		Set<Answer> newAnswers = new Set<Answer>();
		for (int i = 0; i < answerQuestion.getCurrentSize(); i++) {
			if (answerQuestion.get(i).isCorrect() == true && countTrueAnswers == 0) {
				newAnswers.add((answerQuestion.get(i)).clone());
				countTrueAnswers++;
				countNewAnswers++;
				break;
			}
		}
		while (countNewAnswers < 4 && counterOldAnswers < answerQuestion.getCurrentSize()
				&& counterNewAnswerwithoutTrue < 4) {
			if (((answerQuestion.get(counterOldAnswers)).isCorrect() == false)
					&& answerQuestion.get(counterOldAnswers) != null) {
				newAnswers.add(answerQuestion.get(counterOldAnswers).clone());
				countNewAnswers++;
				counterNewAnswerwithoutTrue++;
			}
			counterOldAnswers++;
		}

		if (countTrueAnswers == 0) {
			newAnswers.add(new Answer("More than one correct answer", false));
			newAnswers.add(new Answer("No answer is correct", true));
		} else {
			newAnswers.add(new Answer("More than one correct answer", false));
			newAnswers.add(new Answer("No answer is correct", false));
		}
		this.answerQuestion = newAnswers;
	}

	public int lengthOfCharAnswer() {
		int countOfCharInAnswer = 0;
		for (int i = 0; i < answerQuestion.getCurrentSize(); i++) {
			if (answerQuestion.get(i) != null) {
				countOfCharInAnswer += answerQuestion.get(i).lenghtOfAnswer();
			}
		}
		return countOfCharInAnswer;
	}

	@Override
	public String toString() {
		StringBuffer answers = new StringBuffer(super.toString() + "[close] The answers is : ");
		for (int i = 0; i < answerQuestion.getCurrentSize(); i++) {
			if (answerQuestion.get(i) != null) {
				answers.append(answerQuestion.get(i).toString());
			}
		}
		return answers.toString();

	}

	public boolean deleteAnswer(Answer value2) throws Exception {

		if (answerQuestion.getCurrentSize() > 2) {
			for (int i = 0; i < answerQuestion.getCurrentSize(); i++) {
				if (answerQuestion.get(i).equals(value2)) {
					answerQuestion.removeElementAt(i);
					return true;
				}
			}

		}
		throw new Exception("Unable to delete answer because there are only 2 answers to this question");
	}
}
