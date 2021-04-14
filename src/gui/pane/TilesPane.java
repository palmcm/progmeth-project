package gui.pane;

import gui.cell.TileCell;
import javafx.scene.layout.GridPane;
import logic.gmanager.Board;
import logic.gmanager.GameButtons;
import logic.gmanager.GameManager;
import logic.gmanager.Tile;
import logic.misc.Coordinate;

public class TilesPane extends GridPane{
	private int lanes;
	private int cols;
	private Board board;
	private TileCell tilesCell[][] = new TileCell[5][12];
	public TilesPane() {
		board = GameManager.getGameInstance().getBoard();
		lanes = board.getLanes();
		cols = board.getBorder()+1;
		Tile tiles[][] = board.getTiles();
		for (int i=0;i<lanes;i++) {
			for (int j=1;j<cols;j++) {
				TileCell tile = new TileCell(tiles[i][j]);
				tilesCell[i][j] = tile;
				this.add(tile, j, i);
				tile.setOnMouseClicked(e -> {
					if (GameButtons.selectTile(tile.getTile().getLoc())) {
						tile.update();
					}
				});
			}
		}
	}
	
	public TileCell getTileCell(Coordinate loc) {
		System.out.println(loc.getX() + " " + loc.getY());
		return tilesCell[loc.getX()][loc.getY()];
	}
}
