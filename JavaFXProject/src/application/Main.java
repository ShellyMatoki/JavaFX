package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Vector;
import controller_project.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import model_project.Manager;
import model_project.Question;
import view_project.AbstractView;
import view_project.SystemJavaFx;

public class Main extends Application  {
	
	@Override
	public void start(Stage primaryStage) {
		Manager theModel = new Manager();
		
		AbstractView theView1 = new SystemJavaFx(new Stage());
		try {
			ObjectInputStream inputStorage = new ObjectInputStream(new FileInputStream("StorageQuestion.txt"));
			theModel.setQuestions((Vector<Question>)inputStorage.readObject());
			inputStorage.close();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Empty file");
		} catch (Exception e) {
			System.out.println("Empty file");
		}
		Controller newController = new Controller(theModel, theView1);

	}

	public static void main(String[] args) {
		launch(args);
	}

}






