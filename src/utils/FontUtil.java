package utils;

import javafx.scene.text.Font;
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
}
