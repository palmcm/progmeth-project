package gui.pane;

import gui.SceneController;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.gmanager.GameManager;
import utils.FontUtil;

public class GameOverPane extends VBox{
	
	private Text winner;
	
	public GameOverPane() {
		super(10);
		winner = new Text("Winner is");
		winner.setFont(FontUtil.loadFont(36));
		
		Text playAgain = new Text("Play Again");
		playAgain.setOnMouseClicked(e -> {
			GameManager.playAgain();
//			close();
		});
		
		playAgain.setFont(FontUtil.loadFont(24));
		
		Text mainmenu = new Text("Back to Main menu");
		mainmenu.setOnMouseClicked(e -> {
			close();
			SceneController.setScene("menu");
		});
		mainmenu.setFont(FontUtil.loadFont(24));
		
		this.getChildren().addAll(winner,playAgain,mainmenu);
		this.setMaxSize(500, 200);
		this.setBackground(new Background(new BackgroundFill(Color.WHITE,null,null)));
		this.setAlignment(Pos.CENTER);
	}
	
	private void close() {
		SceneController.getGameScene().closeGameEnd();
	}
	
	public void setWinner(int player) {
		if (player == 3) {
			winner.setText("Tie");
			return;
		}
		winner.setText("Winner is Player " + player);
	}

}
