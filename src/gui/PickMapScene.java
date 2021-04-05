package gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PickMapScene {
	Scene scene;
	
	public PickMapScene() {
		VBox root = new VBox(100);
		root.setAlignment(Pos.CENTER);
		
		Text gameName = new Text("Pick your map");
		gameName.setFont(new Font(100));
		
		Button next = new Button("Next");
		next.setPrefSize(500, 100);
		next.setOnMouseClicked(e -> {
			SceneController.setScene("pickTower");
		});
		
		Button back = new Button("Back");
		back.setPrefSize(500, 100);
		back.setOnMouseClicked(e -> {
			SceneController.setScene("menu");
		});
		
		root.getChildren().addAll(gameName,next,back);
		
		scene = new Scene(root,1440,790);
	}
	
	public Scene getScene() {
		return scene;
	}
}
