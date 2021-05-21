package gui.pane;

import java.util.ArrayList;

import gui.SceneController;
import gui.cell.ToolCell;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import logic.gmanager.GameButtons;
import logic.gmanager.GameManager;
import utils.ImageUtil;

/**
 * Pane for displaying all tools
 */
public class ToolsPane extends VBox {
	/** All tool*/
	ArrayList<ToolCell> toolsList = new ArrayList<ToolCell>();
	
	/**
	 * Constructor for ToolsPane
	 */
	public ToolsPane() {
		ToolCell upgradeIncomeIcon = new ToolCell();
		upgradeIncomeIcon.setIcon(ImageUtil.ImageLoader("buttons/upgrade_income.png", 100));
		upgradeIncomeIcon.setDes("Increase Research", GameManager.getCurrentPlayerIncomeToolTip());
		ToolCell upgradeIcon  = new ToolCell();
		upgradeIcon.setIcon(ImageUtil.ImageLoader("buttons/upgrade_unit.png", 100));
		upgradeIcon.setDes("Upgrade Unit", "Upgrade unit to the next level");
		ToolCell sellIcon = new ToolCell();
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
		this.setMinWidth(150);
		this.setAlignment(Pos.CENTER);
		this.setSpacing(5);
	}
	
	/**
	 * Getter for {@link #toolsList toolsList}
	 * @return {@link #toolsList toolsList}
	 */
	public ArrayList<ToolCell> getToolsList() {
		return toolsList;
	}
}
