package gui.pane;

import config.GameConfig;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import utils.FontUtil;
/**
 * Pane for displaying tower information in game
 */
public class DescriptionPane extends VBox{
	/** Title text*/
	private Text nameText;
	/** Detail text*/
	private Text desText;
	/**
	 * Constructor for DescriptionPane
	 */
	public DescriptionPane() {
		
		nameText = new Text();
		nameText.setFont(FontUtil.loadFont(32));
		nameText.setFill(Color.WHITE);
		
		desText = new Text();
		this.setDesDefault();
		desText.setFont(FontUtil.loadFont(20));
		desText.setFill(Color.WHITE);
		
		this.getChildren().addAll(nameText, desText);
		this.setAlignment(Pos.TOP_LEFT);
		this.setMinSize(650, GameConfig.SCREEN_HEIGHT*0.25);
		this.setPadding(new Insets(50,0,0,55));
		this.getChildren().addAll();
	}
	/**
	 * Set display to default display
	 */
	public void setDesDefault() {
		nameText.setText("");
		desText.setText("");
	}
	/**
	 * Set display according to parameter
	 * @param name Title
	 * @param des Detail
	 */
	public void setDes(String name, String des) {
		nameText.setText(name);
		desText.setText(des);
	}
}
