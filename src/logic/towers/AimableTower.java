package logic.towers;

import logic.misc.Coordinate;
import utils.CommonStrings;

public abstract class AimableTower extends AttackableTower {
	
	private Coordinate target;
	private int hRange[],vRange[];

	public Coordinate getTarget() {
		return target;
	}
	
	protected void initialize()
	{
		this.setUpgradeLevel(0);
		this.setCurrentName(this.getTowerName(0));
		this.setCurrentDamage(this.getTowerDamage(0));
		this.setCooldown(0);
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
				"Attack Damage: "+this.getCurrentDamage()+"\n"+
				"Attack Vertical Range: "+this.getCurrentvRange()+"\n"+
				"Attack Horizontal Range: "+this.getCurrenthRange()+"\n"+
				"Health: "+this.getCurrentHealth()+"/"+this.getMaxHealth()+"\n"+
				CommonStrings.SeparatorLine+
				this.getNextUpgradeInfo();
	}

	
	public String getBuyToolTip()
	{
		return  CommonStrings.SeparatorLine+
				this.getTowerUpgradeDescription(0)+"\n"+
				CommonStrings.SeparatorLine+
				"Attack Damage: "+this.getCurrentDamage()+"\n"+
				"Attack Vertical Range: "+this.getCurrentvRange()+"\n"+
				"Attack Horizontal Range: "+this.getCurrenthRange()+"\n"+
				"Health: "+this.getMaxHealth()+"\n"+
				CommonStrings.SeparatorLine+
				"Cost: "+this.getCost()+"\n";
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
