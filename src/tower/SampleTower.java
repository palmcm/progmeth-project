package tower;

import exception.InvalidPlayerException;
import logic.attacks.GenericAttack;
import logic.misc.Coordinate;
import logic.towers.AttackableTower;
import logic.towers.BaseTower;

public class SampleTower extends AttackableTower{
	
	public BaseTower getNewInstance(Coordinate loc)
	{
		return new SampleTower(loc,true);
	}
	
	public SampleTower(Coordinate loc,boolean isInstance)
	{
		this.setIsInstance(isInstance);
		
		this.setCost(10);
		
		this.setMaxHealth(5);
		this.setUpgradeHealth(new int[]{2,3,5});
		this.setCurrentHealth(this.getMaxHealth());
		
		this.setMaxUpgradeLevel(3);
		this.setUpgradeCost(new int[]{5,10,30});
		
		this.setCooldown(1);
		this.setCurrentCooldown(1);
		
		this.setLoc(loc);
		
		this.setUrl("towers/apprentice.png");
		
		// ------------------ TOWER INFO/DESCRIPTION ------------------------
		
		this.setTowerName(new String[] {
				"Sample Tower",
				"Sample Tower II",
				"Sample Tower III",
				"Sample Tower IV"
		});
		
		this.setTowerDescription(new String[] {
				"Fires a single projectile.",
				"Fires a single projectile.",
				"Fires a single projectile.",
				"Fires two projectiles in succession."
		});
		
		this.setTowerDamage(new String[] {
				"1",
				"1",
				"2",
				"2"
		});
		
		this.setTowerRange(new String[] {
				"6 Tiles, Single Lane",
				"8 Tiles, Single Lane",
				"8 Tiles, Single Lane",
				"8 Tiles, Single Lane"
		});	
		
		this.setTowerUpgradeDescription(new String[] {
				"Basic tower which fires a single projectile which deals 2 damage",
				"Increases projectile range by 2 tiles",
				"Deals +1 damage per projectile",
				"Fires twice per attack."
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
			GenericAttack.simpleProjectile(this.getLoc(), 3, 8, this.getOwner());
			GenericAttack.simpleProjectile(this.getLoc(), 3, 8, this.getOwner());
			break;		
			
		}
	}
	
	

}
