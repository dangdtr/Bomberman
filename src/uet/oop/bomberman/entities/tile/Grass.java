package uet.oop.bomberman.entities.tile;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class Grass extends Tile {

	public Grass(int x, int y, Image img) {
		super(x, y, img);
	}

	@Override
	public void update() {

	}

	public static boolean collide(Entity bomber) {
		return true;
	}
}
