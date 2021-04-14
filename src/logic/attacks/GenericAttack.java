package logic.attacks;

import exception.InvalidPlayerException;
import logic.gmanager.GameInstance;
import logic.gmanager.GameManager;
import logic.misc.Coordinate;

public class GenericAttack {
	
	public static void simpleProjectile(Coordinate start, int damage, int range, int player) throws InvalidPlayerException
	{
		//System.out.println("simpleProjectile!");
		start = new Coordinate(start.getX(),start.getY());
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
				break;
			}
			else
			{
				range -= 1;
				if(range<1)
				{
					attackCompleted=true;
					break;
				}
				//System.out.println(start.getX()+","+start.getY());
				
				start.setY(start.getY()+direction);
				if(start.getY() == endBorder && !attackCompleted)
				{
					gameInstance.getPlayer(opponent).damage(damage);
					attackCompleted=true;
				}
			}
		}
	}
			
	public static void piercingProjectile(Coordinate start, int damage, int range, int pierce, int player) throws InvalidPlayerException
	{
		start = new Coordinate(start.getX(),start.getY());
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
				if(start.getY() == endBorder && !attackCompleted)
				{
					gameInstance.getPlayer(opponent).damage(damage);
					attackCompleted=true;
				}
			}
		}				
		
	}
	
	public static void tileDamage(Coordinate target, int damage, int player) throws InvalidPlayerException
	{
		target = new Coordinate(target.getX(),target.getY());
		GameInstance gameInstance = GameManager.getGameInstance();
		int opponent = gameInstance.getPlayer(player).getOppositePlayerId();
		int endBorder = gameInstance.getBoard().getPlayerBorder(opponent);
		
		if(target.getY() == endBorder)
		{
			gameInstance.getPlayer(opponent).damage(damage);
			return;
		}
		
		if(gameInstance.getBoard().getTile(target).attackable(player))
		{
			gameInstance.getBoard().getTile(target).getTower().damage(damage);
			
		}
	}
	
	public static boolean splashTileDamage(Coordinate target, int damage, int player, boolean canHitPlayer) throws InvalidPlayerException
	{
		target = new Coordinate(target.getX(),target.getY());
		GameInstance gameInstance = GameManager.getGameInstance();
		int opponent = gameInstance.getPlayer(player).getOppositePlayerId();
		int endBorder = gameInstance.getBoard().getPlayerBorder(opponent);
		
		if(target.getY() == endBorder)
		{
			if(canHitPlayer)
			{
				gameInstance.getPlayer(opponent).damage(damage);				
			}
			return false;
		}
		else
		{
			GenericAttack.tileDamage(target, damage, player);
			return canHitPlayer;
		}
		
		
		
	}

	
	public static void targetSquareExplosion(Coordinate target, int damage, int player,int radius) throws InvalidPlayerException
	{
		target = new Coordinate(target.getX(),target.getY());		
		GameInstance gameInstance = GameManager.getGameInstance();
		int x = target.getX();
		int y = target.getY();
		int i,j;
		boolean canHitPlayer = true;

		for(i=x-radius;i<=x+radius;i++)
		{
			for(j=y-radius;j<=y+radius;j++)
			{
				if(gameInstance.getBoard().checkTile(i, j))
				{
					canHitPlayer = GenericAttack.splashTileDamage(new Coordinate(i,j), damage, player,canHitPlayer);
				}
			}
		}
		
		
		
	}
}
