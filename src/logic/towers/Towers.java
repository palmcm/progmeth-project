package logic.towers;

import tower.Pyromancer;
import tower.Scholar;
import tower.ShockWeaver;
import tower.TerraRaiser;
import tower.Apprentice;
import tower.Corruptor;
import tower.Cryomaster;
import tower.Dryad;
import tower.Illuminant;

public class Towers {

	
	private static BaseTower TowerList[] = {
			new Apprentice(null,false),
			new TerraRaiser(null,false),
			new Cryomaster(null,false),
			new Pyromancer(null,false),
			new Dryad(null,false),
			new Illuminant(null,false),
			new Scholar(null,false),
			new Corruptor(null,false),
			new ShockWeaver(null,false)
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