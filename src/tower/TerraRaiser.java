package tower;

import logic.misc.Coordinate;
import logic.towers.BaseTower;

/**
 * TerraRaiser Unit
 */
public class TerraRaiser extends BaseTower{
	
	public BaseTower getNewInstance(Coordinate loc)
	{
		return new TerraRaiser(loc,true);
	}
	

	/**
	 * Constructor for TerraRaiser
	 * @param loc location of tower
	 * @param isInstance whether that its an instance
	 */
	public TerraRaiser(Coordinate loc,boolean isInstance)
	{
		this.setIsInstance(isInstance);
		
		this.setCost(10);
		
		this.setMaxHealth(10);
		this.setUpgradeHealth(new int[]{5,10,15});
		this.setCurrentHealth(this.getMaxHealth());
		
		this.setMaxUpgradeLevel(3);
		this.setUpgradeCost(new int[]{10,15,20});
		
		this.setLoc(loc);
		
		this.setUrl("towers/terraraiser.png");
		this.setDamageUrl("towers/terraraiser_damaged.png");
		
		// ------------------ TOWER INFO/DESCRIPTION ------------------------
		
		this.setTowerName(new String[] {
				"Terraraiser",
				"Terraraiser II",
				"Terraraiser III",
				"Terraraiser IV"
		});
		
		this.setTowerDescription(new String[] {
				"Creates a sturdy wall which absorbs damage.",
				"Creates a sturdy wall which absorbs damage.",
				"Creates a sturdy wall which absorbs damage.",
				"Creates a sturdy wall which absorbs damage.",
		});
		
		this.setTowerUpgradeDescription(new String[] {
				"Raises a wall from earth, protecting towers behind it.",
				"Increases toughness of the wall.",
				"Greatly increases toughness of the wall.",
				"Massively increases toughness of the wall.",
		});
		
		this.initialize();
		
	}
	

}
