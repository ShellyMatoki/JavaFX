package model_project;

import java.io.Serializable;

public class Answer implements Serializable, Cloneable {

	private String text;
	private boolean correct;

	public Answer(String text, boolean correct) {
		this.text = text;
		this.correct = correct;
	}

	
	public Answer clone() throws CloneNotSupportedException {	
		return (Answer) super.clone();
	}

	public boolean setTextAnswer(String text) {
		this.text = text;
		return true;
	}

	public int lenghtOfAnswer() {
		return text.length();
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Answer))
			return false;
		Answer answer = (Answer) other;
		return answer.text.equals(text);
	}

	public String getText() {
		return text;
	}

	public boolean updateAnswer(String text) {
		this.text = text;
		this.correct = true;
		return true;
	}

	public boolean isCorrect() {
		return correct;
	}

	public boolean setCorrect(boolean correct) {
		this.correct = correct;
		return true;
	}

	@Override
	public String toString() {
		return "\n" + text + " " + "[" + correct + "]";
	}
}
