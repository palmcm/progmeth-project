package gui.cell;


import config.GameConfig;
import gui.SceneController;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
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
	private int SIZE = GameConfig.SCREEN_WIDTH / 18;;
	private Text attackSeq;
	private ImageView highlightBackground;
	
	public TileCell(Tile tile) {
		this.tile = tile;
		towerImg = new ImageView();
		towerImg.setFitWidth(SIZE);
		towerImg.setFitHeight(SIZE);
		highlightBackground = new ImageView();
		highlightBackground.setFitWidth(SIZE);
		highlightBackground.setFitHeight(SIZE);
		
		attackSeq = new Text(tile.getTileOwner()+"");
		this.getChildren().addAll(highlightBackground,towerImg,attackSeq);
		this.setPrefSize(SIZE, SIZE);
		this.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
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
		} else {
			towerImg.setImage(null);
		}
		
	}
	
	public void setAttackSeq(int seq) {
		attackSeq.setText(seq+"");
	}
	
	public void showDes() {
		if(this.getTile().getTower() == null)
		{
			SceneController.getGameScene().getDescriptionPane().setDesDefault();
			return;
		}
		SceneController.getGameScene().getDescriptionPane().setDes(this.getTileTowerName(), this.getTileTowerDesc());
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
}
