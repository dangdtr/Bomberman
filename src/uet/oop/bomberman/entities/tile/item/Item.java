package uet.oop.bomberman.entities.tile.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.tile.Tile;

public abstract class Item extends Tile {
	//    public static int timeItem = 0;
//    public static boolean isPickUp = false;
	public Item(int xUnit, int yUnit, Image img) {
		super(xUnit, yUnit, img);
	}
}
