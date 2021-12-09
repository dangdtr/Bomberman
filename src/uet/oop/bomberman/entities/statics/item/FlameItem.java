package uet.oop.bomberman.entities.statics.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.collisions.Collisions;
import uet.oop.bomberman.entities.dynamics.bomber.Bomber;
import uet.oop.bomberman.maps.GameMap;

public class FlameItem extends Item {
    public static int timeItem = 0;
    public static boolean isPickUp = false;
    public FlameItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

}
