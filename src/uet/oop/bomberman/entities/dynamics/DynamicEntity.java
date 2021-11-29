package uet.oop.bomberman.entities.dynamics;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.statics.destroyable.Brick;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.maps.GameMap;

public abstract class DynamicEntity extends Entity {
    protected int _animate = 0;
    protected int _time = 36;

    protected final int MAX_ANIMATE = 7500;


    public DynamicEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    private int x1_temp, x2_temp, y1_temp, y2_temp;
    protected final int pixel = 1;

    protected boolean leftable(int x_pos, int y_pos) {
        x1_temp = (y_pos + pixel) / Sprite.SCALED_SIZE;
        y1_temp = (x_pos - pixel) / Sprite.SCALED_SIZE;

        x2_temp = (y_pos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE;
        y2_temp = (x_pos - pixel) / Sprite.SCALED_SIZE;

//      bảng tra cứu của thăng brick, đang nghiên cứu
//        if (BombermanGame.brickList.get(GameMap.generateKey(x1_temp, y1_temp)) != null ||
//                BombermanGame.brickList.get(GameMap.generateKey(x2_temp, y2_temp)) != null) {
//            return false;
//        }
        return GameMap.getMap()[x1_temp][y1_temp] != '#' &&
                GameMap.getMap()[x2_temp][y2_temp] != '#' &&
                GameMap.getMap()[x1_temp][y1_temp] != '*' &&
                GameMap.getMap()[x2_temp][y2_temp] != '*'
                ;
    }


    protected boolean rightable(int x_pos, int y_pos) {
        x1_temp = (y_pos + pixel) / Sprite.SCALED_SIZE;
        y1_temp = (x_pos + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE;

        x2_temp = (y_pos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE;
        y2_temp = (x_pos + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE;

//        if (BombermanGame.brickList.containsKey(GameMap.generateKey(x1_temp, y1_temp)) ||
//                BombermanGame.brickList.containsKey(GameMap.generateKey(x2_temp, y2_temp))) {
//            return false;
//        }
        return GameMap.getMap()[x1_temp][y1_temp] != '#' &&
                GameMap.getMap()[x2_temp][y2_temp] != '#' &&
                GameMap.getMap()[x1_temp][y1_temp] != '*' &&
                GameMap.getMap()[x2_temp][y2_temp] != '*'
                ;
    }


    protected boolean downable(int x_pos, int y_pos) {
        x1_temp = (y_pos + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE;
        y1_temp = (x_pos + pixel) / Sprite.SCALED_SIZE;

        x2_temp = (y_pos + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE;
        y2_temp = (x_pos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE;

//        if (BombermanGame.brickList.containsKey(GameMap.generateKey(x1_temp, y1_temp)) ||
//                BombermanGame.brickList.containsKey(GameMap.generateKey(x2_temp, y2_temp))) {
//            return false;
//        }

        return GameMap.getMap()[x1_temp][y1_temp] != '#' &&
                GameMap.getMap()[x2_temp][y2_temp] != '#' &&
                GameMap.getMap()[x1_temp][y1_temp] != '*' &&
                GameMap.getMap()[x2_temp][y2_temp] != '*';
    }

    protected boolean upable(int x_pos, int y_pos) {
        x1_temp = (y_pos - pixel) / Sprite.SCALED_SIZE;
        y1_temp = (x_pos + pixel) / Sprite.SCALED_SIZE;

        x2_temp = (y_pos - pixel) / Sprite.SCALED_SIZE;
        y2_temp = (x_pos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE;

//        if (BombermanGame.brickList.containsKey(GameMap.generateKey(x1_temp, y1_temp)) ||
//                BombermanGame.brickList.containsKey(GameMap.generateKey(x2_temp, y2_temp))) {
//            return false;
//        }

        return GameMap.getMap()[x1_temp][y1_temp] != '#' &&
                GameMap.getMap()[x2_temp][y2_temp] != '#'&&
                GameMap.getMap()[x1_temp][y1_temp] != '*' &&
                GameMap.getMap()[x2_temp][y2_temp] != '*';
    }



    protected void animate() {
        if (_animate < MAX_ANIMATE) _animate++;
        else _animate = 0;
    }


}
