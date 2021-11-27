package uet.oop.bomberman.entities.dynamics.bomber;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.statics.Grass;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.maps.GameMap;
import uet.oop.bomberman.modules.Keyboard;

public class Bomber extends Player {
    private final int v = 2;
    private boolean moving = false;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        sprite = Sprite.player_right;
    }


    @Override
    public void update() {
        moving = true;
        if (Keyboard.UP) {
            if (canMove(x, y - v)) y = y - v;
        }
        if (Keyboard.LEFT) {
            if (canMove(x-v, y)) x = x - v;
        }
        if (Keyboard.DOWN) {
            if (canMove(x, y+v)) y = y + v;
        }
        if (Keyboard.RIGHT) {
            if (canMove(x+v, y)) x = x + v;
        }

        chooseSprite();
        animate();
        setImg(sprite.getFxImage());

    }

    private boolean checkCollide(Entity other) {
        int leftBomber = x;
        int rightBomber = x + Sprite.DEFAULT_SIZE;
        int topBomber = y;
        int bottomBomber = y + Sprite.DEFAULT_SIZE;

        int leftEntity = other.getX();
        int rightEntity = leftEntity + Sprite.DEFAULT_SIZE;
        int topEntity = other.getY();
        int bottomEntity = topEntity + Sprite.DEFAULT_SIZE;

        if (rightBomber > leftEntity && rightBomber < rightEntity) {
            if (bottomBomber > topEntity && bottomBomber < bottomEntity) return true;
            if (topBomber > topEntity && topBomber < bottomEntity) return true;
        }

        if (leftBomber > leftEntity && leftBomber < rightEntity) {
            if (bottomBomber > topEntity && bottomBomber < bottomEntity) return true;
            if (topBomber > topEntity && topBomber < bottomEntity) return true;
        }

        if (rightEntity > leftBomber && rightEntity < rightBomber) {
            if (bottomEntity > topBomber && bottomEntity < bottomBomber) return true;
            if (topEntity > topBomber && topEntity < bottomBomber) return true;
        }

        if (leftEntity > leftBomber && leftEntity < rightBomber) {
            if (bottomEntity > topBomber && bottomEntity < bottomBomber) return true;
            if (topEntity > topBomber && topEntity < bottomBomber) return true;
        }

        if (leftBomber == leftEntity && rightBomber == rightEntity
                && topBomber == topEntity && bottomBomber == bottomEntity) {
            return !(other instanceof Grass);
        }

        return false;
    }

    private boolean canMove(int x, int y) {
        Entity a = GameMap.getEntity(x, y);
        if (a != null) {
            return !this.checkCollide(a);
        }
        return false;
    }

    private void chooseSprite() {
        if (Keyboard.UP) {
            if (moving) {
                sprite = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, _animate, _time);
            } else {
                sprite = Sprite.player_up;
            }
        }
        if (Keyboard.LEFT) {
            if (moving) {
                sprite = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, _animate, _time);
            } else {
                sprite = Sprite.player_left;
            }
        }
        if (Keyboard.DOWN) {
            if (moving) {
                sprite = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, _animate, _time);
            } else {
                sprite = Sprite.player_down;
            }
        }
        if (Keyboard.RIGHT) {
            if (moving) {
                sprite = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, _animate, _time);
            } else {
                sprite = Sprite.player_right;
            }
        }

    }


}
