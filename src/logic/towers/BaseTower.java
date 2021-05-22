package logic.towers;

import exception.InvalidPlayerException;
import gui.SceneController;
import logic.gmanager.GameManager;
import logic.misc.Coordinate;
import utils.CommonStrings;

/**
 * An abstract class which represents a basic tower.
 * Towers which implements this directly will not be able to attack.
 * See AttackableTower and AimableTower for towers capable of attacking.
 */
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
	private String attackUrl;
	private String damageUrl;
	
	// ** Abstract Methods **
	/**
	 * Create new instance of tower at the location
	 * @param loc location
	 * @return Tower instance with location
	 */
	public abstract BaseTower getNewInstance(Coordinate loc);
	
	// ** Methods **

	/**
	 * Method called when a unit is created to initialize statuses.
	 */
	protected void initialize()
	{
		this.setUpgradeLevel(0);
		this.setCurrentName(this.getTowerName(0));
		this.setOwner(GameManager.getCurrentPlayer());
		this.unfroze();
		
	}
	
	/**
	 * Upgrades this instance of tower, if possible.
	 * @return whether or not the upgrade succeeds.
	 */
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
	
	/**
	 * Updates unit's info string according to its level.
	 * Mostly called when upgraded.
	 */
	public void updateInfo()
	{
		int level = this.getUpgradeLevel();
		this.setCurrentName(this.getTowerName(level));
	}
	
	/**
	 * Returns a string of the unit's tooltip depending on
	 * whether the instance is an actual unit or one in the buy panel.
	 * @return Tooltip String
	 */
	public String getToolTipString()
	{
		if(this.isInstance)
			return this.getInstanceToolTipString();
		else
			return this.getBuyToolTip();
	}
	
	/**
	 * Returns a tooltip string of an actual unit on the field.
	 * @return Tooltip String
	 */
	public String getInstanceToolTipString()
	{
		return CommonStrings.SeparatorLine+
				this.getCurrentDescription()+"\n"+
				CommonStrings.SeparatorLine+
				CommonStrings.stats_health+this.getCurrentHealth()+"/"+this.getMaxHealth();
	}
	
	/**
	 * Returns a tooltip string of a unit on the buy panel (or the deck selector screen).
	 * @return Tooltip String
	 */
	public String getBuyToolTip()
	{
		return 	CommonStrings.SeparatorLine+
				this.getTowerUpgradeDescription(-1)+"\n"+
				CommonStrings.SeparatorLine+
				CommonStrings.stats_health+this.getMaxHealth()+"\n"+
				CommonStrings.SeparatorLine+
				CommonStrings.stats_cost+this.getCost()+CommonStrings.currency_symbol+"\n";
	}
	
	/**
	 * Returns a string of the unit's upgrade description.
	 * @return upgrade description
	 */
	public String getNextUpgradeInfo()
	{
		if(this.getUpgradeLevel() == this.getMaxUpgradeLevel())
		{
			return "This tower cannot be upgraded any further.";
		}
		return 	"Upgrade:\n"+
				this.getCurrentUpgradeDescription()+"\n"+
				CommonStrings.stats_cost+this.getCurrentUpgradeCost()+CommonStrings.currency_symbol+"\n"+
				"Upgrading will heal "+this.getCurrentUpgradeHealth()+CommonStrings.stats_health;
	}
	
	/**
	 * Updates the unit's healthbar.
	 */
	private void updateHealthBar()
	{
		SceneController.getGamePane().getTilesPane().getTileCell(this.loc).updateHealth();
	}
	
	/**
	 * Sets whether the unit is an actual instance or for display in the buy panel.
	 * @param isInstance True for actual instance, false for display.
	 */
	protected void setIsInstance(boolean isInstance)
	{
		this.isInstance = isInstance;
	}
	
	/**
	 * Returns whether the unit is still active and can be attacked.
	 * @return True if can be attacked, false otherwise.
	 */
	public boolean isActive()
	{
		return this.currentHealth > 0;
	}
	
	/**
	 * Damage this unit by a specified amount
	 * @param dmg amount of damage.
	 */
	public void damage(int dmg)
	{
		this.setCurrentHealth(this.getCurrentHealth()-dmg);
		this.updateHealthBar();
		GameManager.doDamageAnimation(loc);
	}
	
	/**
	 * Removes the overheal applied to this unit.
	 */
	public void removeOverheal()
	{
		this.setCurrentHealth(this.getMaxHealth());
		this.updateHealthBar();
	}
	
	/**
	 * Heals this unit by a specified amount
	 * @param heal amount to heal
	 */
	public void heal(int heal)
	{
		//this.setCurrentHealth(this.getCurrentHealth()+heal);
		this.currentHealth = this.getCurrentHealth() + heal;
		this.updateHealthBar();
	}
	
	/**
	 * Returns whether the unit can be hit by an attack of a player.
	 * @param player attacking source's player
	 * @return True if can be hit, false otherwise.
	 * @throws InvalidPlayerException Invalid player
	 */
	public boolean attackable(int player) throws InvalidPlayerException
	{
		if(this.currentHealth <=0)
			return false;
		if(player==3)
			return true;
		else return player!=this.getOwner();
	}
	
	/**
	 * Getter for location
	 * @return unit location
	 */
	public Coordinate getLoc()
	{
		return this.loc;
	}
	
	/**
	 * Getter for current health
	 * @return unit current health
	 */
	public int getCurrentHealth() {
		
		return currentHealth;
	}
	
	/**
	 * Setter for current health
	 * @param currentHealth health value
	 */
	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
		if(this.currentHealth > this.getMaxHealth())
			this.currentHealth = this.getMaxHealth();
	}
	
	/**
	 * Getter for max health
	 * @return max health
	 */
	public int getMaxHealth() {
		return maxHealth;
	}
	
	/**
	 * Setter for max health
	 * @param maxHealth health value
	 */
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	/**
	 * Getter for health gained when upgrading to a specific level.
	 * @param level level to query
	 * @return health value
	 */
	public int getUpgradeHealth(int level) {
		return upgradeHealth[level];
	}
	
	/**
	 * Getter for health gained when upgrading this unit to the next level
	 * @return health value
	 */
	public int getCurrentUpgradeHealth() {
		return this.getUpgradeHealth(this.getUpgradeLevel());
	}
	
	/**
	 * Setter for UpgradeHealth
	 * @param upgradeHealth array of UpgradeHealth value
	 */
	public void setUpgradeHealth(int[] upgradeHealth) {
		this.upgradeHealth = upgradeHealth;
	}
	
	/**
	 * Getter for {@link #upgradeLevel upgradeLevel}
	 * @return {@link #upgradeLevel upgradeLevel}
	 */
	public int getUpgradeLevel() {
		return upgradeLevel;
	}
	
	/**
	 * Setter for {@link #upgradeLevel upgradeLevel}
	 * @param upgradeLevel {@link #upgradeLevel upgradeLevel}
	 */
	public void setUpgradeLevel(int upgradeLevel) {
		this.upgradeLevel = upgradeLevel;
	}
	
	/**
	 * Getter for {@link #owner Owner}
	 * @return {@link #owner Owner}
	 */
	public int getOwner() {
		return owner;
	}
	

	/**
	 * Getter for {@link #owner Owner}
	 * @param owner {@link #owner Owner}
	 */
	public void setOwner(int owner) {
		this.owner = owner;
	}
	
	/**
	 * Getter for {@link #towerName towerName} of a specific level
	 * @param level level to query
	 * @return {@link #towerName towerName} of that level
	 */
	public String getTowerName(int level) {
		return towerName[level];
	}
	
	/**
	 * Setter for {@link #towerName towerName}
	 * @param towerName {@link #towerName towerName}
	 */
	public void setTowerName(String[] towerName) {
		this.towerName = towerName;
	}
	
	/**
	 * Getter for {@link #towerDescription} of a specific level
	 * @param level level to query
	 * @return {@link #towerDescription} of that level
	 */
	public String getTowerDescription(int level) {
		return towerDescription[level];
	}
	
	/**
	 * Setter for {@link #towerDescription}
	 * @param towerDescription {@link #towerDescription}
	 */
	public void setTowerDescription(String[] towerDescription) {
		this.towerDescription = towerDescription;
	}
	
	/**
	 * Getter for {@link #currentName}
	 * @return {@link #currentName}
	 */
	public String getCurrentName() {
		return currentName;
	}
	
	/**
	 * Setter for {@link #currentName}
	 * @param currentName {@link #currentName}
	 */
	public void setCurrentName(String currentName) {
		this.currentName = currentName;
	}
	
	/**
	 * Getter for {@link #towerDescription} of the current level.
	 * @return {@link #towerDescription} of the current level.
	 */
	public String getCurrentDescription() {
		return this.getTowerDescription(this.getUpgradeLevel());
	}
	
	/**
	 * Setter for {@link #loc}
	 * @param loc {@link #loc}
	 */
	public void setLoc(Coordinate loc) {
		this.loc = loc;
	}
	
	/**
	 * Getter for {@link #maxUpgradeLevel}
	 * @return {@link #maxUpgradeLevel}
	 */
	public int getMaxUpgradeLevel() {
		return maxUpgradeLevel;
	}
	
	/**
	 * Setter for {@link #maxUpgradeLevel}
	 * @param maxUpgradeLevel {@link #maxUpgradeLevel}
	 */
	public void setMaxUpgradeLevel(int maxUpgradeLevel) {
		this.maxUpgradeLevel = maxUpgradeLevel;
	}
	
	/**
	 * Getter for {@link #cost}
	 * @return {@link #cost}
	 */
	public int getCost() {
		return cost;
	}
	
	/**
	 * Getter for {@link #cost}
	 * @param cost {@link #cost}
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	/**
	 * Getter for {@link #upgradeCost} of a specific level.
	 * @param level level to query
	 * @return {@link #upgradeCost} of that level.
	 */
	public int getUpgradeCost(int level) {
		return upgradeCost[level];
	}
	
	/**
	 * Getter for {@link #upgradeCost} of the next upgrade level.
	 * @return {@link #upgradeCost} of the next upgrade level.
	 */
	public int getCurrentUpgradeCost()
	{
		return this.upgradeCost[this.getUpgradeLevel()];
	}
	
	/**
	 * Setter for {@link #upgradeCost}
	 * @param upgradeCost {@link BaseTower#upgradeCost}
	 */
	public void setUpgradeCost(int[] upgradeCost) {
		this.upgradeCost = upgradeCost;
	}

	/**
	 * Getter for {@link #towerUpgradeDescription} of a specific tower level
	 * @param level level to query
	 * @return {@link #towerUpgradeDescription} of a tower of that level.
	 */
	public String getTowerUpgradeDescription(int level) {
		return towerUpgradeDescription[level+1];
	}

	/**
	 * Setter for {@link #towerUpgradeDescription}
	 * @param towerUpgradeDescription {@link #towerUpgradeDescription}
	 */
	public void setTowerUpgradeDescription(String[] towerUpgradeDescription) {
		this.towerUpgradeDescription = towerUpgradeDescription;
	}
	
	/**
	 * Getter for {@link #towerUpgradeDescription} of this unit.
	 * @return {@link BaseTower#towerUpgradeDescription}
	 */
	public String getCurrentUpgradeDescription() {
		return this.getTowerUpgradeDescription(this.getUpgradeLevel());
	}

	/**
	 * Setter for {@link #isInstance}.
	 * @param isInstance {@link #isInstance}.
	 * True for actual unit, false for display panel.
	 */
	public void setInstance(boolean isInstance) {
		this.isInstance = isInstance;
	}
	
	/**
	 * Getter for {@link #url url} of the tower's base sprite.
	 * @return {@link #url}
	 */
	public String getUrl() {
		return url;
	}
	

	/**
	 * Setter for url of the tower's {@link #url base}, {@link #attackUrl attacking} 
	 * and {@link #damageUrl damaged} sprite.
	 * @param url {@link #url}
	 */
	public void setUrl(String url) {
		this.url = url;
		this.attackUrl = url;
		this.damageUrl = url;
	}
	
	/**
	 * Getter for {@link #attackUrl}
	 * @return {@link #attackUrl}
	 */
	public String getAttackUrl() {
		return attackUrl;
	}
	
	/**
	 * Setter for {@link #attackUrl}
	 * @param attackUrl {@link #attackUrl}
	 */
	public void setAttackUrl(String attackUrl) {
		this.attackUrl = attackUrl;
	}
	
	/**
	 * Getter for {@link #damageUrl}
	 * @return {@link #damageUrl}
	 */
	public String getDamageUrl() {
		return damageUrl;
	}
	
	/**
	 * Setter for {@link #damageUrl}
	 * @param damageUrl {@link #damageUrl}
	 */
	public void setDamageUrl(String damageUrl) {
		this.damageUrl = damageUrl;
	}
	
	/**
	 * Attempts to freeze this unit for a duration
	 * @param duration freezing duration in turns
	 */
	public void freeze(int duration)
	{
		this.frozen = duration;
	}
	
	/**
	 * Reduce this unit's cyrrent frozen duration by 1 turn.
	 * Called at the end of a turn for all units.
	 */
	public void defrost()
	{
		if(this.frozenDuration > 0)
		{
			this.frozenDuration -= 1;
		}
	}
	
	/**
	 * Applies the frozen duration to this unit.
	 */
	public void applyFreeze()
	{
		if(this.frozen > this.frozenDuration)
		{
			this.frozenDuration = this.frozen;
		}
		this.frozen = 0;
	}
	
	/**
	 * Returns whether or not this unit this frozen.
	 * @return True if frozen, false otherwise.
	 */
	public boolean isFrozen()
	{
		return this.frozenDuration > 0;
	}
	
	/**
	 * Forcibly unfreezes this unit.
	 */
	public void unfroze()
	{
		this.frozen = 0;
		this.frozenDuration = 0;
	}
	
	
	
	
	
	
	
	
	
	
	

}
