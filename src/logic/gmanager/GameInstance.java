package logic.gmanager;

import java.util.ArrayList;

import logic.actions.AttackAction;
import logic.actions.AttackPhaseAction;
import logic.menu.DeckSelector;
import logic.misc.Coordinate;

/**
 * A class which contains information and method regarding the current game.
 *
 */
public class GameInstance {
	
	private Board board;
	
	//private int maxIncomeLevel;
	//private int income;
	//private int incomeUpgradeCost;
	//private int startingIncome;
	private Player player1,player2;
	
	private ArrayList<AttackPhaseAction> attackOrder;
	private ArrayList<AttackPhaseAction> persistentEffects;
	
	private int turnNumber;
	
	/**
	 * Creates a new game/match
	 */
	public GameInstance()
	{
		this.board = new Board();
		//this.maxIncomeLevel = 5;
		//this.income = 5;
		//this.incomeUpgradeCost = 25;
		this.turnNumber = 1;
		
		this.player1 = new Player(1);
		this.player2 = new Player(2);
		
		GameManager.setStartingPlayer(1);
		GameManager.setCurrentPlayer(1);
		
		GameManager.setTurnPhase(TurnPhase.BUILD);
		
		this.attackOrder = new ArrayList<AttackPhaseAction>();
		this.persistentEffects = new ArrayList<AttackPhaseAction>();
		
		DeckSelector.setFlipSelector(true);
		
		
	}
	
	/**
	 * Resets a game
	 */
	public void reset() {
		GameManager.setStartingPlayer(1);
		GameManager.setCurrentPlayer(1);
		
		GameManager.setTurnPhase(TurnPhase.BUILD);
		DeckSelector.setFlipSelector(true);
		
		for (int i=0;i<board.getLanes();i++) {
			for (int j=0;j<board.getBorder();j++) {
				board.getTiles()[i][j].setTower(null);
			}
		}
		
		attackOrder.clear();
		persistentEffects.clear();
		player1.reset();
		player2.reset();
	}
	// ---------------- ATTACK ORDER ---------------------
	
	/**
	 * Clears the attack order from the last turn
	 */
	public void clearAttackOrder()
	{
		//System.out.println("Clearing previous attack orders...");
		this.attackOrder.clear();
		int i,j;
		for(i=0;i<this.getBoard().getLanes();i++)
		{
			for(j=0;j<this.getBoard().getBorder();j++)
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
	
	/**
	 * Adds an attack order to the turn
	 * @param attack action to add
	 */
	public void addAttackOrder(AttackPhaseAction attack)
	{
		attackOrder.add(attack);
		if(attack instanceof AttackAction)
		{
			this.getBoard().getTile(((AttackAction) attack).getTrigger()).setMarkAttacked(true);			
		}
	}
	
	/**
	 * Removes an attack order from the turn
	 * @param attack action to remove
	 */
	public void removeAttackOrder(AttackPhaseAction attack)
	{
		if(attack instanceof AttackPhaseAction)
		{
			int removedIndex = -1;
			for(int i=0;i<attackOrder.size();i++)
			{
				if(attackOrder.get(i) instanceof AttackAction)
				{
					AttackAction j = (AttackAction) attackOrder.get(i);
					AttackAction k = (AttackAction) attack;
					if(j.getTrigger().getX() == k.getTrigger().getX() && j.getTrigger().getY() == k.getTrigger().getY())
					{
						removedIndex = i;
					}
				}
			}
			//attackOrder.remove(attack);
			if(removedIndex != -1)
			{
				attackOrder.remove(removedIndex);
			}
			if(attack instanceof AttackAction)
			{
				this.getBoard().getTile(((AttackAction) attack).getTrigger()).setMarkAttacked(false);			
			}
			
		}
	}
	
	/**
	 * Getter for {@link #attackOrder}
	 * @return {@link #attackOrder}
	 */
	public ArrayList<AttackPhaseAction> getAttackOrder() {
		return this.attackOrder;
	}
	
	
	
	// ---------------- ATTACK ORDER ---------------------
	
	/**
	 * Getter for {@link #board}
	 * @return {@link #board}
	 */
	public Board getBoard()
	{
		return this.board;
	}
	
	/**
	 * Getter for a player with a specific id
	 * @param player id to get
	 * @return {@link #player1} or {@link #player2}
	 */
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
	
	//  ---------------- turn number --------------------
	
	/**
	 * Increments the turn number
	 */
	public void incrementTurnNumber()
	{
		this.turnNumber++;
	}

	/**
	 * Getter for {@link #turnNumber}
	 * @return {@link #turnNumber}
	 */
	public int getTurnNumber() {
		return turnNumber;
	}
	
	
	
}
