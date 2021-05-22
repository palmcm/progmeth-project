package logic.misc;

/**
 * A class which represents an X,Y coordinate.
 *
 */
public class Coordinate {
	
	private int x,y;
	
	// Getters
	
	/**
	 * Creates a new coordinate of 0,0
	 */
	public Coordinate() {
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * Creates a new coordinate of X,Y
	 * @param x
	 * @param y
	 */
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Setter for {@link #x}
	 * @param x {@link #x}
	 */
	public void setX(int x)
	{
		this.x = x;
	}
	
	/**
	 * Setter for {@link #y}
	 * @param y {@link #y}
	 */
	public void setY(int y)
	{
		this.y = y;
	}
	
	// Setters
	
	/**
	 * Getter for {@link #x}
	 * @return  {@link #x}
	 */
	public int getX()
	{
		return this.x;
	}
	
	/**
	 * Getter for {@link #y}
	 * @return  {@link #y}
	 */
	public int getY()
	{
		return this.y;
	}

}
