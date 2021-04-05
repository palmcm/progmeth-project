package gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PickTowerScreen {
	Scene scene;
	
	public PickTowerScreen() {
		VBox root = new VBox(100);
		root.setAlignment(Pos.CENTER);
		
		Text gameName = new Text("Pick Tower");
		gameName.setFont(new Font(100));
		
		Button back = new Button("Back");
		back.setPrefSize(500, 100);
		back.setOnMouseClicked(e -> {
			SceneController.setScene("menu");
		});
		
		root.getChildren().addAll(gameName,back);
		
		scene = new Scene(root,1440,790);
	}
	
	public Scene getScene() {
		return scene;
	}
}
