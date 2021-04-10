package logic.gmanager;

public class GameSettings {

	private static int deckSize;
	private static int MaxHealth = 100;
	private static int startingMoney = 50;
	private static Board board = new Board();
	
	// ------------- GETTER AND SETTERS -----------------
	
	public static int getDeckSize() {
		return deckSize;
	}
	public static void setDeckSize(int deckSize) {
		GameSettings.deckSize = deckSize;
	}
	public static int getMaxHealth() {
		return MaxHealth;
	}
	public static void setMaxHealth(int maxHealth) {
		MaxHealth = maxHealth;
	}
	public static int getStartingMoney() {
		return startingMoney;
	}
	public static void setStartingMoney(int startingMoney) {
		GameSettings.startingMoney = startingMoney;
	}
	public static Board getBoard() {
		return board;
	}
	public static void setBoard(Board board) {
		GameSettings.board = board;
	}
	
	
	
	
}
