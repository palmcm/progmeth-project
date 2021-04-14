package gui.pane;

import config.GameConfig;
import gui.SceneController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import logic.gmanager.GameButtons;
import logic.gmanager.GameManager;

public class TurnPane extends VBox {
	Text phaseText;
	Text playerTurn;
	Text turnNumber;
	
	public TurnPane() {
		phaseText = new Text("Attacking Phase");
		playerTurn = new Text("Player1's Turn");
		turnNumber = new Text("Turn 1");
		Button endTurn = new Button("End turn");
		this.getChildren().addAll(phaseText,playerTurn,turnNumber,endTurn);
		this.setMinWidth(GameConfig.SCREEN_WIDTH/5-10);
		this.setAlignment(Pos.CENTER);
		this.setSpacing(5);
		this.setOnMouseClicked(e -> {
			int health = GameManager.getGameInstance().getPlayer(2).getHealth();
			GameManager.getGameInstance().getPlayer(2).setHealth(health-1);
			SceneController.getGameScene().updateHp();
		});
		endTurn.setOnMouseClicked(e -> {
			GameButtons.proceedGamePhase();
		});
	}
	
	public void updateTurnPane()
	{
		this.phaseText.setText(GameManager.getTurnPhaseString());
		this.playerTurn.setText(GameManager.getCurrentPlayerString());
		this.turnNumber.setText(GameManager.getCurrentTurnString());
		
		
		
		
	}
}
