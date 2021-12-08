package uet.oop.bomberman.entities.dynamics.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.dynamics.DynamicEntity;
import uet.oop.bomberman.entities.dynamics.enemy.mode.AI;

public abstract class Enemy extends DynamicEntity {
    protected AI ai;
    protected int speed = 2;
    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public abstract void killBomber();
    protected abstract void chooseSprite();
}
