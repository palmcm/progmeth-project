package logic.menu;

import java.util.ArrayList;

import gui.SceneController;
import logic.gmanager.GameManager;
import logic.gmanager.GameSettings;
import logic.gmanager.Player;
import logic.towers.BaseTower;

/**
 * Logic for deck selecting
 */
public class DeckSelector {

	/** When to flip toggle everytime */
	private static boolean flipSelector = true;
	/** Whether free style pick is on*/
	private static boolean freestyleMode = false;

	// ---------------- WHEN CLICKING A TOWER ---------------------

	/**
	 * Handle when select tower
	 * @param tower selected tower
	 */
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

	/**
	 * Set scene to game scene and start game
	 */
	public static void clickBeginGame() {
		GameManager.setCurrentPlayer(1);
		SceneController.newGameScene();
		SceneController.getGamePane().updateDeck();
		SceneController.setScene("game");
	}

	// ------------------------------------------------------------

	/**
	 * Add tower to player deck
	 * @param tower selected tower
	 * @param player player deck owner
	 * @return successfully added
	 */
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

	/**
	 * Getter for player deck
	 * @param player owner of deck
	 * @return player deck
	 */
	public static ArrayList<BaseTower> getPlayerCards(int player) {
		return GameManager.getGameInstance().getPlayer(player).getDeck();
	}

	/**
	 * Get tower from player deck
	 * @param player owner of deck
	 * @param idx index from player deck
	 * @return selected tower
	 */
	public static BaseTower getPlayerCard(int player, int idx) {
		return GameManager.getGameInstance().getPlayer(player).getDeck().get(idx);

	}

	/**
	 * Setter for {@link #flipSelector flipSelector}
	 * @param flipSelector {@link #flipSelector flipSelector}
	 */
	public static void setFlipSelector(boolean flipSelector) {
		DeckSelector.flipSelector = flipSelector;
	}
	
	/**
	 * Handle for press next button
	 */
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
	
	/**
	 * Check if can press next
	 */
	public static void canNext() {
		if (GameManager.getGameInstance().getPlayer(GameManager.getCurrentPlayer()).getDeck().size() == GameSettings.getDeckSize()) {
			SceneController.getPickTowerScene().setCanNext(true);
		}else {
			SceneController.getPickTowerScene().setCanNext(false);
		}
	}
	
	/**
	 * Get string for option in setting for pick deck style
	 * @return Option string
	 */
	public static String getFreestyle() {
		if (freestyleMode) {
			return "Freestyle";
		}
		return "Pattern Lock";
	}
	
	/**
	 * Toggle {@link #freestyleMode}
	 */
	public static void toggleFreestyle() {
		freestyleMode = !freestyleMode;
	}
}
