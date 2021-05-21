package application;
	
import gui.SceneController;
import javafx.application.Application;
import javafx.stage.Stage;
import logic.gmanager.GameManager;
import javafx.scene.Scene;

/**
 * Main class launches the JavaFX application.
 *
 */
public class Main extends Application {
	/**
	 * Start method
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			
			GameManager.initialize();
			
			Scene scene = SceneController.getScene();
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Wizardland Saga");
			primaryStage.setResizable(false);
			SceneController.setStage(primaryStage);
			primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Main method
	 * @param args 
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
