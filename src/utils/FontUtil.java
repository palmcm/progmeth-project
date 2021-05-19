package utils;

import javafx.scene.text.Font;

public class FontUtil {
	public static Font loadFont(double size) {
		return Font.loadFont(ClassLoader.getSystemResource("ui/windows_command_prompt.ttf").toString(),size);
//		return Font.loadFont(ClassLoader.getSystemResource("ui/04B30.TTF").toString(),size);	
	}
}
