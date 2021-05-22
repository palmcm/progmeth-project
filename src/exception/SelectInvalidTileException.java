package exception;

/**
 * Exception raised when an action from clicking a tile is invalid/impossible.
 *
 */
public class SelectInvalidTileException extends Exception {

	 /**
	 * Serial version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for SelectInvalidTileException
	 * @param message Reason of invalid
	 */
	public SelectInvalidTileException(String message) {
		super(message);
	}
	
	/**
	 * Constructor for SelectInvalidTileException
	 */
	public SelectInvalidTileException() {
	}

}
