package logic.gmanager;

import java.util.ArrayList;

import exception.InvalidPlayerException;
import logic.towers.BaseTower;
import utils.CommonStrings;

public class Player {
	
	private int income;
	private int money;
	
	private int maxHealth;

	private int health;
	private ArrayList<BaseTower> deck;
	
	private int playerId;
	
	// INITIALIZER
	
	public Player(int id)
	{
		this.income = GameSettings.getStartingIncome();
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
		return this.income;
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
		return "Player "+this.getPlayerId()+"'s Income: "+this.getIncome()+"\n"+
				CommonStrings.SeparatorLine+"Next level: +"+GameSettings.getIncome()+
				"\n"+"Upgrade cost: "+GameSettings.getIncomeUpgradeCost();
	}
	
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


	public void setIncome(int income) {
		this.income = income;
	}
	
	
	
	
	
}
