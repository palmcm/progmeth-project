package logic.towers;

import exception.InvalidPlayerException;

public interface Attacker {
	
	public void attack() throws InvalidPlayerException;
	
	public void setCooldown(int cooldown);
	public void doCooldown();
	public boolean canAttack();
	
}
