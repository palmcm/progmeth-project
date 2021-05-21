package gui.scene;

import config.GameConfig;
import gui.pane.GameOverPane;
import gui.pane.GamePane;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import utils.ImageUtil;
/**
 * Scene for game
 */
public class GameScene extends Scene {
	/** Game interactable */
	private GamePane gamePane;
	/** Map background */
	private ImageView mapLayer;
	/** Game over popup */
	private GameOverPane gameOver;
	/** Root for scene*/
	private StackPane root;
	
	/**
	 * Constructor for GameScene
	 * @param root StackPane object
	 */
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
	
	/**
	 * Getter for {@link #gamePane gamePane}
	 * @return {@link #gamePane gamePane}
	 */
	public GamePane getGamePane() {
		return gamePane;
	}
	
	/**
	 * Open end game pop-up
	 * @param player Winner player
	 */
	public void gameEnd(int player) {
		gameOver = new GameOverPane();
		gameOver.setWinner(player);
		root.getChildren().add(gameOver);
	}
	
	/**
	 * Close end game pop-up
	 */
	public void closeGameEnd() {
		root.getChildren().remove(root.getChildren().size()-1);
	}
}
