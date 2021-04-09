package gui.scene;

import config.GameConfig;
import gui.SceneController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PickTowerScreen extends Scene{
	
	public PickTowerScreen(VBox root) {
		super(root,GameConfig.SCREEN_WIDTH,GameConfig.SCREEN_HEIGHT);
		root.setAlignment(Pos.CENTER);
		root.setSpacing(100);
		
		Text gameName = new Text("Pick Tower");
		gameName.setFont(new Font(100));
		
		Button back = new Button("Back");
		back.setPrefSize(500, 100);
		back.setOnMouseClicked(e -> {
			SceneController.setScene("menu");
		});
		
		root.getChildren().addAll(gameName,back);
		
	}
}
