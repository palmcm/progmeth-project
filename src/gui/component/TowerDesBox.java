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

public class TowerDesBox extends VBox{
	private Text towerName;
	private Text towerDes;
	
	public TowerDesBox() {
		towerName = new Text("Tower name");
		towerName.setFont(new Font(20));
		towerDes = new Text("Attack Range:\nDef:");
		this.setPadding(new Insets(5));
		this.getChildren().addAll(towerName,towerDes);
		this.setMaxWidth(GameConfig.SCREEN_WIDTH/4);
		this.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}
	
	public void setTowerData(String towerName, String towerDes) {
		this.towerName.setText(towerName);
		this.towerDes.setText(towerDes);
	}
	
	public void setTowerDefault() {
		this.towerName.setText("Tower name");
		this.towerDes.setText("Attack Range:\nDef:");
	}
}
