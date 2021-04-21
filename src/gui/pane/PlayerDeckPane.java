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

public class PlayerDeckPane extends VBox{
	
	public PlayerDeckPane() {
		super(20);
		Text gameName = new Text("Player 1's Deck");
		gameName.setFont(new Font(50));
		
		DeckPane showDeck = new DeckPane();
		
		this.getChildren().addAll(gameName,showDeck);
		this.setAlignment(Pos.CENTER);
		this.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}
	
}
