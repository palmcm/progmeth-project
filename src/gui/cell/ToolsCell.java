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
import logic.gmanager.GameButtons;
import logic.gmanager.GameManager;
import logic.gmanager.TurnPhase;

public class ToolsCell extends StackPane{
	
	private String toolName;
	private String toolDescription;
	private ImageView icon;
	private String tool;
	
	public ToolsCell() {
		icon = new ImageView();
		icon.setFitWidth(70);
		icon.setFitHeight(70);
		this.getChildren().add(icon);
		this.setMaxSize(70, 70);
		this.setOnMouseEntered(e -> {
			showDes();
		});
		
		this.setOnMouseExited(e -> {
			SceneController.getGamePane().getDescriptionPane().setDesDefault();
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
		SceneController.getGamePane().getDescriptionPane().setDes(toolName, toolDescription);
	}
	
	public void setIcon(Image img) {
		this.icon.setImage(img);
	}
	
	public void selectHandle() {
		if (tool == "upgrade") {
			GameButtons.upgradeMode();
		}else if(tool == "sell"){
			GameButtons.destroyMode();
		}
	}
	
	public void setTool(String tool) {
		this.tool = tool;
	}
}
