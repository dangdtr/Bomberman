package uet.oop.bomberman.entities.dynamics.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.collisions.Collisions;
import uet.oop.bomberman.entities.dynamics.bomber.Bomber;
import uet.oop.bomberman.entities.dynamics.enemy.mode.RandomMode;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.entities.dynamics.enemy.Oneal.gameMap;

public class Balloom extends Enemy {
    private int count = 0;
    public Balloom(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        sprite = Sprite.balloom_left1;
        ai = new RandomMode();
    }

    @Override
    public void kill() {

    }

    @Override
    protected void afterKill() {

    }

    private void calculateMove() {
        count++;
        if (count < 150 && count >= 0) {
            if (rightable(x, y)) {
                x += speed;
                direction = 1;
            }
        }
        if (count >= 150 && count < 300) {
            if (leftable(x, y)) {
                x -= speed;
                direction = 3;
            }
        }
        if (count >= 300 && count < 450) {
            if (upable(x, y)) {
                y -= speed;
                direction = 0;
            }
        }
        if (count >= 450 && count < 600) {
            if (downable(x, y)) {
                y += speed;
                direction = 2;
            }
        }
        if (count >= 600) count = ai.calcDirection();
    }

    protected void chooseSprite() {
        switch (direction) {
            case 0:
            case 1:
                sprite = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, _animate, 60);
                break;
            case 2:
            case 3:
                sprite = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, _animate, 60);
                break;
        }
    }
    public void killBomber() {
        if (Bomber.alive) {
            if (Collisions.checkCollision(this, gameMap.getBomber())) {
                Bomber.alive = false;
            }
        }
    }
    @Override
    public void update() {
        killBomber();
        animate();
        calculateMove();
        setImg(sprite.getFxImage());
    }

    public void render(GraphicsContext gc) {
        chooseSprite();
        gc.drawImage(img, x , y);
    }
}
