package uet.oop.bomberman.entities.dynamics.bomber;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.modules.Keyboard;

public class Bomber extends Player {
    private final int v = 1;
    private boolean moving = false;
    private Sprite prevSprite = Sprite.player_right;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        sprite = Sprite.player_right;
    }


    @Override
    public void update() {
        moving = true;
        if (Keyboard.UP) y = y - v;
        if (Keyboard.LEFT) x = x - v;
        if (Keyboard.DOWN) y = y + v;
        if (Keyboard.RIGHT) x = x + v;

        chooseSprite();
        animate();
        moving = false;
        setImg(sprite.getFxImage());

    }

    private void canMove() {
    }

    private void chooseSprite() {
        if (Keyboard.UP) {
//            if (moving) {
                sprite = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, _animate, _time);
//            } else {
                prevSprite = Sprite.player_up;
//            }
        }
        if (Keyboard.LEFT) {
//            if (moving) {
                sprite = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, _animate, _time);
//            } else {
            prevSprite = Sprite.player_left;
//            }
        }
        if (Keyboard.DOWN) {
//            if (moving) {
                sprite = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, _animate, _time);
//            } else {
            prevSprite = Sprite.player_down;
//            }
        }
        if (Keyboard.RIGHT) {
//            if (moving) {
                sprite = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, _animate, _time);
//            } else {
            prevSprite = Sprite.player_right;
//            }
        }

        if(!Keyboard.UP && !Keyboard.LEFT && !Keyboard.DOWN && !Keyboard.RIGHT) {
            sprite = prevSprite;
        }

    }


}
