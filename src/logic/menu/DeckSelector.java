package logic.menu;

import java.util.ArrayList;

import exception.InvalidPlayerException;
import logic.gmanager.GameManager;
import logic.gmanager.Player;
import logic.towers.BaseTower;

public class DeckSelector {
	
	public static BaseTower addCardToPlayer(BaseTower tower, int player)
	{
		try {
			
			Player currentPlayer = GameManager.getGameInstance().getPlayer(player);
			if(currentPlayer.hasTowerInDeck(tower))
				return null;
			currentPlayer.addTowerToDeck(tower);
			return tower;
			
			
		} catch (InvalidPlayerException e) {
			e.printStackTrace();
			return null;
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
}
