package logic.gmanager;

import exception.InvalidPlayerException;
import logic.actions.AttackAction;
import logic.actions.AttackPhaseAction;
import logic.misc.Coordinate;
import logic.towers.AimableTower;
import logic.towers.BaseTower;

public class GameManager {
	
	private static GameInstance gameInstance;
	
	private static ButtonMode buttonMode;
	private static TurnPhase turnPhase;
	private static BaseTower selectedTower;
	
	private static Coordinate selectedTile;
	
	public static ButtonMode getButtonMode()
	{
		return GameManager.buttonMode;
	}
	
	public static GameInstance getGameInstance()
	{
		return GameManager.gameInstance;
	}
	
	public static void setGameInstance(GameInstance gameInstance)
	{
		GameManager.gameInstance = gameInstance;
	}
	
	public static void StartNewGame()
	{
		GameManager.setGameInstance(new GameInstance());
	}
	
	public static void victory(int player)
	{
		// TBD: Game victory handler
		
	}
	
	// ---------------------- TURN PROCESSOR : CLICKING ANY TILES -----------------------

	
	public static boolean selectTile(Coordinate loc,int player)
	{
		if(GameManager.turnPhase == TurnPhase.ATTACK)
		{
			return GameManager.selectAttackPhaseTile(loc, player);
		}
		else if(GameManager.turnPhase == TurnPhase.BUILD)
		{
			return GameManager.buildTower(selectedTower, loc, player);
		}
		
		
		return false;
	}
	
	
	// ---------------------- TURN PROCESSOR : ATTACK -----------------------------------
	
	public static void processAttackPhase()
	{
		for(AttackPhaseAction i:GameManager.getGameInstance().getAttackOrder())
		{
			i.processAction();
		}
	}
	
	private static boolean selectAttackPhaseTile(Coordinate loc, int player)
	{
		if(GameManager.getButtonMode() == ButtonMode.AIM)
		{
			if(GameManager.targetAimable(loc, player))
			{
				GameManager.buttonMode = ButtonMode.SELECT;
				GameManager.queueAttack(GameManager.selectedTile, player);
				return true;
			}
			else
			{
				GameManager.buttonMode = ButtonMode.SELECT;
				return false;
				
			}
		}
		else if(GameManager.getButtonMode() == ButtonMode.SELECT)
		{
			return GameManager.selectAttackingTile(loc, player);
		}
		else return false;
			
	}
	
	private static boolean selectAttackingTile(Coordinate loc, int player)
	{
		Tile selectedTile = GameManager.getGameInstance().getBoard().getTile(loc);
		if(selectedTile.getTower() == null)
			return false;
		if(selectedTile.getTower().getOwner() != player)
			return false;
		if(selectedTile.isMarkAttacked())
			unqueueAttack(loc,player);
		else
			queueAttack(loc,player);
		return true;
	}
	
	private static void queueAttack(Coordinate loc,int player)
	{
		Tile selectedTile = GameManager.getGameInstance().getBoard().getTile(loc);
		if(selectedTile.getTower() instanceof AimableTower)
		{
			GameManager.selectAimable(loc,player);
			return;
		}
		GameManager.getGameInstance().addAttackOrder(new AttackAction(loc));
	}
	
	private static void unqueueAttack(Coordinate loc,int player)
	{
		GameManager.getGameInstance().removeAttackOrder(new AttackAction(loc));
	}
	
	private static void selectAimable(Coordinate loc, int player)
	{
		GameManager.buttonMode = ButtonMode.AIM;
		GameManager.selectedTile = loc;
	}
	
	private static boolean targetAimable(Coordinate loc,int player)
	{
		return ((AimableTower) GameManager.getGameInstance().getBoard().getTile(GameManager.selectedTile).getTower()).setTarget(loc);
	}
	
	
	// ---------------------- TURN PROCESSOR : BUILDING AND UPGRADING -----------------------------------
	
	public static boolean selectBuildPhaseTile(Coordinate loc,int player)
	{
		if(GameManager.buttonMode == ButtonMode.BUILD)
		{
			return GameManager.buildTower(GameManager.selectedTower, loc, player);
		}
		else if(GameManager.buttonMode == ButtonMode.UPGRADE)
		{
			return GameManager.upgradeTower(loc, player);
		}
		else if(GameManager.buttonMode == ButtonMode.DESTROY)
		{
			return GameManager.removeTower(loc, player);
		}
		else return false;
	}
	
	public static boolean buildTower(BaseTower tower, Coordinate loc, int player)
	{
		try {
			int cost = tower.getCost();
			Player currentPlayer = GameManager.getGameInstance().getPlayer(player);
			if(currentPlayer.getMoney() < tower.getCost())
			{
				return false;
			}
			if(GameManager.getGameInstance().getBoard().getTile(loc).getTower() != null)
			{
				return false;
			}
			else
			{
				currentPlayer.spendMoney(cost);
				BaseTower newTower = tower.getNewInstance(loc);
				GameManager.getGameInstance().getBoard().getTile(loc).setTower(newTower);
				return true;
				
			}
		} catch (InvalidPlayerException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean upgradeTower(Coordinate loc,int player)
	{
		try {
			
			BaseTower selectedTower = GameManager.getGameInstance().getBoard().getTile(loc).getTower();
			Player currentPlayer = GameManager.getGameInstance().getPlayer(player);
			
			if(selectedTower.getOwner()!=player)
			{
				return false;
			}
			if(selectedTower.getUpgradeLevel() >= selectedTower.getMaxUpgradeLevel())
			{
				return false;
				
			}

			int cost = selectedTower.getCurrentUpgradeCost();
			if(cost > currentPlayer.getMoney())
			{
				currentPlayer.spendMoney(cost);
				selectedTower.upgrade();
				return true;
			}
			return false;
			
			
			
		} catch (InvalidPlayerException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean removeTower(Coordinate loc,int player)
	{
		BaseTower selectedTower = GameManager.getGameInstance().getBoard().getTile(loc).getTower();
		if(selectedTower.getOwner() != player)
		{
			return false;
		}
		else
		{
			GameManager.getGameInstance().getBoard().getTile(loc).setTower(null);
			return true;
		}
	}

	public static BaseTower getSelectedTower() {
		return selectedTower;
	}

	public static void setSelectedTower(BaseTower selectedTower) {
		GameManager.selectedTower = selectedTower;
	}
	
	
	
	
}
