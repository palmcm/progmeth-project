package logic.gmanager;

import exception.InvalidPlayerException;
import logic.misc.Coordinate;
import logic.towers.BaseTower;

public class GameButtons {

	// --------------------- BOARD INTERACTION ------------------------

	public static boolean selectTile(Coordinate loc) {
		int player = GameManager.getCurrentPlayer();
		if (GameManager.getTurnPhase() == TurnPhase.ATTACK) {
			return GameManager.selectAttackPhaseTile(loc, player);
		} else if (GameManager.getTurnPhase() == TurnPhase.BUILD) {
			return GameManager.selectBuildPhaseTile(loc, player);
		}

		return false;
	}

	// --------------------- NEXT PHASE BUTTON -----------------------

	public static void proceedGamePhase() {
		if (GameManager.getCurrentPlayer() == GameManager.getStartingPlayer()) {
			GameManager.flipCurrentPlayer();
		} else {
			if (GameManager.getTurnPhase() == TurnPhase.BUILD) {
				GameManager.flipStartingPlayer();
				GameManager.setTurnPhase(TurnPhase.ATTACK);
			} else if (GameManager.getTurnPhase() == TurnPhase.ATTACK) {
				GameManager.setTurnPhase(TurnPhase.AFTERMATH);
				GameManager.processAttackPhase();
			} else if (GameManager.getTurnPhase() == TurnPhase.AFTERMATH) {
				GameManager.processAftermath();
			}

		}

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
