package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    private int direction = -1;
    private final int v = 2;
    private boolean moving = false;
    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        sprite = Sprite.player_right;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public void update() {
        switch (getDirection()) {
            case -1:
                break;
            case 0:
                y = y - v;
                break;
            case 1:
                x = x + v;
                break;
            case 2:
                y = y + v;
                break;
            case 3:
                x = x - v;
                break;

        }
    }

    public void render() {
        chooseSprite();
    }
    //    @Override
    public void inputKeyHandle(javafx.scene.input.KeyEvent event) {
        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            if (event.getCode() == KeyCode.UP) {
                setDirection(0);
                moving = true;
            } else if (event.getCode() == KeyCode.RIGHT) {
                setDirection(1);
                moving = true;
            } else if (event.getCode() == KeyCode.DOWN) {
                setDirection(2);
                moving = true;
            } else if (event.getCode() == KeyCode.LEFT) {
                setDirection(3);
                moving = true;
            }
        } else {
            setDirection(-1);
            moving = false;
        }
    }

    private void chooseSprite() {
        switch (direction) {
            case 0:
                sprite = Sprite.player_up;
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, _animate, 20);
                }
                break;
            case 1:
                sprite = Sprite.player_right;
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, _animate, 20);
                }
                break;
            case 2:
                sprite = Sprite.player_down;
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, _animate, 20);
                }
                break;
            case 3:
                sprite = Sprite.player_left;
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, _animate, 20);
                }
                break;
            default:
                sprite = Sprite.player_right;
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, _animate, 20);
                }
                break;
        }
    }

}
