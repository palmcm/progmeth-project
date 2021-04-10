package logic.actions;

import logic.gmanager.GameManager;
import logic.misc.Coordinate;
import logic.towers.AttackableTower;
import logic.towers.BaseTower;

public class AttackAction extends AttackPhaseAction {
	
	private Coordinate trigger;
	
	public AttackAction(Coordinate loc)
	{
		this.trigger = loc;
	}
	
	public Coordinate getTrigger()
	{
		return this.trigger;
	}
	
	public void processAction()
	{
		BaseTower tower = GameManager.getGameInstance().getBoard().getTile(trigger).getTower();
		if(tower instanceof AttackableTower)
		{
			((AttackableTower) tower).doAttack();
		}
	}
	

}
