package gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MenuScene {
	
	Scene scene;
	
	public MenuScene() {
		VBox root = new VBox(100);
		root.setAlignment(Pos.CENTER);
		
		Text gameName = new Text("Game Name");
		gameName.setFont(new Font(100));
		
		Button single = new Button("Single player");
		single.setPrefSize(500, 100);
		
		Button duel = new Button("Two players");
		duel.setPrefSize(500, 100);
		
		duel.setOnMouseClicked(e -> {
			SceneController.setScene("pickMap");
		});
		
		root.getChildren().addAll(gameName,single,duel);
		
		scene = new Scene(root,1440,790);
		
	}
	
	public Scene getScene() {
		return scene;
	}
}
