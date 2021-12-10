package uet.oop.bomberman.entities.tile.item;

import javafx.scene.image.Image;

public class FlameItem extends Item {
	public static int timeItem = 0;
	public static boolean isPickUp = false;

	public FlameItem(int xUnit, int yUnit, Image img) {
		super(xUnit, yUnit, img);
	}

}
