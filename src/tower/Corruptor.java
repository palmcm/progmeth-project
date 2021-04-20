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
		
		this.setCost(30);
		
		this.setMaxHealth(15);
		this.setUpgradeHealth(new int[]{3,5,7});
		this.setCurrentHealth(this.getMaxHealth());
		
		this.setMaxUpgradeLevel(3);
		this.setUpgradeCost(new int[]{20,20,30});
		
		this.setCooldown(1);
		
		this.setLoc(loc);
		
		this.setUrl("towers/corruptor.png");
		
		// ------------------ TOWER INFO/DESCRIPTION ------------------------
		
		this.setTowerName(new String[] {
				"Corruptor",
				"Corruptor II",
				"Corruptor III",
				"Corruptor IV"
		});
		
		this.setTowerDescription(new String[] {
				"Cast a short-ranged burst of dark energy.",
				"Cast a short-ranged burst of dark energy which all enemies in its range.",
				"Cast a short-ranged burst of dark energy which all enemies in its range.",
				"Cast a long-ranged wave of dark energy, followed by\nshort-ranged burst of dark energy which all enemies in its range.",
		});
		
		this.setTowerDamage(new String[] {
				"10",
				"10",
				"15",
				"5 (long-ranged) + 15 (short-ranged)"
		});
		
		this.setTowerRange(new String[] {
				"2",
				"3",
				"3",
				"11 (long-ranged), 3 (short-ranged)"
		});	
		
		this.setTowerRangeInt(new int[] {
				2,
				3,
				3,
				11
		});	
		
		this.setTowerUpgradeDescription(new String[] {
				"Casts a strong but short ranged burst of energy.",
				"Extends the range of the burst by 1 tile.\nThe burst will hit all enemies within its range.",
				"Deals +5 damage per burst.",
				"Casts a long-ranged wave of dark energy which\ndeals 8 damage before casting the short-ranged burst."
		});
		
		this.initialize();
		
	}

	// ------------- ATTACKING --------------------
	
	public void attack() throws InvalidPlayerException {
		switch(this.getUpgradeLevel()) {
		case 0:
			GenericAttack.simpleProjectile(this.getLoc(), 10, 3, this.getOwner());
			break;
		case 1:
			GenericAttack.piercingProjectile(this.getLoc(), 10, 3, 3, this.getOwner());
			break;
		case 2:
			GenericAttack.piercingProjectile(this.getLoc(), 15, 3, 3, this.getOwner());
			break;
		case 3:
			GenericAttack.simpleProjectile(this.getLoc(), 5, 11, this.getOwner());
			GenericAttack.piercingProjectile(this.getLoc(), 15, 3, 3, this.getOwner());
			break;
		}
	}
	
	

}
