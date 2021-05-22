package gui.pane;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import logic.gmanager.GameButtons;
import logic.gmanager.GameManager;
import utils.FontUtil;
import utils.ImageUtil;

/**
 * Pane for displaying turn status
 */
public class TurnPane extends VBox {
	/** Current Phase text*/
	private Text phaseText;
	/** Current Player*/
	private Text playerTurn;
	/** Current Turn*/
	private Text turnNumber;
	/** End turn Button*/
	private ImageView endTurn;
	/** End turn Button Image*/
	private final Image endTurnPic = ImageUtil.ImageLoader("buttons/end_turn.png");
	
	/**
	 * Constructor for TurnPane
	 */
	public TurnPane() {
		phaseText = new Text("Management");
		playerTurn = new Text("Player1's Turn");
		turnNumber = new Text("Turn 1");
		
		phaseText.setFont(FontUtil.loadFont(16));
		playerTurn.setFont(FontUtil.loadFont(16));
		turnNumber.setFont(FontUtil.loadFont(16));
		endTurn = new ImageView(endTurnPic);
		
		this.getChildren().addAll(phaseText,playerTurn,turnNumber,endTurn);
		this.setMinWidth(256);
		this.setAlignment(Pos.CENTER);
		endTurn.setOnMouseClicked(e -> {
			GameButtons.proceedGamePhase();
		});
	}
	
	/**
	 * Update display for phase, player, turn
	 */
	public void updateTurnPane()
	{
		this.phaseText.setText(GameManager.getTurnPhaseString());
		this.playerTurn.setText(GameManager.getCurrentPlayerString());
		this.turnNumber.setText(GameManager.getCurrentTurnString());
		
	}
	
	/**
	 * Make {@link #endTurn end turn button} disable
	 * @param disable Can't use status
	 */
	public void setDisableEnd(boolean disable) {
		endTurn.setDisable(disable);
		if (disable) {
			endTurn.setImage(null);
		}else {
			endTurn.setImage(endTurnPic);
		}
	}
}
