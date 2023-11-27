package model_project;
import java.io.Serializable;
import java.util.Vector;

public abstract class Question implements Comparable<Question>, Serializable ,Cloneable{

	protected int serialNumber;
	protected  static int counter = 1;
	protected String textQuestion;

	public Question(String textQuestion) {
		this.serialNumber = counter++;
		this.textQuestion = textQuestion;
	}

	public Question(String textQuestion, int serialNumber) {
		this.serialNumber = serialNumber;
		this.textQuestion = textQuestion;
	}
	
	public Question clone ()throws CloneNotSupportedException{
		return (Question)super.clone();
	}
	
	public void Updatecounter(int count) {
		Question.counter=count+1;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public String getTextQuestion() {
		return textQuestion;
	}

	public boolean setTextQuestion(String textQuestion) {
		this.textQuestion = textQuestion;
		return true;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Question))
			return false;
		Question question = (Question) other;
		return question.serialNumber == serialNumber && question.textQuestion.equals(textQuestion);
	}
	
	public boolean equal(Vector<Question>Questionnn) {
		for (int i=0;i<Questionnn.size();i++) {
		if(this.getSerialNumber()==Questionnn.get(i).getSerialNumber())
			return true;
		}
		return false;
	}
	
	@Override
	public int compareTo(Question o) {
		return (this.lengthOfCharAnswer()-o.lengthOfCharAnswer());
	}

	public abstract int  lengthOfCharAnswer() ;

	@Override
	public String toString() {

		String strQuestion = "\n" + "serialNumber:" + serialNumber + "\nThe question:" + textQuestion + "\n";
		return strQuestion;
	}

}
