package logic.actions;

import logic.gmanager.GameManager;
import logic.misc.Coordinate;
import logic.towers.AttackableTower;
import logic.towers.BaseTower;

/**
 * A class which represents an attack action of a unit.
 *
 */
public class AttackAction extends AttackPhaseAction {
	
	private Coordinate trigger;
	
	/**
	 * Creates a new attack action from a coordinate
	 */
	public AttackAction(Coordinate loc)
	{
		this.trigger = loc;
	}
	
	/**
	 * Getter for the action's tile location.
	 * @return {@link #trigger}
	 */
	public Coordinate getTrigger()
	{
		return this.trigger;
	}
	
	/**
	 * Make the unit at {@link #trigger} cast its spell
	 */
	public void processAction()
	{
		BaseTower tower = GameManager.getGameInstance().getBoard().getTile(trigger).getTower();
		if(tower instanceof AttackableTower)
		{
			((AttackableTower) tower).doAttack();
		}
	}
	

}
