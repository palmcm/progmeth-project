package gui.scene;

import config.GameConfig;
import gui.SceneController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MenuScene extends Scene {
	
	public MenuScene(VBox root) {
		super(root,GameConfig.SCREEN_WIDTH,GameConfig.SCREEN_HEIGHT);
//		VBox root = new VBox(100);
		root.setAlignment(Pos.CENTER);
		root.setSpacing(50);
		
		Text gameName = new Text("Game Name");
		gameName.setFont(new Font(100));
		
		Button single = new Button("Single player");
		single.setPrefSize(500, 100);
		
		Button duel = new Button("Two players");
		duel.setPrefSize(500, 100);
		
		duel.setOnMouseClicked(e -> {
			SceneController.setScene("pickMap");
		});
		
		Button setting = new Button("Setting");
		setting.setPrefSize(500, 100);
		
		root.getChildren().addAll(gameName,single,duel,setting);
		
//		scene = new Scene(root,1440,790);
		
	}
}
