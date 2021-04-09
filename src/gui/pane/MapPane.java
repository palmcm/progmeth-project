package gui.pane;

import config.GameConfig;
import gui.cell.MapCell;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class MapPane extends GridPane{
	public MapPane() {
		MapCell mapCell = new MapCell();
		this.add(mapCell, 0, 0);
		this.setWidth(GameConfig.SCREEN_WIDTH/2);
		this.setAlignment(Pos.CENTER);
	}
}
