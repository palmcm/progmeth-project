package gui.scene;

import config.GameConfig;
import gui.SceneController;
import gui.component.TowerDesBox;
import gui.pane.TowerPane;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PickTowerScene extends Scene{
	private TowerDesBox desBox;
	
	public PickTowerScene(VBox root) {
		super(root,GameConfig.SCREEN_WIDTH,GameConfig.SCREEN_HEIGHT);
		root.setAlignment(Pos.CENTER);
		root.setSpacing(50);
		
		Text gameName = new Text("Pick Tower");
		gameName.setFont(new Font(80));
		
		TowerPane towerPane = new TowerPane();
		
		desBox = new TowerDesBox();
		
		Button back = new Button("Back");
		back.setPrefSize(250, 50);
		back.setOnMouseClicked(e -> {
			SceneController.setScene("menu");
		});
		
		root.getChildren().addAll(gameName,towerPane,desBox,back);
		
	}
	
	public TowerDesBox getDesBox() {
		return desBox;
	}
}
