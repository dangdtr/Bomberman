package uet.oop.bomberman.entities.dynamics;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.maps.GameMap;

public abstract class DynamicEntity extends Entity {
    protected int _animate = 0;
    protected int _time = 36;

    protected final int MAX_ANIMATE = 7500;


    public DynamicEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    protected final int pixel = 4;
    protected boolean leftable(int x_pos, int y_pos) {
        return GameMap.getMap()[(y_pos + pixel) / Sprite.SCALED_SIZE][(x_pos - pixel) / Sprite.SCALED_SIZE] != '#' &&
                GameMap.getMap()[(y_pos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE][(x_pos - pixel) / Sprite.SCALED_SIZE] != '#';
    }


    protected boolean rightable(int x_pos, int y_pos) {
        return GameMap.getMap()[(y_pos + pixel) / Sprite.SCALED_SIZE][(x_pos + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE] != '#' &&
                GameMap.getMap()[(y_pos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE][(x_pos + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE] != '#';
    }


    protected boolean downable(int x_pos, int y_pos) {
        return GameMap.getMap()[(y_pos + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE][(x_pos + pixel) / Sprite.SCALED_SIZE] != '#' &&
                GameMap.getMap()[(y_pos + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE][(x_pos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE] != '#';
    }

    protected boolean upable(int x_pos, int y_pos) {
        return GameMap.getMap()[(y_pos - pixel) / Sprite.SCALED_SIZE][(x_pos + pixel) / Sprite.SCALED_SIZE] != '#' &&
                GameMap.getMap()[(y_pos - pixel) / Sprite.SCALED_SIZE][(x_pos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE] != '#';
    }

//    protected boolean leftable(int x_pos, int y_pos) {
//        return GameMap.getMap()[(y_pos + 1) / Sprite.SCALED_SIZE][(x_pos - 1) / Sprite.SCALED_SIZE] != '#' &&
//                GameMap.getMap()[(y_pos + Sprite.SCALED_SIZE - 1) / Sprite.SCALED_SIZE][(x_pos - 1) / Sprite.SCALED_SIZE] != '#';
//    }
//
//
//    protected boolean rightable(int x_pos, int y_pos) {
//        return GameMap.getMap()[(y_pos + 1) / Sprite.SCALED_SIZE][(x_pos + Sprite.SCALED_SIZE + 1) / Sprite.SCALED_SIZE] != '#' &&
//                GameMap.getMap()[(y_pos + Sprite.SCALED_SIZE - 1) / Sprite.SCALED_SIZE][(x_pos + Sprite.SCALED_SIZE + 1) / Sprite.SCALED_SIZE] != '#';
//    }
//
//
//    protected boolean downable(int x_pos, int y_pos) {
//        return GameMap.getMap()[(y_pos + Sprite.SCALED_SIZE + 1) / Sprite.SCALED_SIZE][(x_pos + 1) / Sprite.SCALED_SIZE] != '#' &&
//                GameMap.getMap()[(y_pos + Sprite.SCALED_SIZE + 1) / Sprite.SCALED_SIZE][(x_pos + Sprite.SCALED_SIZE - 1) / Sprite.SCALED_SIZE] != '#';
//    }
//
//    protected boolean upable(int x_pos, int y_pos) {
//        return GameMap.getMap()[(y_pos - 1) / Sprite.SCALED_SIZE][(x_pos + 1) / Sprite.SCALED_SIZE] != '#' &&
//                GameMap.getMap()[(y_pos - 1) / Sprite.SCALED_SIZE][(x_pos + Sprite.SCALED_SIZE - 1) / Sprite.SCALED_SIZE] != '#';
//    }



    protected void animate() {
        if(_animate < MAX_ANIMATE) _animate++; else _animate = 0;
    }



}
