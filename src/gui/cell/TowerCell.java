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

public class TowerCell extends VBox{
	
	public TowerCell() {
		ImageView towerIcon = new ImageView();
		Image img = new Image(ClassLoader.getSystemResource("tower1.png").toString());
		towerIcon.setImage(img);
		double scale = img.getHeight()/100;
//		System.out.println(scale);
		towerIcon.setFitHeight(img.getHeight()/scale);
		towerIcon.setFitWidth(img.getWidth()/scale);
		
		this.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		this.getChildren().add(towerIcon);
		this.setOnMouseEntered(e -> {
			SceneController.getPickTowerScene().getDesBox().setTowerData("Glue Gunner","Attack Range: 5\nDef: 3");
		});
		
		this.setOnMouseExited(e -> {
			SceneController.getPickTowerScene().getDesBox().setTowerDefault();
		});
	}

}
