package logic.gmanager;

import java.util.ArrayList;

import exception.InvalidPlayerException;
import gui.SceneController;
import logic.towers.BaseTower;
import utils.CommonStrings;

/**
 * A class which represents a player and contain all their information and methods.
 *
 */
public class Player {
	
	private int income;
	private int money;
	
	private int maxHealth;

	private int health;
	private ArrayList<BaseTower> deck;
	
	private int playerId;
	
	// INITIALIZER
	
	/**
	 * Creates a new player with an id
	 * @param id
	 */
	public Player(int id)
	{
		this.income = GameSettings.getStartingIncome();
		this.money = GameSettings.getStartingMoney();
		this.maxHealth = GameSettings.getMaxHealth();
		this.health = this.maxHealth;
		this.deck = new ArrayList<BaseTower>();
				
		this.playerId = id;
	}
	
	/**
	 * Resets a player
	 */
	public void reset() {
		this.income = GameSettings.getStartingIncome();
		this.money = GameSettings.getStartingMoney();
		this.health = this.maxHealth;
	}
	// METHODS
	
	/**
	 * Damages the player
	 * @param dmg amount of damage
	 * @throws InvalidPlayerException
	 */
	public void damage(int dmg) throws InvalidPlayerException
	{
		this.setHealth(this.getHealth() - dmg);
	}
	
	/**
	 * Gets the id of opposing player
	 * @return 1 or 2
	 * @throws InvalidPlayerException
	 */
	public int getOppositePlayerId() throws InvalidPlayerException
	{
		if(this.getPlayerId() > 2 || this.getPlayerId() < 1)
			throw new InvalidPlayerException(this.getPlayerId());
		
		if(this.getPlayerId() == 1)
			return 2;
		else return 1;
	}
	
	/**
	 * Grants money ("Intellect") to a player
	 * @param money the amount to grant
	 */
	public void gainMoney(int money)
	{
		this.setMoney(this.getMoney()+money);
		SceneController.getGamePane().updateMoney();
	}
	
	/**
	 * Takes an amount of money ("Intellect") from a player
	 * @param money the amount to grant
	 */
	public void spendMoney(int money)
	{
		this.setMoney(this.getMoney()-money);
		SceneController.getGamePane().updateMoney();
	}
	
	/**
	 * Getter for {@link #playerId}
	 * @return {@link #playerId}
	 */
	public int getPlayerId()
	{
		return this.playerId;
	}
	
	/**
	 * Getter for {@link #income}
	 * @return {@link #income}
	 */
	public int getIncome()
	{
		return this.income;
	}
	
	/**
	 * Apply this player's income
	 */
	public void applyIncome()
	{
		this.setMoney(this.getMoney()+this.getIncome());
	}
	
	
	/**
	 * Add a tower to this player's deck
	 * @param tower
	 */
	public void addTowerToDeck(BaseTower tower)
	{
		this.deck.add(tower);
	}
	
	/**
	 * removes a tower from an index from this player's deck
	 * @param idx
	 */
	public void removeTowerFromDeck(int idx)
	{
		this.deck.remove(idx);
	}
	
	/**
	 * Returns the income upgrade button tooltip for the player
	 * @return Tooltip String
	 */
	public String getIncomeToolTip()
	{
		return CommonStrings.SeparatorLine+"Current Research: "+this.getIncome()+CommonStrings.currency_symbol+"\n"+
				CommonStrings.SeparatorLine+"Increase: +"+GameSettings.getIncome()+CommonStrings.currency_symbol+
				"\n"+"Increase cost: "+GameSettings.getIncomeUpgradeCost()+CommonStrings.currency_symbol;
	}
	
	/**
	 * Upgrades the income of this player
	 * @return whether or not the upgrade suceeds
	 */
	public boolean upgradeIncome()
	{
		if(this.getMoney() < GameSettings.getIncomeUpgradeCost())
		{
			return false;
		}
		this.spendMoney(GameSettings.getIncomeUpgradeCost());
		this.setIncome(this.getIncome()+GameSettings.getIncome());
		return true;
	}
	
	// GENERATED GETTER & SETTER

	/**
	 * Getter for {@link #money}
	 * @return {@link #money}
	 */
	public int getMoney() {
		return money;
	}

	/**
	 * Setter for {@link #money}
	 * @param money {@link #money}
	 */
	public void setMoney(int money) {
		this.money = money;
		if(this.money < 0)
			this.money = 0;

		SceneController.getGamePane().updateMoney();
	}

	/**
	 * Getter for {@link #maxHealth}
	 * @return {@link #maxHealth}
	 */
	public int getMaxHealth() {
		return maxHealth;
	}

	/**
	 * Setter for {@link #maxHealth}
	 * @param maxHealth {@link #maxHealth}
	 */
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	/**
	 * Getter for {@link #health}
	 * @return {@link #health}
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Setter for {@link #health}
	 * @param health {@link #health}
	 */
	public void setHealth(int health) {
		this.health = health;
		if(this.getHealth() < 0)
			this.health = 0;
		if(this.getHealth() > this.getMaxHealth())
			this.health = this.getMaxHealth();
	}
	
	/**
	 * Getter for {@link #deck}
	 * @return {@link #deck}
	 */
	public ArrayList<BaseTower> getDeck() {
		return deck;
	}
	
	/**
	 * Getter for a tower's index in this player's deck
	 * @param tower tower to query
	 * @return index
	 */
	public int getTowerIndexDeck(BaseTower tower)
	{
		int found = -1;
		for(int i=0;i<deck.size();i++)
		{
			if(deck.get(i).getClass().equals(tower.getClass()))
			{
				found = i;
				break;
			}
		}
		
		return found;
	}

    /**
     * Setter for {@link #income}
     * @param income {@link #income}
     */
	public void setIncome(int income) {
		this.income = income;
	}
	
	/**
	 * Heals a player for an amount
	 * @param amount amount to heal
	 */
	public void heal(int amount)
	{
		this.setHealth(this.health+amount);
	}
	
	
	
	
	
}
