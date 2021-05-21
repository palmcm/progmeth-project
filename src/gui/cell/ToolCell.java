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
import logic.gmanager.GameButtons;
import utils.CommonImages;

/**
 * Component cell for showing tool in game
 */
public class ToolCell extends StackPane{
	
	/** Tool name */
	private String toolName;
	/** Tool description*/
	private String toolDescription;
	/** Picture of tool*/
	private ImageView icon;
	/** Type of tool*/
	private String tool;
	
	/**
	 * Constructor for ToolCell
	 */
	public ToolCell() {
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
	
	/**
	 * Highlight the cell when selected
	 */
	public void highlight() {
		this.setBackground(new Background(new BackgroundImage(CommonImages.getHighlighter("select"), BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
		        BackgroundPosition.DEFAULT,
		        new BackgroundSize(1.0, 1.0, true, true, false, false))));
	}
	/**
	 * Remove Highlight
	 */
	public void unhighlight() {
		this.setBackground(null);
	}
	/**
	 * Setter for {@link #toolName toolName} and {@link #toolDescription toolDescription}
	 * @param toolName Name for the tool
	 * @param toolDescription Description for the tool
	 */
	public void setDes(String toolName, String toolDescription) {
		this.toolName = toolName;
		this.toolDescription = toolDescription;
	}
	/**
	 * Show tool information in {@link gui.pane.DescriptionPane DescriptionPane}
	 */
	public void showDes() {
		SceneController.getGamePane().getDescriptionPane().setDes(toolName, toolDescription);
	}
	/**
	 * Set display icon for tool
	 * @param img Image for tool
	 */
	public void setIcon(Image img) {
		this.icon.setImage(img);
	}
	/**
	 * Handle for select tool
	 */
	public void selectHandle() {
		if (tool == "upgrade") {
			GameButtons.upgradeMode();
		}else if(tool == "sell"){
			GameButtons.destroyMode();
		}
	}
	/**
	 * Setter for {@link #tool tool}
	 * @param tool Tool type
	 */
	public void setTool(String tool) {
		this.tool = tool;
	}
}
