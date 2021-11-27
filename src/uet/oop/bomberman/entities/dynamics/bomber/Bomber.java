package uet.oop.bomberman.entities.dynamics.bomber;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.modules.Keyboard;

public class Bomber extends Player {
    private final int VELOCITY = 1;

    private Sprite prevSprite = Sprite.player_right;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        sprite = Sprite.player_right;
    }


    public void render(GraphicsContext gc) {
        gc.drawImage(img, x+3, y);
    }

    @Override
    public void update() {
//        chooseSprite();

        calculateMove();
        chooseSprite();
        animate();
        setImg(sprite.getFxImage());
    }

    private void move() {

    }


    private void calculateMove() {
        if (Keyboard.UP) {
            if (upable(x, y)) {
                y = y - VELOCITY;
            }
//            Keyboard.UP = false;
        }
        if (Keyboard.LEFT) {
            if (leftable(x, y)) {
                x = x - VELOCITY;
            }
//            Keyboard.LEFT = false;
        }
        if (Keyboard.DOWN) {
            if (downable(x, y)) {
                y = y + VELOCITY;
            }
//            Keyboard.DOWN = false;

        }
        if (Keyboard.RIGHT) {
            if (rightable(x, y)) {
                x = x + VELOCITY;

            }

        }
//        if ((x / Sprite.SCALED_SIZE + 1) * Sprite.SCALED_SIZE - pixel < x &&
//                x < (x / Sprite.SCALED_SIZE + 1) * Sprite.SCALED_SIZE + pixel) {
//            x = (x / Sprite.SCALED_SIZE + 1) * Sprite.SCALED_SIZE;
//        } else if ((x / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE - pixel< x + Sprite.SCALED_SIZE &&
//                x + Sprite.SCALED_SIZE < (x / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE + pixel) {
//            x = (x / Sprite.SCALED_SIZE - 1) * Sprite.SCALED_SIZE;
//        }
//        if ((y / Sprite.SCALED_SIZE + 1) * Sprite.SCALED_SIZE - pixel < y &&
//                y < (y / Sprite.SCALED_SIZE + 1) * Sprite.SCALED_SIZE + pixel) {
//            y = (y / Sprite.SCALED_SIZE + 1) * Sprite.SCALED_SIZE;
//        } else if ((y / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE - pixel < y + Sprite.SCALED_SIZE &&
//                y + Sprite.SCALED_SIZE < (y / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE + pixel) {
//            y = (y / Sprite.SCALED_SIZE - 1) * Sprite.SCALED_SIZE;
//        }

    }


    private void chooseSprite() {
        if (Keyboard.UP) {
            sprite = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, _animate, _time);
            prevSprite = Sprite.player_up;
        }
        if (Keyboard.LEFT) {
            sprite = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, _animate, _time);
            prevSprite = Sprite.player_left;
        }
        if (Keyboard.DOWN) {
            sprite = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, _animate, _time);
            prevSprite = Sprite.player_down;
        }
        if (Keyboard.RIGHT) {
            sprite = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, _animate, _time);
            prevSprite = Sprite.player_right;
        }

        if (!Keyboard.UP && !Keyboard.LEFT && !Keyboard.DOWN && !Keyboard.RIGHT) {
            sprite = prevSprite;
        }

    }


}
