package logic.menu;

import java.util.ArrayList;

import exception.InvalidPlayerException;
import gui.SceneController;
import logic.gmanager.GameManager;
import logic.gmanager.GameSettings;
import logic.gmanager.Player;
import logic.towers.BaseTower;

public class DeckSelector {

	private static boolean flipSelector = true;
	private static boolean freestyleMode = false;

	// ---------------- WHEN CLICKING A TOWER ---------------------

	public static void clickSelectCard(BaseTower tower) {
		if (GameManager.getGameInstance().getPlayer(1).getDeck().size() == GameSettings.getDeckSize()
				&& GameManager.getGameInstance().getPlayer(2).getDeck().size() == GameSettings.getDeckSize() && !freestyleMode) {
			return;
		}
		if (addCardToPlayer(tower, GameManager.getCurrentPlayer())) {
			if (flipSelector == true && !freestyleMode) {
				GameManager.flipCurrentPlayer();
			}
			flipSelector = !flipSelector;
		}
		canNext();
	}

	// ---------------- WHEN CLICKING START GAME ------------------

	public static void clickBeginGame() {
		GameManager.setCurrentPlayer(1);
		SceneController.newGameScene();
		SceneController.getGamePane().updateDeck();
		SceneController.setScene("game");
	}

	// ------------------------------------------------------------

	public static boolean addCardToPlayer(BaseTower tower, int player) {

		Player currentPlayer = GameManager.getGameInstance().getPlayer(player);
		int index = currentPlayer.getTowerIndexDeck(tower);
		if (index != -1) {
			if (freestyleMode) {
				currentPlayer.removeTowerFromDeck(index);
			}
			return false;
		}
		if (currentPlayer.getDeck().size() >= GameManager.getMaxDeckSize())
			return false;
		currentPlayer.addTowerToDeck(tower);
		return true;

	}

	public static ArrayList<BaseTower> getPlayerCards(int player) {
		return GameManager.getGameInstance().getPlayer(player).getDeck();
	}

	public static BaseTower getPlayerCard(int player, int idx) {
		return GameManager.getGameInstance().getPlayer(player).getDeck().get(idx);

	}

	public static void setFlipSelector(boolean flipSelector) {
		DeckSelector.flipSelector = flipSelector;
	}
	
	public static void nextHandler() {
		if (GameManager.getGameInstance().getPlayer(1).getDeck().size() == GameSettings.getDeckSize()
				&& GameManager.getGameInstance().getPlayer(2).getDeck().size() == GameSettings.getDeckSize()) {
			clickBeginGame();
			return;
		}
		if (GameManager.getGameInstance().getPlayer(GameManager.getCurrentPlayer()).getDeck().size() == GameSettings.getDeckSize()) {
			GameManager.flipCurrentPlayer();
			SceneController.getPickTowerScene().highlightdeck();
			return;
		}
	}
	
	public static void canNext() {
		if (GameManager.getGameInstance().getPlayer(GameManager.getCurrentPlayer()).getDeck().size() == GameSettings.getDeckSize()) {
			SceneController.getPickTowerScene().setCanNext(true);
		}else {
			SceneController.getPickTowerScene().setCanNext(false);
		}
	}
}
