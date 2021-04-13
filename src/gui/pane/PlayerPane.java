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
import logic.gmanager.GameInstance;
import logic.gmanager.GameManager;
import logic.gmanager.Player;

public class PlayerPane extends VBox {
	
	final private double BASE_WIDTH = GameConfig.SCREEN_WIDTH/2.5;
	private Rectangle hpTab;
	private Text hpText;
	private Text moneyText;
	private Text incomeText;
	private Text upgradeText;
	private Player player;
	
	
	public PlayerPane(int player) {
		this.player = GameManager.getGameInstance().getPlayer(player);
		
		StackPane hpPane = new StackPane();
		
		Insets inset = new Insets(GameConfig.SCREEN_WIDTH/100);
		
		HBox hpBar = new HBox();
		hpTab = new Rectangle(BASE_WIDTH, 50.0, Color.LIMEGREEN);
		hpBar.getChildren().add(hpTab);
		
		HBox playerBox = new HBox();
		Text playerText = new Text("Player " + player);
		playerBox.getChildren().add(playerText);
		playerBox.setPadding(inset);

		HBox hpBox = new HBox();
		hpText = new Text();
		updateHp();
		hpBox.getChildren().add(hpText);
		hpBox.setPadding(inset);
		
		if (player == 1) {
			hpBar.setAlignment(Pos.CENTER_RIGHT);
			playerBox.setAlignment(Pos.CENTER_LEFT);
			hpBox.setAlignment(Pos.CENTER_RIGHT);
		}else {
			hpBar.setAlignment(Pos.CENTER_LEFT);
			playerBox.setAlignment(Pos.CENTER_RIGHT);
			hpBox.setAlignment(Pos.CENTER_LEFT);
		}
		
		hpPane.getChildren().addAll(hpBar, playerBox, hpBox);
		
		StackPane infoPane = new StackPane();
		HBox moneyBox = new HBox();
		moneyText = new Text();
		updateMoney();
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
		this.setMinWidth(BASE_WIDTH);
	}
	
	public void setHpBar(double hpPercent) {
		hpTab.setWidth(BASE_WIDTH*hpPercent);
	}
	
	public void updateHp() {
		int fullHp = player.getMaxHealth();
		int nowHp = player.getHealth();
		setHpBar((double)nowHp/fullHp);
		hpText.setText(nowHp+"/"+fullHp);
	}
	
	public void updateIncome() {
		
	}
	
	public void updateMoney() {
		int money = player.getMoney();
		moneyText.setText(money+"$");
	}
}
