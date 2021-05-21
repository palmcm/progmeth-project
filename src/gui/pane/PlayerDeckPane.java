package gui.pane;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import utils.FontUtil;

/**
 * Pane for displaying Player and DeckPane
 */
public class PlayerDeckPane extends StackPane{
	/** Player deck*/
	private DeckPane showDeck;
	/**
	 * Constructor for PlayerDeckPane
	 * @param player Owner of deck
	 */
	public PlayerDeckPane(int player) {
		VBox deckBox = new VBox(20);
		Text gameName = new Text("Player "+player+"'s Deck");
		gameName.setFont(FontUtil.loadFont(50));
		
		showDeck = new DeckPane(player);
		
		deckBox.getChildren().addAll(gameName,showDeck);
		deckBox.setAlignment(Pos.CENTER);
		
		this.getChildren().addAll(deckBox);
		this.setAlignment(Pos.CENTER);
		
		
	}
	/**
	 * Update display for deck
	 */
	public void showNewDeck() {
		showDeck.update();
	}
	/**
	 * Set background of DeckCell
	 * @param highlight True when their turn to pick
	 */
	public void highlight(boolean highlight) {
		showDeck.highlight(highlight);
	}
	
}
