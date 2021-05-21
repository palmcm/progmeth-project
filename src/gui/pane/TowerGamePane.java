package gui.pane;

import java.util.ArrayList;

import config.GameConfig;
import gui.cell.TowerGameCell;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import logic.gmanager.GameManager;
import logic.gmanager.Player;
import logic.towers.BaseTower;

/**
 * Pane for displaying playable 
 */
public class TowerGamePane extends VBox{
	
	/** All playable tower*/
	private ArrayList<TowerGameCell> towerList = new ArrayList<TowerGameCell>();
	
	/**
	 * Constructor for TowerGamePane
	 */
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
		this.setMinSize(650, GameConfig.SCREEN_HEIGHT*0.25);
		this.setPadding(new Insets(5));
		this.getChildren().addAll(towerDeckBox);
//		this.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, 
//				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}
	
	/**
	 * Getter for {@link #towerList towerList}
	 * @return {@link #towerList towerList}
	 */
	public ArrayList<TowerGameCell> getTowerList() {
		return towerList;
	}
	
	/**
	 * Set display for {@link #towerList towerList}
	 * @param player Current player
	 */
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
