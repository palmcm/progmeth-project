package gui.pane;

import java.util.ArrayList;

import gui.cell.DeckCell;
import gui.cell.TowerCell;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import logic.gmanager.GameManager;
import logic.towers.BaseTower;

public class DeckPane extends GridPane{
	
	public ArrayList<DeckCell> deckCells = new ArrayList<DeckCell>();
	
	public DeckPane() {
		for(int i=0;i<5;i++) {
			DeckCell cell = new DeckCell();
			deckCells.add(cell);
			this.add(cell, 0, i);
		}
		this.setAlignment(Pos.CENTER);
	}
	
	public DeckPane(ArrayList<BaseTower> deck) {
		int i = 0;
		for(BaseTower tower:deck) {
			this.add(new DeckCell(tower), i/5, i);
			i++;
		}
		for(;i<5;i++) {
			this.add(new DeckCell(), 0, i);
		}
		this.setAlignment(Pos.CENTER);
	}
	
	public void update(int player) {
		ArrayList<BaseTower> deck = GameManager.getGameInstance().getPlayer(player).getDeck();
		clear();
		
		int i = 0;
		for(BaseTower tower:deck) {
			this.add(new DeckCell(tower), i/5, i);
			i++;
		}
		for(;i<5;i++) {
			this.add(new DeckCell(), 0, i);
		}
		this.setAlignment(Pos.CENTER);
	}
	
	public void clear() {
		while (this.getChildren().size()>0) {
			this.getChildren().remove(0);
		}
	}
//	public void update() {
//		ArrayList<BaseTower> deck = GameManager.getGameInstance().getPlayer(1).getDeck();
//		int i = 0;
//		for(BaseTower tower:deck) {
//			deckCells.get(i).setTower(tower);
//			i++;
//		}
//		for(;i<5;i++) {
//			this.add(new DeckCell(), 0, i);
//		}
//		this.setAlignment(Pos.CENTER);
//	}
}
