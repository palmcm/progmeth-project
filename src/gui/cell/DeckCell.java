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

/**
 * Component cell for showing tower in player's deck
 */
public class DeckCell extends StackPane{
	/** Background for display */
	private ImageView background;
	/** For display tower */
	private ImageView towerIcon;
	/** Size for ImageView */
	private final int SIZE = 80;
	/** Owner of this cell */
	private int player;
	
	/**
	 * Constructor for DeckCell
	 * 
	 * @param player Side of tower cell
	 */
	public DeckCell(int player) {
		this.player = player;
		towerIcon = new ImageView();
		towerIcon.setFitHeight(SIZE);
		towerIcon.setFitWidth(SIZE);
		
		background = new ImageView();
		background.setFitHeight(SIZE);
		background.setFitWidth(SIZE);
		
		this.getChildren().addAll(background,towerIcon);
		this.setMinSize(SIZE+2, SIZE+2);
		this.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}
	
	/**
	 * Constructor for DeckCell with tower
	 * 
	 * @param tower Tower on this cell
	 * @param player Side of tower cell
	 */
	public DeckCell(BaseTower tower, int player) {
		this.player = player;
		towerIcon = new ImageView();
		setTower(tower);
		towerIcon.setFitHeight(SIZE);
		towerIcon.setFitWidth(SIZE);
		
		background = new ImageView();
		background.setFitHeight(SIZE);
		background.setFitWidth(SIZE);
		
		this.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		this.getChildren().addAll(background,towerIcon);
		this.setOnMouseEntered(e -> {
			SceneController.getPickTowerScene().getDesBox().setTowerData(tower);
		});
		
		this.setOnMouseExited(e -> {
			SceneController.getPickTowerScene().getDesBox().setTowerDefault();
		});
	}
	
	/**
	 * Show and display tower on this cell
	 * 
	 * @param tower Tower on this cell
	 */
	public void setTower(BaseTower tower) {
		if (tower == null) {
			towerIcon.setImage(null);
			return;
		}
		Image img = ImageUtil.ImageLoader(tower.getUrl(), 80);
		if (player == 2) {
			towerIcon.setScaleX(-1);
		}
		towerIcon.setImage(img);
	}
	
	/**
	 * Getter for {@link #background background}
	 * 
	 * @return {@link #background background}
	 */
	public ImageView getCellBackground() {
		return background;
	}
}
