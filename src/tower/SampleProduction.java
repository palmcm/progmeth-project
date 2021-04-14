package tower;

import exception.InvalidPlayerException;
import logic.gmanager.GameManager;
import logic.misc.Coordinate;
import logic.towers.Passive;
import logic.towers.BaseTower;

public class SampleProduction extends BaseTower implements Passive{
	
	public BaseTower getNewInstance(Coordinate loc)
	{
		return new SampleProduction(loc,true);
	}
	

	
	public SampleProduction(Coordinate loc,boolean isInstance)
	{
		this.setIsInstance(isInstance);
		
		this.setCost(25);
		
		this.setMaxHealth(10);
		this.setUpgradeHealth(new int[]{5,10,15});
		this.setCurrentHealth(this.getMaxHealth());
		
		this.setMaxUpgradeLevel(3);
		this.setUpgradeCost(new int[]{15,20,40});
		
		this.setLoc(loc);
		
		this.setUrl("towers/scholar.png");
		
		// ------------------ TOWER INFO/DESCRIPTION ------------------------
		
		this.setTowerName(new String[] {
				"Scholar",
				"Scholar II",
				"Scholar III",
				"Scholar IV"
		});
		
		this.setTowerDescription(new String[] {
				"Produce 6 gold at the end of turn.",
				"Produce 10 gold at the end of turn.",
				"Produce 15 gold at the end of turn.",
				"Produce 25 gold at the end of turn.",
		});
		
		this.setTowerUpgradeDescription(new String[] {
				"Produces extra 6 gold at the end of each turn.",
				"Increases income by 4 per turn.",
				"Increases income by 5 per turn.",
				"Increases income by 10 per turn."
		});
		
		this.initialize();
		
	}

	public void doPassive() throws InvalidPlayerException {
		switch(this.getUpgradeLevel()) {
		case 0:
			GameManager.getGameInstance().getPlayer(this.getOwner()).gainMoney(6);
			break;
		case 1:
			GameManager.getGameInstance().getPlayer(this.getOwner()).gainMoney(10);
			break;
		case 2:
			GameManager.getGameInstance().getPlayer(this.getOwner()).gainMoney(15);
			break;
		case 3:
			GameManager.getGameInstance().getPlayer(this.getOwner()).gainMoney(25);
			break;		
			
		}
		
	}
	

}
