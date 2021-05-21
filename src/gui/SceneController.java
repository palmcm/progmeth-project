package gui;

import gui.pane.GamePane;
import gui.scene.GameScene;
import gui.scene.GameSettingScene;
import gui.scene.MenuScene;
import gui.scene.PickTowerScene;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Control the display scene for application.
 *
 */
public class SceneController {
	/**
	 * Currently display scene
	 */
	private static String scene = "menu";
	
	/**
	 * MenuScene
	 */
	private static MenuScene menuScene = new MenuScene(new VBox());
	/**
	 * PickTowerScene
	 */
	private static PickTowerScene pickTowerScene = new PickTowerScene(new VBox());
	/**
	 * GameSettingScene
	 */
	private static GameSettingScene gameSettingScene = new GameSettingScene(new VBox());
	/**
	 * GameScene
	 */
	private static GameScene gameScene;
	/**
	 * The stage for the application
	 */
	private static Stage stage;

	/**
	 * Return the scene that currently display.
	 * 
	 * @return the current scene
	 */
	public static Scene getScene() {
		if (scene.equals("menu")) {
			return menuScene;
		}else if(scene.equals("setting")){
			return gameSettingScene;
		}else if(scene.equals("pickTower")) {
			SceneController.getPickTowerScene().updateDeck();
			return pickTowerScene;
		}else if(scene.equals("game")) {
			return gameScene;
		}else {
			return menuScene;
		}
	}
	
	/**
	 * Set the display scene to the {@link #stage stage}
	 * default scene is {@link #menuScene menuScene}
	 * 
	 * @param scene String of scene that set to display
	 */
	public static void setScene(String scene) {
		SceneController.scene = scene;
		Scene newScene = getScene();
		stage.setScene(newScene);
	}
	
	/**
	 * Set stage that use in {@link application.Main#start(Stage) start} method in {@link application.Main Main} 
	 * 
	 * @param stage Stage that use in start method
	 */
	public static void setStage(Stage stage) {
		SceneController.stage = stage;
	}
	
	/**
	 * Getter for {@link #menuScene menuScene}
	 * 
	 * @return {@link #menuScene menuScene}
	 */
	public static MenuScene getMenuScene() {
		return menuScene;
	}

	/**
	 * Getter for {@link #pickTowerScene pickTowerScene}
	 * 
	 * @return {@link #pickTowerScene pickTowerScene}
	 */
	public static PickTowerScene getPickTowerScene() {
		return pickTowerScene;
	}
	
	/**
	 * Create new {@link #gameScene gameScene} for new {@link logic.gmanager.GameInstance GameInstance}
	 */
	public static void newGameScene() {
		gameScene = new GameScene(new StackPane());
		return;
	}
	
	/**
	 * Getter for {@link #gameScene gameScene}
	 * 
	 * @return {@link #gameScene gameScene}
	 */
	public static GameScene getGameScene() {
		return gameScene;
	}

	
	/**
	 * Getter for {@link GameScene#gamePane gamePane}
	 * 
	 * @return {@link GameScene#gamePane gamePane}
	 */
	public static GamePane getGamePane() {
		return gameScene.getGamePane();
	}
	
}
