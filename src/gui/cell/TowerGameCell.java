package gui.cell;

import gui.SceneController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import logic.towers.BaseTower;
import utils.CommonImages;
import utils.ImageUtil;

/**
 * 
 * Component cell for showing playable tower in game
 *
 */
public class TowerGameCell extends StackPane {
	/** Picture size*/
	final private int PIC_SIZE = 64;
	/** Tower in the cell*/
	private BaseTower tower;
	/** Picture of tower*/
	private ImageView towerIcon;
	
	/**
	 *  Constructor for TowerGameCell
	 */
	public TowerGameCell() {
		towerIcon = new ImageView();
		towerIcon.setFitHeight(PIC_SIZE);
		towerIcon.setFitWidth(PIC_SIZE);
		
		this.getChildren().add(towerIcon);
		this.setPrefSize(PIC_SIZE, PIC_SIZE);
		
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
	/**
	 * Highlight the cell for select the cell
	 */
	public void highlight() {
		this.setBackground(new Background(new BackgroundImage(CommonImages.getHighlighter("select"), BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
		        BackgroundPosition.DEFAULT,
		        new BackgroundSize(1.0, 1.0, true, true, false, false))));
	}
	/**
	 * Remove highlight from the cell
	 */
	public void unhighlight() {
		this.setBackground(null);
	}
	/**
	 * Getter for {@link #tower tower}
	 * @return {@link #tower tower}
	 */
	public BaseTower getTower() {
		return tower;
	}
	
	/**
	 * Setter for {@link #tower tower} and display
	 * @param tower Tower to display and select
	 */
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
