package tower;

import exception.InvalidPlayerException;
import logic.gmanager.GameManager;
import logic.misc.Coordinate;
import logic.towers.Passive;
import utils.CommonStrings;
import logic.towers.BaseTower;

/**
 * Scholar Unit
 */
public class Scholar extends BaseTower implements Passive{
	
	public BaseTower getNewInstance(Coordinate loc)
	{
		return new Scholar(loc,true);
	}
	

	/**
	 * Constructor for Scholar
	 * @param loc location of tower
	 * @param isInstance whether that its an instance
	 */
	public Scholar(Coordinate loc,boolean isInstance)
	{
		this.setIsInstance(isInstance);
		
		this.setCost(25);
		
		this.setMaxHealth(10);
		this.setUpgradeHealth(new int[]{3,5,7});
		this.setCurrentHealth(this.getMaxHealth());
		
		this.setMaxUpgradeLevel(3);
		this.setUpgradeCost(new int[]{15,20,40});
		
		this.setLoc(loc);
		
		this.setUrl("towers/scholar.png");
		this.setAttackUrl("towers/scholar_attack.png");
		this.setDamageUrl("towers/scholar_damaged.png");
		
		// ------------------ TOWER INFO/DESCRIPTION ------------------------
		
		this.setTowerName(new String[] {
				"Scholar",
				"Scholar II",
				"Scholar III",
				"Scholar IV"
		});
		
		this.setTowerDescription(new String[] {
				"Researches 5"+CommonStrings.currency_symbol+" at the end of turn.",
				"Researches 8"+CommonStrings.currency_symbol+" at the end of turn.",
				"Researches 12"+CommonStrings.currency_symbol+" intellect at the end of turn.",
				"Researches 20"+CommonStrings.currency_symbol+" intellect at the end of turn.",
		});
		
		this.setTowerUpgradeDescription(new String[] {
				"Researches extra 5"+CommonStrings.currency_symbol+" at the end of each turn.",
				"Increases research by 3 per turn.",
				"Increases research by 4 per turn.",
				"Increases research by 8 per turn."
		});
		
		this.initialize();
		
	}

	public void doPassive() throws InvalidPlayerException {
		if(this.isFrozen())
			return;
		switch(this.getUpgradeLevel()) {
		case 0:
			GameManager.getGameInstance().getPlayer(this.getOwner()).gainMoney(5);
			break;
		case 1:
			GameManager.getGameInstance().getPlayer(this.getOwner()).gainMoney(8);
			break;
		case 2:
			GameManager.getGameInstance().getPlayer(this.getOwner()).gainMoney(12);
			break;
		case 3:
			GameManager.getGameInstance().getPlayer(this.getOwner()).gainMoney(20);
			break;		
			
		}
		
	}
	

}
