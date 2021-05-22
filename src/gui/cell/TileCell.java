package gui.cell;


import config.GameConfig;
import gui.SceneController;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import logic.gmanager.Tile;
import logic.towers.AimableTower;
import logic.towers.AttackableTower;
import logic.towers.BaseTower;
import utils.CommonImages;
import utils.FontUtil;
import utils.ImageUtil;
import utils.SoundUtil;


/**
 * Component cell for display tower in game map
 */
public class TileCell extends StackPane{
	/** Tile data in the cell*/
	private Tile tile;
	/** For display tower image*/
	private ImageView towerImg;
	/** For display tower level*/
	private ImageView towerUpgradeEmblem;
	/** Size of the cell*/
	private int SIZE = GameConfig.SCREEN_WIDTH / 18;
	/** Size for scaling health bar*/
	private double pxSize = SIZE/32.0;
	/** For display attacking sequence*/
	private Text attackSeq;
	/** For display highlight of the cell*/
	private ImageView highlightBackground;
	/** For display heath*/
	private Rectangle healthbar;
	/** For display heath bar background*/
	private ImageView hpBackground;
	/** @deprecated For display damage frame*/
	private ImageView damageFrame;
	
	/**
	 * Constructor for TileCell
	 * 
	 * @param tile {@link #tile tile} for display
	 */
	public TileCell(Tile tile) {
		this.tile = tile;
		towerImg = new ImageView();
		towerImg.setFitWidth(SIZE);
		towerImg.setFitHeight(SIZE);
		
		highlightBackground = new ImageView();
		highlightBackground.setFitWidth(SIZE);
		highlightBackground.setFitHeight(SIZE);
		
		towerUpgradeEmblem = new ImageView();
		towerUpgradeEmblem.setFitWidth(SIZE);
		towerUpgradeEmblem.setFitHeight(SIZE);
		
		healthbar = new Rectangle();
		healthbar.setWidth(0);
		healthbar.setHeight(pxSize*3);
		healthbar.setTranslateY(-pxSize*12);
		
		hpBackground = new ImageView();
		hpBackground.setFitWidth(SIZE);
		hpBackground.setFitHeight(SIZE);
		
		hpBackground.setImage(CommonImages.hpBackground);
		hpBackground.setScaleX(0);
		hpBackground.setScaleY(1);
		hpBackground.setTranslateY(-pxSize);
		
		damageFrame  = new ImageView();
		damageFrame.setFitWidth(SIZE);
		damageFrame.setFitHeight(SIZE);
		
		StackPane attackSeqBox = new StackPane();
		attackSeq = new Text();
		attackSeq.setFont(FontUtil.loadFont(16));
		attackSeqBox.setAlignment(Pos.TOP_LEFT);
		attackSeqBox.setPadding(new Insets(5));
		attackSeqBox.getChildren().add(attackSeq);
		
		this.getChildren().addAll(highlightBackground,towerImg,damageFrame,attackSeqBox,towerUpgradeEmblem,hpBackground,healthbar);
		this.setPrefSize(SIZE, SIZE);
		/*if (tile.getTileOwner() != 0) {
			this.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, 
					CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		}*/
	}
	
	/**
	 * Getter for {@link TileCell#tile tile}
	 * 
	 * @return {@link TileCell#tile tile}
	 */
	public Tile getTile() {
		return tile;
	}
	
	/**
	 * Update display for {@link #towerImg towerImg} and {@link #towerUpgradeEmblem towerUpgradeEmblem}
	 */
	public void update() {
		BaseTower tower = tile.getTower();
		if (tower != null) {
			towerImg.setImage(ImageUtil.ImageLoader(tower.getUrl(),SIZE));
			if(tower.getOwner() == 2)
			{
				towerImg.setScaleX(-1);
			}
			if(tower.getUpgradeLevel() == 0)
			{
				towerUpgradeEmblem.setImage(null);
			}
			else
			{
				towerUpgradeEmblem.setImage(CommonImages.getUpgradeEmblem(tower.getUpgradeLevel()));				
			}
		} else {
			towerImg.setImage(null);
			towerUpgradeEmblem.setImage(null);
			updateHealth();
		}
		
	}
	
	/**
	 * Update display for health bar
	 */
	public void updateHealth()
	{
		BaseTower tower = tile.getTower();
		
		if(tower == null)
		{
			healthbar.setWidth(0);
			hpBackground.setScaleX(0);
		}
		else if(tower.getCurrentHealth() == tower.getMaxHealth())
		{
			healthbar.setWidth(0);
			hpBackground.setScaleX(0);
		}
		else
		{
//			System.out.println(tower.getCurrentHealth()+" "+tower.getMaxHealth());
			double fullWidth,cWidth,hpp;
			fullWidth = pxSize*17;
			boolean overheal = false;
			
			hpp = (double)tower.getCurrentHealth() / (double)tower.getMaxHealth();
			hpBackground.setScaleX(1);
			if(hpp<0.0)
				hpp = 0.0;

			if(hpp > 1.0)
			{
				hpp = 1.0;
				overheal = true;
			}
			cWidth = fullWidth * hpp;
			this.healthbar.setWidth(cWidth);
			this.healthbar.setHeight(SIZE/10);
			this.healthbar.setTranslateX(-(fullWidth - cWidth)/2);
			if(overheal)
			{
				this.healthbar.setFill(Color.LIGHTCYAN);
			}
			else if(hpp >= 0.5)
			{
				this.healthbar.setFill(Color.LIMEGREEN);
			}
			else if(hpp > 0.2)
			{
				this.healthbar.setFill(Color.YELLOW);
				
			}
			else
			{
				this.healthbar.setFill(Color.RED);
			}
		}
		
	}
	
	/**
	 * Animation for tower on this tile taking damage
	 */
	public void takeDamageAnimation(){
		new Thread(() -> {
		try {
			Thread.sleep(100);
			Platform.runLater(() -> {
//				damageFrame.setImage(CommonImages.getHighlighter("empty"));
				towerImg.setImage(ImageUtil.ImageLoader(tile.getTower().getDamageUrl(),80));
			});
			Thread.sleep(250);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			Platform.runLater(() -> {
//				damageFrame.setImage(null);
				update();
			});
		}).start();
	}
	
	/**
	 * Animation for tower on this tile attacking
	 */
	public void attackAnimation() {
		new Thread(() -> {
			Platform.runLater(() -> {
				SoundUtil.playAttackEffect(tile.getTower());
				attackSeq.setFill(Color.WHITE);
				towerImg.setImage(ImageUtil.ImageLoader(tile.getTower().getAttackUrl(),80));
			});
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			Platform.runLater(() -> update());
		}).start();
	}
	
	/**
	 * Set display for {@link #attackSeq attackSeq} 
	 * 
	 * @param seq  sequence in attacking; if 0, attackSeq will be blank
	 */
	public void setAttackSeq(int seq) {
		if (seq == 0) {
			attackSeq.setText("");
			return;
		}
		attackSeq.setText(seq + "");
	}
	
	/**
	 * Set font color of {@link #attackSeq attackSeq} to default
	 */
	public void setDefaultFill() {
		attackSeq.setFill(Color.BLACK);
	}
	/**
	 * Show tower information in {@link gui.pane.DescriptionPane DescriptionPane}
	 */
	public void showDes() {
		if(this.getTile().getTower() == null)
		{
			SceneController.getGamePane().getDescriptionPane().setDesDefault();
			return;
		}
		SceneController.getGamePane().getDescriptionPane().setDes(this.getTileTowerName(), this.getTileTowerDesc());
	}
	/**
	 * Getter for tower's name
	 * @return Name of tower in the cell
	 */
	private String getTileTowerName()
	{
		return this.getTile().getTower().getCurrentName();
	}
	/**
	 * Getter for tower's description
	 * @return Description of tower in the cell
	 */
	private String getTileTowerDesc()
	{
		return this.getTile().getTower().getInstanceToolTipString();
	}
	/**
	 * Getter for tower's next upgrade info
	 * @return Next upgrade info of tower in the cell
	 */
	public String getTileUpgradeTooltip()
	{
		return this.getTile().getTower().getNextUpgradeInfo();
	}
	/**
	 * Getter for tower's attack status
	 * @return Attack status of tower in the cell
	 */
	public String getTileAttackStatus()
	{
		BaseTower tower = this.getTile().getTower();
		if(!(tower instanceof AttackableTower))
		{
			return "This unit cannot cast a spell!";
		}
		if(tower.isFrozen())
		{
			return "This unit is currently frozen!";
		}
		if(this.getTile().isMarkAttacked())
		{
			return "This unit already has a spell queued!";
		}
		AttackableTower aTower = (AttackableTower) tower;
		if(aTower.getCurrentCooldown()>0)
		{
			return "This unit still needs "+aTower.getCurrentCooldown()+" turns to recharge.";
		}
		if(aTower instanceof AimableTower)
		{
			return "This unit can cast a targeted spell!\nSelect the unit then select a tile to queue the spell.";
		}
		return "This unit can cast a spell! Click to queue the spell.";
	}
	
	/**
	 * Highlight the cell for range of selected tower
	 */
	public void doHighlight()
	{
		this.highlightBackground.setImage(CommonImages.getHighlighter("range"));
		
	}
	/**
	 * Remove highlight from the cell
	 */
	public void unHighlight()
	{
		this.highlightBackground.setImage(null);
	}
	/**
	 * Highlight the cell for target of the action
	 */
	public void doTargetHighlight()
	{
		this.highlightBackground.setImage(CommonImages.getHighlighter("target"));
		
	}
	/**
	 * Highlight the cell for place the unit
	 */
	public void doUnitHighlight()
	{
		this.highlightBackground.setImage(CommonImages.getHighlighter("unit"));
		
	}
	/**
	 * Highlight the cell for showing placeable cell
	 */
	public void doEmptyHighlight()
	{
		this.highlightBackground.setImage(CommonImages.getHighlighter("empty"));
	}
}
