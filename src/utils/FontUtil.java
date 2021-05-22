package utils;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
/**
 * Utility for font
 */
public class FontUtil {
	/**
	 * Load font with font family
	 * @param size Font size
	 * @return Loaded font
	 */
	public static Font loadFont(double size) {
		return Font.loadFont(ClassLoader.getSystemResource("ui/windows_command_prompt.ttf").toString(),size);
//		return Font.loadFont(ClassLoader.getSystemResource("ui/04B30.TTF").toString(),size);	
	}
	
	/**
	 * Add event to Text for highlight when mouse hover
	 * @param text Text to add handle
	 * @param init Initial color
	 */
	public static void addHoverHighlight(Text text,Color init) {
		text.setOnMouseEntered(e -> {
			text.setFill(Color.LIGHTGRAY);
		});
		
		text.setOnMouseExited(e -> {
			text.setFill(init);
		});
	}
}
