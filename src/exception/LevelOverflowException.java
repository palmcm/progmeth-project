package exception;

public class LevelOverflowException extends Exception {
	
	public LevelOverflowException(String obj,int level){
		super("Error: Attempting to upgrade "+obj+" to level "+level+", but that level does not exist.");
	}

}