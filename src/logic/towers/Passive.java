package logic.towers;

import exception.InvalidPlayerException;

public interface Passive {
	
	/**
	 * Action to perform at the end of the turn
	 * @throws InvalidPlayerException
	 */
	public void doPassive() throws InvalidPlayerException;

}
