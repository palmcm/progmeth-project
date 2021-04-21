package gui.cell;


import config.GameConfig;
import gui.SceneController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.gmanager.Tile;
import logic.towers.AimableTower;
import logic.towers.AttackableTower;
import logic.towers.BaseTower;
import utils.CommonImages;
import utils.ImageUtil;

public class TileCell extends StackPane{
	private Tile tile;
	private ImageView towerImg;
	private ImageView towerUpgradeEmblem;
	private int SIZE = GameConfig.SCREEN_WIDTH / 18;;
	private double pxSize = SIZE/32.0;
	private Text attackSeq;
	private ImageView highlightBackground;
	private Rectangle healthbar;
	private ImageView hpBackground;
	
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
		
		StackPane attackSeqBox = new StackPane();
		attackSeq = new Text();
		attackSeqBox.setAlignment(Pos.TOP_LEFT);
		attackSeqBox.setPadding(new Insets(5));
		attackSeqBox.getChildren().add(attackSeq);
		
		this.getChildren().addAll(highlightBackground,towerImg,attackSeqBox,towerUpgradeEmblem,hpBackground,healthbar);
		this.setPrefSize(SIZE, SIZE);
		/*if (tile.getTileOwner() != 0) {
			this.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, 
					CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		}*/
	}
	
	public Tile getTile() {
		return tile;
	}
	
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
		}
		
	}
	
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
	
	public void setAttackSeq(int seq) {
		if (seq == 0) {
			attackSeq.setText("");
			return;
		}
		attackSeq.setText(seq + "");
	}
	
	public void showDes() {
		if(this.getTile().getTower() == null)
		{
			SceneController.getGamePane().getDescriptionPane().setDesDefault();
			return;
		}
		SceneController.getGamePane().getDescriptionPane().setDes(this.getTileTowerName(), this.getTileTowerDesc());
	}
	
	private String getTileTowerName()
	{
		return this.getTile().getTower().getCurrentName();
	}
	
	private String getTileTowerDesc()
	{
		return this.getTile().getTower().getInstanceToolTipString();
	}
	
	public String getTileUpgradeTooltip()
	{
		return this.getTile().getTower().getNextUpgradeInfo();
	}
	
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
	
	public void doHighlight()
	{
		this.highlightBackground.setImage(CommonImages.getHighlighter("range"));
		
	}
	
	public void unHighlight()
	{
		this.highlightBackground.setImage(null);
	}
	
	public void doTargetHighlight()
	{
		this.highlightBackground.setImage(CommonImages.getHighlighter("target"));
		
	}
	
	public void doUnitHighlight()
	{
		this.highlightBackground.setImage(CommonImages.getHighlighter("unit"));
		
	}
	
	public void doEmptyHighlight()
	{
		this.highlightBackground.setImage(CommonImages.getHighlighter("empty"));
	}
}
