package logic.gmanager;

import exception.SelectInvalidTileException;
import gui.SceneController;
import logic.misc.Coordinate;
import logic.towers.BaseTower;

public class GameButtons {

	// --------------------- BOARD INTERACTION ------------------------

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

	public static boolean upgradeIncome() {
		if (GameManager.getTurnPhase() != TurnPhase.BUILD) {
			return false;
		}
		return GameManager.getGameInstance().getPlayer(GameManager.getCurrentPlayer()).upgradeIncome();
	}

	// --------------------- UPGRADE MODE --------------------------------
	public static boolean upgradeMode() {
		if (GameManager.getTurnPhase() != TurnPhase.BUILD) {
			return false;
		}
		GameManager.setButtonMode(ButtonMode.UPGRADE);
		return true;
	}

	// ---------------------- DESTROY MODE -------------------------------

	public static boolean destroyMode() {
		if (GameManager.getTurnPhase() != TurnPhase.BUILD) {
			return false;
		}
		GameManager.setButtonMode(ButtonMode.DESTROY);
		return true;
	}

	// --------------------- SELECT TOWER -------------------------------

	public static void selectTower(BaseTower tower) {
		if (GameManager.getTurnPhase() != TurnPhase.BUILD) {
			return;
		} else {
			GameManager.setButtonMode(ButtonMode.BUILD);
			GameManager.setSelectedTower(tower);
		}

	}
}
