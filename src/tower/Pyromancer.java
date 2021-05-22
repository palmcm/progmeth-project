package tower;

import exception.InvalidPlayerException;
import logic.attacks.GenericAttack;
import logic.misc.Coordinate;
import logic.towers.AimableTower;
import logic.towers.BaseTower;

/**
 * Pyromancer Unit
 */
public class Pyromancer extends AimableTower{
	
	public BaseTower getNewInstance(Coordinate loc)
	{
		return new Pyromancer(loc,true);
	}
	
	/**
	 * Constructor for Pyromancer
	 * @param loc location of tower
	 * @param isInstance whether that its an instance
	 */
	public Pyromancer(Coordinate loc,boolean isInstance)
	{
		this.setIsInstance(isInstance);
		
		this.setCost(20);
		
		this.setMaxHealth(15);
		this.setUpgradeHealth(new int[]{3,5,7});
		this.setCurrentHealth(this.getMaxHealth());
		
		this.setMaxUpgradeLevel(3);
		this.setUpgradeCost(new int[]{15,25,50});
		
		this.setCooldown(2);
		//this.setCurrentCooldown(1);
		
		this.setTarget(null);
		
		this.sethRange(new int[] {7,7,9,9});
		this.setvRange(new int[] {1,1,2,2});

		this.sethRadius(new int[]{0,0,0,1});
		this.setvRadius(new int[]{0,0,0,1});
		
		this.setLoc(loc);
		
		this.setUrl("towers/pyromancer.png");
		this.setAttackUrl("towers/pyromancer_attack.png");
		this.setDamageUrl("towers/pyromancer_damaged.png");
		
		// ------------------ TOWER INFO/DESCRIPTION ------------------------
		
		this.setTowerName(new String[] {
				"Pyromancer",
				"Pyromancer II",
				"Pyromancer III",
				"Pyromancer IV"
		});
		
		this.setTowerDescription(new String[] {
				"Attacks a selected tile. Takes a turn to recharge.",
				"Attacks a selected tile. Takes a turn to recharge.",
				"Attacks a selected tile. Takes a turn to recharge.",
				"Creates an explosion at the selected tile, \ndamaging adjacent tiles. Takes a turn to recharge.",
		});
		
		this.setTowerDamage(new String[] {
				"4",
				"7",
				"7",
				"7"
		});
		
		this.setTowerUpgradeDescription(new String[] {
				"Can attack any targeted unit within his range.\nTakes a turn to recharge.",
				"Increases attack damage by 3.",
				"Significantly increases tower's reach.",
				"Attacks damages adjacent tiles."
		});
		
		this.initialize();
		
	}
	
	// ------------- ATTACKING --------------------
	
	public void attack() throws InvalidPlayerException {
		switch(this.getUpgradeLevel()) {
		case 0:
			GenericAttack.tileDamage(this.getTarget(), 4, this.getOwner());
			break;
		case 1:
			GenericAttack.tileDamage(this.getTarget(), 7, this.getOwner());
			break;
		case 2:
			GenericAttack.tileDamage(this.getTarget(), 7, this.getOwner());
			break;
		case 3:
			GenericAttack.targetSquareExplosion(this.getTarget(), 7, this.getOwner(),1);
			break;		
			
		}
	}

}
