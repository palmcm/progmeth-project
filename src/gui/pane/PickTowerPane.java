package gui.pane;

import config.GameConfig;
import gui.SceneController;
import gui.component.TowerDesBox;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PickTowerPane extends VBox{
	
	private TowerDesBox desBox;
	
	public PickTowerPane() {
		this.setAlignment(Pos.CENTER);
		this.setSpacing(50);
		
		Text gameName = new Text("Pick Tower");
		gameName.setFont(new Font(80));
		
		TowerPane towerPane = new TowerPane();
		
		desBox = new TowerDesBox();
		
		Button back = new Button("Back");
		back.setPrefSize(250, 50);
		back.setOnMouseClicked(e -> {
			SceneController.setScene("menu");
		});
		this.getChildren().addAll(gameName,towerPane,desBox,back);
	}
	
	public TowerDesBox getDesBox() {
		return desBox;
	}
}
