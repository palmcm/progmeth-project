package tower;

import exception.InvalidPlayerException;
import logic.attacks.GenericAttack;
import logic.misc.Coordinate;
import logic.towers.AttackableTower;
import logic.towers.BaseTower;

public class Corruptor extends AttackableTower{
	
	public BaseTower getNewInstance(Coordinate loc)
	{
		return new Corruptor(loc,true);
	}
	
	public Corruptor(Coordinate loc,boolean isInstance)
	{
		this.setIsInstance(isInstance);
		
		this.setCost(25);
		
		this.setMaxHealth(15);
		this.setUpgradeHealth(new int[]{5,10,15});
		this.setCurrentHealth(this.getMaxHealth());
		
		this.setMaxUpgradeLevel(3);
		this.setUpgradeCost(new int[]{5,10,30});
		
		this.setCooldown(1);
		
		this.setLoc(loc);
		
		this.setUrl("towers/Corruptor.png");
		
		// ------------------ TOWER INFO/DESCRIPTION ------------------------
		
		this.setTowerName(new String[] {
				"Corruptor",
				"Corruptor II",
				"Corruptor III",
				"Corruptor IV"
		});
		
		this.setTowerDescription(new String[] {
				"Fires a single projectile.",
				"Fires a single projectile.",
				"Fires a single projectile.",
				"Fires a single projectile which pierces an enemy."
		});
		
		this.setTowerDamage(new String[] {
				"2",
				"2",
				"3",
				"3"
		});
		
		this.setTowerRange(new String[] {
				"6",
				"8",
				"8",
				"8"
		});	
		
		this.setTowerUpgradeDescription(new String[] {
				"Casts a simple magical bolt.",
				"Increases projectile range by 2 tiles",
				"Deals +1 damage per projectile",
				"Attack pierces one enemy."
		});
		
		this.initialize();
		
	}

	// ------------- ATTACKING --------------------
	
	public void attack() throws InvalidPlayerException {
		switch(this.getUpgradeLevel()) {
		case 0:
			GenericAttack.simpleProjectile(this.getLoc(), 2, 6, this.getOwner());
			break;
		case 1:
			GenericAttack.simpleProjectile(this.getLoc(), 2, 8, this.getOwner());
			break;
		case 2:
			GenericAttack.simpleProjectile(this.getLoc(), 3, 8, this.getOwner());
			break;
		case 3:
			GenericAttack.piercingProjectile(this.getLoc(), 3, 8, 2, this.getOwner());
			break;
		}
	}
	
	

}
