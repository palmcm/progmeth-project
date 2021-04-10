package application;
	
import gui.SceneController;
import javafx.application.Application;
import javafx.stage.Stage;
import logic.gmanager.GameManager;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			GameManager.initialize();
			
			Scene scene = SceneController.getScene();
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("CubicCopper");
			SceneController.setStage(primaryStage);
			SceneController.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
