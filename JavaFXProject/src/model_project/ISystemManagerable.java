package model_project;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;


public interface ISystemManagerable {

	public void displayStorage(Manager storageQuestionAndAnswers);

	public void addQuestion(Manager storageQuestionAndAnswers) throws FileNotFoundException, IOException;

	public void updateQuestion(Manager storageQuestionAndAnswers) throws FileNotFoundException, IOException;

	public void updateAnswer(Manager storageQuestionAndAnswers) throws FileNotFoundException, IOException;

	public void deleteAnswer(Manager storageQuestionAndAnswers) throws FileNotFoundException, IOException;

	public void createManuallyExam(Manager storageQuestionAndAnswers) throws Exception;

	public void createAutomaticallyExam(Manager storageQuestionAndAnswers) throws FileNotFoundException, Exception;

	public void copyExam(Manager storageQuestionAndAnswers) throws FileNotFoundException, Exception;

	public boolean ExistIdInExam(Vector<Integer> serialOfQuestion, int serial);

	public void cloneCopyExam(Manager storageQuestionAndAnswers) throws Exception;

	public boolean InputBoolean();

	public int InputInteger();
	
	
}
