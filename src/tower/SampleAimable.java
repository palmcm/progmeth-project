package tower;

import exception.InvalidPlayerException;
import logic.attacks.GenericAttack;
import logic.misc.Coordinate;
import logic.towers.AimableTower;

public class SampleAimable extends AimableTower{
	
	public SampleAimable(Coordinate loc)
	{
		this.setCost(15);
		
		this.setMaxHealth(15);
		this.setUpgradeHealth(new int[]{5,10,15});
		this.setCurrentHealth(this.getMaxHealth());
		
		this.setMaxUpgradeLevel(3);
		this.setUpgradeCost(new int[]{10,20,25});
		
		this.setCooldown(2);
		this.setCurrentCooldown(1);
		
		this.setTarget(null);
		
		this.sethRange(new int[] {5,5,5,7});
		this.setvRange(new int[] {1,1,1,2});
		
		this.setLoc(loc);
		
		// ------------------ TOWER INFO/DESCRIPTION ------------------------
		
		this.setTowerName(new String[] {
				"Sample Missile",
				"Sample Missile II",
				"Sample Missile III",
				"Sample Missile IV"
		});
		
		this.setTowerDescription(new String[] {
				"Attacks a selected tile. Takes a turn to recharge.",
				"Attacks a selected tile. Takes a turn to recharge.",
				"Creates an explosion at the selected tile, damaging adjacent tiles. Takes a turn to recharge.",
				"Creates an explosion at the selected tile, damaging adjacent tiles. Takes a turn to recharge.",
		});
		
		this.setTowerDamage(new String[] {
				"4",
				"7",
				"7",
				"7"
		});
		
		this.setTowerRange(new String[] {
				"5 Tiles, Reaches adjacent lanes",
				"5 Tiles, Reaches adjacent lanes",
				"5 Tiles, Reaches adjacent lanes",
				"7 Tiles, Reaches 2 lanes above and below.",
		});	
		
		this.setTowerUpgradeDescription(new String[] {
				"Tower which can attack any targeted tower within its range, dealing 4 damage. Takes a turn to recharge.",
				"Increases attack damage by 3.",
				"Attacks damages adjacent tiles.",
				"Significantly increases tower's reach."
		});		
		
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
			GenericAttack.targetSquareExplosion(this.getTarget(), 7, this.getOwner(),1);
			break;
		case 3:
			GenericAttack.targetSquareExplosion(this.getTarget(), 7, this.getOwner(),1);
			break;		
			
		}
	}

}
