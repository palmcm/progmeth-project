package logic.towers;

import exception.InvalidPlayerException;

/**
 * Interface for towers which may perform actions at the end of a turn.
 *
 */
public interface Passive {
	
	/**
	 * Action to perform at the end of the turn
	 * @throws InvalidPlayerException
	 */
	public void doPassive() throws InvalidPlayerException;

}
