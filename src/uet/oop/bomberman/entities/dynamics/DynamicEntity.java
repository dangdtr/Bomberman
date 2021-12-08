package uet.oop.bomberman.entities.dynamics;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.statics.destroyable.Brick;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.maps.GameMap;

import java.io.IOException;

public abstract class DynamicEntity extends Entity {
    protected int _animate = 0;
    protected int _time = 36;
    protected boolean moving = true;
    public static boolean alive = true;
    protected int direction = -1;
    protected final int MAX_ANIMATE = 7500;


    public DynamicEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    private int x1_temp, x2_temp, y1_temp, y2_temp;
    protected final int pixel = 1;

//    protected boolean leftable(int x_pos, int y_pos) {
//        x1_temp = (y_pos + pixel) / Sprite.SCALED_SIZE;
//        y1_temp = (x_pos - pixel) / Sprite.SCALED_SIZE;
//
//        x2_temp = (y_pos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE;
//        y2_temp = (x_pos - pixel) / Sprite.SCALED_SIZE;
//
//        return GameMap.getMap()[x1_temp][y1_temp] != '#' &&
//                GameMap.getMap()[x2_temp][y2_temp] != '#' &&
//                GameMap.getMap()[x1_temp][y1_temp] != '*' &&
//                GameMap.getMap()[x2_temp][y2_temp] != '*'
//                ;
//    }
//
//
//    protected boolean rightable(int x_pos, int y_pos) {
//        x1_temp = (y_pos + pixel) / Sprite.SCALED_SIZE;
//        y1_temp = (x_pos + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE;
//
//        x2_temp = (y_pos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE;
//        y2_temp = (x_pos + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE;
//
//
//        return GameMap.getMap()[x1_temp][y1_temp] != '#' &&
//                GameMap.getMap()[x2_temp][y2_temp] != '#' &&
//                GameMap.getMap()[x1_temp][y1_temp] != '*' &&
//                GameMap.getMap()[x2_temp][y2_temp] != '*'
//                ;
//    }
//
//
//    protected boolean downable(int x_pos, int y_pos) {
//        x1_temp = (y_pos + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE;
//        y1_temp = (x_pos + pixel) / Sprite.SCALED_SIZE;
//
//        x2_temp = (y_pos + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE;
//        y2_temp = (x_pos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE;
//
//
//        return GameMap.getMap()[x1_temp][y1_temp] != '#' &&
//                GameMap.getMap()[x2_temp][y2_temp] != '#' &&
//                GameMap.getMap()[x1_temp][y1_temp] != '*' &&
//                GameMap.getMap()[x2_temp][y2_temp] != '*';
//    }
//
//    protected boolean upable(int x_pos, int y_pos) {
//        x1_temp = (y_pos - pixel) / Sprite.SCALED_SIZE;
//        y1_temp = (x_pos + pixel) / Sprite.SCALED_SIZE;
//
//        x2_temp = (y_pos - pixel) / Sprite.SCALED_SIZE;
//        y2_temp = (x_pos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE;
//
//
//        return GameMap.getMap()[x1_temp][y1_temp] != '#' &&
//                GameMap.getMap()[x2_temp][y2_temp] != '#'&&
//                GameMap.getMap()[x1_temp][y1_temp] != '*' &&
//                GameMap.getMap()[x2_temp][y2_temp] != '*';
//    }

    protected boolean leftable(int x_pos, int y_pos) {
        x1_temp = (y_pos + pixel) / Sprite.SCALED_SIZE;
        y1_temp = (x_pos - pixel) / Sprite.SCALED_SIZE;

        x2_temp = (y_pos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE;
        y2_temp = (x_pos - pixel) / Sprite.SCALED_SIZE;

        return GameMap.getMap()[x1_temp][y1_temp] != '#' &&
                GameMap.getMap()[x2_temp][y2_temp] != '#';
    }


    protected boolean rightable(int x_pos, int y_pos) {
        x1_temp = (y_pos + pixel) / Sprite.SCALED_SIZE;
        y1_temp = (x_pos + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE;

        x2_temp = (y_pos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE;
        y2_temp = (x_pos + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE;

        return GameMap.getMap()[x1_temp][y1_temp] != '#' &&
                GameMap.getMap()[x2_temp][y2_temp] != '#';
    }


    protected boolean downable(int x_pos, int y_pos) {
        x1_temp = (y_pos + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE;
        y1_temp = (x_pos + pixel) / Sprite.SCALED_SIZE;

        x2_temp = (y_pos + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE;
        y2_temp = (x_pos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE;

        return GameMap.getMap()[x1_temp][y1_temp] != '#' &&
                GameMap.getMap()[x2_temp][y2_temp] != '#' ;
    }

    protected boolean upable(int x_pos, int y_pos) {
        x1_temp = (y_pos - pixel) / Sprite.SCALED_SIZE;
        y1_temp = (x_pos + pixel) / Sprite.SCALED_SIZE;

        x2_temp = (y_pos - pixel) / Sprite.SCALED_SIZE;
        y2_temp = (x_pos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE;

        return GameMap.getMap()[x1_temp][y1_temp] != '#' &&
                GameMap.getMap()[x2_temp][y2_temp] != '#';
    }


    protected boolean leftableBrick(int x_pos, int y_pos) {
        x1_temp = (y_pos + pixel) / Sprite.SCALED_SIZE;
        y1_temp = (x_pos - pixel) / Sprite.SCALED_SIZE;

        x2_temp = (y_pos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE;
        y2_temp = (x_pos - pixel) / Sprite.SCALED_SIZE;

        if (GameMap.brickList.get(GameMap.generateKey(y1_temp, x1_temp)) != null ||
                GameMap.brickList.get(GameMap.generateKey(y2_temp, x2_temp)) != null) {
            return false;
        }
        return true;
    }


    protected boolean rightableBrick(int x_pos, int y_pos) {
        x1_temp = (y_pos + pixel) / Sprite.SCALED_SIZE;
        y1_temp = (x_pos + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE;

        x2_temp = (y_pos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE;
        y2_temp = (x_pos + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE;

        if (GameMap.brickList.containsKey(GameMap.generateKey(y1_temp, x1_temp)) ||
                GameMap.brickList.containsKey(GameMap.generateKey(y2_temp, x2_temp))) {
            return false;
        }
        return true;
    }


    protected boolean downableBrick(int x_pos, int y_pos) {
        x1_temp = (y_pos + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE;
        y1_temp = (x_pos + pixel) / Sprite.SCALED_SIZE;

        x2_temp = (y_pos + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE;
        y2_temp = (x_pos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE;

        if (GameMap.brickList.containsKey(GameMap.generateKey(y1_temp, x1_temp)) ||
                GameMap.brickList.containsKey(GameMap.generateKey(y2_temp, x2_temp))) {
            return false;
        }

        return true;
    }

    protected boolean upableBrick(int x_pos, int y_pos) {
        x1_temp = (y_pos - pixel) / Sprite.SCALED_SIZE;
        y1_temp = (x_pos + pixel) / Sprite.SCALED_SIZE;

        x2_temp = (y_pos - pixel) / Sprite.SCALED_SIZE;
        y2_temp = (x_pos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE;

        if (GameMap.brickList.containsKey(GameMap.generateKey(y1_temp, x1_temp)) ||
                GameMap.brickList.containsKey(GameMap.generateKey(y2_temp, x2_temp))) {
            return false;
        }
        return true;
    }

    public abstract void kill();

    protected abstract void afterKill() throws IOException;

    protected void animate() {
        if (_animate < MAX_ANIMATE) _animate++;
        else _animate = 0;
    }

}
