
package gui.scene;

import config.GameConfig;
import gui.cell.ToolsCell;
import gui.cell.TowerGameCell;
import gui.pane.DescriptionPane;
import gui.pane.GamePane;
import gui.pane.PlayerPane;
import gui.pane.TilesPane;
import gui.pane.ToolsPane;
import gui.pane.TowerGamePane;
import gui.pane.TurnPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import logic.gmanager.GameButtons;
import logic.gmanager.GameManager;
import logic.gmanager.TurnPhase;

public class GameScene extends Scene {
	private GamePane gamePane;
	
	public GameScene(StackPane root) {
		super(root,GameConfig.SCREEN_WIDTH,GameConfig.SCREEN_HEIGHT);
			gamePane = new GamePane();
			
			root.getChildren().addAll(gamePane);
		}
	
	public GamePane getGamePane() {
		return gamePane;
	}
}
