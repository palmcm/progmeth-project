package logic.gmanager;

import exception.InvalidPlayerException;
import gui.SceneController;
import logic.misc.Coordinate;
import logic.towers.BaseTower;

public class Tile {
	
	private BaseTower tower;
	private Coordinate loc;
	private boolean markAttacked;
	
	private int tileOwner; 
	// 0 - can't be built on, 1 - owned by player 1, same for 2. 3 is placeable by both
	
	public Tile()
	{
		this.tower = null;
		this.tileOwner = 0;
		this.loc = null;
		this.markAttacked = false;
	}
	
	public boolean canPlace(int player)
	{
		if(player == this.tileOwner || this.tileOwner == 3)
			return true;
		return false;
	}
	
	public boolean attackable(int player) throws InvalidPlayerException
	{
		if(this.tower == null)
			return false;
		return this.getTower().attackable(player);
	}
	
	public BaseTower getTower()
	{
		return this.tower;
	}
	
	public void setTower(BaseTower tower)
	{
		this.tower = tower;
	}
	
	public void removeTower()
	{
		this.tower = null;
		SceneController.getGamePane().getTilesPane().getTileCell(this.loc).update();
	}

	public int getTileOwner() {
		return tileOwner;
	}

	public void setTileOwner(int tileOwner) {
		this.tileOwner = tileOwner;
	}

	public Coordinate getLoc() {
		return loc;
	}

	public void setLoc(Coordinate loc) {
		this.loc = loc;
	}

	public boolean isMarkAttacked() {
		return this.markAttacked;
	}

	public void setMarkAttacked(boolean markAttacked) {
		this.markAttacked = markAttacked;
	}
	
	
	
	
	
	
	

}
