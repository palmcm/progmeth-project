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
				this.setCurrentCooldown(this.getCooldown());
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
		this.setCurrentCooldown(0);
		this.unfroze();
		
	}

	
	public void updateInfo()
	{
		int level = this.getUpgradeLevel();
		this.setCurrentName(this.getTowerName(level));
		this.setCurrentDamage(this.getTowerDamage(level));
		this.setCurrentRange(this.getTowerRange(level));
	}
	
	public String getInstanceToolTipString()
	{
		return 	CommonStrings.SeparatorLine+
				this.getCurrentDescription()+"\n"+
				CommonStrings.SeparatorLine+
				CommonStrings.stats_health+this.getCurrentHealth()+"/"+this.getMaxHealth()+"\n"+
				CommonStrings.stats_damage+this.getCurrentDamage()+"\n"+
				CommonStrings.stats_range+this.getCurrentRange();
	}
	
	public String getBuyToolTip()
	{
		return  CommonStrings.SeparatorLine+
				this.getTowerUpgradeDescription(-1)+"\n"+
				CommonStrings.SeparatorLine+
				CommonStrings.stats_health+this.getMaxHealth()+"\n"+
				CommonStrings.stats_damage+this.getCurrentDamage()+"\n"+
				CommonStrings.stats_range+this.getCurrentRange()+"\n"+
				CommonStrings.SeparatorLine+
				"Cost: "+this.getCost()+CommonStrings.currency_symbol+"\n";
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
		if(this.currentCooldown < currentCooldown)
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
		return this.currentCooldown <= 0 && !this.isFrozen();
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
		return this.getTowerDamage(this.getUpgradeLevel());
	}
	
	public void setCurrentDamage(String currentDamage) {
		this.currentDamage = currentDamage;
	
	}
}
