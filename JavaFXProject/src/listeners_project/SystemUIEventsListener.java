package listeners_project;

import model_project.Answer;

public interface SystemUIEventsListener {	
	void showAllDataViewEvent();
	void addQuestionUIEvent(String question,boolean open);
	void updateQuestionUIEvent(int serialNumber,String question);
	void updateAnswerUIEvent(int id, String newAnswer, boolean correct, int index);
	void saveViewEvent();
    void addAnswerUIEvent(String textQuestion,String answer) ;
    void addAnswerUIEvent(String textQuestion,String answer,boolean correct) ;
    void openOrCloseUIEvent(Integer value);	
	void createAutomaticallyExamViewEvent(int numOfQuestion);
	void allAnswersUIEvent(Integer serial);
	void deleteAnswersUIEvent(int serial, Answer answer);
	void copyExamUIEvent();
	void updateQuestionInExamUIEvent(int serialNumber);
	void getIndexAnswerUIEvent(Integer integer, Answer value);
	void updateAnswersInControllerUIEvent(Integer num);
	void createManuallyExamUIEvent();
}
