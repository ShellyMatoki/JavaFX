package model_project;

import java.io.Serializable;

public class OpenQuestion extends Question implements Serializable, Cloneable {

	private Answer textAnswer;

	public OpenQuestion(String textQuestion, Answer textAnswer) {
		super(textQuestion);
		if (textAnswer != null) {
			this.textAnswer.setTextAnswer(textAnswer.getText());
			this.textAnswer.setCorrect(true);
		}
	}

	public OpenQuestion clone() throws CloneNotSupportedException {
		OpenQuestion temp = (OpenQuestion) super.clone();
		temp.textAnswer = textAnswer.clone();
		return temp;
	}

	public int lengthOfCharAnswer() {
		return textAnswer.lenghtOfAnswer();
	}

	public Answer getTextAnswer() {
		return textAnswer;
	}

	public boolean setTextAnswer(Answer textAnswer) {
		this.textAnswer = textAnswer;
		return true;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof OpenQuestion))
			return false;
		if (!(super.equals(other)))
			return false;
		OpenQuestion open = (OpenQuestion) other;
		return textAnswer == open.textAnswer;
	}

	@Override
	public String toString() {
		String openQuestion = (super.toString() + "[open] The answer is : " + textAnswer.getText() + "\n");
		return openQuestion;
	}

}
