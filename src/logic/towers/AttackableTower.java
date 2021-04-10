package logic.towers;

import exception.InvalidPlayerException;

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

	
	public void updateInfo()
	{
		int level = this.getUpgradeLevel();
		this.setCurrentDescription(this.getTowerDescription(level));
		this.setCurrentName(this.getTowerName(level));
		this.setCurrentDamage(this.getTowerDamage(level));
		this.setCurrentDescription(this.getTowerDescription(level));
		this.setCurrentRange(this.getTowerRange(level));
	}
	
	// -------------- COOLDOWN --------------------
	
	private int cooldown;

	public void setCooldown(int cooldown) {
		
		this.cooldown = cooldown;
	}
	
	public int getCooldown()
	{
		return this.cooldown;
	}

	public void doCooldown() {
		this.cooldown -= 1;
		if(this.cooldown<0)
		{
			this.cooldown = 0;
		}
	}

	public boolean canAttack() {
		return this.cooldown <= 0;
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
