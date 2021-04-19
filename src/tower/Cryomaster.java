package tower;

import exception.InvalidPlayerException;
import logic.attacks.GenericAttack;
import logic.misc.Coordinate;
import logic.towers.AimableTower;
import logic.towers.BaseTower;

public class Cryomaster extends AimableTower{
	
	public BaseTower getNewInstance(Coordinate loc)
	{
		return new Cryomaster(loc,true);
	}
	
	public Cryomaster(Coordinate loc,boolean isInstance)
	{
		this.setIsInstance(isInstance);
		
		this.setCost(15);
		
		this.setMaxHealth(8);
		this.setUpgradeHealth(new int[]{2,4,6});
		this.setCurrentHealth(this.getMaxHealth());
		
		this.setMaxUpgradeLevel(3);
		this.setUpgradeCost(new int[]{10,15,25});
		
		this.setCooldown(3);
		//this.setCurrentCooldown(1);
		
		this.setTarget(null);
		
		this.sethRange(new int[] {8,8,8,8});
		this.setvRange(new int[] {1,2,2,2});
		
		this.setLoc(loc);
		
		this.setUrl("towers/cryomaster.png");
		
		// ------------------ TOWER INFO/DESCRIPTION ------------------------
		
		this.setTowerName(new String[] {
				"Cryomaster",
				"Cryomaster II",
				"Cryomaster III",
				"Cryomaster IV"
		});
		
		this.setTowerDescription(new String[] {
				"Freezes a selected tile, disabling it in the next turn.",
				"Freezes a selected tile, disabling it in the next turn.",
				"Freezes a selected tile, disabling it in the next turn.",
				"Freezes a selected tile, disabling it in the next turn.",
		});
		
		this.setTowerDamage(new String[] {
				"1",
				"1",
				"10",
				"10"
		});
		
		this.setTowerUpgradeDescription(new String[] {
				"Can freeze a target, dealing light damage and disabling\n it in the next turn. Takes 2 turns to recharge.",
				"Can reach 1 more lane above and below.",
				"Increases damage by 9.",
				"Recharges 1 turn faster."
		});
		
		this.initialize();
		
	}
	
	// ------------- ATTACKING --------------------
	
	public void attack() throws InvalidPlayerException {
		switch(this.getUpgradeLevel()) {
		case 0:
			GenericAttack.tileDamage(this.getTarget(), 1, this.getOwner());
			GenericAttack.freeze(this.getTarget(), 1, this.getOwner());
			break;
		case 1:
			GenericAttack.tileDamage(this.getTarget(), 1, this.getOwner());
			GenericAttack.freeze(this.getTarget(), 1, this.getOwner());
			break;
		case 2:
			GenericAttack.tileDamage(this.getTarget(), 10, this.getOwner());
			GenericAttack.freeze(this.getTarget(), 1, this.getOwner());
			break;
		case 3:
			this.setCooldown(2);
			GenericAttack.tileDamage(this.getTarget(), 10, this.getOwner());
			GenericAttack.freeze(this.getTarget(), 1, this.getOwner());
			break;	
			
		}
	}

}