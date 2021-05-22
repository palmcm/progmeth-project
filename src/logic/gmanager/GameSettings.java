package logic.gmanager;

/**
 * Data for game setting and set in to game when start
 */
public class GameSettings {

	private static int deckSize = 5;
	private static int MaxHealth = 100;
	private static int startingMoney = 50;
	private static int income = 5;
	private static int incomeUpgradeCost = 25;
	private static int startingIncome = 20;
	private static Board board = new Board();
	
	// ------------- SET DEFAULT -----------------
	/**
	 * Set all option to default
	 */
	public static void setDefault() {
		deckSize = 5;
		MaxHealth = 100;
		startingMoney = 50;
		income = 5;
	}
	
	// ------------- GETTER AND SETTERS -----------------
	/**
	 * Getter for {@link #deckSize}
	 * @return {@link #deckSize}
	 */
	public static int getDeckSize() {
		return deckSize;
	}
	/**
	 * Setter for {@link #deckSize}
	 * @param deckSize {@link #deckSize}
	 */
	public static void setDeckSize(int deckSize) {
		GameSettings.deckSize = deckSize;
	}
	/**
	 * Getter for {@link #MaxHealth}
	 * @return {@link #MaxHealth}
	 */
	public static int getMaxHealth() {
		return MaxHealth;
	}
	/**
	 * Setter for {@link #MaxHealth}
	 * @param deckSize {@link #MaxHealth}
	 */
	public static void setMaxHealth(int maxHealth) {
		MaxHealth = maxHealth;
	}
	/**
	 * Getter for {@link #startingMoney}
	 * @return {@link #startingMoney}
	 */
	public static int getStartingMoney() {
		return startingMoney;
	}
	/**
	 * Setter for {@link #startingMoney}
	 * @param deckSize {@link #startingMoney}
	 */
	public static void setStartingMoney(int startingMoney) {
		GameSettings.startingMoney = startingMoney;
	}
	/**
	 * Getter for {@link #board}
	 * @return {@link #board}
	 */
	public static Board getBoard() {
		return board;
	}
	/**
	 * Setter for {@link #board}
	 * @param deckSize {@link #board}
	 */
	public static void setBoard(Board board) {
		GameSettings.board = board;
	}
	/**
	 * Getter for {@link #income}
	 * @return {@link #income}
	 */
	public static int getIncome() {
		return income;
	}
	/**
	 * Setter for {@link #income}
	 * @param deckSize {@link #income}
	 */
	public static void setIncome(int income) {
		GameSettings.income = income;
	}
	/**
	 * Getter for {@link #incomeUpgradeCost}
	 * @return {@link #incomeUpgradeCost}
	 */
	public static int getIncomeUpgradeCost() {
		return incomeUpgradeCost;
	}
	/**
	 * Setter for {@link #incomeUpgradeCost}
	 * @param deckSize {@link #incomeUpgradeCost}
	 */
	public static void setIncomeUpgradeCost(int incomeUpgradeCost) {
		GameSettings.incomeUpgradeCost = incomeUpgradeCost;
	}
	/**
	 * Getter for {@link #startingIncome}
	 * @return {@link #startingIncome}
	 */
	public static int getStartingIncome() {
		return startingIncome;
	}
	/**
	 * Setter for {@link #startingIncome}
	 * @param deckSize {@link #startingIncome}
	 */
	public static void setStartingIncome(int startingIncome) {
		GameSettings.startingIncome = startingIncome;
	}
	
	
	
	
	
	
}
