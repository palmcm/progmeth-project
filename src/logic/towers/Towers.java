package logic.towers;

import tower.Pyromancer;
import tower.Scholar;
import tower.TerraRaiser;
import tower.Apprentice;
import tower.Cryomaster;
import tower.Dryad;

public class Towers {

	
	private static BaseTower TowerList[] = {
			new Apprentice(null,false),
			new Scholar(null,false),
			new Pyromancer(null,false),
			new Cryomaster(null,false),
			new TerraRaiser(null,false),
			new Dryad(null,false)
	};
	
	public static BaseTower[] getTowers()
	{
		return Towers.TowerList;
	}
	
	public static void test()
	{
		int i;
		for(i=0;i<Towers.getTowers().length;i++) {

			System.out.println(Towers.getTowers()[i].getCurrentName());  // <------- NAME
			
			System.out.println(Towers.getTowers()[i].getToolTipString()); // <--------- TOOLTIP DESC
		}
	}
}