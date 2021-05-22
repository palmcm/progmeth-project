package exception;

/**
 * Exception called when attempting to upgrade a tower to a level which doesn't exist.
 *
 */
public class LevelOverflowException extends Exception {
	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for LevelOverflowException
	 * @param obj Tower
	 * @param level Invalid level
	 */
	public LevelOverflowException(String obj,int level){
		super("Error: Attempting to upgrade "+obj+" to level "+level+", but that level does not exist.");
	}

}