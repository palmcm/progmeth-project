package tower;

import exception.InvalidPlayerException;
import logic.gmanager.GameManager;
import logic.misc.Coordinate;
import logic.towers.Passive;
import logic.towers.BaseTower;

public class SampleProduction extends BaseTower implements Passive{
	

	
	public SampleProduction(Coordinate loc)
	{
		this.setCost(20);
		
		this.setMaxHealth(10);
		this.setUpgradeHealth(new int[]{10,15,25});
		this.setCurrentHealth(this.getMaxHealth());
		
		this.setMaxUpgradeLevel(3);
		this.setUpgradeCost(new int[]{10,50,150});
		
		// ------------------ TOWER INFO/DESCRIPTION ------------------------
		
		this.setTowerName(new String[] {
				"Sample Mine",
				"Sample Mine II",
				"Sample Mine III",
				"Sample Mine IV"
		});
		
		this.setTowerDescription(new String[] {
				"Produce 4 gold at the end of turn.",
				"Produce 6 gold at the end of turn.",
				"Produce 10 gold at the end of turn.",
				"Produce 20 gold at the end of turn.",
		});
		
		this.setTowerUpgradeDescription(new String[] {
				"Produces extra 4 gold at the end of each turn.",
				"Increases income by 2 per turn.",
				"Increases income by 4 per turn.",
				"Increases income by 10 per turn."
		});		
		
	}

	public void doPassive() throws InvalidPlayerException {
		switch(this.getUpgradeLevel()) {
		case 0:
			GameManager.getGameInstance().getPlayer(this.getOwner()).gainMoney(4);
			break;
		case 1:
			GameManager.getGameInstance().getPlayer(this.getOwner()).gainMoney(6);
			break;
		case 2:
			GameManager.getGameInstance().getPlayer(this.getOwner()).gainMoney(10);
			break;
		case 3:
			GameManager.getGameInstance().getPlayer(this.getOwner()).gainMoney(20);
			break;		
			
		}
		
	}
	

}
