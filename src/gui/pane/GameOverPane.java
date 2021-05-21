package gui.pane;

import gui.SceneController;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.gmanager.GameManager;
import utils.FontUtil;
import utils.ImageUtil;

public class GameOverPane extends VBox{
	
	private Text winner;
	
	public GameOverPane() {
		super(10);
		winner = new Text("Winner is");
		winner.setFont(FontUtil.loadFont(36));
		winner.setFill(Color.WHITE);
		
		Text playAgain = new Text("Play Again");
		playAgain.setOnMouseClicked(e -> {
			GameManager.playAgain();
//			close();
		});
		playAgain.setFill(Color.WHITE);
		playAgain.setFont(FontUtil.loadFont(24));
		
		Text mainmenu = new Text("Back to Main menu");
		mainmenu.setOnMouseClicked(e -> {
			close();
			SceneController.setScene("menu");
		});
		mainmenu.setFont(FontUtil.loadFont(24));
		mainmenu.setFill(Color.WHITE);
		
		this.getChildren().addAll(winner,playAgain,mainmenu);
		this.setMaxSize(500, 200);
		this.setBackground(new Background(new BackgroundImage( ImageUtil.ImageLoader("ui/victory_box.png",500), BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
		        BackgroundPosition.DEFAULT,
		        new BackgroundSize(1.0, 1.0, true, true, false, false))));
		this.setAlignment(Pos.CENTER);
	}
	
	private void close() {
		SceneController.getGameScene().closeGameEnd();
	}
	
	public void setWinner(int player) {
		if (player == 3) {
			winner.setText("The match ended in a tie.");
			return;
		}
		winner.setText("Player " + player+" emerges victorious!");
	}

}
