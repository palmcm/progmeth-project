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

public class ToolsPane extends VBox {
	ArrayList<ToolsCell> toolsList = new ArrayList<ToolsCell>();
	public ToolsPane() {
		ToolsCell upgradeIncomeIcon = new ToolsCell();
		upgradeIncomeIcon.setIcon(new Image("/buttons/upgrade_income.png"));
		upgradeIncomeIcon.setDes("Increase Research", GameManager.getCurrentPlayerIncomeToolTip());
		ToolsCell upgradeIcon  = new ToolsCell();
		upgradeIcon.setIcon(new Image("/buttons/upgrade_unit.png"));
		upgradeIcon.setDes("Upgrade Unit", "Upgrade unit to the next level");
		ToolsCell sellIcon = new ToolsCell();
		sellIcon.setIcon(new Image("/buttons/remove_unit.png"));
		sellIcon.setDes("Retire Unit", "Remove unit from the board");
		
		upgradeIncomeIcon.setOnMouseClicked(e -> {
			if (GameButtons.upgradeIncome()) {
				upgradeIncomeIcon.setDes("Upgrade Income", GameManager.getCurrentPlayerIncomeToolTip());
				upgradeIncomeIcon.showDes();
				SceneController.getGameScene().updateMoney();

				SceneController.getGameScene().getPlayerPane(GameManager.getCurrentPlayer()).updateIncome();
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
