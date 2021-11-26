package uet.oop.bomberman.entities.dynamics.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.dynamics.DynamicEntity;

public abstract class Enemy extends DynamicEntity {
    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
}
