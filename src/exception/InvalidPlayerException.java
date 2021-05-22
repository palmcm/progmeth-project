package exception;

/**
 * Exception raised when an invalid player id is called.
 */
public class InvalidPlayerException extends Exception {
	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for InvalidPlayerException
	 * @param player Invalid player
	 */
	public InvalidPlayerException(int player){
		super("Error: Invalid Player (Specified Player: "+player+")");
	}

}
