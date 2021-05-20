package gui.scene;

import config.GameConfig;
import gui.SceneController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.gmanager.GameManager;
import utils.FontUtil;
import utils.ImageUtil;

public class MenuScene extends Scene {
	
	public MenuScene(VBox root) {
		super(root,GameConfig.SCREEN_WIDTH,GameConfig.SCREEN_HEIGHT);
//		VBox root = new VBox(100);
		root.setAlignment(Pos.CENTER);
		root.setSpacing(50);
		
		ImageView gameName = new ImageView(ImageUtil.ImageLoader("ui/game_icon.png",100));
		
		Button single = new Button("Single player");
		single.setPrefSize(250, 100);
		single.setFont(FontUtil.loadFont(25));
		
		Button duel = new Button("Two players");
		duel.setPrefSize(250, 100);
		duel.setFont(FontUtil.loadFont(25));
		
		duel.setOnMouseClicked(e -> {
			GameManager.initialize();
			SceneController.setScene("pickTower");
		});
		
		Button setting = new Button("Setting");
		setting.setPrefSize(250, 100);
		setting.setFont(FontUtil.loadFont(25));
		
		root.getChildren().addAll(gameName,single,duel,setting);
		
//		scene = new Scene(root,1440,790);
		
	}
}
