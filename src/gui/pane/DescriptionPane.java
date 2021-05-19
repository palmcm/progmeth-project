package gui.pane;

import config.GameConfig;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import utils.FontUtil;

public class DescriptionPane extends VBox{
	private Text nameText;
	private Text desText;
	
	public DescriptionPane() {
		
		nameText = new Text();
		nameText.setFont(FontUtil.loadFont(22));
		nameText.setFill(Color.WHITE);
		
		desText = new Text();
		this.setDesDefault();
		desText.setFont(FontUtil.loadFont(13));
		desText.setFill(Color.WHITE);
		
		this.getChildren().addAll(nameText, desText);
		this.setAlignment(Pos.TOP_LEFT);
		this.setMinSize(650, GameConfig.SCREEN_HEIGHT*0.25);
		this.setPadding(new Insets(35,0,0,45));
		this.getChildren().addAll();
//		this.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, 
//				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}
	
	public void setDesDefault() {
		nameText.setText("");
		desText.setText("");
	}
	
	public void setDes(String name, String des) {
		nameText.setText(name);
		desText.setText(des);
	}
}
