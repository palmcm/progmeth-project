package logic.gmanager;

import java.util.ArrayList;

import exception.InvalidPlayerException;
import logic.towers.BaseTower;
import utils.CommonStrings;

public class Player {
	
	private int incomeLevel;
	private int money;
	
	private int maxHealth;

	private int health;
	private ArrayList<BaseTower> deck;
	
	private int playerId;
	
	// INITIALIZER
	
	public Player(int id)
	{
		this.incomeLevel = 0;
		this.money = GameSettings.getStartingMoney();
		this.maxHealth = GameSettings.getMaxHealth();
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
	
	public void gainMoney(int money)
	{
		this.setMoney(this.getMoney()+money);
	}
	
	public void spendMoney(int money)
	{
		this.setMoney(this.getMoney()-money);
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
	
	public void addTowerToDeck(BaseTower tower)
	{
		this.deck.add(tower);
	}
	
	public void removeTowerFromDeck(int idx)
	{
		this.deck.remove(idx);
	}
	
	public String getIncomeToolTip()
	{
		if(this.getIncomeLevel() >= GameManager.getGameInstance().getMaxIncomeLevel())
		{
			return "Player "+this.getPlayerId()+"'s Income: "+this.getIncome()+"\n"+
					CommonStrings.SeparatorLine+"Income cannot be upgraded further!";
		}
		else
		{
			return "Player "+this.getPlayerId()+"'s Income: "+this.getIncome()+"\n"+
					CommonStrings.SeparatorLine+"Next level: "+GameManager.getGameInstance().getIncome(this.getIncomeLevel()+1)
					+"\n"+"Upgrade cost: "+GameManager.getGameInstance().getIncomeUpgradeCost(this.getIncomeLevel()+1);
		}
	}
	
	public boolean upgradeIncome()
	{
		if(this.getIncomeLevel() >= GameManager.getGameInstance().getMaxIncomeLevel())
		{
			return false;
		}
		if(this.getMoney() < GameManager.getGameInstance().getIncomeUpgradeCost(this.getIncomeLevel()))
		{
			return false;
		}
		this.spendMoney(GameManager.getGameInstance().getIncomeUpgradeCost(this.getIncomeLevel()));
		this.setIncomeLevel(this.getIncomeLevel()+1);
		return true;
	}
	
	// GENERATED GETTER & SETTER
	
	public int getIncomeLevel() {
		return incomeLevel;
	}

	public void setIncomeLevel(int incomeLevel) {
		this.incomeLevel = incomeLevel;
		if(this.incomeLevel > GameManager.getGameInstance().getMaxIncomeLevel())
			this.incomeLevel = GameManager.getGameInstance().getMaxIncomeLevel();
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
		if(this.money < 0)
			this.money = 0;
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

	public ArrayList<BaseTower> getDeck() {
		return deck;
	}
	
	public boolean hasTowerInDeck(BaseTower tower)
	{
		boolean found = false;
		for(BaseTower i:this.getDeck())
		{
			if(i.getClass().equals(tower.getClass()))
			{
				found = true;
				break;
			}
		}
		
		return found;
	}
	
	
	
}
