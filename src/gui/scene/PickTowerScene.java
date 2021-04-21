package gui.scene;

import config.GameConfig;
import gui.SceneController;
import gui.component.TowerDesBox;
import gui.pane.PickTowerPane;
import gui.pane.PlayerDeckPane;
import gui.pane.TowerPane;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PickTowerScene extends Scene{
	
	private PickTowerPane pickTowerPane;
	
	public PickTowerScene(VBox root) {
		super(root,GameConfig.SCREEN_WIDTH,GameConfig.SCREEN_HEIGHT);
		
		HBox menuBox = new HBox(50);
		
		pickTowerPane = new PickTowerPane();
		
		PlayerDeckPane p1 = new PlayerDeckPane();
		PlayerDeckPane p2 = new PlayerDeckPane();
		
		menuBox.getChildren().addAll(p1,pickTowerPane,p2);
		menuBox.setAlignment(Pos.CENTER);
		
		root.getChildren().add(menuBox);
	}
	
	public TowerDesBox getDesBox() {
		return pickTowerPane.getDesBox();
	}
}
