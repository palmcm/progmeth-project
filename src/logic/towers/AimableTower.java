package logic.towers;

import logic.misc.Coordinate;
import utils.CommonStrings;

public abstract class AimableTower extends AttackableTower {
	
	private Coordinate target;
	private int hRange[],vRange[];

	public Coordinate getTarget() {
		return target;
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
		this.setCurrentDamage(this.getTowerDamage(0));
		this.setCurrentCooldown(0);
		this.unfroze();
	}

	public boolean setTarget(Coordinate target) {
		if(target == null)
			return false;
		
		
		Coordinate currentLoc = this.getLoc();
		int vdiff = Math.abs(target.getX()-currentLoc.getX());
		int hdiff = Math.abs(target.getY()-currentLoc.getY());
		
		if(vdiff <= this.getvRange(this.getUpgradeLevel()) && hdiff <= this.gethRange(this.getUpgradeLevel()))
		{			
			this.target = target;
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
				"Cost: "+this.getCost()+CommonStrings.currency_symbol+"\n";
	}

	public int gethRange(int level) {
		return hRange[level];
	}

	public void sethRange(int[] hRange) {
		this.hRange = hRange;
	}

	public int getvRange(int level) {
		return vRange[level];
	}

	public void setvRange(int[] vRange) {
		this.vRange = vRange;
	}
	
	public int getCurrenthRange()
	{
		return this.gethRange(this.getUpgradeLevel());
	}
	
	public int getCurrentvRange()
	{
		return this.getvRange(this.getUpgradeLevel());
	}
	

}
