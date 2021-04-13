package gui.cell;

import config.GameConfig;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.gmanager.Tile;
import logic.towers.BaseTower;
import utils.ImageUtil;

public class TileCell extends StackPane{
	private Tile tile;
	private ImageView towerImg;
	private int SIZE = GameConfig.SCREEN_WIDTH / 18;;
	public TileCell(Tile tile) {
		this.tile = tile;
		towerImg = new ImageView();
		towerImg.setFitWidth(SIZE);
		towerImg.setFitHeight(SIZE);
		Text test = new Text(tile.getTileOwner()+"");
		this.getChildren().addAll(towerImg,test);
		this.setPrefSize(SIZE, SIZE);
		this.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}
	
	public Tile getTile() {
		return tile;
	}
	
	public void update() {
		BaseTower tower = tile.getTower();
		if (tower != null) {
			towerImg.setImage(ImageUtil.ImageLoader(tower.getUrl(),SIZE));
		} else {
			towerImg.setImage(null);
		}
		
	}
}
