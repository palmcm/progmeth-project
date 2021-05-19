package gui.component;

import config.GameConfig;
import javafx.geometry.Insets;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.towers.BaseTower;
import utils.FontUtil;

public class TowerDesBox extends VBox{
	private Text towerName;
	private Text towerDes;
	
	public TowerDesBox() {
		towerName = new Text();
		towerName.setFont(FontUtil.loadFont(20));
		towerDes = new Text();
		this.setTowerDefault();
		towerDes.setFont(FontUtil.loadFont(16));
		this.setPadding(new Insets(5));
		this.getChildren().addAll(towerName,towerDes);
		this.setMaxWidth(GameConfig.SCREEN_WIDTH/4);
		this.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		this.setMinSize(600,250);
//		this.setPrefHeight(180);
	}
	
	public void setTowerData(String towerName, String towerDes) {
		this.towerName.setText(towerName);
		this.towerDes.setText(towerDes);
	}
	
	public void setTowerData(BaseTower tower) {
		this.towerName.setText(tower.getCurrentName());
		this.towerDes.setText(tower.getToolTipString());
	}
	
	public void setTowerDefault() {
		this.towerName.setText("Tower Info");
		this.towerDes.setText("Hover over a tower to view its information.");
	}
	
}
