package gui.pane;

import gui.SceneController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import utils.FontUtil;

public class GameOverPane extends VBox{
	
	private Text winner;
	
	public GameOverPane() {
		super(5);
		winner = new Text("Winner is");
		winner.setFont(FontUtil.loadFont(16));
		
		Button playAgain = new Button("Play Again");
		playAgain.setOnMouseClicked(e -> {
			close();
		});
		
		Button mainmenu = new Button("Back to Main menu");
		mainmenu.setOnMouseClicked(e -> {
			close();
		});
		
		this.getChildren().addAll(winner,playAgain,mainmenu);
		this.setMaxSize(500, 200);
		this.setBackground(new Background(new BackgroundFill(Color.WHITE,null,null)));
		this.setAlignment(Pos.CENTER);
	}
	
	private void close() {
		SceneController.getGameScene().closeGameEnd();
	}
	
	public void setWinner(int player) {
		winner.setText("Winner is " + player);
	}

}
