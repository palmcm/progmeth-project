package logic.actions;

/**
 * Abstract class for any actions which can be queued in the attacking phase
 *
 */
public abstract class AttackPhaseAction {
	
	/**
	 * Method to run when the action is processed
	 */
	public abstract void processAction();	
}
