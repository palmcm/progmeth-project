package gui.pane;

import config.GameConfig;
import gui.cell.TowerGameCell;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class TowerGamePane extends VBox{
	public TowerGamePane() {
		TilePane towerDeckBox = new TilePane();
		for (int i=0;i<5;i++) {
			towerDeckBox.getChildren().add(new TowerGameCell());
		}
		towerDeckBox.setHgap(1);
		towerDeckBox.setAlignment(Pos.CENTER);
		this.setAlignment(Pos.CENTER);
		this.setMinSize(GameConfig.SCREEN_WIDTH/2.5, GameConfig.SCREEN_HEIGHT*0.3);
		this.setPadding(new Insets(5));
		this.getChildren().addAll(towerDeckBox);
		this.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}
}
