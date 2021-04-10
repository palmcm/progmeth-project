package logic.towers;

import exception.InvalidPlayerException;
import utils.CommonStrings;

public abstract class AttackableTower extends BaseTower{
	
	public abstract void attack() throws InvalidPlayerException;
	
	public void doAttack()
	{
		if(canAttack())
			try {
				attack();
				this.setCooldown(this.getCooldown());
			} catch (InvalidPlayerException e) {
				e.printStackTrace();
			}
	}
	
	// -------------- UPGRADE ---------------------
	
	private String[] towerRange;
	private String[] towerDamage;
	private String currentRange;
	private String currentDamage;
	
	protected void initialize()
	{
		this.setUpgradeLevel(0);
		this.setCurrentName(this.getTowerName(0));
		this.setCurrentDamage(this.getTowerDamage(0));
		this.setCurrentRange(this.getTowerRange(0));
		
	}

	
	public void updateInfo()
	{
		int level = this.getUpgradeLevel();
		this.setCurrentDescription(this.getTowerDescription(level));
		this.setCurrentName(this.getTowerName(level));
		this.setCurrentDamage(this.getTowerDamage(level));
		this.setCurrentDescription(this.getTowerDescription(level));
		this.setCurrentRange(this.getTowerRange(level));
	}
	
	protected String getInstanceToolTipString()
	{
		return 	CommonStrings.SeparatorLine+
				this.getCurrentDescription()+"\n"+
				CommonStrings.SeparatorLine+
				"Tower Info:\n"+
				"Attack Damage: "+this.getCurrentDamage()+"\n"+
				"Attack Range: "+this.getCurrentRange()+"\n"+
				"Health: "+this.getCurrentHealth()+"/"+this.getMaxHealth()+"\n"+
				CommonStrings.SeparatorLine+
				this.getNextUpgradeInfo();
	}
	
	protected String getBuyToolTip()
	{
		return  CommonStrings.SeparatorLine+
				this.getTowerUpgradeDescription(0)+"\n"+
				CommonStrings.SeparatorLine+
				"Attack Damage: "+this.getCurrentDamage()+"\n"+
				"Attack Range: "+this.getCurrentRange()+"\n"+
				"Health: "+this.getMaxHealth()+"\n";
	}
	
	// -------------- COOLDOWN --------------------
	
	private int cooldown;
	private int currentCooldown;

	public void setCooldown(int cooldown) {
		
		this.cooldown = cooldown;
	}
	
	public int getCooldown()
	{
		return this.cooldown;
	}
	

	public int getCurrentCooldown() {
		return currentCooldown;
	}

	public void setCurrentCooldown(int currentCooldown) {
		this.currentCooldown = currentCooldown;
	}

	public void doCooldown() {
		this.currentCooldown -= 1;
		if(this.currentCooldown<0)
		{
			this.currentCooldown = 0;
		}
	}

	public boolean canAttack() {
		return this.currentCooldown <= 0;
	}
	
	// ------------------- GETTER/SETTER -----------------

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
