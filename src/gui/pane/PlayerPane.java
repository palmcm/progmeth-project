package gui.pane;

import config.GameConfig;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class PlayerPane extends VBox {
	
	private Rectangle hpTab;
	private Text hpText;
	private Text moneyText;
	private Text incomeText;
	private Text upgradeText;
	
	public PlayerPane(int player) {
		
		StackPane hpPane = new StackPane();
		
		Insets inset = new Insets(GameConfig.SCREEN_WIDTH/100);
		
		HBox hpBar = new HBox();
		hpTab = new Rectangle(GameConfig.SCREEN_WIDTH/2.5, 50.0, Color.LIMEGREEN);
		hpBar.getChildren().add(hpTab);
		
		HBox playerBox = new HBox();
		Text playerText = new Text("Player 1");
		playerBox.getChildren().add(playerText);
		playerBox.setPadding(inset);

		HBox hpBox = new HBox();
		hpText = new Text("100/100");
		hpBox.getChildren().add(hpText);
		hpBox.setPadding(inset);
		
		if (player == 1) {
			playerBox.setAlignment(Pos.CENTER_LEFT);
			hpBox.setAlignment(Pos.CENTER_RIGHT);
		}else {
			playerBox.setAlignment(Pos.CENTER_RIGHT);
			hpBox.setAlignment(Pos.CENTER_LEFT);
		}
		
		hpPane.getChildren().addAll(hpBar, playerBox, hpBox);
		
		StackPane infoPane = new StackPane();
		HBox moneyBox = new HBox();
		moneyText = new Text("100$");
		moneyBox.getChildren().add(moneyText);
		moneyBox.setPadding(inset);
		
		HBox incomeBox = new HBox();
		incomeText = new Text("+10$/Turn");
		incomeBox.getChildren().add(incomeText);
		incomeBox.setPadding(inset);
		
		HBox upgradeBox = new HBox();
		upgradeText = new Text("*****");
		upgradeBox.getChildren().add(upgradeText);
		upgradeBox.setPadding(inset);
		upgradeBox.setAlignment(Pos.CENTER);
		
		if (player == 1) {
			moneyBox.setAlignment(Pos.CENTER_LEFT);
			incomeBox.setAlignment(Pos.CENTER_RIGHT);
		}else {
			moneyBox.setAlignment(Pos.CENTER_RIGHT);
			incomeBox.setAlignment(Pos.CENTER_LEFT);
		}
		
		infoPane.getChildren().addAll(moneyBox, incomeBox, upgradeBox);
		
		this.getChildren().addAll(hpPane, infoPane);
	}
	
	
}
