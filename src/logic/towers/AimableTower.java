package logic.towers;

import logic.misc.Coordinate;

public abstract class AimableTower extends AttackableTower {
	
	private Coordinate target;
	private int hRange[],vRange[];

	public Coordinate getTarget() {
		return target;
	}

	public boolean setTarget(Coordinate target) {
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
	
	protected String getInstanceToolTipString()
	{
		return "-----------------------------"+
				this.getCurrentDescription()+"\n"+
				"-----------------------------"+
				"Tower Info:\n"+
				"Attack Damage: "+this.getCurrentDamage()+"\n"+
				"Attack Vertical Range: "+this.getCurrentvRange()+"\n"+
				"Attack Horizontal Range: "+this.getCurrenthRange()+"\n"+
				"Health: "+this.getCurrentHealth()+"/"+this.getMaxHealth()+"\n"+
				"-----------------------------"+
				this.getNextUpgradeInfo();
	}

	
	protected String getBuyToolTip()
	{
		return  "-----------------------------"+
				this.getTowerUpgradeDescription(0)+"\n"+
				"-----------------------------"+
				"Attack Damage: "+this.getCurrentDamage()+"\n"+
				"Attack Vertical Range: "+this.getCurrentvRange()+"\n"+
				"Attack Horizontal Range: "+this.getCurrenthRange()+"\n"+
				"Health: "+this.getMaxHealth()+"\n";
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
