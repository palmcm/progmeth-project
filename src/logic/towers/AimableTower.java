package logic.towers;

import java.util.ArrayList;

import logic.misc.Coordinate;
import utils.CommonStrings;

public abstract class AimableTower extends AttackableTower {
	
	private Coordinate target;
	private int hRange[],vRange[];
	private int hRadius[],vRadius[];

	public Coordinate getTarget() {
		return target;
	}
	
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

	public boolean setTarget(Coordinate target) {
		
		if(this.canTarget(target))
		{
			this.target = target;
			return true;
		}
		return false;
	}
	
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
	
	public int getCurrenthRadius()
	{
		return this.hRadius[this.getUpgradeLevel()];
	}
	
	public int getCurrentvRadius()
	{
		return this.vRadius[this.getUpgradeLevel()];
	}


	public void sethRadius(int[] hRadius) {
		this.hRadius = hRadius;
	}


	public void setvRadius(int[] vRadius) {
		this.vRadius = vRadius;
	}
	
	
	

}
