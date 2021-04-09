package logic.gmanager;

import exception.InvalidPlayerException;
import logic.misc.Coordinate;

public class Board {
	
	private Tile tiles[][];
	
	private int border;
	
	public int getBorder(int player) throws InvalidPlayerException
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
	
	
	
	
	
	

}
