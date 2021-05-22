package logic.gmanager;

import java.util.ArrayList;

import exception.InvalidPlayerException;
import exception.SelectInvalidTileException;
import gui.SceneController;
import gui.cell.TileCell;
import javafx.application.Platform;
import logic.actions.AttackAction;
import logic.actions.AttackPhaseAction;
import logic.misc.Coordinate;
import logic.towers.AimableTower;
import logic.towers.AttackableTower;
import logic.towers.BaseTower;
import logic.towers.Passive;

/**
 * A class which contains static methods and fields required to manage a game.
 *
 */
public class GameManager {

	private static GameInstance gameInstance;

	private static ButtonMode buttonMode;
	private static TurnPhase turnPhase;
	private static BaseTower selectedTower;
	private static int startingPlayer;
	private static int currentPlayer;
	private static int maxDeckSize = 5;
	private static int currentTurn;
	private static Coordinate selectedTile;
	
	private static String turnPhaseString;
	private static String currentPlayerString;
	private static String currentTurnString;

	/**
	 * Initializes a game
	 */
	public static void initialize() {
		createNewGame();
	}

	/**
	 * returns the id of the opposing player
	 * @param player player to query
	 * @return 1 or 2
	 */
	private static int flipPlayer(int player) {
		if (player == 1)
			return 2;
		return 1;
	}

	/**
	 * Getter for the max deck size
	 * @return {@link #maxDeckSize}
	 */
	public static int getMaxDeckSize() {
		return maxDeckSize;
	}

	/**
	 * Setter for {@link #maxDeckSize}
	 * @param maxDeckSize {@link #maxDeckSize}
	 */
	public static void setMaxDeckSize(int maxDeckSize) {
		GameManager.maxDeckSize = maxDeckSize;
	}

	/**
	 * Flips the starting player of the next turn
	 */
	public static void flipStartingPlayer() {
		GameManager.setStartingPlayer(GameManager.flipPlayer(GameManager.startingPlayer));
	}

	/**
	 * Flips the current player
	 */
	public static void flipCurrentPlayer() {
		GameManager.setCurrentPlayer(GameManager.flipPlayer(GameManager.currentPlayer));
	}

	/**
	 * Getter for current turn phase
	 * @return {@link #turnPhase}
	 */
	public static TurnPhase getTurnPhase() {
		return turnPhase;
	}

	/**
	 * Setter for {@link #turnPhase}
	 * @param turnPhase {@link #turnPhase}
	 */
	public static void setTurnPhase(TurnPhase turnPhase) {
		GameManager.turnPhase = turnPhase;
	}

	/**
	 * Getter for {@link #currentPlayer}
	 * @param currentPlayer {@link #currentPlayer}
	 */
	public static void setCurrentPlayer(int currentPlayer) {
		GameManager.currentPlayer = currentPlayer;
	}

	/**
	 * Setter for {@link #buttonMode}
	 * @param buttonMode {@link #buttonMode}
	 */
	public static void setButtonMode(ButtonMode buttonMode) {
		GameManager.buttonMode = buttonMode;
	}

	/**
	 * Getter for {@link #startingPlayer}
	 * @return {@link #startingPlayer}
	 */
	public static int getStartingPlayer() {
		return startingPlayer;
	}

	/**
	 * Setter for {@link #startingPlayer}
	 * @param startingPlayer {@link #startingPlayer}
	 */
	public static void setStartingPlayer(int startingPlayer) {
		GameManager.startingPlayer = startingPlayer;
	}

	/**
	 * Getter for {@link #currentPlayer}
	 * @return {@link #currentPlayer}
	 */
	public static int getCurrentPlayer() {
		return GameManager.currentPlayer;
	}

	/**
	 * Returns the tooltip for the current player's income upgrade button
	 * @return Tooltip String
	 */
	public static String getCurrentPlayerIncomeToolTip() {
		return GameManager.getGameInstance().getPlayer(currentPlayer).getIncomeToolTip();
	}

	/**
	 * Getter for {@link #buttonMode}
	 * @return {@link #buttonMode}
	 */
	public static ButtonMode getButtonMode() {
		return GameManager.buttonMode;
	}

	/**
	 * Getter for {@link #gameInstance}
	 * @return {@link #gameInstance}
	 */
	public static GameInstance getGameInstance() {
		return GameManager.gameInstance;
	}

	/**
	 * Setter for {@link #gameInstance}
	 * @param gameInstance {@link #gameInstance}
	 */
	public static void setGameInstance(GameInstance gameInstance) {
		GameManager.gameInstance = gameInstance;
	}

	/**
	 * Creates a new game
	 */
	public static void createNewGame() {
		GameManager.setGameInstance(new GameInstance());
		currentTurn = 1;
	}
	
	/**
	 * Starts a new game
	 */
	public static void startGame() {
		SceneController.newGameScene();
//		GameManager.setGamePhaseInfo();
		
//		SceneController.getGamePane().getPlayerPane(1).updateIncome();
//		SceneController.getGamePane().getPlayerPane(2).updateIncome();
//
//		SceneController.getGamePane().getPlayerPane(1).updateMoney();
//		SceneController.getGamePane().getPlayerPane(2).updateMoney();
	}

	/**
	 * Ends a game with a winning player.
	 * @param player 1 if player 1 wins, 2 if player 2 wins, 3 if tie.
	 */
	public static void victory(int player) {
		// TBD: Game victory handler
		SceneController.getGameScene().gameEnd(player);
	}
	
	/**
	 * Starts another game.
	 */
	public static void playAgain(){
		GameButtons.proceedGamePhase();
		GameManager.getGameInstance().reset();
		currentTurn = 1;
		GameManager.setButtonMode(ButtonMode.BUILD);
		GameManager.setTurnPhase(TurnPhase.BUILD);
		SceneController.getGameScene().closeGameEnd();
		setGamePhaseInfo();
		SceneController.getGamePane().updateScreen();
	}

	// ---------------------- TURN PROCESSOR : ATTACK
	// -----------------------------------

	/**
	 * Process all the attacking action on board.
	 */
	public static void processAttackPhase() {
		new Thread( () -> {
			Platform.runLater(() -> SceneController.getGamePane().inAnimation(true));
			for (AttackPhaseAction i : GameManager.getGameInstance().getAttackOrder()) {
				if (i instanceof AttackAction) {
					SceneController.getGamePane().getTilesPane().getTileCell(((AttackAction)i).getTrigger()).attackAnimation();
				}
				i.processAction();
				Platform.runLater(() -> SceneController.getGamePane().updateHp());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Platform.runLater(() -> {
				processAftermath();
				GameButtons.proceedGamePhase();
				SceneController.getGamePane().inAnimation(false);
			});
		}).start();
	}

	/**
	 * Make a tower at a certain tile perform an attacking animation
	 * @param loc the tile to perform
	 */
	public static void doDamageAnimation(Coordinate loc) {
		SceneController.getGamePane().getTilesPane().getTileCell(loc).takeDamageAnimation();
	}
	
	/**
	 * Called when player clicks a tile during the attacking phase
	 * @param loc clicked tile
	 * @param player clicking player
	 * @throws SelectInvalidTileException Can't select tile in that moment
	 */
	public static void selectAttackPhaseTile(Coordinate loc, int player) throws SelectInvalidTileException {
		if (GameManager.getButtonMode() == ButtonMode.AIM) {
			if (GameManager.targetAimable(loc, player)) {
				GameManager.buttonMode = ButtonMode.SELECT;
				GameManager.queueAttack(GameManager.selectedTile, player);
				updateAttackSeqTile();
				return;
			} else {
				GameManager.buttonMode = ButtonMode.SELECT;
				throw new SelectInvalidTileException("Aim out of range");

			}
		} else if (GameManager.getButtonMode() == ButtonMode.SELECT) {
			GameManager.selectAttackingTile(loc, player);
		} else
			throw new SelectInvalidTileException("Wrong button mode");

	}

	/**
	 * Called when the clicked tile will attempt to queue an attack action
	 * @param loc clicked tile
	 * @param player clicking player
	 * @throws SelectInvalidTileException Can't select tile in that moment
	 */
	private static void selectAttackingTile(Coordinate loc, int player) throws SelectInvalidTileException {
		Tile selectedTile = GameManager.getGameInstance().getBoard().getTile(loc);
		if (selectedTile.getTower() == null)
			throw new SelectInvalidTileException("No tower in this tile");
		if (selectedTile.getTower().getOwner() != player)
			throw new SelectInvalidTileException("Wrong owner");
		if(!(selectedTile.getTower() instanceof AttackableTower))
			throw new SelectInvalidTileException("This unit can't attack");
		//System.out.println("Can attack: "+((AttackableTower) selectedTile.getTower()).canAttack()+" Cooldown: "+((AttackableTower) selectedTile.getTower()).getCurrentCooldown());
		if (!((AttackableTower) selectedTile.getTower()).canAttack())
			throw new SelectInvalidTileException("This unit can't attack");
		if (selectedTile.isMarkAttacked())
		{
			unqueueAttack(loc, player);
			
		}

		else if (selectedTile.getTower() instanceof AimableTower) {
			GameManager.selectAimable(loc, player);
		}
		
		else
		{
			queueAttack(loc, player);
		}
		updateAttackSeqTile();
	}
	
	/**
	 * Update the attack sequence number of a tile.
	 */
	private static void updateAttackSeqTile() {
		ArrayList<AttackPhaseAction> attackOrders = GameManager.getGameInstance().getAttackOrder();
		if (attackOrders.size() <= 0) {
			return;
		}
		int start = SceneController.getGamePane().getTilesPane().getTileCell(((AttackAction)attackOrders.get(0)).getTrigger()).getTile().getTileOwner();
		int i=1;
		for (AttackPhaseAction attackOrder : attackOrders) {
			AttackAction attack = (AttackAction)attackOrder;
			TileCell tileCell = SceneController.getGamePane().getTilesPane().getTileCell(attack.getTrigger());
			if (tileCell.getTile().getTileOwner() != start) {
				start = tileCell.getTile().getTileOwner();
				i = 1;
			}
			tileCell.setAttackSeq(i);
			i++;
		}
	}
	
	/**
	 * Clears the attack sequence number of a tile.
	 */
	private static void clearAttackSeqTile() {
		ArrayList<AttackPhaseAction> attackOrders = GameManager.getGameInstance().getAttackOrder();
		for (int i=0;i<attackOrders.size();i++) {
			AttackAction attackOrder = (AttackAction)attackOrders.get(i);
			TileCell cell = SceneController.getGamePane().getTilesPane().getTileCell(attackOrder.getTrigger());
			cell.setAttackSeq(0);
			cell.setDefaultFill();
		}
	}

	/**
	 * Attempts to queue an attack from a location for a player
	 * @param loc location to queue
	 * @param player player to queue for
	 */
	private static void queueAttack(Coordinate loc, int player) {
		//Tile selectedTile = GameManager.getGameInstance().getBoard().getTile(loc);
		GameManager.getGameInstance().getBoard().getTile(loc).setMarkAttacked(true);
		GameManager.getGameInstance().addAttackOrder(new AttackAction(loc));
		updateAttackSeqTile();
	}

	/**
	 * Unqueues a tile from the attacking sequence.
	 * @param loc location to unqueue
	 * @param player player to unqueue for.
	 */ 
	private static void unqueueAttack(Coordinate loc, int player) {
		clearAttackSeqTile();
		GameManager.getGameInstance().removeAttackOrder(new AttackAction(loc));
		updateAttackSeqTile();
	}

	/**
	 * Selects an aimableTower at a location.
	 * @param loc location to select
	 * @param player player to select for
	 */
	private static void selectAimable(Coordinate loc, int player) {
		GameManager.buttonMode = ButtonMode.AIM;
		GameManager.selectedTile = loc;
		//System.out.println("Aiming a tower");
	}

	/**
	 * Selects a target for an aimableTower
	 * @param loc target to select
	 * @param player player to select for
	 * @return whether or not the selection suceeds.
	 */
	private static boolean targetAimable(Coordinate loc, int player) {

		//System.out.println("Targeting a tower");
		return ((AimableTower) GameManager.getGameInstance().getBoard().getTile(GameManager.selectedTile).getTower())
				.setTarget(loc);
	}

	// ---------------------- TURN PROCESSOR : BUILDING AND UPGRADING
	// -----------------------------------

	/**
	 * Called when a tile is clicked during the building phase
	 * @param loc clicked tile
	 * @param player clicking player
	 * @throws SelectInvalidTileException Can't select tile in that moment
	 */
	public static void selectBuildPhaseTile(Coordinate loc, int player) throws SelectInvalidTileException {
		if (GameManager.buttonMode == ButtonMode.BUILD) {
			if(GameManager.getSelectedTower() != null)
				GameManager.buildTower(GameManager.selectedTower, loc, player);
			return;
		} else if (GameManager.buttonMode == ButtonMode.UPGRADE) {
			GameManager.upgradeTower(loc, player);
		} else if (GameManager.buttonMode == ButtonMode.DESTROY) {
			GameManager.removeTower(loc, player);
		} else
			throw new SelectInvalidTileException("Invalid buttonmode");
	}

	/**
	 * Attempts to place a unit on a tile for a player
	 * @param tower unit to place
	 * @param loc location to place
	 * @param player placing player
	 * @throws SelectInvalidTileException Can't select tile in that moment
	 */
	private static void buildTower(BaseTower tower, Coordinate loc, int player) throws SelectInvalidTileException {
		if (tower == null) {
			throw new SelectInvalidTileException("No tower select to build");
		}
		int cost = tower.getCost();
		Player currentPlayer = GameManager.getGameInstance().getPlayer(player);
		if (currentPlayer.getMoney() < tower.getCost()) {
			throw new SelectInvalidTileException("Not enough money");
		}
		if(GameManager.getGameInstance().getBoard().getTile(loc).getTileOwner() != currentPlayer.getPlayerId())
		{
			throw new SelectInvalidTileException("Wrong owner");
		}
		if (GameManager.getGameInstance().getBoard().getTile(loc).getTower() != null) {
			throw new SelectInvalidTileException("Already have tower on this tile");
		} else {
			currentPlayer.spendMoney(cost);
			BaseTower newTower = tower.getNewInstance(loc);
			newTower.setOwner(player);
			GameManager.getGameInstance().getBoard().getTile(loc).setTower(newTower);
		}
	}

	/**
	 * Upgrades the tower in the tile.
	 * @param loc location to be upgraded
	 * @param player player who is upgrading
	 * @throws SelectInvalidTileException Can't select tile in that moment
	 */
	public static void upgradeTower(Coordinate loc, int player) throws SelectInvalidTileException {
		BaseTower selectedTower = GameManager.getGameInstance().getBoard().getTile(loc).getTower();
		Player currentPlayer = GameManager.getGameInstance().getPlayer(player);
		if (selectedTower == null) {
			throw new SelectInvalidTileException("No tower to upgrade");
		}
		if (selectedTower.getOwner() != player) {
			throw new SelectInvalidTileException("Wrong owner");
		}
		if (selectedTower.getUpgradeLevel() >= selectedTower.getMaxUpgradeLevel()) {
			throw new SelectInvalidTileException("Max upgrade");

		}
		int cost = selectedTower.getCurrentUpgradeCost();
		if (cost <= currentPlayer.getMoney()) {
			currentPlayer.spendMoney(cost);
			selectedTower.upgrade();
			return;
		}
		throw new SelectInvalidTileException("Not enough money to upgrade");

	}

	/**
	 * Attempts to retire a unit from a location
	 * @param loc location to retire
	 * @param player player attempting to reture
	 * @throws SelectInvalidTileException Can't select tile in that moment
	 */
	public static void removeTower(Coordinate loc, int player) throws SelectInvalidTileException {
		BaseTower selectedTower = GameManager.getGameInstance().getBoard().getTile(loc).getTower();
		if (selectedTower == null) {
			throw new SelectInvalidTileException("No tower to delete");
		}
		if (selectedTower.getOwner() != player) {
			throw new SelectInvalidTileException("Wrong owner to remove");
		} else {
			GameManager.getGameInstance().getBoard().getTile(loc).setTower(null);
		}
	}

	/**
	 * Getter for {@link #selectedTower}
	 * @return {@link #selectedTower}
	 */
	public static BaseTower getSelectedTower() {
		return selectedTower;
	}

	/**
	 * Setter for {@link #selectedTower}
	 * @param selectedTower {@link #selectedTower}
	 */
	public static void setSelectedTower(BaseTower selectedTower) {
		GameManager.selectedTower = selectedTower;
	}

	// ----------------------- Aftermath phase ------------------------------

	/**
	 * Processes what comes after the attacking phase such as applying freeze, activating passives and removed heath less than equal 0 towers.
	 */
	public static void processAftermath() {
		Board board = GameManager.getGameInstance().getBoard();
		BaseTower b;
		int i, j;
		for (i = 0; i < board.getLanes(); i++) {
			for (j = 0; j < board.getBorder(); j++) {
				b = board.getTile(new Coordinate(i, j)).getTower();
				if (b != null) {
					if (b.getCurrentHealth() > b.getMaxHealth())
					{
						b.removeOverheal();
					}
					if (b.getCurrentHealth() <= 0) {
						board.getTile(b.getLoc()).removeTower();
					}
					else if (b instanceof Passive) {
						try {
							((Passive) b).doPassive();
							SceneController.getGamePane().getTilesPane().getTileCell(b.getLoc()).attackAnimation();
						} catch (InvalidPlayerException e) {
							e.printStackTrace();
						}
					} else if (b instanceof AttackableTower) {
						((AttackableTower) b).doCooldown();
					}
					else if(b instanceof AimableTower)
					{
						((AimableTower) b).doCooldown();
					}
					b.defrost();
					b.applyFreeze();
				}
			}
		}
		
		clearAttackSeqTile();
		GameManager.getGameInstance().clearAttackOrder();
		
		if (checkWinner() == -1) {
			GameManager.getGameInstance().getPlayer(1).applyIncome();
			GameManager.getGameInstance().getPlayer(2).applyIncome();
			currentTurn++;
		}else {
			victory(checkWinner());
		}
		
		
	}
	
	/**
	 * Check who won the game if any.
	 * @return -1 if no one, otherwise returns the winner's id (3 if tie)
	 */
	private static int checkWinner() {
		int hp1 = GameManager.getGameInstance().getPlayer(1).getHealth();
		int hp2 = GameManager.getGameInstance().getPlayer(2).getHealth();
		if (hp1<=0 || hp2<=0) {
			if (hp1<hp2) {
				return 2;
			}
			if (hp2<hp1) {
				return 1;
			}
			return 3;
		}
		return -1;
	}
	
	// ---------------- GAME PHASE UPDATER ---------------------------
	
	/**
	 * Updates the game info
	 */
	public static void setGamePhaseInfo()
	{
		
		switch(GameManager.getCurrentPlayer())
		{
		case 1:
			GameManager.currentPlayerString = "Player 1's turn";
			break;
		case 2:
			GameManager.currentPlayerString = "Player 2's turn";
			break;
		}
		
		// turn phase
				switch(GameManager.getTurnPhase())
				{
				case AFTERMATH:
					GameManager.turnPhaseString = "Attack";
					GameManager.currentPlayerString = "";
					break;
				case ATTACK:
					GameManager.turnPhaseString = "Strategy";
					break;
				case BUILD:
					GameManager.turnPhaseString = "Management";
					break;
				default:
					break;
				}
		
		currentTurnString = "Turn " + currentTurn;
		
		SceneController.getGamePane().getTurnStatus().updateTurnPane();
		
		
	}

	/**
	 * Getter for {@link #turnPhaseString}
	 * @return {@link #turnPhaseString}
	 */
	public static String getTurnPhaseString() {
		return turnPhaseString;
	}

	/**
	 * Getter for {@link #currentPlayerString}
	 * @return {@link #currentPlayerString}
	 */
	public static String getCurrentPlayerString() {
		return currentPlayerString;
	}

	/**
	 * Getter for {@link #currentTurnString}
	 * @return {@link #currentTurnString}
	 */
	public static String getCurrentTurnString() {
		return currentTurnString;
	}
	
	/**
	 * Getter for {@link #selectedTile}'s Tower
	 * @return {@link #selectedTile}'s Tower
	 */
	public static BaseTower getSelectedTileTower() {
		return GameManager.getGameInstance().getBoard().getTile(GameManager.selectedTile).getTower();
	}
	
	

}
