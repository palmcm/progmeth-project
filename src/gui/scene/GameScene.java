
package gui.scene;

import config.GameConfig;
import gui.pane.DescriptionPane;
import gui.pane.PlayerPane;
import gui.pane.TilesPane;
import gui.pane.ToolsPane;
import gui.pane.TowerGamePane;
import gui.pane.TurnPane;
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

public class GameScene extends Scene {
	private PlayerPane player1Status;
	private PlayerPane player2Status;
	private TurnPane turnStatus;
	private TilesPane tilesPane;
	
	public GameScene(VBox root) {
		super(root,GameConfig.SCREEN_WIDTH,GameConfig.SCREEN_HEIGHT);
		
		HBox playerTab = new HBox();
		player1Status = new PlayerPane(1);
		turnStatus = new TurnPane();
		player2Status = new PlayerPane(2);
		playerTab.getChildren().addAll(player1Status,turnStatus, player2Status);
		playerTab.setPadding(new Insets(5));
		
		HBox boardArea = new HBox();
		tilesPane = new TilesPane();
		boardArea.getChildren().addAll(tilesPane);
		boardArea.setAlignment(Pos.CENTER);
		VBox.setMargin(boardArea, new Insets(20, 0, 20, 0));
		
		HBox towerTab = new HBox();
		TowerGamePane towergamePane = new TowerGamePane();
		ToolsPane toolsPane = new ToolsPane();
		DescriptionPane descriptionPane = new DescriptionPane();
		towerTab.setPadding(new Insets(5));
		towerTab.getChildren().addAll(towergamePane,toolsPane,descriptionPane);
		
		root.getChildren().addAll(playerTab, boardArea, towerTab);
//		root.setBackground(new Background(new BackgroundImage(new Image(ClassLoader.getSystemResource("img1.png").toString()), BackgroundRepeat.NO_REPEAT,
//		        BackgroundRepeat.NO_REPEAT,
//		        BackgroundPosition.DEFAULT,
//		        new BackgroundSize(1.0, 1.0, true, true, false, false))));
		}
}