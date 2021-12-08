package uet.oop.bomberman.entities.dynamics.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.dynamics.DynamicEntity;

public class Bomb extends DynamicEntity {
    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void kill() {

    }

    @Override
    protected void afterKill() {

    }

    @Override
    public void update() {

    }

}
