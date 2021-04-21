package gui.pane;

import config.GameConfig;
import gui.SceneController;
import gui.cell.TowerCell;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import logic.gmanager.GameManager;
import logic.menu.DeckSelector;
import logic.towers.BaseTower;
import logic.towers.Towers;

public class TowerPane extends GridPane {
	public TowerPane() {
		int i=0;
		for (BaseTower tower:Towers.getTowers()) {
			TowerCell towerCell = new TowerCell(tower);
			this.add(towerCell, i%5, i/5);
			towerCell.setOnMouseClicked(e -> {
				DeckSelector.clickSelectCard(tower);
				SceneController.getPickTowerScene().updateDeck();
			});
			i++;
		}
		for (;i<10;i++) {
			this.add(new TowerCell(), i%5, i/5);
		}
		this.setHgap(1);
		this.setVgap(1);
		this.setWidth(GameConfig.SCREEN_WIDTH / 2);
		this.setAlignment(Pos.CENTER);
	}
}
