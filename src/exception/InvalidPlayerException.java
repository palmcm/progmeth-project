package exception;

/**
 * Exception raised when an invalid player id is called.
 *
 */
public class InvalidPlayerException extends Exception {
	
	public InvalidPlayerException(int player){
		super("Error: Invalid Player (Specified Player: "+player+")");
	}

}
