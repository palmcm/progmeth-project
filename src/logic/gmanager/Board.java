package logic.gmanager;

import exception.InvalidPlayerException;
import logic.misc.Coordinate;

/**
 * A class which represents the whole board of a game.
 *
 */
public class Board {
	
	private Tile tiles[][];
	private int lanes;
	private int border;
	
	/**
	 * Creates a new board of 5x11
	 */
	public Board()
	{
		this.border = 13;
		this.lanes = 5;
		initTiles(5,11);
		
		
		this.setBorderColumns();
		
		int i,j;
		
		for(i=1;i<=5;i++)
		{
			this.setOwnerColumn(1, i);
		}		
		for(i=7;i<=11;i++)
		{
			this.setOwnerColumn(2, i);
		}
	}
	
	/**
	 * Creates a new board with specific size of x*y
	 * @param x
	 * @param y
	 */
	public Board(int x, int y)
	{
		this.border = y+1;
		this.lanes = x;
		initTiles(x,y);
		
		int i;
		
		for(i=1;i<=y/2;i++)
		{
			this.setOwnerColumn(1, i);
		}		
		for(i=y/2+2;i<=y+1;i++)
		{
			this.setOwnerColumn(2, i);
		}
		
		
	}
	
	/**
	 * Sets the initial value of each tiles for a board size.
	 * @param lane
	 * @param col
	 */
	private void initTiles(int lane, int col) {
		this.tiles = new Tile[lane][col+2];
		for(int i=0;i<lane;i++) {
			for(int j=0;j<col+2;j++) {
				this.tiles[i][j] = new Tile();
				this.tiles[i][j].setLoc(new Coordinate(i,j));
			}
		}
	}
	
	/**
	 * Set the left and right border column to unplaceable tiles.
	 */
	public void setBorderColumns()
	{
		int i;
		for(i=0;i<this.lanes;i++)
		{
			this.tiles[i][0].setTileOwner(0);
			this.tiles[i][this.getBorder()-1].setTileOwner(0);
		}
		
	}
	
	/**
	 * Sets tile owner for a whole column.
	 * @param owner
	 * @param column
	 */
	public void setOwnerColumn(int owner,int column)
	{
		int i;
		for(i=0;i<this.lanes;i++)
		{
			this.tiles[i][column].setTileOwner(owner);
		}
		
	}
	
	/**
	 * Get the {@link #border} of the board.
	 * @return
	 */
	public int getBorder() {
		return border;
	}

	/**
	 * Get the border column index of a player.
	 * @param player
	 * @return index
	 * @throws InvalidPlayerException
	 */
	public int getPlayerBorder(int player) throws InvalidPlayerException
	{
		if(player == 1)
		{
			return 0;
		}
		
		else if(player == 2)
		{
			return this.border;
		}
		
		else throw new InvalidPlayerException(player);
	}
	
	/**
	 * Getter for a tile in a specific location
	 * @param loc the location to query
	 * @return Tile at that location.
	 */
	public Tile getTile(Coordinate loc)
	{
		return this.tiles[loc.getX()][loc.getY()];
	}
	
	/**
	 * Checks if a tile at x,y exists
	 * @param x
	 * @param y
	 * @return True if it does, false otherwise
	 */
	public boolean checkTile(int x,int y)
	{
		if(x < 0 || y < 0 || x >= this.lanes || y >= this.getBorder() )
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	/**
	 * Getter for {@link #lanes}
	 * @return {@link #lanes}
	 */
	public int getLanes() {
		return lanes;
	}
	
	/**
	 * Getter for all tiles on boards
	 * @return {@link #tiles}
	 */
	public Tile[][] getTiles() {
		return tiles;
	}
	
	
	
	
	
	
	
	

}
