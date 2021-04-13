package gui.cell;

import gui.SceneController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import logic.towers.BaseTower;

public class TowerCell extends VBox{
	private BaseTower tower;
	
	public TowerCell() {
		this.setMinSize(100, 100);
		
		this.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}
	
	public TowerCell(BaseTower tower) {
		this.tower = tower;
		ImageView towerIcon = new ImageView();
		Image img = new Image(ClassLoader.getSystemResource("towers/pyromancer.png").toString());
		towerIcon.setImage(img);
		double scale = img.getHeight()/100;
//		System.out.println(scale);
		towerIcon.setFitHeight(img.getHeight()/scale);
		towerIcon.setFitWidth(img.getWidth()/scale);
		
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

}
