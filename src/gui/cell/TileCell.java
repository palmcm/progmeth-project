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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.gmanager.Tile;
import logic.towers.BaseTower;
import utils.ImageUtil;

public class TileCell extends StackPane{
	private Tile tile;
	private ImageView towerImg;
	private int SIZE = GameConfig.SCREEN_WIDTH / 18;;
	private Text attackSeq;
	public TileCell(Tile tile) {
		this.tile = tile;
		towerImg = new ImageView();
		towerImg.setFitWidth(SIZE);
		towerImg.setFitHeight(SIZE);
		attackSeq = new Text(tile.getTileOwner()+"");
		this.getChildren().addAll(towerImg,attackSeq);
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
	
	public void showUpgradeToolTip()
	{
		SceneController.getGameScene().getTilesPane().getTooltip().setText(this.getTile().getTower().getNextUpgradeInfo());
	}
}
