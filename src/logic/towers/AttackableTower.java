package logic.towers;

import exception.InvalidPlayerException;

public abstract class AttackableTower extends Tower{
	
	public abstract void attack() throws InvalidPlayerException;
	
	public void doAttack()
	{
		if(canAttack())
			try {
				attack();
			} catch (InvalidPlayerException e) {
				e.printStackTrace();
			}
	}
	
	// -------------- COOLDOWN --------------------
	
	private int cooldown;

	public void setCooldown(int cooldown) {
		
		this.cooldown = cooldown;
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

}
