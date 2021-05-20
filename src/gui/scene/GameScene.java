
package gui.scene;

import config.GameConfig;
import gui.cell.ToolsCell;
import gui.cell.TowerGameCell;
import gui.pane.DescriptionPane;
import gui.pane.GameOverPane;
import gui.pane.GamePane;
import gui.pane.PlayerPane;
import gui.pane.TilesPane;
import gui.pane.ToolsPane;
import gui.pane.TowerGamePane;
import gui.pane.TurnPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import utils.ImageUtil;

public class GameScene extends Scene {
	private GamePane gamePane;
	private ImageView mapLayer;
	private GameOverPane gameOver;
	private StackPane root;
	
	public GameScene(StackPane root){
		super(root,GameConfig.SCREEN_WIDTH,GameConfig.SCREEN_HEIGHT);
			gamePane = new GamePane();
			
			mapLayer = new ImageView();
			mapLayer.setImage(ImageUtil.ImageLoader("maps/classic.png"));
			
			ImageView uiLayer = new ImageView();
			uiLayer.setImage(ImageUtil.ImageLoader("ui/ingame_overlay.png"));
			
			root.getChildren().addAll(mapLayer,uiLayer,gamePane);
			root.setAlignment(Pos.CENTER);
			this.root = root;
		}
	
	public GamePane getGamePane() {
		return gamePane;
	}
	
	public void gameEnd(int player) {
		gameOver = new GameOverPane();
		gameOver.setWinner(player);
		root.getChildren().add(gameOver);
	}
	
	public void closeGameEnd() {
		root.getChildren().remove(3);
	}
}
