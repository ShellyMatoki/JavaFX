
package model_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class Exam implements Cloneable,Serializable {

	private int numOfQuestion;
	private Vector<Question> questions = new Vector<Question>();

	public Exam(int numOfQuestion) {
		this.numOfQuestion = numOfQuestion;
	}

	public Exam clone() throws CloneNotSupportedException {
		Exam temp = (Exam) super.clone();
		temp.questions = new Vector<Question>();
		for (int i = 0; i < questions.size(); i++) {
			temp.questions.add((Question)questions.get(i).clone());
		} 
		return temp;
	}
	
	
	public int getNumOfQuestion() {
		return numOfQuestion;
	}

	public boolean setNumOfQuestion(int numOfQuestion) {
		this.numOfQuestion = numOfQuestion;
		return true;
	}

	public Vector<Question> getQuestions() {
		return questions;
	}

	public boolean setQuestions(Vector<Question> questions) {
		this.questions = questions;
		return true;
	}

	public void save() throws FileNotFoundException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm");
		Date today = Calendar.getInstance().getTime();
		String logDate = dateFormat.format(today);
		File createFileExam = new File("exam" + logDate + ".txt");
		File createFileSolution = new File("solution" + logDate + ".txt");
		PrintWriter writerExam = new PrintWriter(createFileExam);
		PrintWriter writerSolution = new PrintWriter(createFileSolution);
		if (questions != null) {
			for (int i = 0; i < questions.size(); i++) {
				if (questions.get(i) != null) {
					writerSolution.println((i + 1) + ")" + "The question is:" + questions.get(i).getTextQuestion());
					writerExam.println((i + 1) + ")" + "The question is: " + questions.get(i).getTextQuestion());
					if (questions.get(i) instanceof OpenQuestion) {
						OpenQuestion open = (OpenQuestion) questions.get(i);
						writerSolution.println("  The Answer:" + open.getTextAnswer().getText());
					}
					if (questions.get(i) instanceof CloseQuestion) {
						CloseQuestion close = (CloseQuestion) questions.get(i);
						writerExam.println("  The Answers:");
						writerSolution.println("  The Answers:");
						if (close.getAnswerQuestion() != null)
							for (int j = 0; j < close.getAnswerQuestion().getCurrentSize(); j++) {
								if (close.getAnswerQuestion().get(j) != null) {

									writerExam
											.println("  " + (j + 1) + "." + close.getAnswerQuestion().get(j).getText());
									writerSolution
											.println("  " + (j + 1) + "." + close.getAnswerQuestion().get(j).getText()
													+ "  " + close.getAnswerQuestion().get(j).isCorrect());
								}
							}
					}
					System.out.println();
					writerExam.println("\n");
					writerSolution.println("\n");
				}
			}
		}

		writerExam.close();
		writerSolution.close();

	}

	public int findIndexQuestionByIdInTest(int serialNumber) throws Exception {
		for (int index = 0; index < questions.size(); index++) {
			if (questions.get(index) != null) {
				if (questions.get(index).getSerialNumber() == serialNumber) {
					return index;
				}
			}
		}
		throw new Exception("This serial question is not exist \n");
	}

	public void addAnswerToTest(int id, String text, boolean correct) throws Exception {
		int index = findIndexQuestionByIdInTest(id);
		CloseQuestion closeQuestion = (CloseQuestion) questions.get(index);
		Answer newAnswer = new Answer(text, correct);
		closeQuestion.addAnswer(newAnswer);
	}

	public Question findQuestionByID(int serialNumber) throws Exception {
		int index = findIndexQuestionByIdInTest(serialNumber);
		return questions.get(index);
	}

	public int addQuestionInTest(Question question) throws Exception {
		if (question instanceof OpenQuestion) {
			questions.add(((OpenQuestion) question).clone());
			return questions.get(questions.size() - 1).getSerialNumber();
		} else if (question instanceof CloseQuestion) {
			questions.add(((CloseQuestion) question).clone());
			return questions.get(questions.size() - 1).getSerialNumber();
		}
		return -1;
	}

	public void addQuestionWithousAnswers(CloseQuestion question) throws Exception {
		CloseQuestion newQuestion = new CloseQuestion(question.getTextQuestion(), question.getSerialNumber());
		this.addQuestionInTest(newQuestion);
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Exam))
			return false;
		Exam managerObject = (Exam) object;
		return (managerObject.equals(this.questions));
	}

	@Override
	public String toString() {
		StringBuffer question = new StringBuffer("The test with the answers:\n");
		if (questions != null)
			for (int i = 0; i < questions.size(); i++) {
				if (questions.get(i) != null) {
					question.append(questions.get(i).toString() + "\n");
				}

			}
		return question.toString();
	}

}
