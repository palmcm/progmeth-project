package exception;

public class InvalidPlayerException extends Exception {
	
	public InvalidPlayerException(int player){
		super("Error: Invalid Player (Specified Player: "+player+")");
	}

}
