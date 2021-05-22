package logic.gmanager;

import exception.SelectInvalidTileException;
import gui.SceneController;
import logic.misc.Coordinate;
import logic.towers.BaseTower;

/**
 * A class which provides static method for game actions caused by pressing a button or tile.
 *
 */
public class GameButtons {

	// --------------------- BOARD INTERACTION ------------------------

	/**
	 * Called when selecting a tile at a location
	 * @param loc Location
	 * @throws SelectInvalidTileException Can't select tile in that moment
	 */
	public static void selectTile(Coordinate loc) throws SelectInvalidTileException {

		//System.out.println(loc.getX()+","+loc.getY());
		
		int player = GameManager.getCurrentPlayer();
		if (GameManager.getTurnPhase() == TurnPhase.ATTACK) {
			GameManager.selectAttackPhaseTile(loc, player);
		} else if (GameManager.getTurnPhase() == TurnPhase.BUILD) {
			GameManager.selectBuildPhaseTile(loc, player);
		} else {
			throw new SelectInvalidTileException("Can't select tile in this phase");
		}
	}

	// --------------------- NEXT PHASE BUTTON -----------------------

	/**
	 * Called when end turn button is pressed
	 */
	public static void proceedGamePhase() {
		if (GameManager.getCurrentPlayer() == GameManager.getStartingPlayer()) {
			GameManager.flipCurrentPlayer();
		} else {
			if (GameManager.getTurnPhase() == TurnPhase.BUILD) {
				GameManager.flipStartingPlayer();
				GameManager.setTurnPhase(TurnPhase.ATTACK);
				SceneController.getGamePane().updateDeck();
			} else if (GameManager.getTurnPhase() == TurnPhase.ATTACK) {
				GameManager.setTurnPhase(TurnPhase.AFTERMATH);
				GameManager.processAttackPhase();
			} else if (GameManager.getTurnPhase() == TurnPhase.AFTERMATH) {
//				GameManager.processAftermath();
				GameManager.flipCurrentPlayer();
				GameManager.setStartingPlayer(GameManager.getCurrentPlayer());
				GameManager.setTurnPhase(TurnPhase.BUILD);
			}

		}	
		
		if (GameManager.getTurnPhase() == TurnPhase.BUILD) {
			SceneController.getGamePane().updateDeck();
			GameManager.setButtonMode(ButtonMode.BUILD);
		} else if (GameManager.getTurnPhase() == TurnPhase.ATTACK) {
			GameManager.setButtonMode(ButtonMode.SELECT);
		} else if (GameManager.getTurnPhase() == TurnPhase.AFTERMATH) {
			GameManager.setButtonMode(ButtonMode.NONE);
		}

		GameManager.setGamePhaseInfo();

	}

	// --------------------- UPGRADE INCOME -----------------------------

	/**
	 * Called when the upgrade income button is pressed
	 * @return whether or not the upgrade succeeds
	 */
	public static boolean upgradeIncome() {
		if (GameManager.getTurnPhase() != TurnPhase.BUILD) {
			return false;
		}
		return GameManager.getGameInstance().getPlayer(GameManager.getCurrentPlayer()).upgradeIncome();
	}

	// --------------------- UPGRADE MODE --------------------------------
	/**
	 * Called when the upgrade unit button is pressed.
	 * @return whether or not the upgrade succeeds
	 */
	public static boolean upgradeMode() {
		if (GameManager.getTurnPhase() != TurnPhase.BUILD) {
			return false;
		}
		GameManager.setButtonMode(ButtonMode.UPGRADE);
		return true;
	}

	// ---------------------- DESTROY MODE -------------------------------

	/**
	 * Called when the retire unit button is pressed.
	 * @return whether or not the retire succeeds
	 */
	public static boolean destroyMode() {
		if (GameManager.getTurnPhase() != TurnPhase.BUILD) {
			return false;
		}
		GameManager.setButtonMode(ButtonMode.DESTROY);
		return true;
	}

	// --------------------- SELECT TOWER -------------------------------

	/**
	 * Called when player selects a tower from the tower panel.
	 * @param tower selected tower
	 */
	public static void selectTower(BaseTower tower) {
		if (GameManager.getTurnPhase() != TurnPhase.BUILD) {
			return;
		} else {
			GameManager.setButtonMode(ButtonMode.BUILD);
			GameManager.setSelectedTower(tower);
		}

	}
}
