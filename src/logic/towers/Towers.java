package logic.towers;

import tower.SampleAimable;
import tower.SampleProduction;
import tower.SampleTower;

public class Towers {

	
	private static BaseTower TowerList[] = {
			new SampleTower(null,false),
			new SampleProduction(null,false),
			new SampleAimable(null,false)
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