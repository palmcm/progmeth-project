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
		System.out.println(Towers.getTowers()[0].getCurrentName());  // <------- NAME
		
		System.out.println(Towers.getTowers()[0].getToolTipString()); // <--------- TOOLTIP DESC
	}
}