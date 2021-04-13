package logic.gmanager;

import java.util.ArrayList;

import exception.InvalidPlayerException;
import logic.actions.AttackAction;
import logic.actions.AttackPhaseAction;
import logic.menu.DeckSelector;
import logic.misc.Coordinate;
import logic.towers.BaseTower;

public class GameInstance {
	
	private Board board;
	
	private int maxIncomeLevel;
	private int income[];
	private int incomeUpgradeCost[];
	private Player player1,player2;
	
	private ArrayList<AttackPhaseAction> attackOrder;
	private ArrayList<AttackPhaseAction> persistentEffects;
	
	public GameInstance()
	{
		this.board = new Board();
		this.maxIncomeLevel = 5;
		this.income = new int[]{10,15,20,25,30,35};
		this.incomeUpgradeCost = new int[]{0,50,100,150,200,250};
		
		this.player1 = new Player(1);
		this.player2 = new Player(2);
		
		GameManager.setStartingPlayer(1);
		GameManager.setCurrentPlayer(1);
		
		GameManager.setTurnPhase(TurnPhase.BUILD);
		
		this.attackOrder = new ArrayList<AttackPhaseAction>();
		this.persistentEffects = new ArrayList<AttackPhaseAction>();
		
		DeckSelector.setFlipSelector(true);
		
	}
	
	// ---------------- ATTACK ORDER ---------------------
	
	public void clearAttackOrder()
	{
		this.attackOrder.clear();
		int i,j;
		for(i=0;i<this.getBoard().getLanes();i++)
		{
			for(j=0;j<=this.getBoard().getBorder();j++)
			{
				this.getBoard().getTile(new Coordinate(i,j)).setMarkAttacked(false);
			}
		}
		for(AttackPhaseAction a:this.persistentEffects)
		{
			this.attackOrder.add(a);
			this.persistentEffects.remove(a);
		}
		
	}
	
	public void addAttackOrder(AttackPhaseAction attack)
	{
		attackOrder.add(attack);
		if(attack instanceof AttackAction)
		{
			this.getBoard().getTile(((AttackAction) attack).getTrigger()).setMarkAttacked(true);			
		}
	}
	
	public void removeAttackOrder(AttackPhaseAction attack)
	{
		attackOrder.remove(attack);
		if(attack instanceof AttackAction)
		{
			this.getBoard().getTile(((AttackAction) attack).getTrigger()).setMarkAttacked(false);			
		}
	}
	
	public ArrayList<AttackPhaseAction> getAttackOrder() {
		return this.attackOrder;
	}
	
	
	
	// ---------------- ATTACK ORDER ---------------------
	
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
		return incomeUpgradeCost[level + 1];
	}
	
	public Player getPlayer(int player)
	{
		if(player < 1  || player > 2)
		{
			return null;
		}
		else if(player==1)
			return this.player1;
		return this.player2;
	}

	public int getMaxIncomeLevel() {
		return maxIncomeLevel;
	}
	
	
}
