package gui.pane;

import exception.SelectInvalidTileException;
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
					
						try {
							GameButtons.selectTile(tile.getTile().getLoc());
							tile.update();
							tile.showDes();
							onMouseEnter(tile,e);
						} catch (SelectInvalidTileException error) {
//							System.out.println(error.getMessage());
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
		if(tile.getTile().getTower() == null)
			return;

		if(GameManager.getButtonMode() == ButtonMode.UPGRADE)
		{
			tooltip.setText(tile.getTileUpgradeTooltip());;
			tooltip.show(this, e.getScreenX()+10, e.getScreenY()+10);						
		}
		else if(GameManager.getButtonMode() == ButtonMode.SELECT)
		{
			if(tile.getTile().getTower().getOwner() != GameManager.getCurrentPlayer())
				return;
			tooltip.setText(tile.getTileAttackStatus());
			tooltip.show(this, e.getScreenX()+10, e.getScreenY()+10);
			
		}
	}
}
