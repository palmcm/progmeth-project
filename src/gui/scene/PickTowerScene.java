package gui.scene;

import config.GameConfig;
import gui.component.TowerDesBox;
import gui.pane.PickTowerPane;
import gui.pane.PlayerDeckPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import logic.gmanager.GameManager;
/**
 * Scene for pick tower
 */
public class PickTowerScene extends Scene{
	/** Select tower pane*/
	private PickTowerPane pickTowerPane;
	/** Player 1 deck*/
	private PlayerDeckPane p1;
	/** Player 2 deck*/
	private PlayerDeckPane p2;
	/**
	 * Constructor for PickTowerScene
	 * @param root VBox object
	 */
	public PickTowerScene(VBox root) {
		super(root,GameConfig.SCREEN_WIDTH,GameConfig.SCREEN_HEIGHT);
		
		HBox menuBox = new HBox(50);
		
		pickTowerPane = new PickTowerPane();
		
		p1 = new PlayerDeckPane(1);
		p2 = new PlayerDeckPane(2);
		
		menuBox.getChildren().addAll(p1,pickTowerPane,p2);
		menuBox.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(5));
		
		root.getChildren().add(menuBox);
		root.setBackground(new Background(new BackgroundImage(new Image(ClassLoader.getSystemResource("ui/deck_selector.png").toString()), BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
		        BackgroundPosition.DEFAULT,
		        new BackgroundSize(1.0, 1.0, true, true, false, false))));
	}
	
	/**
	 * Getter for info box
	 * @return DesBox
	 */
	public TowerDesBox getDesBox() {
		return pickTowerPane.getDesBox();
	}
	/**
	 * Update display for all decks
	 */
	public void updateDeck() {
		p1.showNewDeck();
		p2.showNewDeck();
		highlightdeck();
	}
	
	/**
	 * Highlight deck according to current player
	 */
	public void highlightdeck() {
		int player = GameManager.getCurrentPlayer();
		if (player == 1) {
			p1.highlight(true);
			p2.highlight(false);
		}else {
			p1.highlight(false);
			p2.highlight(true);
		}
	}
	
	public void setCanNext(boolean canUse) {
		pickTowerPane.getNext().setDisable(!canUse);
		if (canUse) {
			pickTowerPane.getNext().setFill(Color.BLACK);
		}
	}
}
