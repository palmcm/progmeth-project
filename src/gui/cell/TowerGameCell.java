package gui.cell;

import gui.SceneController;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import logic.towers.BaseTower;
import logic.towers.Towers;
import utils.ImageUtil;

public class TowerGameCell extends VBox {
	final private int PIC_SIZE = 64;
	private BaseTower tower;
	private ImageView towerIcon;
	
	public TowerGameCell() {
		towerIcon = new ImageView();
		towerIcon.setFitHeight(PIC_SIZE);
		towerIcon.setFitWidth(PIC_SIZE);
		
		this.getChildren().add(towerIcon);
		this.setPrefSize(PIC_SIZE, PIC_SIZE);
		this.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		this.setOnMouseEntered(e -> {
			if (this.tower != null) {
				SceneController.getPickTowerScene().getDesBox().setTowerData(tower);
				SceneController.getGamePane().getDescriptionPane().setDes(
						this.tower.getCurrentName(),
						this.tower.getBuyToolTip()
						);
			}
		});
		
		this.setOnMouseExited(e -> {
			SceneController.getPickTowerScene().getDesBox().setTowerDefault();
		});
	}
	
	public void highlight() {
		this.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	public void unhighlight() {
		this.setBackground(new Background(new BackgroundFill(null, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	public BaseTower getTower() {
		return tower;
	}
	
	public void setTower(BaseTower tower) {
		this.tower = tower;
		if (tower != null) {
			Image img = ImageUtil.ImageLoader(tower.getUrl(),PIC_SIZE);
			towerIcon.setImage(img);
		}else {
			towerIcon.setImage(null);
		}
		
	}
}
