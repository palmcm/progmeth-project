package gui.pane;

import config.GameConfig;
import gui.cell.TowerCell;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import logic.towers.BaseTower;
import logic.towers.Towers;

public class TowerPane extends GridPane {
	public TowerPane() {
		int i=0;
		for (BaseTower tower:Towers.getTowers()) {
			this.add(new TowerCell(tower), i%4, i/4);
			i++;
		}
		this.add(new TowerCell(), 3, 1);
		this.setWidth(GameConfig.SCREEN_WIDTH / 2);
		this.setAlignment(Pos.CENTER);
	}
}
