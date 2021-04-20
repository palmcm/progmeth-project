package tower;

import exception.InvalidPlayerException;
import logic.attacks.GenericAttack;
import logic.misc.Coordinate;
import logic.towers.AttackableTower;
import logic.towers.BaseTower;

public class Illuminant extends AttackableTower{
	
	public BaseTower getNewInstance(Coordinate loc)
	{
		return new Illuminant(loc,true);
	}
	
	public Illuminant(Coordinate loc,boolean isInstance)
	{
		this.setIsInstance(isInstance);
		
		this.setCost(25);
		
		this.setMaxHealth(15);
		this.setUpgradeHealth(new int[]{3,5,7});
		this.setCurrentHealth(this.getMaxHealth());
		
		this.setMaxUpgradeLevel(3);
		this.setUpgradeCost(new int[]{15,30,80});
		
		this.setCooldown(1);
		
		this.setLoc(loc);
		
		this.setUrl("towers/illuminant.png");
		
		// ------------------ TOWER INFO/DESCRIPTION ------------------------
		
		this.setTowerName(new String[] {
				"Illuminant",
				"Illuminant II",
				"Illuminant III",
				"Illuminant IV"
		});
		
		this.setTowerDescription(new String[] {
				"Casts a beam of light which penetrates an enemy unit.",
				"Casts a beam of light which penetrates two enemy units.",
				"Casts a beam of light which penetrates two enemy units.",
				"Casts a beam of light which penetrates four enemy units.",
		});
		
		this.setTowerDamage(new String[] {
				"3",
				"3",
				"5",
				"7"
		});
		
		this.setTowerRange(new String[] {
				"4",
				"6",
				"6",
				"8"
		});	
		
		this.setTowerUpgradeDescription(new String[] {
				"Casts a beam of light which goes through an enemy,\ndamaging it and another unit behind.",
				"Beam penetrates an extra enemy and reaches further.",
				"The beam deals +2 more damage.",
				"Increases the range of the beam, making it deal +2 damage\nand penetrates 2 more enemy units."
		});
		
		this.initialize();
		
	}

	// ------------- ATTACKING --------------------
	
	public void attack() throws InvalidPlayerException {
		switch(this.getUpgradeLevel()) {
		case 0:
			GenericAttack.piercingProjectile(this.getLoc(), 3, 4, 2, this.getOwner());
			break;
		case 1:
			GenericAttack.piercingProjectile(this.getLoc(), 3, 6, 3, this.getOwner());
			break;
		case 2:
			GenericAttack.piercingProjectile(this.getLoc(), 5, 6, 3, this.getOwner());
			break;
		case 3:
			GenericAttack.piercingProjectile(this.getLoc(), 7, 8, 5, this.getOwner());
			break;
		}
	}
	
	

}