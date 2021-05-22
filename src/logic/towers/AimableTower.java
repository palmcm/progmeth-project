package logic.towers;

import java.util.ArrayList;

import logic.misc.Coordinate;
import utils.CommonStrings;

/**
 * An abstract class for tower which can select a target.
 *
 */
public abstract class AimableTower extends AttackableTower {
	
	private Coordinate target;
	private int hRange[],vRange[];
	private int hRadius[],vRadius[];

	/**
	 * Getter for this unit's targeted tile.
	 * @return {@link #target}
	 */
	public Coordinate getTarget() {
		return target;
	}
	
	/**
	 * Creates a base AimableTower with no range.
	 */
	public AimableTower()
	{
		this.sethRadius(new int[]{0,0,0,0});
		this.setvRadius(new int[]{0,0,0,0});
	}

	
	public void updateInfo()
	{
		int level = this.getUpgradeLevel();
		this.setCurrentName(this.getTowerName(level));
	}
	
	protected void initialize()
	{
		this.setUpgradeLevel(0);
		this.setCurrentName(this.getTowerName(0));
		this.setCurrentCooldown(1);
		this.unfroze();
	}
	
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
		for(int i = -this.getCurrentvRange();i<=this.getCurrentvRange();i++)
		{
			for(int j = -this.getCurrenthRange(); j<= this.getCurrenthRange();j++)
			{
				r.add(new Coordinate(x+i,y+(j*dir)));
				
			}
		}
		return r;
	}
	
	/**
	 * Returns an ArrayList of Coordinates that will be affected by aiming a location.
	 * @param target targeted location
	 * @return ArrayList of Coordinates.
	 */
	public ArrayList<Coordinate> getAimingTarget(Coordinate target)
	{
		ArrayList<Coordinate> r = new ArrayList<Coordinate>();
		if(!this.canTarget(target))
		{
			return r;
		}
		
		int x = target.getX();
		int y = target.getY();
		
		for(int i = -this.getCurrentvRadius();i<=this.getCurrentvRadius();i++)
		{
			for(int j = -this.getCurrenthRadius(); j<= this.getCurrenthRadius();j++)
			{
				r.add(new Coordinate(x+i,y+j));
				
			}
		}
		return r;
	}

	/**
	 * Setter for {@link #target}
	 * @param target {@link #target}
	 * @return True if the target is within reach, false otherwise.
	 */
	public boolean setTarget(Coordinate target) {
		
		if(this.canTarget(target))
		{
			this.target = target;
			return true;
		}
		return false;
	}
	
	/**
	 * Returns whether or not this unit can target a specific tile.
	 * @param target The target tile to check
	 * @return True if within reach, false otherwise.
	 */
	private boolean canTarget(Coordinate target)
	{
		if(target == null)
			return false;
		
		
		Coordinate currentLoc = this.getLoc();
		int vdiff = Math.abs(target.getX()-currentLoc.getX());
		int hdiff = Math.abs(target.getY()-currentLoc.getY());
		
		if(vdiff <= this.getvRange(this.getUpgradeLevel()) && hdiff <= this.gethRange(this.getUpgradeLevel()))
		{
			return true;
			
		}
		return false;
		
	}
	
	public String getInstanceToolTipString()
	{
		return CommonStrings.SeparatorLine+
				this.getCurrentDescription()+"\n"+
				CommonStrings.SeparatorLine+
				CommonStrings.stats_health+this.getCurrentHealth()+"/"+this.getMaxHealth()+"  \t"+
				CommonStrings.stats_damage+this.getCurrentDamage()+"\n"+
				CommonStrings.stats_vrange+this.getCurrentvRange()+"    \t"+
				CommonStrings.stats_range+this.getCurrenthRange();
	}

	
	public String getBuyToolTip()
	{
		return  CommonStrings.SeparatorLine+
				this.getTowerUpgradeDescription(-1)+"\n"+
				CommonStrings.SeparatorLine+
				CommonStrings.stats_health+this.getMaxHealth()+"  \t"+
				CommonStrings.stats_damage+this.getCurrentDamage()+"\n"+
				CommonStrings.stats_vrange+this.getCurrentvRange()+"    \t"+
				CommonStrings.stats_range+this.getCurrenthRange()+"\n"+
				CommonStrings.SeparatorLine+
				CommonStrings.stats_cost+this.getCost()+CommonStrings.currency_symbol+"\n";
	}
	
	/**
	 * Getter for {@link #hRange} of a specific level.
	 * @param level level to query
	 * @return {@link #hRange} of that level.
	 */
	public int gethRange(int level) {
		return hRange[level];
	}

	/**
	 * Setter for {@link #hRange}
	 * @param hRange {@link #hRange}
	 */
	public void sethRange(int[] hRange) {
		this.hRange = hRange;
	}
	
	/**
	 * Getter for {@link #vRange} of a specific level.
	 * @param level level to query
	 * @return {@link #vRange} of that level.
	 */
	public int getvRange(int level) {
		return vRange[level];
	}

	/**
	 * Setter for {@link #vRange}
	 * @param vRange {@link #vRange}
	 */
	public void setvRange(int[] vRange) {
		this.vRange = vRange;
	}

	/**
	 * Getter for {@link #hRange} of this unit.
	 * @return {@link #hRange}
	 */
	public int getCurrenthRange()
	{
		return this.gethRange(this.getUpgradeLevel());
	}

	/**
	 * Getter for {@link #vRange} of this unit.
	 * @return {@link #vRange}
	 */
	public int getCurrentvRange()
	{
		return this.getvRange(this.getUpgradeLevel());
	}

	/**
	 * Getter for {@link #hRadius} of this unit.
	 * @return {@link #hRadius}
	 */
	public int getCurrenthRadius()
	{
		return this.hRadius[this.getUpgradeLevel()];
	}

	/**
	 * Getter for {@link #vRadius} of this unit.
	 * @return {@link #vRadius}
	 */
	public int getCurrentvRadius()
	{
		return this.vRadius[this.getUpgradeLevel()];
	}

	/**
	 * Setter for {@link #hRadius}
	 * @param hRadius {@link #hRadius}
	 */
	public void sethRadius(int[] hRadius) {
		this.hRadius = hRadius;
	}

	/**
	 * Setter for {@link #vRadius}
	 * @param vRadius {@link #vRadius}
	 */
	public void setvRadius(int[] vRadius) {
		this.vRadius = vRadius;
	}
	
	
	

}
