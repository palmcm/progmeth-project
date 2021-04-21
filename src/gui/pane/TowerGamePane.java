package gui.pane;

import java.util.ArrayList;


import config.GameConfig;
import gui.cell.ToolsCell;
import gui.cell.TowerGameCell;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import logic.gmanager.GameManager;
import logic.gmanager.GameSettings;
import logic.gmanager.Player;
import logic.towers.BaseTower;
import logic.towers.Towers;

public class TowerGamePane extends VBox{
	
	private ArrayList<TowerGameCell> towerList = new ArrayList<TowerGameCell>();
	
	public TowerGamePane() {
		TilePane towerDeckBox = new TilePane();
		for (int i=0;i<GameManager.getMaxDeckSize();i++) {
			TowerGameCell cell = new TowerGameCell();
			towerList.add(cell);
			towerDeckBox.getChildren().add(cell);
		}
//		for (BaseTower tower:Towers.getTowers()) {
//			TowerGameCell cell = new TowerGameCell(tower);
//			towerList.add(cell);
//			towerDeckBox.getChildren().add(cell);
//		}
		towerDeckBox.setHgap(1);
		towerDeckBox.setAlignment(Pos.CENTER);
		this.setAlignment(Pos.CENTER);
		this.setMinSize(GameConfig.SCREEN_WIDTH/2.5, GameConfig.SCREEN_HEIGHT*0.25);
		this.setPadding(new Insets(5));
		this.getChildren().addAll(towerDeckBox);
		this.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}
	
	public ArrayList<TowerGameCell> getTowerList() {
		return towerList;
	}
	
	public void setDeck(int player) {
		Player play = GameManager.getGameInstance().getPlayer(player);
		if (play != null) {
			ArrayList<BaseTower> deck = play.getDeck();
			for (int i=0;i<GameManager.getMaxDeckSize();i++) {
				towerList.get(i).setTower(deck.get(i));
			}
		} else {
			for (int i=0;i<GameManager.getMaxDeckSize();i++) {
				towerList.get(i).setTower(null);
			}
		}
		
	}
}
