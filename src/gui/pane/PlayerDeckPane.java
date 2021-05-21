package gui.pane;

import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.gmanager.GameInstance;
import logic.gmanager.GameManager;
import utils.FontUtil;

public class PlayerDeckPane extends StackPane{
	
	private DeckPane showDeck;
	private int player;
	
	public PlayerDeckPane(int player) {
		VBox deckBox = new VBox(20);
		this.player = player;
		Text gameName = new Text("Player "+player+"'s Deck");
		gameName.setFont(FontUtil.loadFont(50));
		
		showDeck = new DeckPane(player);
		
		deckBox.getChildren().addAll(gameName,showDeck);
		deckBox.setAlignment(Pos.CENTER);
		
		this.getChildren().addAll(deckBox);
		this.setAlignment(Pos.CENTER);
		
		
	}
	
	public void showNewDeck() {
		showDeck.update(player);
	}
	
	public void highlight() {
		this.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, null, null)));
	}
	
	public void unhighlight() {
		this.setBackground(new Background(new BackgroundFill(null, null, null)));
	}
}
