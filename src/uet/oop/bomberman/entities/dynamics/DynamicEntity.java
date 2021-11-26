package uet.oop.bomberman.entities.dynamics;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class DynamicEntity extends Entity {
    protected int _animate = 0;
    protected int _time = 51;

    public DynamicEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }


    protected final int MAX_ANIMATE = 7500;
    protected void animate() {
        if(_animate < MAX_ANIMATE) _animate++; else _animate = 0;
    }



}
