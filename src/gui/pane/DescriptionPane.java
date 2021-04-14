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

public class DescriptionPane extends VBox{
	private Text nameText;
	private Text desText;
	
	public DescriptionPane() {
		
		nameText = new Text();
		nameText.setFont(new Font(20));
		desText = new Text();
		this.setDesDefault();
		desText.setFont(new Font(13));
		
		this.getChildren().addAll(nameText, desText);
		this.setAlignment(Pos.TOP_LEFT);
		this.setMinSize(GameConfig.SCREEN_WIDTH/2.5, GameConfig.SCREEN_HEIGHT*0.25);
		this.setPadding(new Insets(5));
		this.getChildren().addAll();
		this.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
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
