package logic.gmanager;

/**
 * Data for game setting and set in to game when start
 */
public class GameSettings {

	/**
	 * Maximum amount of wizard in a team.
	 */
	private static int deckSize = 5;
	/**
	 * Player's starting health
	 */
	private static int MaxHealth = 100;
	/**
	 * Player's starting intellect
	 */
	private static int startingMoney = 30;
	/**
	 * Amount of increased research per upgrade
	 */
	private static int income = 5;
	/**
	 * Cost to upgrade research once
	 */
	private static int incomeUpgradeCost = 30;
	/**
	 * Player's initial income
	 */
	private static int startingIncome = 10;
	/**
	 * Board used when creating a new game
	 */
	private static Board board = new Board();
	
	/**
	 * Whether having animation in game or not
	 */
	private static boolean enableAnimation = true;
	
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
	 * @param maxHealth {@link #MaxHealth}
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
	 * @param startingMoney {@link #startingMoney}
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
	 * @param board {@link #board}
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
	 * @param income {@link #income}
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
	 * @param incomeUpgradeCost {@link #incomeUpgradeCost}
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
	 * @param startingIncome {@link #startingIncome}
	 */
	public static void setStartingIncome(int startingIncome) {
		GameSettings.startingIncome = startingIncome;
	}

	/**
	 * Getter for {@link #enableAnimation}
	 * @return {@link #enableAnimation}
	 */
	public static boolean isEnableAnimation() {
		return enableAnimation;
	}
	
	/**
	 * toggle {@link #enableAnimation}
	 */
	public static void toggleEnableAnimation() {
		enableAnimation = !enableAnimation;
	}
	
	/**
	 * Getter for {@link #enableAnimation} as String to put in setting
	 * @return {@link #enableAnimation}
	 */
	public static String getEnableAnimation() {
		if (enableAnimation) {
			return "Enabled";
		}else {
			return "Disabled";
		}
	}
	
	
	
}
