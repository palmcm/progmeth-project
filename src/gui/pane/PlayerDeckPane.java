package gui.pane;

import javafx.geometry.Pos;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.gmanager.GameInstance;
import logic.gmanager.GameManager;

public class PlayerDeckPane extends VBox{
	
	private DeckPane showDeck;
	private int player;
	
	public PlayerDeckPane(int player) {
		super(20);
		this.player = player;
		Text gameName = new Text("Player "+player+"'s Deck");
		gameName.setFont(new Font(50));
		
		showDeck = new DeckPane();
		
		this.getChildren().addAll(gameName,showDeck);
		this.setAlignment(Pos.CENTER);
	}
	
	public void showNewDeck() {
		showDeck.update(player);
	}
}
