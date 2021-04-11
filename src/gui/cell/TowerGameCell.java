package gui.cell;

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
import logic.towers.Towers;

public class TowerGameCell extends VBox {
	private Towers tower;
	public TowerGameCell() {
//		this.tower = tower;
		ImageView towerIcon = new ImageView();
		Image img = new Image(ClassLoader.getSystemResource("tower1.png").toString());
		towerIcon.setImage(img);
		double scale = img.getHeight()/80;
		towerIcon.setFitHeight(img.getHeight()/scale);
		towerIcon.setFitWidth(img.getWidth()/scale);
		
		this.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		this.getChildren().add(towerIcon);
	}
	
	public void highlight() {
		this.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	public void unhighlight() {
		this.setBackground(new Background(new BackgroundFill(null, CornerRadii.EMPTY, Insets.EMPTY)));
	}
}
