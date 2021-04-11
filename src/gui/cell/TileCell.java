package gui.cell;

import config.GameConfig;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.gmanager.Tile;

public class TileCell extends StackPane{
	Tile tile;
	
	public TileCell(Tile tile) {
		this.tile = tile;
		double size = (double) GameConfig.SCREEN_WIDTH / 18;
		Text test = new Text(tile.getTileOwner()+"");
		
		this.getChildren().add(test);
		this.setPrefSize(size, size);
		this.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}
}
