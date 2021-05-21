package gui.pane;

import gui.cell.ToolCell;
import gui.cell.TowerGameCell;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.gmanager.GameButtons;
import logic.gmanager.GameManager;
import logic.gmanager.TurnPhase;
/**
 * Pane for displaying game
 */
public class GamePane extends VBox{
	/** Player status for player 1*/
	private PlayerPane player1Status;
	/** Player status for player 2*/
	private PlayerPane player2Status;
	/** Phase and turn status*/
	private TurnPane turnStatus;
	/** Play area*/
	private TilesPane tilesPane;
	/** Info Box*/
	private DescriptionPane descriptionPane;
	/** Current player deck*/
	private TowerGamePane towergamePane;
	/** Tools Pane*/
	private ToolsPane toolsPane;
	/**
	 * Constructor for GamePane
	 */
	public GamePane() {
		HBox playerTab = new HBox();
		player1Status = new PlayerPane(1);
		turnStatus = new TurnPane();
		player2Status = new PlayerPane(2);
		playerTab.getChildren().addAll(player1Status,turnStatus, player2Status);
		playerTab.setPadding(new Insets(0,0,0,10));
		
		HBox boardArea = new HBox();
		tilesPane = new TilesPane();
		boardArea.getChildren().addAll(tilesPane);
		boardArea.setAlignment(Pos.CENTER);
		VBox.setMargin(boardArea, new Insets(20, 0, 20, 0));
		
		HBox towerTab = new HBox();
		towergamePane = new TowerGamePane();
		
		for(TowerGameCell tower:towergamePane.getTowerList()) {
			tower.setOnMouseClicked(e -> {
				if(GameManager.getTurnPhase() != TurnPhase.BUILD)
					return;
				selectedTower(tower);
			});
		}
		
		toolsPane = new ToolsPane();
		
		for(ToolCell tool:toolsPane.getToolsList()) {
			tool.setOnMouseClicked(e -> {
				if(GameManager.getTurnPhase() != TurnPhase.BUILD)
					return;
				selectedTool(tool);
			});
		}
		
		descriptionPane = new DescriptionPane();
		towerTab.setPadding(new Insets(5));
		towerTab.getChildren().addAll(towergamePane,toolsPane,descriptionPane);
		
		this.getChildren().addAll(playerTab, boardArea, towerTab);
		}

	/**
	 * Getter for {@link #descriptionPane descriptionPane}
	 * @return {@link #descriptionPane descriptionPane}
	 */
	public DescriptionPane getDescriptionPane() {
		return descriptionPane;
	}
	
	/**
	 * Handle for select tower in {@link #towergamePane towergamePane}
	 * @param selectedTower Tower that player choose
	 */
	public void selectedTower(TowerGameCell selectedTower) {
		unhighlightOption();
		GameButtons.selectTower(selectedTower.getTower());
		selectedTower.highlight();
	}
	/**
	 * Handle for select tool in {@link #toolsPane toolsPane}
	 * @param selectedTool Tool that player choose
	 */
	public void selectedTool(ToolCell selectedTool) {
		unhighlightOption();
		selectedTool.selectHandle();
		selectedTool.highlight();
	}
	/**
	 * Remove highlight from all tools and tower cells
	 */
	public void unhighlightOption() {
		for(TowerGameCell tower:towergamePane.getTowerList()) {
			tower.unhighlight();
		}
		for(ToolCell tool:toolsPane.getToolsList()) {
			tool.unhighlight();
		}
	}
	/**
	 * Update health bar display for 2 players
	 */
	public void updateHp() {
		player1Status.updateHp();
		player2Status.updateHp();
	}
	
	/**
	 * Update money display for 2 players
	 */
	public void updateMoney() {
		player1Status.updateMoney();
		player2Status.updateMoney();
	}
	
	/**
	 * Getter for {@link #turnStatus turnStatus}
	 * @return {@link #turnStatus turnStatus}
	 */
	public TurnPane getTurnStatus() {
		return turnStatus;
	}
	
	/**
	 * Getter for {@link #tilesPane tilesPane}
	 * @return {@link #tilesPane tilesPane}
	 */
	public TilesPane getTilesPane() {
		return tilesPane;
	}
	
	/**
	 * Getter for {@link PlayerPane PlayerPane} 
	 * @param player Player
	 * @return playerStatus from player parameter
	 */
	public PlayerPane getPlayerPane(int player)
	{
		if(player==1)
			return this.player1Status;
		return this.player2Status;
	}
	
	/**
	 * Update display for {@link #towergamePane towergamePane}
	 */
	public void updateDeck() {
		if (GameManager.getTurnPhase() == TurnPhase.BUILD) {
			towergamePane.setDeck(GameManager.getCurrentPlayer());
		}else {
			towergamePane.setDeck(0);
		}
		GameButtons.selectTower(null);
		unhighlightOption();
	}
	
	/**
	 * Update health bar, money, turn phase, play area
	 */
	public void updateScreen() {
		updateHp();
		updateMoney();
		turnStatus.updateTurnPane();
		tilesPane.updateAllTiles();
	}
	
	/**
	 * Hide and disable next phase
	 * @param isin Is in animation or set disable to next phase button
	 */
	public void inAnimation(boolean isin) {
		turnStatus.setDisableEnd(isin);
	}
}
