package gui.scene;

import config.GameConfig;
import gui.SceneController;
import gui.pane.SettingPane;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.gmanager.GameSettings;
import logic.menu.DeckSelector;
import utils.FontUtil;
import utils.SoundUtil;

/**
 * Scene for setting
 */
public class GameSettingScene extends Scene{
	
	/**
	 * Constructor for GameSettingScene
	 * @param root VBox object
	 */
	public GameSettingScene(VBox root) {
		super(root,GameConfig.SCREEN_WIDTH,GameConfig.SCREEN_HEIGHT);
		root.setAlignment(Pos.CENTER);
		root.setSpacing(50);
		
		Text title = new Text("Game Setting");
		title.setFont(FontUtil.loadFont(48));
		
		SettingPane setting1 = new SettingPane("Change Song :", SoundUtil.getBgm());
		setting1.getSetting().setOnMouseClicked(e -> {
			setting1.getSetting().setText(SoundUtil.changeBgm());
		});
		
		SettingPane setting2 = new SettingPane("Picktower Mode :", DeckSelector.getFreestyle());
		setting2.getSetting().setOnMouseClicked(e -> {
			DeckSelector.toggleFreestyle();
			setting2.getSetting().setText(DeckSelector.getFreestyle());
		});
		
		SettingPane setting3 = new SettingPane("Attack Animation :", GameSettings.getEnableAnimation());
		setting3.getSetting().setOnMouseClicked(e -> {
			GameSettings.toggleEnableAnimation();
			setting3.getSetting().setText(GameSettings.getEnableAnimation());
		});
		
		SettingPane setting4 = new SettingPane("Sound Effect :", SoundUtil.getEffectPack());
		setting4.getSetting().setOnMouseClicked(e -> {
			SoundUtil.changeEffectPack();
			setting4.getSetting().setText(SoundUtil.getEffectPack());
		});
		
		Text back = new Text("Back");
		back.setFont(FontUtil.loadFont(25));
		
		back.setOnMouseClicked(e -> {
			SceneController.setScene("menu");
		});
		FontUtil.addHoverHighlight(back,Color.BLACK);
		
		root.getChildren().addAll(title,setting1,setting2,setting3,setting4,back);
		
		root.setBackground(new Background(new BackgroundImage(new Image(ClassLoader.getSystemResource("ui/main_menu.png").toString()), BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
		        BackgroundPosition.DEFAULT,
		        new BackgroundSize(1.0, 1.0, true, true, false, false))));
	}
}
