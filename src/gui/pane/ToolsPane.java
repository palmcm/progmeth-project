package gui.pane;

import java.util.ArrayList;

import config.GameConfig;
import gui.cell.ToolsCell;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import logic.gmanager.GameButtons;
import logic.gmanager.GameManager;

public class ToolsPane extends VBox {
	ArrayList<ToolsCell> toolsList = new ArrayList<ToolsCell>();
	public ToolsPane() {
		ToolsCell upgradeIncomeIcon = new ToolsCell();
		
		upgradeIncomeIcon.setDes("Upgrade Income", GameManager.getCurrentPlayerIncomeToolTip());
		ToolsCell upgradeIcon  = new ToolsCell();
		upgradeIcon.setDes("Upgrade Tower", "Upgrade tower to next level");
		ToolsCell sellIcon = new ToolsCell();
		sellIcon.setDes("Sell Tower", "Sell tower for money");
		
		toolsList.add(upgradeIcon);
		toolsList.add(sellIcon);
		
		upgradeIncomeIcon.setOnMouseClicked(e -> {
			GameButtons.upgradeIncome();
			upgradeIncomeIcon.setDes("Upgrade Income", GameManager.getCurrentPlayerIncomeToolTip());
			upgradeIncomeIcon.showDes();
		});
		
		upgradeIcon.setOnMouseClicked(e -> {
			GameButtons.upgradeMode();
		});
		
		sellIcon.setOnMouseClicked(e -> {
			GameButtons.destroyMode();
		});
		
		this.getChildren().addAll(upgradeIncomeIcon,upgradeIcon,sellIcon);
		this.setMinWidth(GameConfig.SCREEN_WIDTH/5-10);
		this.setAlignment(Pos.CENTER);
		this.setSpacing(5);
	}
	
	public ArrayList<ToolsCell> getToolsList() {
		return toolsList;
	}
}
