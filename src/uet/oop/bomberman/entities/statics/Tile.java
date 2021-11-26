package uet.oop.bomberman.entities.statics;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

/**
 * invariable entity
 */
public abstract class Tile extends Entity {

    public Tile(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {

    }
}
