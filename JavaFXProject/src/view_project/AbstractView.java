package view_project;


import listeners_project.SystemUIEventsListener;
import model_project.Answer;
import model_project.Exam;
import model_project.Manager;
import model_project.Set;


public interface AbstractView   {
	
	void registerListener(SystemUIEventsListener systemController);

	void addQuestionViewEvent(boolean falseQuestion);
	void addAnswerCloseViewEvent(String answer);	
	void updateAllQuestionAndAnswersEvent(String string, int size);
	void exceptionMessage(String string);
	void openOrCloseViewEvent(int openOrclose);
	void deleteAnswerViewEvent(int id, int index,Manager manager);
	void addAnswerViewEvent(String answer);
	void AnswerViewEvent(Set<Answer> answerQuestion);
	void DeleteViewEvent(Answer answer,String manager);
	void copyExamEvent(Exam clone);
	void updateQuestionViewEvent(String question);
	void updateCloseQuestionAnswersEvent(Set<Answer> answerQuestion);
	void numOfAnswers(int currentSize);
	void numOfAnswerupdate(Answer answer);
	void createManuallyExamViewEvent(String exam);
	void answerOpenQuestioViewEvent(int i);
	void updateAnswerViewEvent(String newAnswer); 
	void createExamViewEvent(String exam); 

}
