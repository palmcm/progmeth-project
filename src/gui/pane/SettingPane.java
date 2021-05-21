package gui.pane;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import utils.FontUtil;

public class SettingPane extends HBox{
	private Text label;
	private Text setting;
	
	public SettingPane(String label,String setting) {
		super(10);
		this.label = new Text(label);
		this.label.setFont(FontUtil.loadFont(25));
		this.setting = new Text(setting);
		this.setting.setFont(FontUtil.loadFont(25));
		
		this.getChildren().addAll(this.label, this.setting);
		this.setAlignment(Pos.CENTER);
	}
	
	public Text getSetting() {
		return setting;
	}
	
}
