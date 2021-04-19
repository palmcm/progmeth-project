package gui.pane;

import gui.SceneController;
import gui.cell.TileCell;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import logic.gmanager.Board;
import logic.gmanager.ButtonMode;
import logic.gmanager.GameButtons;
import logic.gmanager.GameManager;
import logic.gmanager.Tile;
import logic.misc.Coordinate;

public class TilesPane extends GridPane{
	private int lanes;
	private int cols;
	private Board board;
	private TileCell tilesCell[][] = new TileCell[5][13];
	private Tooltip tooltip;
	
	public Tooltip getTooltip()
	{
		return tooltip;
	}
	
	public TilesPane() {
		board = GameManager.getGameInstance().getBoard();
		lanes = board.getLanes();
		cols = board.getBorder();
		Tile tiles[][] = board.getTiles();
		tooltip = new Tooltip();
		tooltip.setFont(new Font(15));
		for (int i=0;i<lanes;i++) {
			for (int j=0;j<cols;j++) {
				TileCell tile = new TileCell(tiles[i][j]);
				tilesCell[i][j] = tile;
				this.add(tile, j, i);
				tile.setOnMouseClicked(e -> {
					if (GameButtons.selectTile(tile.getTile().getLoc())) {
						tile.update();
						tile.showDes();
						onMouseEnter(tile,e);
					}
				});

				tile.setOnMouseEntered(e -> {
					//System.out.println("HOVERING");
					tile.showDes();
					onMouseEnter(tile,e);
					
				});
				
				tile.setOnMouseExited(e -> {
					SceneController.getGameScene().getDescriptionPane().setDesDefault();
					tooltip.hide();
				});
			}
		}
	}
	
	public TileCell getTileCell(Coordinate loc) {
		System.out.println(loc.getX() + " " + loc.getY());
		return tilesCell[loc.getX()][loc.getY()];
	}
	
	private void onMouseEnter(TileCell tile, MouseEvent e)
	{

		if(GameManager.getButtonMode() == ButtonMode.UPGRADE && tile.getTile().getTower() != null)
		{
			tile.showUpgradeToolTip();
			tooltip.show(this, e.getScreenX()+5, e.getScreenY()+5);						
		}
	}
}
