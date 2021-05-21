package gui.pane;

import java.util.ArrayList;

import gui.cell.DeckCell;
import gui.cell.TowerCell;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import logic.gmanager.GameManager;
import logic.towers.BaseTower;
import utils.CommonImages;

public class DeckPane extends GridPane{
	
	private ArrayList<DeckCell> deckCells = new ArrayList<DeckCell>();
	
	private int player;
	
	public DeckPane(int player) {
		this.player = player;
		for(int i=0;i<5;i++) {
			DeckCell cell = new DeckCell(player);
			deckCells.add(cell);
			this.add(cell, 0, i);
		}
		this.setAlignment(Pos.CENTER);
	}
	
	public DeckPane(ArrayList<BaseTower> deck,int player) {
		this.player = player;
		int i = 0;
		for(BaseTower tower:deck) {
			DeckCell cell = new DeckCell(tower,player);
			deckCells.add(cell);
			this.add(cell, i/5, i);
			i++;
		}
		for(;i<5;i++) {
			DeckCell cell = new DeckCell(player);
			deckCells.add(cell);
			this.add(cell, 0, i);
		}
		this.setAlignment(Pos.CENTER);
	}
	
	public void update() {
		ArrayList<BaseTower> deck = GameManager.getGameInstance().getPlayer(player).getDeck();
		int i = 0;
		for(BaseTower tower:deck) {
			deckCells.get(i).setTower(tower, player);
			i++;
		}
		for(;i<5;i++) {
			deckCells.get(i).setTower(null, player);
		}
		this.setAlignment(Pos.CENTER);
	}
	
	public void highlight(boolean highlight) {
		for (DeckCell d : deckCells) {
			d.getCellBackground().setImage(CommonImages.getDeckBackground(player, highlight));
		}
	}
}
