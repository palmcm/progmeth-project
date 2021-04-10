package logic.menu;

import java.util.ArrayList;

import exception.InvalidPlayerException;
import logic.gmanager.GameManager;
import logic.gmanager.Player;
import logic.towers.BaseTower;

public class DeckSelector {
	
	private static boolean flipSelector = true;
	
	// ---------------- WHEN CLICKING A TOWER ---------------------
	
	public static void clickSelectCard(BaseTower tower)
	{
		if(addCardToPlayer(tower,GameManager.getCurrentPlayer()))
		{
			if(DeckSelector.flipSelector == true)
			{
				GameManager.flipCurrentPlayer();				
			}
			else
			{
				DeckSelector.flipSelector = !DeckSelector.flipSelector;
			}
		}
	}
	
	// ---------------- WHEN CLICKING START GAME ------------------
	
	public static void clickBeginGame()
	{
		
	}
	
	// ------------------------------------------------------------
	
	public static boolean addCardToPlayer(BaseTower tower, int player)
	{
		try {
			
			Player currentPlayer = GameManager.getGameInstance().getPlayer(player);
			if(currentPlayer.hasTowerInDeck(tower))
				return false;
			if(currentPlayer.getDeck().size() >= GameManager.getMaxDeckSize())
				return false;
			currentPlayer.addTowerToDeck(tower);
			return true;
			
			
		} catch (InvalidPlayerException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static ArrayList<BaseTower> getPlayerCards(int player)
	{
		try {
			return GameManager.getGameInstance().getPlayer(player).getDeck();
		} catch (InvalidPlayerException e) {
			return null;
		}
	}
	
	public static BaseTower getPlayerCard(int player,int idx)
	{
		try {
			return GameManager.getGameInstance().getPlayer(player).getDeck().get(idx);
		} catch (InvalidPlayerException e) {
			return null;
		}
		
	}

	public static void setFlipSelector(boolean flipSelector) {
		DeckSelector.flipSelector = flipSelector;
	}
}
