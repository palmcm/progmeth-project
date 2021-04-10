package logic.gmanager;

import exception.InvalidPlayerException;
import logic.misc.Coordinate;

public class Board {
	
	private Tile tiles[][];
	private int lanes;
	private int border;
	
	public Board()
	{
		this.border = 11;
		this.lanes = 5;
		this.tiles = new Tile[5][12];
		this.setBorderColumns();
		
		int i,j;
		for(i=0;i<lanes;i++)
		{
			for(j=0;j<=border;j++)
			{
				this.tiles[i][j].setLoc(new Coordinate(i,j));
			}
		}
		
		for(i=1;i<=5;i++)
		{
			this.setOwnerColumn(1, i);
		}		
		for(i=6;i<=10;i++)
		{
			this.setOwnerColumn(2, i);
		}
	}
	
	public Board(int x, int y)
	{
		this.border = y+1;
		this.lanes = x;
		this.tiles = new Tile[x][y+2];
		
		int i;
		
		for(i=1;i<=y/2;i++)
		{
			this.setOwnerColumn(1, i);
		}		
		for(i=y/2+1;i<=y;i++)
		{
			this.setOwnerColumn(2, i);
		}
		
		
	}
	
	public void setBorderColumns()
	{
		int i;
		for(i=0;i<this.lanes;i++)
		{
			this.tiles[i][0].setTileOwner(0);
			this.tiles[i][this.getBorder()].setTileOwner(0);
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
	
	
	
	
	
	

}
