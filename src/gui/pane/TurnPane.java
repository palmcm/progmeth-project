package gui.pane;

import config.GameConfig;
import gui.SceneController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.gmanager.GameButtons;
import logic.gmanager.GameManager;
import utils.FontUtil;
import utils.ImageUtil;

public class TurnPane extends VBox {
	private Text phaseText;
	private Text playerTurn;
	private Text turnNumber;
	
	public TurnPane() {
		phaseText = new Text("Attacking Phase");
		playerTurn = new Text("Player1's Turn");
		turnNumber = new Text("Turn 1");
		
		phaseText.setFont(FontUtil.loadFont(16));
		playerTurn.setFont(FontUtil.loadFont(16));
		turnNumber.setFont(FontUtil.loadFont(16));
		ImageView endTurn = new ImageView(ImageUtil.ImageLoader("buttons/end_turn.png"));
		this.getChildren().addAll(phaseText,playerTurn,turnNumber,endTurn);
		this.setMinWidth(256);
		this.setAlignment(Pos.CENTER);
		endTurn.setOnMouseClicked(e -> {
			GameButtons.proceedGamePhase();
		});
//		this.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, 
//				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}
	
	public void updateTurnPane()
	{
		this.phaseText.setText(GameManager.getTurnPhaseString());
		this.playerTurn.setText(GameManager.getCurrentPlayerString());
		this.turnNumber.setText(GameManager.getCurrentTurnString());
		
		
		
		
	}
}
