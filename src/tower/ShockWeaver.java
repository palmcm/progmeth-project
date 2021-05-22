package tower;

import exception.InvalidPlayerException;
import logic.attacks.GenericAttack;
import logic.misc.Coordinate;
import logic.towers.AttackableTower;
import logic.towers.BaseTower;

/**
 * Shockweaver Unit
 */
public class ShockWeaver extends AttackableTower{
	
	public BaseTower getNewInstance(Coordinate loc)
	{
		return new ShockWeaver(loc,true);
	}
	
	public ShockWeaver(Coordinate loc,boolean isInstance)
	{
		this.setIsInstance(isInstance);
		
		this.setCost(40);
		
		this.setMaxHealth(25);
		this.setUpgradeHealth(new int[]{5,10,10});
		this.setCurrentHealth(this.getMaxHealth());
		
		this.setMaxUpgradeLevel(3);
		this.setUpgradeCost(new int[]{20,30,60});
		
		this.setCooldown(5);
		
		this.setLoc(loc);
		
		this.setUrl("towers/shockweaver.png");
		this.setAttackUrl("towers/shockweaver_attack.png");
		this.setDamageUrl("towers/shockweaver_damaged.png");
		
		// ------------------ TOWER INFO/DESCRIPTION ------------------------
		
		this.setTowerName(new String[] {
				"Shockweaver",
				"Shockweaver II",
				"Shockweaver III",
				"Shockweaver IV"
		});
		
		this.setTowerDescription(new String[] {
				"Casts a powerful shock of electricity",
				"Casts a powerful shock of electricity",
				"Casts a powerful shock of electricity",
				"Casts a devastating shock of electricity",
		});
		
		this.setTowerDamage(new String[] {
				"30",
				"50",
				"50",
				"999"
		});
		
		this.setTowerRange(new String[] {
				"7",
				"7",
				"11",
				"11"
		});	
		
		this.setTowerRangeInt(new int[] {
				7,
				7,
				11,
				11
		});	
		
		this.setTowerUpgradeDescription(new String[] {
				"Unleashes a powerful electric shock.\nTakes 4 turns to recharge.",
				"Increases damage by 20.",
				"Greatly increases the reach of the shock.",
				"The shock will obilterate anything it touches."
		});
		
		this.initialize();
		
	}

	// ------------- ATTACKING --------------------
	
	public void attack() throws InvalidPlayerException {
		switch(this.getUpgradeLevel()) {
		case 0:
			GenericAttack.simpleProjectile(this.getLoc(), 30, 7, this.getOwner());
			break;
		case 1:
			GenericAttack.simpleProjectile(this.getLoc(), 50, 7, this.getOwner());
			break;
		case 2:
			GenericAttack.simpleProjectile(this.getLoc(), 50, 11, this.getOwner());
			break;
		case 3:
			GenericAttack.simpleProjectile(this.getLoc(), 999, 11, this.getOwner());
			break;
		}
	}
	
	

}