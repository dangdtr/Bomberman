package uet.oop.bomberman.entities.statics.item;

import javafx.scene.image.Image;

public class SpeedItem extends Item {
    public static int timeItem = 0;
    public static boolean isPickUp = false;
    public SpeedItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
}
