package gui.pane;

import config.GameConfig;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ToolsPane extends VBox {
	public ToolsPane() {
		ImageView upgradeIcon = new ImageView(new Image(ClassLoader.getSystemResource("tower1.png").toString()));
		upgradeIcon.setFitWidth(80);
		upgradeIcon.setFitHeight(80);
		
		ImageView sellIcon = new ImageView(new Image(ClassLoader.getSystemResource("tower1.png").toString()));
		sellIcon.setFitWidth(80);
		sellIcon.setFitHeight(80);
		
		ImageView cancelIcon = new ImageView(new Image(ClassLoader.getSystemResource("tower1.png").toString()));
		cancelIcon.setFitWidth(80);
		cancelIcon.setFitHeight(80);
		this.getChildren().addAll(upgradeIcon,sellIcon,cancelIcon);
		this.setMinWidth(GameConfig.SCREEN_WIDTH/5-10);
		this.setAlignment(Pos.CENTER);
		this.setSpacing(5);
	}
}
