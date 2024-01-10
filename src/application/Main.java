package application;
	
import java.io.IOException;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	public static Stage stg;
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root =FXMLLoader.load(getClass().getResource("Window1.fxml"));
			primaryStage.getIcons().add(new Image(getClass().getResource("img/artmu.png").toURI().toString()));
			primaryStage.setTitle("Palestinian Museum");
			Scene scene = new Scene(root,876,587);
			
			scene.setOnMousePressed(pressEvent -> {
				scene.setOnMouseDragged(dragEvent -> {
					stg.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
					stg.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
				});
			});
			scene.setFill(Color.TRANSPARENT);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
			//scene.getStylesheets().add(getClass().getResource("TableViewStyle.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			stg = 	primaryStage;
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void changeScene(String fxml) throws IOException {
		stg.getScene().setFill(new LinearGradient(
		        0, 0, 1, 1, true,                      //sizing
		        CycleMethod.NO_CYCLE                 //cycling
		        /* new Stop(0, Color.web("#FFD9C0")),     //colors
		        new Stop(1, Color.web("#FFD9C0"))*/ ));
		
		Parent pane = FXMLLoader.load(getClass().getResource(fxml));
		stg.getScene().setRoot(pane);
		pane.translateXProperty().set(stg.getHeight());
		pane.setTranslateX(100);		
	
		Timeline time= new Timeline();
		KeyValue kv=new KeyValue(pane.translateXProperty(),0,Interpolator.EASE_IN);
		KeyFrame kf=new KeyFrame(Duration.seconds(1),kv);
		time.getKeyFrames().add(kf);
		time.play();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
