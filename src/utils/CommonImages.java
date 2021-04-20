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
	
}
