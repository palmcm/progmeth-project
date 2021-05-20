package tower;

import exception.InvalidPlayerException;
import logic.attacks.GenericAttack;
import logic.misc.Coordinate;
import logic.towers.AttackableTower;
import logic.towers.BaseTower;

public class Windcaller extends AttackableTower{
	
	public BaseTower getNewInstance(Coordinate loc)
	{
		return new Windcaller(loc,true);
	}
	
	public Windcaller(Coordinate loc,boolean isInstance)
	{
		this.setIsInstance(isInstance);
		
		this.setCost(15);
		
		this.setMaxHealth(7);
		this.setUpgradeHealth(new int[]{3,5,10});
		this.setCurrentHealth(this.getMaxHealth());
		
		this.setMaxUpgradeLevel(3);
		this.setUpgradeCost(new int[]{5,10,50});
		
		this.setCooldown(1);
		
		this.setLoc(loc);
		
		this.setUrl("towers/Windcaller.png");
		this.setAttackUrl("towers/Windcaller_attack.png");
		
		// ------------------ TOWER INFO/DESCRIPTION ------------------------
		
		this.setTowerName(new String[] {
				"Windcaller",
				"Windcaller II",
				"Windcaller III",
				"Windcaller IV"
		});
		
		this.setTowerDescription(new String[] {
				"Rapidly summons wind currents, each dealing damage to the first enemy it hits.",
				"Rapidly summons wind currents, each dealing damage to the first enemy it hits.",
				"Rapidly summons wind currents, each dealing damage to the first enemy it hits.",
				"Rapidly summons three bursts of wind currents, one on his lane\nand the others on the lane above and below him.",
		});
		
		this.setTowerDamage(new String[] {
				"1 (x3)",
				"1 (x4)",
				"1 (x6)",
				"1 (x6)"
		});
		
		this.setTowerRange(new String[] {
				"5",
				"6",
				"8",
				"8"
		});	
		
		this.setTowerRangeInt(new int[] {
				5,
				6,
				8,
				8
		});	
		
		this.setTowerUpgradeDescription(new String[] {
				"Summons many wind currents which deals damage multiple times.",
				"Summons an extra wind current and slightly increases reach.",
				"Summons two more wind currents and further increases reach.",
				"Summons the same amount of current on adjacent lanes."
		});
		
		this.initialize();
		
	}

	// ------------- ATTACKING --------------------
	
	public void attack() throws InvalidPlayerException {
		int i;
		switch(this.getUpgradeLevel()) {
		case 0:
			for(i=0;i<3;i++)
				GenericAttack.simpleProjectile(this.getLoc(), 1, 5, this.getOwner());
			break;
		case 1:
			for(i=0;i<4;i++)
				GenericAttack.simpleProjectile(this.getLoc(), 1, 6, this.getOwner());
			break;
		case 2:
			for(i=0;i<6;i++)
				GenericAttack.simpleProjectile(this.getLoc(), 1, 8, this.getOwner());
			break;
		case 3:
			int cX = this.getLoc().getX();
			int cY = this.getLoc().getY();
			for(i=0;i<6;i++)
			{
				GenericAttack.simpleProjectile(this.getLoc(), 1, 8, this.getOwner());
				GenericAttack.simpleProjectile(new Coordinate(cX-1,cY), 1, 8, this.getOwner());
				GenericAttack.simpleProjectile(new Coordinate(cX+1,cY), 1, 8, this.getOwner());
			}
			break;
		}
	}
	
	

}