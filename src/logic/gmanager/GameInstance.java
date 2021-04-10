package logic.gmanager;

import exception.InvalidPlayerException;

public class GameInstance {
	
	private Board board;
	
	private int maxIncomeLevel;
	private int income[];
	private int incomeUpgradeCost[];
	private int deckSize;
	private int MaxHealth;
	private int startingMoney;
	
	private Player player1,player2;
	
	public GameInstance()
	{
		this.board = new Board();
		this.maxIncomeLevel = 5;
		this.income = new int[]{10,15,20,25,30,35};
		this.incomeUpgradeCost = new int[]{0,50,100,150,200,250};
		this.startingMoney = 50;
		this.MaxHealth = 100;
		
		this.player1 = new Player(1);
		this.player2 = new Player(2);
	}
	
	public int getStartingMoney()
	{
		return this.startingMoney;
	}
	
	public Board getBoard()
	{
		return this.board;
	}
	
	public int getIncome(int level)
	{
		return income[level];
	}
	
	public int getIncomeUpgradeCost(int level)
	{
		return incomeUpgradeCost[level];
	}
	
	public Player getPlayer(int player) throws InvalidPlayerException
	{
		if(player < 1  || player > 2)
		{
			throw new InvalidPlayerException(player);
		}
		else if(player==1)
			return this.player1;
		return this.player2;
	}

	public int getMaxIncomeLevel() {
		return maxIncomeLevel;
	}

	public int getDeckSize() {
		return deckSize;
	}

	public int getMaxHealth() {
		return MaxHealth;
	}
	
	
	
	
}
