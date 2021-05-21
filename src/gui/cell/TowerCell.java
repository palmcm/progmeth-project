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
import utils.ImageUtil;

/**
 * 
 * Component cell for showing selectable tower in PickTowerScene
 *
 */
public class TowerCell extends VBox {

	/**
	 * Constructor for empty TowerCell
	 */
	public TowerCell() {
		this.setMinSize(100, 100);

		this.setBorder(new Border(
				new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}

	/**
	 * Constructor for TowerCell with tower
	 * 
	 * @param tower Tower to put in cell
	 */
	public TowerCell(BaseTower tower) {
		ImageView towerIcon = new ImageView();
		Image img = ImageUtil.ImageLoader(tower.getUrl(), 100);
		towerIcon.setImage(img);
		towerIcon.setFitHeight(100);
		towerIcon.setFitWidth(100);

		this.setBorder(new Border(
				new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		this.getChildren().add(towerIcon);
		this.setOnMouseEntered(e -> {
			SceneController.getPickTowerScene().getDesBox().setTowerData(tower);
		});

		this.setOnMouseExited(e -> {
			SceneController.getPickTowerScene().getDesBox().setTowerDefault();
		});
	}

}
