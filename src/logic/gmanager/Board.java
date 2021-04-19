package logic.gmanager;

import exception.InvalidPlayerException;
import logic.misc.Coordinate;

public class Board {
	
	private Tile tiles[][];
	private int lanes;
	private int border;
	
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
	
	private void initTiles(int lane, int col) {
		this.tiles = new Tile[lane][col+2];
		for(int i=0;i<lane;i++) {
			for(int j=0;j<col+2;j++) {
				this.tiles[i][j] = new Tile();
				this.tiles[i][j].setLoc(new Coordinate(i,j));
			}
		}
	}
	
	public void setBorderColumns()
	{
		int i;
		for(i=0;i<this.lanes;i++)
		{
			this.tiles[i][0].setTileOwner(0);
			this.tiles[i][this.getBorder()-1].setTileOwner(0);
		}
		
	}
	
	public void setOwnerColumn(int owner,int column)
	{
		int i;
		for(i=0;i<this.lanes;i++)
		{
			this.tiles[i][column].setTileOwner(owner);
		}
		
	}
	
	public int getBorder() {
		return border;
	}

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
	
	public Tile getTile(Coordinate loc)
	{
		return this.tiles[loc.getX()][loc.getY()];
	}
	
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

	public int getLanes() {
		return lanes;
	}

	public Tile[][] getTiles() {
		return tiles;
	}
	
	
	
	
	
	
	
	

}
