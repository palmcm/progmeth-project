package logic.gmanager;

public class GameSettings {

	private static int deckSize = 5;
	private static int MaxHealth = 100;
	private static int startingMoney = 50;
	private static int income = 5;
	private static int incomeUpgradeCost = 25;
	private static int startingIncome = 20;
	private static Board board = new Board();
	
	// ------------- SET DEFAULT -----------------
	public static void setDefault() {
		deckSize = 5;
		MaxHealth = 100;
		startingMoney = 50;
		income = 5;
	}
	
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
	public static int getIncome() {
		return income;
	}
	public static void setIncome(int income) {
		GameSettings.income = income;
	}
	public static int getIncomeUpgradeCost() {
		return incomeUpgradeCost;
	}
	public static void setIncomeUpgradeCost(int incomeUpgradeCost) {
		GameSettings.incomeUpgradeCost = incomeUpgradeCost;
	}
	public static int getStartingIncome() {
		return startingIncome;
	}
	public static void setStartingIncome(int startingIncome) {
		GameSettings.startingIncome = startingIncome;
	}
	
	
	
	
	
	
}
