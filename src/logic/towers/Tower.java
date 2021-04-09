package logic.towers;

import exception.InvalidPlayerException;
import logic.misc.Coordinate;

public abstract class Tower {
	
	// ** Fields **
	
	// Health Related
	
	private int currentHealth;
	private int maxHealth;
	private int maxUpgradeLevel;
	
	private int[] upgradeHealth; // Defines increased health for each upgrade
	
	// Upgrade Related
	
	private int upgradeLevel;
	private int cost;
	private int[] upgradeCost;
	
	// Board Related
	
	private int owner;
	private Coordinate loc;
	
	// Other Infos
	
	private String[] towerName;
	private String[] towerDescription;
	private String[] towerRange;
	private String[] towerDamage;
	
	private String currentName;
	private String currentDescription;
	private String currentRange;
	private String currentDamage;
	
	// ** Abstract Methods **
	
	//public abstract boolean onUpgrade();
	//public abstract void onSell();
	
	//public abstract String getUpgradeDetails();
	
	//public abstract void destroy();
	
	// ** Methods **
	
	public boolean upgrade()
	{
		if(this.getUpgradeLevel() < this.getMaxUpgradeLevel())
		{
			this.setUpgradeLevel(this.getUpgradeLevel()+1);
			this.updateInfo();
			
			int newLevel = this.getUpgradeLevel();
			this.setMaxHealth(this.getUpgradeHealth(newLevel));
			this.heal(this.getUpgradeHealth(newLevel));
			
			return true;
			
		}
		else
		{
			return false;
		}
	}
	
	public void updateInfo()
	{
		int level = this.getUpgradeLevel();
		this.setCurrentDescription(this.getTowerDescription(level));
		this.setCurrentName(this.getTowerName(level));
		this.setCurrentDamage(this.getTowerDamage(level));
		this.setCurrentDescription(this.getTowerDescription(level));
	}
	
	public boolean isActive()
	{
		return this.currentHealth > 0;
	}
	
	public void damage(int dmg)
	{
		this.setCurrentHealth(this.getCurrentHealth()-dmg);
	}
	
	public void heal(int heal)
	{
		this.setCurrentHealth(this.getCurrentHealth()+heal);
	}
	
	public boolean attackable(int player) throws InvalidPlayerException
	{
		if(player==3)
			return true;
		else return player!=this.getOwner();
	}
	
	public Coordinate getLoc()
	{
		return this.loc;
	}
	
	public int getCurrentHealth() {
		return currentHealth;
	}
	
	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
		if(this.currentHealth < 0)
			this.currentHealth = 0;
		else if(this.currentHealth > this.getMaxHealth())
			this.currentHealth = this.getMaxHealth();
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	public int getUpgradeHealth(int level) {
		return upgradeHealth[level];
	}
	
	public void setUpgradeHealth(int[] upgradeHealth) {
		this.upgradeHealth = upgradeHealth;
	}
	
	public int getUpgradeLevel() {
		return upgradeLevel;
	}
	
	public void setUpgradeLevel(int upgradeLevel) {
		this.upgradeLevel = upgradeLevel;
	}
	
	public int getOwner() {
		return owner;
	}
	
	public void setOwner(int owner) {
		this.owner = owner;
	}
	
	public String getTowerName(int level) {
		return towerName[level];
	}
	
	
	public void setTowerName(String[] towerName) {
		this.towerName = towerName;
	}
	
	public String getTowerDescription(int level) {
		return towerDescription[level];
	}
	
	public void setTowerDescription(String[] towerDescription) {
		this.towerDescription = towerDescription;
	}
	
	public String getCurrentName() {
		return currentName;
	}
	
	public void setCurrentName(String currentName) {
		this.currentName = currentName;
	}
	
	public String getCurrentDescription() {
		return currentDescription;
	}
	
	public void setCurrentDescription(String currentDescription) {
		this.currentDescription = currentDescription;
	}
	
	public void setLoc(Coordinate loc) {
		this.loc = loc;
	}
	public int getMaxUpgradeLevel() {
		return maxUpgradeLevel;
	}
	public void setMaxUpgradeLevel(int maxUpgradeLevel) {
		this.maxUpgradeLevel = maxUpgradeLevel;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int[] getUpgradeCost() {
		return upgradeCost;
	}
	public void setUpgradeCost(int[] upgradeCost) {
		this.upgradeCost = upgradeCost;
	}
	
	public String getTowerRange(int level) {
		return towerRange[level];
	}
	public void setTowerRange(String[] towerRange) {
		this.towerRange = towerRange;
	}
	public String getTowerDamage(int level) {
		return towerDamage[level];
	}
	public void setTowerDamage(String[] towerDamage) {
		this.towerDamage = towerDamage;
	}

	public String getCurrentRange() {
		return currentRange;
	}

	public void setCurrentRange(String currentRange) {
		this.currentRange = currentRange;
	}

	public String getCurrentDamage() {
		return currentDamage;
	}

	public void setCurrentDamage(String currentDamage) {
		this.currentDamage = currentDamage;
	}
	
	
	
	
	
	
	
	

}
