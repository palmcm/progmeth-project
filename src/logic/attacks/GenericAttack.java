package logic.attacks;

import exception.InvalidPlayerException;
import logic.gmanager.GameInstance;
import logic.gmanager.GameManager;
import logic.gmanager.Tile;
import logic.misc.Coordinate;

/**
 * A class which contains static method for common types of attack
 *
 */
public class GenericAttack {
	/**
	 * Fires a simple, single-hit projectile.
	 * @param start origin tile
	 * @param damage amount of damage to be dealt
	 * @param range range of the projectile
	 * @param player owner of the attack
	 * @throws InvalidPlayerException
	 */
	public static void simpleProjectile(Coordinate start, int damage, int range, int player) throws InvalidPlayerException
	{
		//System.out.println("simpleProjectile!");
		start = new Coordinate(start.getX(),start.getY());
		
		if(start.getX() < 0 || start.getX() >= GameManager.getGameInstance().getBoard().getLanes())
			return;
		
		GameInstance gameInstance = GameManager.getGameInstance();
		int opponent = gameInstance.getPlayer(player).getOppositePlayerId();
		int endBorder = gameInstance.getBoard().getPlayerBorder(opponent);
		
		int direction;
		
		if(player==1)
			direction = 1;
		else direction = -1;
		
		start.setY(start.getY()+direction);
		
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
				
				start.setY(start.getY()+direction);
				if(start.getY() == endBorder && !attackCompleted)
				{
					gameInstance.getPlayer(opponent).damage(damage);
					attackCompleted=true;
				}
				
				range -= 1;
				if(range<1)
				{
					attackCompleted=true;
					break;
				}
			}
		}
	}
	
	/**
	 * Fires a projectile which can pierce and hit multiple enemies
	 * @param start origin of the projectile
	 * @param damage amount of damage dealt
	 * @param range range of the projectile
	 * @param pierce amount of enemy the projectile can hit
	 * @param player owner of the attack
	 * @throws InvalidPlayerException
	 */
	public static void piercingProjectile(Coordinate start, int damage, int range, int pierce, int player) throws InvalidPlayerException
	{
		start = new Coordinate(start.getX(),start.getY());
		
		if(start.getX() < 0 || start.getX() >= GameManager.getGameInstance().getBoard().getLanes())
			return;
		
		GameInstance gameInstance = GameManager.getGameInstance();
		int opponent = gameInstance.getPlayer(player).getOppositePlayerId();
		int endBorder = gameInstance.getBoard().getPlayerBorder(opponent);
		
		int direction;
		
		if(player==1)
			direction = 1;
		else direction = -1;
		start.setY(start.getY()+direction);
		
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
				start.setY(start.getY()+direction);				
				
			}
			else
			{
				
				start.setY(start.getY()+direction);
				if(start.getY() == endBorder && !attackCompleted)
				{
					gameInstance.getPlayer(opponent).damage(damage);
					attackCompleted=true;
				}
				
				range -= 1;
				if(range<1)
					attackCompleted=true;
			}
		}				
		
	}
	
	/**
	 * Damages a specific tile
	 * @param target targeted tile
	 * @param damage damage dealt
	 * @param player owner of the attack
	 * @throws InvalidPlayerException
	 */
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
	
	/**
	 * Deals a splash damage to a tile.
	 * @param target targeted tile
	 * @param damage damage dealt
	 * @param player owner of the attack
	 * @param canHitPlayer whether or not this splash can hit the opposing player
	 * @return true if it has hit a player, false otherwise
	 * @throws InvalidPlayerException
	 */
	private static boolean splashTileDamage(Coordinate target, int damage, int player, boolean canHitPlayer) throws InvalidPlayerException
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

	/**
	 * Creates an AoE splash damage at a location
	 * @param target targeted tile, center of explosion
	 * @param damage damage dealt
	 * @param player owner of the attack
	 * @param radius radius of the explosion
	 * @throws InvalidPlayerException
	 */
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
	
	/**
	 * Freezes the target for a specific duration
	 * @param target targeted tile
	 * @param duration duration to freeze in turns
	 * @param player owner of the attack
	 */
	public static void freeze(Coordinate target,int duration, int player)
	{
		Tile targetTile = GameManager.getGameInstance().getBoard().getTile(target);
		if(targetTile.getTower() == null)
			return;
		else if(targetTile.getTower().getOwner() == player)
			return;
		else targetTile.getTower().freeze(duration);
	}

	/**
	 * Heals the targeted tile for a certain amount
	 * @param target targeted tile
	 * @param amount amount to heal
	 * @param player owner of the spell
	 * @throws InvalidPlayerException
	 */
	public static void heal(Coordinate target, int amount, int player) throws InvalidPlayerException
	{
		target = new Coordinate(target.getX(),target.getY());
		GameInstance gameInstance = GameManager.getGameInstance();
		int endBorder = gameInstance.getBoard().getPlayerBorder(player);
		
		if(target.getY() == endBorder)
		{
			gameInstance.getPlayer(player).heal(amount);
			return;
		}
		if(gameInstance.getBoard().getTile(target).getTower() == null)
		{
			return;
		}
		
		if(gameInstance.getBoard().getTile(target).getTower().getOwner() == player)
		{
			gameInstance.getBoard().getTile(target).getTower().heal(amount);
			
		}
	}
	
}
