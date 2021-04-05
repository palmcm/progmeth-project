package towerLogic;

public abstract class Tower {
	
	// ** Fields **
	
	// Health Related
	
	private int currentHealth;
	private int maxHealth;
	
	private int[] upgradeHealth; // Defines increased health for each upgrade
	
	// Upgrade Related
	
	private int upgradeLevel;
	
	// Board Related
	
	private int owner;
	private Coordinate loc;
	
	// Other Infos
	
	private String[] towerName;
	private String[] towerDescription;
	
	private String currentName;
	private String currentDescription;
	
	// ** Abstract Methods **
	
	public abstract boolean onUpgrade();
	public abstract void onSell();
	
	public abstract String getUpgradeDetails();
	
	public abstract void destroy();
	
	// ** Methods **
	
	public boolean isActive()
	{
		return this.currentHealth > 0;
	}
	
	
	
}
