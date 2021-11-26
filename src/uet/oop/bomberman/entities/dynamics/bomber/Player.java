package uet.oop.bomberman.entities.dynamics.bomber;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.dynamics.DynamicEntity;

public abstract class Player extends DynamicEntity {
    public Player(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
}
