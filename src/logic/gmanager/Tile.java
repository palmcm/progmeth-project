package logic.gmanager;

import logic.towers.Tower;

public class Tile {
	
	private Tower tower;
	
	private int tileOwner; 
	// 0 - can't be built on, 1 - owned by player 1, same for 2. 3 is placeable by both
	
	public boolean canPlace(int player)
	{
		if(player == this.tileOwner || this.tileOwner == 3)
			return true;
		return false;
	}
	
	public Tower getTower()
	{
		return this.tower;
	}
	
	public void setTower(Tower tower)
	{
		this.tower = tower;
	}
	
	public void removeTower(Tower tower)
	{
		this.tower = null;
	}
	
	
	
	

}
