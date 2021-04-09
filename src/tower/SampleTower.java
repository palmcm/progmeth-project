package tower;

import exception.InvalidPlayerException;
import logic.attacks.GenericAttack;
import logic.misc.Coordinate;
import logic.towers.Attacker;
import logic.towers.Tower;

public class SampleTower extends Tower implements Attacker{
	
	public SampleTower(Coordinate loc)
	{
		this.setCost(10);
		
		this.setMaxHealth(5);
		this.setUpgradeHealth(new int[]{2,3,5});
		this.setCurrentHealth(this.getMaxHealth());
		
		this.setMaxUpgradeLevel(3);
		this.setUpgradeCost(new int[]{5,10,30});
		
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
				"6",
				"8",
				"8",
				"8"
		});		
		
	}

	@Override
	public void attack() throws InvalidPlayerException {
		// TODO Auto-generated method stub
		switch(this.getUpgradeLevel()) {
		case 0:
			GenericAttack.simpleProjectile(this.getLoc(), 1, 6, this.getOwner());
			break;
		case 1:
			GenericAttack.simpleProjectile(this.getLoc(), 1, 8, this.getOwner());
			break;
		case 2:
			GenericAttack.simpleProjectile(this.getLoc(), 2, 8, this.getOwner());
			break;
		case 3:
			GenericAttack.simpleProjectile(this.getLoc(), 2, 8, this.getOwner());
			GenericAttack.simpleProjectile(this.getLoc(), 2, 8, this.getOwner());
			break;
			
			
		}
			
		
		
	}

}
