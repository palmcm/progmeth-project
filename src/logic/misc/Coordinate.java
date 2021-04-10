package logic.misc;

public class Coordinate {
	
	private int x,y;
	
	// Getters
	
	public Coordinate() {
		this.x = 0;
		this.y = 0;
	}
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	// Setters
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}

}
