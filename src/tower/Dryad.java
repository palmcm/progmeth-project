package tower;

import exception.InvalidPlayerException;
import logic.attacks.GenericAttack;
import logic.misc.Coordinate;
import logic.towers.AimableTower;
import logic.towers.BaseTower;

public class Dryad extends AimableTower{
	
	public BaseTower getNewInstance(Coordinate loc)
	{
		return new Dryad(loc,true);
	}
	
	public Dryad(Coordinate loc,boolean isInstance)
	{
		super();
		this.setIsInstance(isInstance);
		
		this.setCost(20);
		
		this.setMaxHealth(10);
		this.setUpgradeHealth(new int[]{2,4,4});
		this.setCurrentHealth(this.getMaxHealth());
		
		this.setMaxUpgradeLevel(3);
		this.setUpgradeCost(new int[]{10,20,40});
		
		this.setCooldown(1);
		//this.setCurrentCooldown(1);
		
		this.setTarget(null);
		
		this.sethRange(new int[] {1,2,2,5});
		this.setvRange(new int[] {1,2,2,5});
		
		this.setLoc(loc);
		
		this.setUrl("towers/dryad.png");
		this.setAttackUrl("towers/dryad_attack.png");
		
		// ------------------ TOWER INFO/DESCRIPTION ------------------------
		
		this.setTowerName(new String[] {
				"Dryad",
				"Dryad II",
				"Dryad III",
				"Dryad IV"
		});
		
		this.setTowerDescription(new String[] {
				"Heals a targeted tower.",
				"Heals a targeted tower.",
				"Heals a targeted tower.",
				"Heals a targeted tower.",
		});
		
		this.setTowerDamage(new String[] {
				"2",
				"2",
				"4",
				"4"
		});
		
		this.setTowerUpgradeDescription(new String[] {
				"Heals a targeted unit within its range.",
				"Increases reach by 1 tile in all directions.",
				"Doubles the healing amount.",
				"Massively increases healing reach."
		});
		
		this.initialize();
		
	}
	
	// ------------- ATTACKING --------------------
	
	public void attack() throws InvalidPlayerException {
		switch(this.getUpgradeLevel()) {
		case 0:
			GenericAttack.heal(this.getTarget(), 2, this.getOwner());
			break;
		case 1:
			GenericAttack.heal(this.getTarget(), 2, this.getOwner());
			break;
		case 2:
			GenericAttack.heal(this.getTarget(), 4, this.getOwner());
			break;
		case 3:
			GenericAttack.heal(this.getTarget(), 4, this.getOwner());
			break;		
			
		}
	}

}
