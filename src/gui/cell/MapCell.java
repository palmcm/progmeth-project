package gui.cell;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MapCell extends VBox{
	public MapCell() {
		Text mapName = new Text("Map Name");
		mapName.setFont(new Font(20));
		Image img = new Image(ClassLoader.getSystemResource("img1.png").toString());
		ImageView mapIcon = new ImageView(img);
		double scale = img.getHeight()/150;
//		System.out.println(scale);
		mapIcon.setFitHeight(img.getHeight()/scale);
		mapIcon.setFitWidth(img.getWidth()/scale);
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(mapName,mapIcon);
		this.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		this.setPadding(new Insets(5));
		
		this.setOnMouseClicked(e -> {
			highlight();
		});
	}
	
	public void highlight() {
		this.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
	}
}
