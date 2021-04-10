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
			this.add(new TowerCell(tower), i%5, i/5);
			i++;
		}
		for (;i<10;i++) {
			this.add(new TowerCell(), i%5, i/5);
		}
		this.setWidth(GameConfig.SCREEN_WIDTH / 2);
		this.setAlignment(Pos.CENTER);
	}
}
