package gui.pane;

import exception.SelectInvalidTileException;
import java.util.ArrayList;

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
import logic.towers.AimableTower;
import logic.towers.AttackableTower;
import logic.towers.BaseTower;

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
		
		this.setOnMouseExited(e -> {
			this.unhighlightAll();
		});		
		
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
							tile.update();
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
					SceneController.getGamePane().getDescriptionPane().setDesDefault();
					tooltip.hide();
					
					//this.unhighlightAll();
					
				});
			}
		}
	}
	
	public TileCell getTileCell(Coordinate loc) {
		//System.out.println(loc.getX() + " " + loc.getY());
		return tilesCell[loc.getX()][loc.getY()];
	}
	
	private void onMouseEnter(TileCell tile, MouseEvent e)
	{
		this.unhighlightAll();
		
		if(GameManager.getButtonMode() == ButtonMode.AIM)
		{
			AimableTower aiming = (AimableTower) GameManager.getSelectedTileTower();
			this.highlightCells(aiming.getReachableTiles());
			this.highlightTargetCells(aiming.getAimingTarget(tile.getTile().getLoc()));
			
		}
		
		if(tile.getTile().getTower() == null && GameManager.getButtonMode() == ButtonMode.SELECT)
		{
			return;
		}

		if(GameManager.getButtonMode() == ButtonMode.BUILD)
		{
			//System.out.println("HI");
			if(GameManager.getSelectedTower() != null)
			{
				this.highlightValidTiles();
				if(GameManager.getSelectedTower() instanceof AttackableTower && tile.getTile().canPlace(GameManager.getCurrentPlayer()))
				{
					AttackableTower cTower = (AttackableTower) GameManager.getSelectedTower();
					cTower = (AttackableTower) cTower.getNewInstance(tile.getTile().getLoc());
					cTower.setOwner(GameManager.getCurrentPlayer());
					this.highlightCells(cTower.getReachableTiles());
				}
				if(tile.getTile().canPlace(GameManager.getCurrentPlayer()))
				{

					this.highlightUnitCell(tile.getTile().getLoc());
				}
			}
		}
		
		if(tile.getTile().getTower() == null) return;
		
		if(GameManager.getButtonMode() == ButtonMode.UPGRADE)
		{
			tooltip.setText(tile.getTileUpgradeTooltip());;
			tooltip.show(this, e.getScreenX()+10, e.getScreenY()+10);						
		}
		else if(GameManager.getButtonMode() == ButtonMode.SELECT)
		{
			
			if(tile.getTile().getTower().getOwner() != GameManager.getCurrentPlayer())
			{
				
			}
			else
			{
				tooltip.setText(tile.getTileAttackStatus());
				tooltip.show(this, e.getScreenX()+10, e.getScreenY()+10);				
			}
			
			if((tile.getTile().getTower() instanceof AttackableTower))
			{
				this.highlightCells(  ((AttackableTower) tile.getTile().getTower()).getReachableTiles()  );
			}
			
			
		}
	}
	
	private void unhighlightAll()
	{
		int i = this.lanes;
		int j = this.cols;
		
		for(i=0;i<this.lanes;i++)
		{
			for(j=0;j<this.cols;j++)
			{
				this.getTileCell(new Coordinate(i,j)).unHighlight();
			}
		}
	}
	
	private void highlightValidTiles()
	{
		int i = this.lanes;
		int j = this.cols;
		
		for(i=0;i<this.lanes;i++)
		{
			for(j=0;j<this.cols;j++)
			{
				if(this.getTileCell(new Coordinate(i,j)).getTile().getTileOwner() != 0)
				{
					this.getTileCell(new Coordinate(i,j)).doEmptyHighlight();;					
				}
				
			}
		}
	}
	
	private void highlightCells(ArrayList<Coordinate> cells)
	{
		for(Coordinate i:cells)
		{
			if(GameManager.getGameInstance().getBoard().checkTile(i.getX(), i.getY()))
			{
				//System.out.println(">> "+i.getX() + " " + i.getY());
				this.getTileCell(i).doHighlight();
			}
		}
	}
	
	private void highlightTargetCells(ArrayList<Coordinate> cells)
	{
		for(Coordinate i:cells)
		{
			if(GameManager.getGameInstance().getBoard().checkTile(i.getX(), i.getY()))
			{
				//System.out.println(">> "+i.getX() + " " + i.getY());
				this.getTileCell(i).doTargetHighlight();
			}
		}
	}
	
	private void highlightUnitCell(Coordinate cell)
	{
		this.getTileCell(cell).doUnitHighlight();
	}
}
