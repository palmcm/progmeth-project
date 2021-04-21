package gui.cell;

import gui.SceneController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import logic.towers.BaseTower;
import utils.ImageUtil;

public class DeckCell extends StackPane{
	private BaseTower tower;
	private ImageView towerIcon;
	private final int SIZE = 80;
	
	public DeckCell() {
		
		towerIcon = new ImageView();
		towerIcon.setFitHeight(SIZE);
		towerIcon.setFitWidth(SIZE);
		
		this.setMinSize(SIZE, SIZE);
		this.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}
	
	public DeckCell(BaseTower tower) {
		this.tower = tower;
		towerIcon = new ImageView();
		Image img = ImageUtil.ImageLoader(tower.getUrl(), 100);
		towerIcon.setImage(img);
		towerIcon.setFitHeight(SIZE);
		towerIcon.setFitWidth(SIZE);
		
		this.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		this.getChildren().add(towerIcon);
		this.setOnMouseEntered(e -> {
			SceneController.getPickTowerScene().getDesBox().setTowerData(tower);
		});
		
		this.setOnMouseExited(e -> {
			SceneController.getPickTowerScene().getDesBox().setTowerDefault();
		});
	}
	
	public void setTower(BaseTower tower) {
		if (tower == null) {
			this.tower = null;
			towerIcon.setImage(null);
		}
		this.tower = tower;
		Image img = ImageUtil.ImageLoader(tower.getUrl(), 100);
		towerIcon.setImage(img);
	}
}
