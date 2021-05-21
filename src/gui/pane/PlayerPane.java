package gui.pane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import logic.gmanager.GameManager;
import logic.gmanager.Player;
import utils.CommonStrings;
import utils.FontUtil;

/**
 * Pane for displaying player status
 */
public class PlayerPane extends VBox {
	/** Width size for pane*/
	final private double BASE_WIDTH = 558;
	/** Player heath bar*/
	private Rectangle hpTab;
	/** Player heath text*/
	private Text hpText;
	/** Player money text*/
	private Text moneyText;
	/** Player income text*/
	private Text incomeText;
	/** Owner of status */
	private Player player;
	
	/**
	 * Constructor for PlayerPane
	 * @param player Owner
	 */
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
		moneyText.setFont(FontUtil.loadFont(18));
		updateMoney();
		moneyBox.getChildren().add(moneyText);
		moneyBox.setPadding(inset);
		
		HBox incomeBox = new HBox();
		incomeText = new Text("+"+this.player.getIncome()+CommonStrings.currency_symbol);
		incomeText.setFill(Color.WHITE);
		incomeText.setFont(FontUtil.loadFont(18));
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
	/**
	 * Set display for heath bar 
	 * @param hpPercent ratio of health by full health (0 to 1)
	 */
	public void setHpBar(double hpPercent) {
		hpTab.setWidth(BASE_WIDTH*hpPercent);
	}
	
	/**
	 * Update display for health bar
	 */
	public void updateHp() {
		int fullHp = player.getMaxHealth();
		int nowHp = player.getHealth();
		setHpBar((double)nowHp/fullHp);
		hpText.setText(nowHp+"/"+fullHp);
	}
	
	/**
	 * Update display for income
	 */
	public void updateIncome() {
		int income = player.getIncome();
		incomeText.setText("+"+income+CommonStrings.currency_symbol);
	}
	
	/**
	 * Update display for money
	 */
	public void updateMoney() {
		int money = player.getMoney();
		moneyText.setText(money+CommonStrings.currency_symbol);
	}
}
