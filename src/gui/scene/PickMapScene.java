package gui.scene;

import config.GameConfig;
import gui.SceneController;
import gui.pane.MapPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PickMapScene extends Scene{
	
	public PickMapScene(VBox root) {
		super(root,GameConfig.SCREEN_WIDTH,GameConfig.SCREEN_HEIGHT);
		root.setAlignment(Pos.CENTER);
		root.setSpacing(50);
		
		Text gameName = new Text("Pick your map");
		gameName.setFont(new Font(100));
		
		MapPane mapPane = new MapPane();
		
		Button next = new Button("Next");
		next.setPrefSize(250, 50);
		next.setOnMouseClicked(e -> {
			SceneController.setScene("pickTower");
		});
		
		Button back = new Button("Back");
		back.setPrefSize(250, 50);
		back.setOnMouseClicked(e -> {
			SceneController.setScene("menu");
		});
		
		root.getChildren().addAll(gameName,mapPane,next,back);
		VBox.setMargin(mapPane, new Insets(50));
		
	}
	
}
