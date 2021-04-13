package gui.cell;

import gui.SceneController;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class ToolsCell extends StackPane{
	
	private String toolName;
	private String toolDescription;
	
	public ToolsCell() {
		ImageView icon = new ImageView(new Image(ClassLoader.getSystemResource("towers/apprentice.png").toString()));
		icon.setFitWidth(70);
		icon.setFitHeight(70);
		this.getChildren().add(icon);
		this.setMaxSize(70, 70);
		this.setOnMouseEntered(e -> {
			showDes();
		});
		
		this.setOnMouseExited(e -> {
			SceneController.getGameScene().getDescriptionPane().setDesDefault();
		});
	}
	
	public void highlight() {
		this.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	public void unhighlight() {
		this.setBackground(new Background(new BackgroundFill(null, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	public void setDes(String toolName, String toolDescription) {
		this.toolName = toolName;
		this.toolDescription = toolDescription;
	}
	
	public void showDes() {
		SceneController.getGameScene().getDescriptionPane().setDes(toolName, toolDescription);
	}
}
