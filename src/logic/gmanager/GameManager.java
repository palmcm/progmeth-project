package logic.gmanager;

public class GameManager {
	
	private static GameInstance gameInstance;
	
	public static GameInstance getGameInstance()
	{
		return GameManager.gameInstance;
	}
	
	public static void setGameInstance(GameInstance gameInstance)
	{
		GameManager.gameInstance = gameInstance;
	}
	
	public static void StartNewGame()
	{
		GameManager.setGameInstance(new GameInstance());
	}
	
	public static void victory(int player)
	{
		// TBD: Game victory handler
		
	}
	
}
