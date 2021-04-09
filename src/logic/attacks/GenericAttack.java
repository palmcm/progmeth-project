package logic.attacks;

import exception.InvalidPlayerException;
import logic.gmanager.GameInstance;
import logic.gmanager.GameManager;
import logic.misc.Coordinate;

public class GenericAttack {
	
	public static void simpleProjectile(Coordinate start, int damage, int range, int player) throws InvalidPlayerException
	{
		GameInstance gameInstance = GameManager.getGameInstance();
		int opponent = gameInstance.getPlayer(player).getOppositePlayerId();
		int endBorder = gameInstance.getBoard().getPlayerBorder(opponent);
		
		int direction;
		
		if(player==1)
			direction = 1;
		else direction = -1;
		
		boolean attackCompleted = false;
		if(range<1)
			attackCompleted=true;
		
		while(!attackCompleted)
		{
			if(gameInstance.getBoard().getTile(start).attackable(player))
			{
				gameInstance.getBoard().getTile(start).getTower().damage(damage);
				attackCompleted = true;
			}
			else
			{
				range -= 1;
				if(range<1)
					attackCompleted=true;
				
				start.setY(start.getY()+direction);
				if(start.getY() == endBorder || !attackCompleted)
				{
					gameInstance.getPlayer(opponent).damage(damage);
					attackCompleted=true;
				}
			}
		}
	}
		

		
	public static void piercingProjectile(Coordinate start, int damage, int range, int pierce, int player) throws InvalidPlayerException
	{
		GameInstance gameInstance = GameManager.getGameInstance();
		int opponent = gameInstance.getPlayer(player).getOppositePlayerId();
		int endBorder = gameInstance.getBoard().getPlayerBorder(opponent);
		
		int direction;
		
		if(player==1)
			direction = 1;
		else direction = -1;
		
		boolean attackCompleted = false;
		if(range<1)
			attackCompleted=true;
		if(pierce<1)
			attackCompleted=true;
		
		while(!attackCompleted)
		{
			if(gameInstance.getBoard().getTile(start).attackable(player))
			{
				gameInstance.getBoard().getTile(start).getTower().damage(damage);
				pierce -=1;
				if(pierce<1)
					attackCompleted=true;
				
			}
			else
			{
				range -= 1;
				if(range<1)
					attackCompleted=true;
				
				start.setY(start.getY()+direction);
				if(start.getY() == endBorder || !attackCompleted)
				{
					gameInstance.getPlayer(opponent).damage(damage);
					attackCompleted=true;
				}
			}
		}
				
		
	}
	
}
