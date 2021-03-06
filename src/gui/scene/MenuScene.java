package gui.scene;

import config.GameConfig;
import gui.SceneController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.gmanager.GameManager;
import utils.FontUtil;
import utils.ImageUtil;
import utils.SoundUtil;

/**
 * Scene for menu
 */
public class MenuScene extends Scene {
	/**
	 * Constructor for MenuScene
	 * @param root VBox object
	 */
	public MenuScene(VBox root) {
		super(root,GameConfig.SCREEN_WIDTH,GameConfig.SCREEN_HEIGHT);
//		VBox root = new VBox(100);
		root.setAlignment(Pos.CENTER);
		root.setSpacing(50);
		
		ImageView gameName = new ImageView(ImageUtil.ImageLoader("ui/game_icon.png",100));
		
		Text duel = new Text("Two players");
		duel.setFont(FontUtil.loadFont(25));
		FontUtil.addHoverHighlight(duel,Color.BLACK);
		duel.setOnMouseClicked(e -> {
			GameManager.initialize();
			SceneController.setScene("pickTower");
		});
		
		
		Text setting = new Text("Game Setting");
		setting.setFont(FontUtil.loadFont(25));
		
		setting.setOnMouseClicked(e -> {
			SceneController.setScene("setting");
		});
		FontUtil.addHoverHighlight(setting,Color.BLACK);
		
		Text stop = new Text(SoundUtil.toggleBgm());
		stop.setFont(FontUtil.loadFont(25));
		
		stop.setOnMouseClicked(e -> {
			stop.setText(SoundUtil.toggleBgm());
		});
		FontUtil.addHoverHighlight(stop,Color.BLACK);
		
		root.getChildren().addAll(gameName,duel,setting,stop);
		
		root.setBackground(new Background(new BackgroundImage(new Image(ClassLoader.getSystemResource("ui/main_menu.png").toString()), BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
		        BackgroundPosition.DEFAULT,
		        new BackgroundSize(1.0, 1.0, true, true, false, false))));
		
	}
	
}
