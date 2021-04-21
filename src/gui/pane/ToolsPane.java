package gui.pane;

import java.util.ArrayList;

import config.GameConfig;
import gui.SceneController;
import gui.cell.ToolsCell;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import logic.gmanager.GameButtons;
import logic.gmanager.GameManager;
import utils.ImageUtil;

public class ToolsPane extends VBox {
	ArrayList<ToolsCell> toolsList = new ArrayList<ToolsCell>();
	public ToolsPane() {
		ToolsCell upgradeIncomeIcon = new ToolsCell();
		upgradeIncomeIcon.setIcon(ImageUtil.ImageLoader("buttons/upgrade_income.png", 100));
		upgradeIncomeIcon.setDes("Increase Research", GameManager.getCurrentPlayerIncomeToolTip());
		ToolsCell upgradeIcon  = new ToolsCell();
		upgradeIcon.setIcon(ImageUtil.ImageLoader("buttons/upgrade_unit.png", 100));
		upgradeIcon.setDes("Upgrade Unit", "Upgrade unit to the next level");
		ToolsCell sellIcon = new ToolsCell();
		sellIcon.setIcon(ImageUtil.ImageLoader("buttons/remove_unit.png", 100));
		sellIcon.setDes("Retire Unit", "Remove unit from the board");
		
		upgradeIncomeIcon.setOnMouseClicked(e -> {
			if (GameButtons.upgradeIncome()) {
				upgradeIncomeIcon.setDes("Upgrade Income", GameManager.getCurrentPlayerIncomeToolTip());
				upgradeIncomeIcon.showDes();
				SceneController.getGamePane().updateMoney();

				SceneController.getGamePane().getPlayerPane(GameManager.getCurrentPlayer()).updateIncome();
			}
			
		});
		
		upgradeIcon.setTool("upgrade");
		
		sellIcon.setTool("sell");
		
		toolsList.add(upgradeIcon);
		toolsList.add(sellIcon);
		
		this.getChildren().addAll(upgradeIncomeIcon,upgradeIcon,sellIcon);
		this.setMinWidth(GameConfig.SCREEN_WIDTH/5-10);
		this.setAlignment(Pos.CENTER);
		this.setSpacing(5);
	}
	
	public ArrayList<ToolsCell> getToolsList() {
		return toolsList;
	}
}
