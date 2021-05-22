package utils;

import config.GameConfig;
import javafx.scene.image.Image;

public class CommonImages {
	
	// TILE HIGHLIGHTER GANG
	
	private static Image deckwaitp1 = ImageUtil.ImageLoader("ui/deck_wait_blue.png",85);
	private static Image deckwaitp2 = ImageUtil.ImageLoader("ui/deck_wait_red.png",GameConfig.SCREEN_WIDTH / 18);
	private static Image deckpickp1 = ImageUtil.ImageLoader("ui/deck_pick_blue.png",GameConfig.SCREEN_WIDTH / 18);
	private static Image deckpickp2 = ImageUtil.ImageLoader("ui/deck_pick_red.png",GameConfig.SCREEN_WIDTH / 18);
	
	/**
	 * Getter for deckselector's tile background.
	 * @param player The player to query for
	 * @param pick Whether or not it is that player's turn to pick
	 * @return an image of {@link #deckwaitp1}, {@link #deckwaitp2}, {@link #deckpickp1} or {@link #deckpickp2}.
	 */
	public static Image getDeckBackground(int player,boolean pick)
	{
		if(pick) {
			if (player == 1) {
				return deckpickp1;
			}else {
				return deckpickp2;
			}
		}else {
			if (player == 1) {
				return deckwaitp1;
			}else {
				return deckwaitp2;
			}
		}
	}
	
	private static Image rangeHighlighter = ImageUtil.ImageLoader("highlighters/range.png",GameConfig.SCREEN_WIDTH / 18);
	private static Image targetHighlighter = ImageUtil.ImageLoader("highlighters/target.png",GameConfig.SCREEN_WIDTH / 18);
	private static Image unitHighlighter = ImageUtil.ImageLoader("highlighters/unit.png",GameConfig.SCREEN_WIDTH / 18);
	private static Image emptyHighlighter = ImageUtil.ImageLoader("highlighters/empty.png",GameConfig.SCREEN_WIDTH / 18);
	private static Image selectHighlighter = ImageUtil.ImageLoader("ui/selector.png",GameConfig.SCREEN_WIDTH / 18);
	
	/**
	 * Getter for tile highlighter.
	 * @param type type to query for. valid types: "range", "target", "unit", "empty" and "select".
	 * @return Image of the highlighter. Returns nulls if invalid.
	 */
	public static Image getHighlighter(String type)
	{
		if(type == "range")
			return CommonImages.rangeHighlighter;
		if(type == "target")
			return CommonImages.targetHighlighter;
		if(type == "unit")
			return CommonImages.unitHighlighter;
		if(type == "empty")
			return CommonImages.emptyHighlighter;
		if (type == "select")
			return selectHighlighter;
		return null;
	}
	
	// UPGRADE EMBLEMS
	
	private static Image upgradeEmblem1 = ImageUtil.ImageLoader("unit_overlay/upgrade_1.png",GameConfig.SCREEN_WIDTH / 18);
	private static Image upgradeEmblem2 = ImageUtil.ImageLoader("unit_overlay/upgrade_2.png",GameConfig.SCREEN_WIDTH / 18);
	private static Image upgradeEmblem3 = ImageUtil.ImageLoader("unit_overlay/upgrade_3.png",GameConfig.SCREEN_WIDTH / 18);
	
	/**
	 * Getter for unit upgrade emblem of a specific level
	 * @param level An integer between 0-3
	 * @return Image of the emblem, false if invalid or the level doesn't have one.
	 */
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
	
	/**
	 * Image of hp bar background.
	 */
	public static Image hpBackground = ImageUtil.ImageLoader("unit_overlay/healthbar_background.png",GameConfig.SCREEN_WIDTH / 18);
	
}
