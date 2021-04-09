package gui.pane;

import config.GameConfig;
import gui.cell.TowerCell;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class TowerPane extends GridPane {
	public TowerPane() {
		TowerCell towerCell = new TowerCell();
		this.add(towerCell, 0, 0);
		this.add(new TowerCell(), 0, 1);
		this.add(new TowerCell(), 1, 0);
		this.add(new TowerCell(), 2, 1);
		this.add(new TowerCell(), 1, 2);
		this.add(new TowerCell(), 3, 2);
		this.setWidth(GameConfig.SCREEN_WIDTH / 2);
		this.setAlignment(Pos.CENTER);
	}
}
