package gui.pane;

import config.GameConfig;
import gui.SceneController;
import gui.component.TowerDesBox;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.menu.DeckSelector;

public class PickTowerPane extends VBox{
	
	private TowerDesBox desBox;
	private Button next;
	
	public PickTowerPane() {
		this.setAlignment(Pos.CENTER);
		this.setSpacing(50);
		
		Text gameName = new Text("Pick Tower");
		gameName.setFont(new Font(80));
		
		TowerPane towerPane = new TowerPane();
		
		desBox = new TowerDesBox();
		
		HBox buttonBox = new HBox(5);
		next = new Button("Next");
		Button back = new Button("Back");
		next.setPrefSize(250, 50);
		next.setDisable(true);
		next.setOnMouseClicked(e -> {
			next.setDisable(true);
			DeckSelector.nextHandler();
		});
		
		back.setPrefSize(250, 50);
		back.setOnMouseClicked(e -> {
			SceneController.setScene("menu");
		});
		
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.getChildren().addAll(next,back);
		
		this.getChildren().addAll(gameName,towerPane,desBox,buttonBox);
	}
	
	public TowerDesBox getDesBox() {
		return desBox;
	}
	
	public Button getNext() {
		return next;
	}
}
