package logic.towers;

import exception.InvalidPlayerException;
import logic.misc.Coordinate;

public abstract class Tower {
	
	// ** Fields **
	
	// Health Related
	
	private int currentHealth;
	private int maxHealth;
	
	private int[] upgradeHealth; // Defines increased health for each upgrade
	
	// Upgrade Related
	
	private int upgradeLevel;
	
	// Board Related
	
	private int owner;
	private Coordinate loc;
	
	// Other Infos
	
	private String[] towerName;
	private String[] towerDescription;
	
	private String currentName;
	private String currentDescription;
	
	// ** Abstract Methods **
	
	public abstract boolean onUpgrade();
	public abstract void onSell();
	
	public abstract String getUpgradeDetails();
	
	public abstract void destroy();
	
	// ** Methods **
	
	public boolean isActive()
	{
		return this.currentHealth > 0;
	}
	
	public void damage(int dmg)
	{
		this.currentHealth -= dmg;
		if(this.currentHealth < 0)
		{
			this.currentHealth = 0;
		}
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
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	public int[] getUpgradeHealth() {
		return upgradeHealth;
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
	
	public String[] getTowerName() {
		return towerName;
	}
	
	public void setTowerName(String[] towerName) {
		this.towerName = towerName;
	}
	
	public String[] getTowerDescription() {
		return towerDescription;
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
	
	
	

}
