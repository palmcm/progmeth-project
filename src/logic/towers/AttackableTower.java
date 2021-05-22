package logic.towers;

import java.util.ArrayList;

import exception.InvalidPlayerException;
import logic.misc.Coordinate;
import utils.CommonStrings;

public abstract class AttackableTower extends BaseTower{
	
	public abstract void attack() throws InvalidPlayerException;
	
	/**
	 * Performs this unit's attack, if possible.
	 */
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
	private int[] towerRangeInt;
	
	protected void initialize()
	{
		this.setUpgradeLevel(0);
		this.setCurrentName(this.getTowerName(0));
		this.setCurrentRange(this.getTowerRange(0));
		this.setCurrentCooldown(1);
		this.unfroze();
		
	}

	
	public void updateInfo()
	{
		int level = this.getUpgradeLevel();
		this.setCurrentName(this.getTowerName(level));
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
	
	/**
	 * Returns an ArrayList of all tiles which this tower can reach.
	 * May include invalid tiles.
	 * @return A list of Coordinates.
	 */
	public ArrayList<Coordinate> getReachableTiles()
	{
		ArrayList<Coordinate> r = new ArrayList<Coordinate>();
		int x = this.getLoc().getX();
		int y = this.getLoc().getY();
		int dir;
		if(this.getOwner() == 1)
		{
			dir = 1;
		}
		else
		{
			dir = -1;
		}
		for(int i = 1;i<=this.getCurrentRangeInt();i++)
		{
			r.add(new Coordinate(x,y+(i*dir)));
		}
		return r;
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
				CommonStrings.stats_cost+this.getCost()+CommonStrings.currency_symbol+"\n";
	}
	
	// -------------- COOLDOWN --------------------
	
	private int cooldown;
	private int currentCooldown;

	/**
	 * Setter for unit's attack {@link #cooldown}
	 * @param cooldown {@link #cooldown}
	 */
	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}
	
	/**
	 * Getter for unit's {@link #cooldown}
	 * @return {@link #cooldown}
	 */
	public int getCooldown()
	{
		return this.cooldown;
	}
	
	/**
	 * Getter for unit's {@link #currentCooldown}
	 * @return {@link #currentCooldown}
	 */
	public int getCurrentCooldown() {
		return currentCooldown;
	}
	
	/**
	 * Setter for unit's {@link #currentCooldown}
	 * @param currentCooldown {@link #currentCooldown}
	 */
	public void setCurrentCooldown(int currentCooldown) {
		if(this.currentCooldown < currentCooldown)
			this.currentCooldown = currentCooldown;
	}
	
	/**
	 * Reduce the unit's {@link #currentCooldown} by 1.
	 * Cannot reduce below 0.
	 */
	public void doCooldown() {
		this.currentCooldown -= 1;
		if(this.currentCooldown<0)
		{
			this.currentCooldown = 0;
		}
	}
	
	/**
	 * Returns whether or not this unit can attack.
	 * @return True if cooldown is 0 and unit isn't frozen, false otherwise.
	 */
	public boolean canAttack() {
		return this.currentCooldown <= 0 && !this.isFrozen();
	}
	
	// ------------------- GETTER/SETTER -----------------

	/**
	 * Getter for {@link #towerRange} of a specific level
	 * @param level level to query
	 * @return {@link #towerRange} of that level.
	 */
	public String getTowerRange(int level) {
		return towerRange[level];
	}
	
	/**
	 * Setter for {@link #towerRange}
	 * @param towerRange {@link #towerRange}
	 */
	public void setTowerRange(String[] towerRange) {
		this.towerRange = towerRange;
	}
	
	/**
	 * Getter for {@link #towerDamage} of a specific level.
	 * @param level level to query
	 * @return {@link #towerDamage} of that level.
	 */
	public String getTowerDamage(int level) {
		return towerDamage[level];
	}
	
	/**
	 * Setter for {@link #towerDamage}
	 * @param towerDamage {@link #towerDamage}
	 */
	public void setTowerDamage(String[] towerDamage) {
		this.towerDamage = towerDamage;
	}
	
	/**
	 * Getter for {@link #currentRange}
	 * @return {@link #currentRange}
	 */
	public String getCurrentRange() {
		return currentRange;
	}
	
	/**
	 * Setter for {@link #currentRange}
	 * @param currentRange {@link #currentRange}
	 */
	public void setCurrentRange(String currentRange) {
		this.currentRange = currentRange;
	}
	
	/**
	 * Getter for {@link #towerDamage} of this unit.
	 * @return {@link #towerDamage} of this unit.
	 */
	public String getCurrentDamage() {
		return this.getTowerDamage(this.getUpgradeLevel());
	}
	
	/**
	 * Getter for current {@link #towerRange} of this unit as an integer.
	 * @return {@link #towerRange} of this unit as an integer.
	 */
	private int getCurrentRangeInt()
	{
		return this.towerRangeInt[this.getUpgradeLevel()];
	}
	

	/**
	 * Setter for {@link #towerRange} as an integer.
	 * @param towerDamage {@link #towerRange} as an integer.
	 */
	public void setTowerRangeInt(int[] towerRangeInt) {
		this.towerRangeInt = towerRangeInt;
	}
	
	
}
