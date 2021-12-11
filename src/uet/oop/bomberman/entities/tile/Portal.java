package uet.oop.bomberman.entities.tile;

import javafx.scene.image.Image;

public class Portal extends Tile {
	private boolean canPass = false;
	public Portal(int xUnit, int yUnit, Image img) {
		super(xUnit, yUnit, img);
	}
	public boolean isCanPass() {
		return canPass;
	}

	public void setCanPass(boolean canPass) {
		this.canPass = canPass;
	}

}
