package gui.pane;

import config.GameConfig;
import gui.SceneController;
import gui.component.TowerDesBox;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.menu.DeckSelector;
import utils.FontUtil;

public class PickTowerPane extends VBox{
	
	private TowerDesBox desBox;
	private Text next;
	
	public PickTowerPane() {
		this.setAlignment(Pos.CENTER);
		this.setSpacing(45);
		
		Text gameName = new Text("Pick Tower");
		gameName.setFont(FontUtil.loadFont(80));
		
		TowerPane towerPane = new TowerPane();
		
		desBox = new TowerDesBox();
		
		HBox buttonBox = new HBox(150);
		next = new Text("Next");
		next.setFont(FontUtil.loadFont(25));
		
		Text back = new Text("Back");
		back.setFont(FontUtil.loadFont(25));
		
		next.setDisable(true);
		next.setFill(Color.LIGHTGRAY);
		next.setOnMouseClicked(e -> {
			next.setDisable(true);
			next.setFill(Color.LIGHTGRAY);
			DeckSelector.nextHandler();
		});
		
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
	
	public Text getNext() {
		return next;
	}
}
