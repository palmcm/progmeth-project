package logic.towers;

import exception.InvalidPlayerException;
import gui.SceneController;
import logic.gmanager.GameManager;
import logic.misc.Coordinate;
import utils.CommonStrings;

public abstract class BaseTower {
	
	// ** Fields **
	
	// Health Related
	
	private boolean isInstance;
	
	private int currentHealth;
	private int maxHealth;
	private int maxUpgradeLevel;
	
	private int[] upgradeHealth; // Defines increased health for each upgrade
	
	// Upgrade Related
	
	private int upgradeLevel;
	private int cost;
	private int[] upgradeCost;
	
	// Board Related
	
	private int owner;
	private Coordinate loc;
	
	// Other Infos
	
	private String[] towerName;
	private String[] towerDescription;
	private String[] towerUpgradeDescription;
	
	private String currentName;

	private int frozen;
	private int frozenDuration;
	
	// Image Url
	
	private String url;
	
	// ** Abstract Methods **
	
	public abstract BaseTower getNewInstance(Coordinate loc);
	
	// ** Methods **
	
	protected void initialize()
	{
		this.setUpgradeLevel(0);
		this.setCurrentName(this.getTowerName(0));
		this.setOwner(GameManager.getCurrentPlayer());
		this.unfroze();
		
	}
	
	public boolean upgrade()
	{
		if(this.getUpgradeLevel() < this.getMaxUpgradeLevel())
		{
			this.setMaxHealth(this.maxHealth+this.getCurrentUpgradeHealth());
			this.heal(this.getCurrentUpgradeHealth());
			
			this.setUpgradeLevel(this.getUpgradeLevel()+1);
			this.updateInfo();
			
			
			return true;
			
		}
		else
		{
			return false;
		}
	}
	
	public void updateInfo()
	{
		int level = this.getUpgradeLevel();
		this.setCurrentName(this.getTowerName(level));
	}
	
	public String getToolTipString()
	{
		if(this.isInstance)
			return this.getInstanceToolTipString();
		else
			return this.getBuyToolTip();
	}
	
	public String getInstanceToolTipString()
	{
		return CommonStrings.SeparatorLine+
				this.getCurrentDescription()+"\n"+
				CommonStrings.SeparatorLine+
				CommonStrings.stats_health+this.getCurrentHealth()+"/"+this.getMaxHealth();
	}
	
	public String getBuyToolTip()
	{
		return 	CommonStrings.SeparatorLine+
				this.getTowerUpgradeDescription(-1)+"\n"+
				CommonStrings.SeparatorLine+
				CommonStrings.stats_health+this.getMaxHealth()+"\n"+
				CommonStrings.SeparatorLine+
				"Cost: "+this.getCost()+CommonStrings.currency_symbol+"\n";
	}
	
	public String getNextUpgradeInfo()
	{
		if(this.getUpgradeLevel() == this.getMaxUpgradeLevel())
		{
			return "This tower cannot be upgraded any further.";
		}
		return 	"Upgrade:\n"+
				this.getCurrentUpgradeDescription()+"\n"+
				"Cost: "+this.getCurrentUpgradeCost()+CommonStrings.currency_symbol+"\n"+
				"Upgrading will heal "+this.getCurrentUpgradeHealth()+CommonStrings.stats_health;
	}
	
	private void updateHealthBar()
	{
		SceneController.getGamePane().getTilesPane().getTileCell(this.loc).updateHealth();
	}
	
	protected void setIsInstance(boolean isInstance)
	{
		this.isInstance = isInstance;
	}
	
	public boolean isActive()
	{
		return this.currentHealth > 0;
	}
	
	public void damage(int dmg)
	{
		this.setCurrentHealth(this.getCurrentHealth()-dmg);
		this.updateHealthBar();
	}
	
	public void removeOverheal()
	{

		this.setCurrentHealth(this.getMaxHealth());
		this.updateHealthBar();
	}
	
	public void heal(int heal)
	{
		//this.setCurrentHealth(this.getCurrentHealth()+heal);
		this.currentHealth = this.getCurrentHealth() + heal;
		this.updateHealthBar();
	}
	
	public boolean attackable(int player) throws InvalidPlayerException
	{
		if(this.currentHealth <=0)
			return false;
		if(player==3)
			return true;
		else return player!=this.getOwner();
	}
	
	public Coordinate getLoc()
	{
		return this.loc;
	}
	
	public int getCurrentHealth() {
		return currentHealth;
	}
	
	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
		if(this.currentHealth > this.getMaxHealth())
			this.currentHealth = this.getMaxHealth();
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	public int getUpgradeHealth(int level) {
		return upgradeHealth[level];
	}
	
	public int getCurrentUpgradeHealth() {
		return this.getUpgradeHealth(this.getUpgradeLevel());
	}
	
	public void setUpgradeHealth(int[] upgradeHealth) {
		this.upgradeHealth = upgradeHealth;
	}
	
	public int getUpgradeLevel() {
		return upgradeLevel;
	}
	
	public void setUpgradeLevel(int upgradeLevel) {
		this.upgradeLevel = upgradeLevel;
	}
	
	public int getOwner() {
		return owner;
	}
	
	public void setOwner(int owner) {
		this.owner = owner;
	}
	
	public String getTowerName(int level) {
		return towerName[level];
	}
	
	
	public void setTowerName(String[] towerName) {
		this.towerName = towerName;
	}
	
	public String getTowerDescription(int level) {
		return towerDescription[level];
	}
	
	public void setTowerDescription(String[] towerDescription) {
		this.towerDescription = towerDescription;
	}
	
	public String getCurrentName() {
		return currentName;
	}
	
	public void setCurrentName(String currentName) {
		this.currentName = currentName;
	}
	
	public String getCurrentDescription() {
		return this.getTowerDescription(this.getUpgradeLevel());
	}
	
	public void setLoc(Coordinate loc) {
		this.loc = loc;
	}
	public int getMaxUpgradeLevel() {
		return maxUpgradeLevel;
	}
	public void setMaxUpgradeLevel(int maxUpgradeLevel) {
		this.maxUpgradeLevel = maxUpgradeLevel;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getUpgradeCost(int level) {
		return upgradeCost[level];
	}
	
	public int getCurrentUpgradeCost()
	{
		return this.upgradeCost[this.getUpgradeLevel()];
	}
	
	public void setUpgradeCost(int[] upgradeCost) {
		this.upgradeCost = upgradeCost;
	}

	public String getTowerUpgradeDescription(int level) {
		return towerUpgradeDescription[level+1];
	}

	public void setTowerUpgradeDescription(String[] towerUpgradeDescription) {
		this.towerUpgradeDescription = towerUpgradeDescription;
	}

	public String getCurrentUpgradeDescription() {
		return this.getTowerUpgradeDescription(this.getUpgradeLevel());
	}

	public void setInstance(boolean isInstance) {
		this.isInstance = isInstance;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public void freeze(int duration)
	{
		this.frozen = duration;
	}
	
	public void defrost()
	{
		if(this.frozenDuration > 0)
		{
			this.frozenDuration -= 1;
		}
	}
	
	public void applyFreeze()
	{
		if(this.frozen > this.frozenDuration)
		{
			this.frozenDuration = this.frozen;
		}
		this.frozen = 0;
	}
	
	public boolean isFrozen()
	{
		return this.frozenDuration > 0;
	}
	
	public void unfroze()
	{
		this.frozen = 0;
		this.frozenDuration = 0;
	}
	
	
	
	
	
	
	
	
	
	
	

}
