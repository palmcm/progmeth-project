package gui.pane;

import gui.cell.TowerCell;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class DeckPane extends GridPane{
	
	public DeckPane() {
		for(int i=0;i<5;i++) {
			this.add(new TowerCell(), 0, i);
		}
		this.setAlignment(Pos.CENTER);
	}
}
