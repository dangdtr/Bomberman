package uet.oop.bomberman.entities.dynamics.bomber;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.collisions.Collisions;
import uet.oop.bomberman.entities.dynamics.bomb.Bomb;
import uet.oop.bomberman.entities.dynamics.enemy.Balloom;
import uet.oop.bomberman.entities.dynamics.enemy.Enemy;
import uet.oop.bomberman.entities.dynamics.enemy.Oneal;
import uet.oop.bomberman.entities.statics.item.FlameItem;
import uet.oop.bomberman.entities.statics.item.Item;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.maps.GameMap;
import uet.oop.bomberman.modules.Keyboard;

import java.io.IOException;

public class Bomber extends Player {
    private final int VELOCITY = 2;
    private Sprite prevSprite = Sprite.player_right;
    public static boolean collide = false;
    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        sprite = Sprite.player_right;
    }

    public void resetBomber() throws IOException {
        img = Sprite.player_right.getFxImage();
        moving = false;
        alive = true;
        GameMap.initMap();
        GameMap.createMap(1);
    }

    @Override
    public void kill() {}

    @Override
    protected void afterKill() throws IOException {
        if (_time > 0) _time--;
        if (_time == 0) {
            resetBomber();
        }
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x , y);
    }

    @Override
    public void update() throws IOException {
        if (!alive) {
            sprite = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, _animate, _time);
            afterKill();
        }
        if (alive) {
            chooseSprite();
        }
        eatItem(GameMap.getItem(x, y));
        animate();
        calculateMove();
        setImg(sprite.getFxImage());
    }

    private void calculateMove() {
        Bomb bomb = BombermanGame.getBomb();
        if (alive) {
            int dx = 0;
            int dy = 0;
            if (Keyboard.UP) {
                if (upable(x, y) && upableBrick(x, y)) {
                    dy--;
                }
            }
            if (Keyboard.LEFT) {
                if (leftable(x, y) && leftableBrick(x, y)) {
                    dx--;
                }
            }
            if (Keyboard.DOWN) {
                if (downable(x, y) && downableBrick(x, y)) {
                    dy++;
                }

            }
            if (Keyboard.RIGHT) {
                if (rightable(x, y) && rightableBrick(x, y)) {
                    dx++;
                }

            }
            x += dx;
            y += dy;

        }
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

    private void eatItem(Item item) {
        if (item instanceof FlameItem) {
            if (Collisions.checkCollision(item, this)) {
                GameMap.brickList.get(GameMap.generateKey(x,y)).pop();
            }
        }
    }
}