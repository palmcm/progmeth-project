package gui.pane;

import java.util.ArrayList;

import gui.cell.DeckCell;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import logic.gmanager.GameManager;
import logic.towers.BaseTower;
import utils.CommonImages;
/**
 * 
 * Pane for displaying player's deck
 *
 */
public class DeckPane extends GridPane{
	/** List of DeckCell containing player's tower*/
	private ArrayList<DeckCell> deckCells = new ArrayList<DeckCell>();
	/** Owner of the pane*/
	private int player;
	/**
	 * Constructor for DeckPane
	 * @param player Owner of the pane
	 */
	public DeckPane(int player) {
		this.player = player;
		for(int i=0;i<5;i++) {
			DeckCell cell = new DeckCell(player);
			deckCells.add(cell);
			this.add(cell, 0, i);
		}
		this.setAlignment(Pos.CENTER);
	}
	/**
	 * Constructor for DeckPane with deck
	 * @param deck Player's deck
	 * @param player Owner of deck
	 */
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
	/**
	 * Update display for DeckPane
	 */
	public void update() {
		ArrayList<BaseTower> deck = GameManager.getGameInstance().getPlayer(player).getDeck();
		int i = 0;
		for(BaseTower tower:deck) {
			deckCells.get(i).setTower(tower);
			i++;
		}
		for(;i<5;i++) {
			deckCells.get(i).setTower(null);
		}
		this.setAlignment(Pos.CENTER);
	}
	/**
	 * Set background of DeckCell
	 * @param highlight True when their turn to pick
	 */
	public void highlight(boolean highlight) {
		for (DeckCell d : deckCells) {
			d.getCellBackground().setImage(CommonImages.getDeckBackground(player, highlight));
		}
	}
}
