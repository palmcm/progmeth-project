package gui.component;

import config.GameConfig;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.towers.BaseTower;
import utils.FontUtil;

/**
 * 
 * Box for display tower information in PickTowerScene
 *
 */
public class TowerDesBox extends VBox{
	/** Tower name*/
	private Text towerName;
	/** Tower description*/
	private Text towerDes;
	
	/**
	 * Constructor for TowerDesBox
	 */
	public TowerDesBox() {
		towerName = new Text();
		towerName.setFont(FontUtil.loadFont(48));
		towerName.setFill(Color.WHITE);
		towerDes = new Text();
		this.setTowerDefault();
		towerDes.setFont(FontUtil.loadFont(24));
		towerDes.setFill(Color.WHITE);
		this.setPadding(new Insets(20,0,0,10));
		this.getChildren().addAll(towerName,towerDes);
		this.setMaxWidth(GameConfig.SCREEN_WIDTH/4);
//		this.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, 
//				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		this.setMinSize(600,250);
//		this.setPrefHeight(180);
	}
	/**
	 * Set to display custom name and description
	 * @param towerName Tower name or Title
	 * @param towerDes Tower Description or Detail
	 */
	public void setTowerData(String towerName, String towerDes) {
		this.towerName.setText(towerName);
		this.towerDes.setText(towerDes);
	}
	/**
	 * Set to display tower name and description
	 * @param tower Tower in the cell
	 */
	public void setTowerData(BaseTower tower) {
		this.towerName.setText(tower.getCurrentName());
		this.towerDes.setText(tower.getToolTipString());
	}
	/**
	 * Set to display default info
	 */
	public void setTowerDefault() {
		this.towerName.setText("Tower Info");
		this.towerDes.setText("Hover over a tower to view its information.");
	}
	
}
