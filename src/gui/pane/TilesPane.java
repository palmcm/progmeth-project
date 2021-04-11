package gui.pane;

import gui.cell.TileCell;
import javafx.scene.layout.GridPane;
import logic.gmanager.Board;
import logic.gmanager.GameManager;
import logic.gmanager.Tile;

public class TilesPane extends GridPane{
	private int lanes;
	private int cols;
	private Board board;
	public TilesPane() {
		board = GameManager.getGameInstance().getBoard();
		lanes = board.getLanes();
		cols = board.getBorder()+1;
		Tile tiles[][] = board.getTiles();
		for (int i=0;i<lanes;i++) {
			for (int j=1;j<cols;j++) {
				this.add(new TileCell(tiles[i][j]), j, i);
			}
		}
	}
}
