package listeners_project;


import model_project.Answer;
import model_project.Exam;
import model_project.Set;

public interface SystemEventsListener {

	void addOpenQuestionModelEvent(boolean trueQuestion);

	void addCloseQuestionModelEvent(boolean falseQuestion);

	void addOpenAnswerModelEvent();

	void updateQuestionModelEvent();

	void updateAnswerModelEvent();

	void deleteAnswerModelEvent(int id, int index);

	void addCloseAnswerModelEvent();

	void openOrCloseModelEvent(int openOrclose);

	void createExamModelEvent(String exam);

	void AnswerModelEvent(Set<Answer> answerQuestion);

	void AnswerDeleteModelEvent(Answer answer);

	void copyExamModelEvent(Exam clone);

	void numOfQuestionModelEvent(String allQuestionAndAnswers, int size);

	void updateCloseQuestionModelEvent(Set<Answer> answerQuestion);

	void numOfAnswerModelEvent(int currentSize,Answer answer);

	void createManuallyExamModelEvent(String exam);

	void openQuestionEvent(int serial);
}
