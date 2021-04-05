package gui;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {
	
	private static String scene = "menu";
	
	private static MenuScene menuScene = new MenuScene();
	private static PickMapScene pickMapScene = new PickMapScene();
	private static PickTowerScreen pickTowerScene = new PickTowerScreen();
	
	private static Stage stage;

	public static MenuScene getMenuScene() {
		return menuScene;
	}

	public static PickMapScene getPickMapScene() {
		return pickMapScene;
	}
	
	public static Scene getScene() {
		if (scene.equals("menu")) {
			return menuScene.scene;
		}else if(scene.equals("pickMap")){
			return pickMapScene.scene;
		}else if(scene.equals("pickTower")) {
			return pickTowerScene.scene;
		}else {
			return menuScene.scene;
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
}
