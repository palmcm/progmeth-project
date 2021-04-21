package gui.pane;

import gui.cell.ToolsCell;
import gui.cell.TowerGameCell;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.gmanager.GameButtons;
import logic.gmanager.GameManager;
import logic.gmanager.TurnPhase;

public class GamePane extends VBox{
	private PlayerPane player1Status;
	private PlayerPane player2Status;
	private TurnPane turnStatus;
	private TilesPane tilesPane;
	private DescriptionPane descriptionPane;
	private TowerGamePane towergamePane;
	private ToolsPane toolsPane;
	
	public GamePane() {
		HBox playerTab = new HBox();
		player1Status = new PlayerPane(1);
		turnStatus = new TurnPane();
		player2Status = new PlayerPane(2);
		playerTab.getChildren().addAll(player1Status,turnStatus, player2Status);
		playerTab.setPadding(new Insets(5));
		
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
		
		for(ToolsCell tool:toolsPane.getToolsList()) {
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
//		this.setBackground(new Background(new BackgroundImage(new Image(ClassLoader.getSystemResource("img1.png").toString()), BackgroundRepeat.NO_REPEAT,
//		        BackgroundRepeat.NO_REPEAT,
//		        BackgroundPosition.DEFAULT,
//		        new BackgroundSize(1.0, 1.0, true, true, false, false))));
		}

	public DescriptionPane getDescriptionPane() {
		return descriptionPane;
	}
	
	
	public void selectedTower(TowerGameCell selectedTower) {
		unhighlightOption();
		GameButtons.selectTower(selectedTower.getTower());
		selectedTower.highlight();
	}
	
	public void selectedTool(ToolsCell selectedTool) {
		unhighlightOption();
		selectedTool.selectHandle();
		selectedTool.highlight();
	}
	
	public void unhighlightOption() {
		for(TowerGameCell tower:towergamePane.getTowerList()) {
			tower.unhighlight();
		}
		for(ToolsCell tool:toolsPane.getToolsList()) {
			tool.unhighlight();
		}
	}
	
	public void updateHp() {
		player1Status.updateHp();
		player2Status.updateHp();
	}
	
	public void updateMoney() {
		player1Status.updateMoney();
		player2Status.updateMoney();
	}

	public TurnPane getTurnStatus() {
		return turnStatus;
	}
	
	public TilesPane getTilesPane() {
		return tilesPane;
	}
	
	public PlayerPane getPlayerPane(int player)
	{
		if(player==1)
			return this.player1Status;
		return this.player2Status;
	}
	
	public void updateDeck() {
		if (GameManager.getTurnPhase() == TurnPhase.BUILD) {
			towergamePane.setDeck(GameManager.getCurrentPlayer());
		}else {
			towergamePane.setDeck(0);
		}
		GameButtons.selectTower(null);
		unhighlightOption();
	}
}
