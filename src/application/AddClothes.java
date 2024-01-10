package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AddClothes extends Application{
	
	public AddClothes() {
		
	}
	public void start(Stage primaryStage) throws Exception {
		
		Parent parent = (Parent)FXMLLoader.load(getClass().getResource("AddClothes.fxml"));
		Scene scene= new Scene(parent);

		primaryStage.setScene(scene);
		primaryStage.initStyle(StageStyle.UTILITY);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		System.out.println("DONEEE,...Add Clothes");
		launch(args);
	}
	

}

