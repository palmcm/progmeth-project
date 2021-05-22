package exception;

/**
 * Exception raised when an action from clicking a tile is invalid/impossible.
 *
 */
public class SelectInvalidTileException extends Exception {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SelectInvalidTileException(String message) {
		super(message);
	}
	
	public SelectInvalidTileException() {
	}

}
