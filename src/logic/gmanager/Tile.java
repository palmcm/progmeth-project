package logic.gmanager;

import exception.InvalidPlayerException;
import gui.SceneController;
import javafx.application.Platform;
import logic.misc.Coordinate;
import logic.towers.BaseTower;

/**
 * A class which represents a tile and contains its method and field.
 * A tile can contain a tower.
 *
 */
public class Tile {
	
	private BaseTower tower;
	private Coordinate loc;
	private boolean markAttacked;

	/**
	 *  0 - can't be built on, 1 - owned by player 1, same for 2. 3 is placeable by both
	 */
	private int tileOwner; 
	
	/**
	 * Initialize a tile.
	 */
	public Tile()
	{
		this.tower = null;
		this.tileOwner = 0;
		this.loc = null;
		this.markAttacked = false;
	}
	
	/**
	 * Returns whether or not a specific player can place on this tile.
	 * @param player player to query
	 * @return True if placable, false otherwise
	 */
	public boolean canPlace(int player)
	{
		if(player == this.tileOwner || this.tileOwner == 3)
			return true;
		return false;
	}
	
	/**
	 * Returns whether or not an attack from a specific player can hit this tile
	 * @param player player to query
	 * @return True if can hit, false otherwise.
	 * @throws InvalidPlayerException
	 */
	public boolean attackable(int player) throws InvalidPlayerException
	{
		if(this.tower == null)
			return false;
		return this.getTower().attackable(player);
	}
	
	/**
	 * Getter for {@link #tower}
	 * @return {@link #tower}
	 */
	public BaseTower getTower()
	{
		return this.tower;
	}
	
	/**
	 * Setter for {@link #tower}
	 * @param tower {@link #tower}
	 */
	public void setTower(BaseTower tower)
	{
		this.tower = tower;
	}
	
	/**
	 * Removes the tower in this tile.
	 */
	public void removeTower()
	{
		this.tower = null;
		SceneController.getGamePane().getTilesPane().getTileCell(this.loc).update();
		SceneController.getGamePane().getTilesPane().getTileCell(this.loc).updateHealth();
	}
	
	/**
	 * Getter for {@link #tileOwner}
	 * @return {@link #tileOwner}
	 */
	public int getTileOwner() {
		return tileOwner;
	}

	/**
	 * Setter for {@link #tileOwner}
	 * @param tileOwner {@link #tileOwner}
	 */
	public void setTileOwner(int tileOwner) {
		this.tileOwner = tileOwner;
	}

	/**
	 * Getter for {@link #loc}
	 * @return {@link #loc}
	 */
	public Coordinate getLoc() {
		return loc;
	}

	/**
	 * Setter for {@link #loc}
	 * @param loc {@link #loc}
	 */
	public void setLoc(Coordinate loc) {
		this.loc = loc;
	}

	/**
	 * Returns whether or not this tile has its attack queued
	 * @return True if so, false otherwise
	 */
	public boolean isMarkAttacked() {
		return this.markAttacked;
	}

	/**
	 * Setter for {@link #markAttacked}
	 * @param markAttacked {@link #markAttacked}
	 */
	public void setMarkAttacked(boolean markAttacked) {
		this.markAttacked = markAttacked;
	}
	
	
	
	
	
	
	

}
