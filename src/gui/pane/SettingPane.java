package gui.pane;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import utils.FontUtil;

/**
 * Pane for displaying setting box
 */
public class SettingPane extends HBox{
	/** Setting label*/
	private Text label;
	/** Setting toggle button*/
	private Text setting;
	
	/**
	 * Constructor for SettingPane
	 * @param label Text for label
	 * @param setting Text for initial option 
	 */
	public SettingPane(String label,String setting) {
		super(10);
		this.label = new Text(label);
		this.label.setFont(FontUtil.loadFont(25));
		this.setting = new Text(setting);
		this.setting.setFont(FontUtil.loadFont(25));
		FontUtil.addHoverHighlight(getSetting(),Color.BLACK);
		
		this.getChildren().addAll(this.label, this.setting);
		this.setAlignment(Pos.CENTER);
	}
	
	/**
	 * Getter for {@link #setting setting}
	 * @return {@link #setting setting}
	 */
	public Text getSetting() {
		return setting;
	}
	
}
