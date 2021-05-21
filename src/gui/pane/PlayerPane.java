package gui.pane;

import config.GameConfig;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import logic.gmanager.GameInstance;
import logic.gmanager.GameManager;
import logic.gmanager.Player;
import utils.CommonStrings;
import utils.FontUtil;

public class PlayerPane extends VBox {
	
	final private double BASE_WIDTH = 558;
	private Rectangle hpTab;
	private Text hpText;
	private Text moneyText;
	private Text incomeText;
	private Text upgradeText;
	private Player player;
	
	
	public PlayerPane(int player) {
		this.player = GameManager.getGameInstance().getPlayer(player);
		
		StackPane hpPane = new StackPane();
		
		Insets inset = new Insets(0,25,0,25);
		
		HBox hpBar = new HBox();
		hpTab = new Rectangle(BASE_WIDTH, 26, Color.GREEN);
		hpBar.getChildren().add(hpTab);
		hpBar.setPadding(new Insets(15));
		
		HBox playerBox = new HBox();
		Text playerText = new Text("Player " + player);
		playerText.setFill(Color.WHITE);
		playerText.setFont(FontUtil.loadFont(16));
		playerBox.getChildren().add(playerText);
		playerBox.setPadding(inset);

		HBox hpBox = new HBox();
		hpText = new Text();
		hpText.setFill(Color.WHITE);
		hpText.setFont(FontUtil.loadFont(16));
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
		moneyText.setFont(FontUtil.loadFont(16));
		updateMoney();
		moneyBox.getChildren().add(moneyText);
		moneyBox.setPadding(inset);
		
		HBox incomeBox = new HBox();
		incomeText = new Text("+10$/Turn");
		incomeText.setFill(Color.WHITE);
		incomeText.setFont(FontUtil.loadFont(16));
		incomeBox.getChildren().add(incomeText);
		incomeBox.setPadding(inset);
		
		if (player == 1) {
			moneyBox.setAlignment(Pos.CENTER_LEFT);
			incomeBox.setAlignment(Pos.CENTER_RIGHT);
		}else {
			moneyBox.setAlignment(Pos.CENTER_RIGHT);
			incomeBox.setAlignment(Pos.CENTER_LEFT);
		}
		
		infoPane.getChildren().addAll(moneyBox, incomeBox);
		infoPane.setMinHeight(44);
		
		this.getChildren().addAll(hpPane, infoPane);
		this.setMinWidth(592);
//		this.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, 
//				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
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
		int income = player.getIncome();
		incomeText.setText("+"+income+CommonStrings.currency_symbol);
	}
	
	public void updateMoney() {
		int money = player.getMoney();
		moneyText.setText(money+CommonStrings.currency_symbol);
	}
}
