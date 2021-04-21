package utils;

import config.GameConfig;
import javafx.scene.image.Image;

public class CommonImages {
	
	// TILE HIGHLIGHTER GANG
	
	private static Image rangeHighlighter = ImageUtil.ImageLoader("highlighters/range.png",GameConfig.SCREEN_WIDTH / 18);
	private static Image targetHighlighter = ImageUtil.ImageLoader("highlighters/target.png",GameConfig.SCREEN_WIDTH / 18);
	private static Image unitHighlighter = ImageUtil.ImageLoader("highlighters/unit.png",GameConfig.SCREEN_WIDTH / 18);
	
	public static Image getHighlighter(String type)
	{
		if(type == "range")
			return CommonImages.rangeHighlighter;
		if(type == "target")
			return CommonImages.targetHighlighter;
		if(type == "unit")
			return CommonImages.unitHighlighter;
		return null;
	}
	
	// UPGRADE EMBLEMS
	
	private static Image upgradeEmblem1 = ImageUtil.ImageLoader("unit_overlay/upgrade_1.png",GameConfig.SCREEN_WIDTH / 18);
	private static Image upgradeEmblem2 = ImageUtil.ImageLoader("unit_overlay/upgrade_2.png",GameConfig.SCREEN_WIDTH / 18);
	private static Image upgradeEmblem3 = ImageUtil.ImageLoader("unit_overlay/upgrade_3.png",GameConfig.SCREEN_WIDTH / 18);
	
	public static Image getUpgradeEmblem(int level)
	{
		if(level == 1)
			return CommonImages.upgradeEmblem1;
		if(level == 2)
			return CommonImages.upgradeEmblem2;
		if(level == 3)
			return CommonImages.upgradeEmblem3;
		return null;
	}
	
	public static Image hpBackground = ImageUtil.ImageLoader("unit_overlay/healthbar_background.png",GameConfig.SCREEN_WIDTH / 18);
	
}
