package logic.gmanager;

import java.util.ArrayList;

import exception.InvalidPlayerException;
import logic.towers.Tower;

public class Player {
	
	private int incomeLevel;
	private int money;
	
	private int maxHealth;

	private int health;
	private ArrayList<Tower> deck;
	
	private int playerId;
	
	// INITIALIZER
	
	public Player(int id)
	{
		this.incomeLevel = 0;
		this.money = GameManager.getGameInstance().getStartingMoney();
		this.maxHealth = GameManager.getGameInstance().getMaxHealth();
		this.health = this.maxHealth;
		
		this.playerId = id;
	}
	
	
	// METHODS
	
	public void damage(int dmg) throws InvalidPlayerException
	{
		this.setHealth(this.getHealth() - dmg);
		if(this.getHealth() <= 0)
		{
			GameManager.victory(this.getOppositePlayerId());
		}
	}
	
	public int getOppositePlayerId() throws InvalidPlayerException
	{
		if(this.getPlayerId() > 2 || this.getPlayerId() < 1)
			throw new InvalidPlayerException(this.getPlayerId());
		
		if(this.getPlayerId() == 1)
			return 2;
		else return 1;
	}
	
	public int getPlayerId()
	{
		return this.playerId;
	}
	
	public int getIncome()
	{
		return GameManager.getGameInstance().getIncome(this.getIncomeLevel());
	}
	
	public void applyIncome()
	{
		this.setMoney(this.getMoney()+this.getIncome());
	}
	
	public void addTowerToDeck(Tower tower)
	{
		this.deck.add(tower);
	}
	
	public void removeTowerFromDeck(int idx)
	{
		this.deck.remove(idx);
	}
	
	
	// GENERATED GETTER & SETTER
	
	public int getIncomeLevel() {
		return incomeLevel;
	}

	public void setIncomeLevel(int incomeLevel) {
		this.incomeLevel = incomeLevel;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
		if(this.getHealth() < 0)
			this.health = 0;
	}

	public ArrayList<Tower> getDeck() {
		return deck;
	}
	
	
	
}
