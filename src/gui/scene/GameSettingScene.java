package gui.scene;

import config.GameConfig;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class GameSettingScene extends Scene{
	
	public GameSettingScene(VBox root) {
		super(root,GameConfig.SCREEN_WIDTH,GameConfig.SCREEN_HEIGHT);
		
	}
}
