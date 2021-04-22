package gui;

import gui.pane.GamePane;
import gui.scene.GameScene;
import gui.scene.MenuScene;
import gui.scene.PickMapScene;
import gui.scene.PickTowerScene;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SceneController {
	
	private static String scene = "menu";
	
	private static MenuScene menuScene = new MenuScene(new VBox());
	private static PickMapScene pickMapScene = new PickMapScene(new VBox());
	private static PickTowerScene pickTowerScene = new PickTowerScene(new VBox());
	private static GameScene gameScene = new GameScene(new StackPane());
	
	private static Stage stage;

	public static Scene getScene() {
		if (scene.equals("menu")) {
			return menuScene;
		}else if(scene.equals("pickMap")){
			return pickMapScene;
		}else if(scene.equals("pickTower")) {
			SceneController.getPickTowerScene().updateDeck();
			return pickTowerScene;
		}else if(scene.equals("game")) {
			return gameScene;
		}else {
			return menuScene;
		}
	}
	
	public static void setScene(String scene) {
		SceneController.scene = scene;
		Scene newScene = getScene();
		stage.setScene(newScene);
	}
	
	public static void setStage(Stage stage) {
		SceneController.stage = stage;
	}
	
	public static void show() {
		stage.show();
	}
	
	public static MenuScene getMenuScene() {
		return menuScene;
	}

	public static PickMapScene getPickMapScene() {
		return pickMapScene;
	}

	public static PickTowerScene getPickTowerScene() {
		return pickTowerScene;
	}
	
	public static GameScene getGameScene() {
		return gameScene;
	}

	public static GamePane getGamePane() {
		return gameScene.getGamePane();
	}

	public static Stage getStage() {
		return stage;
	}
	
}
