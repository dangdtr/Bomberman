package uet.oop.bomberman.entities.dynamics.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.collisions.Collisions;
import uet.oop.bomberman.entities.dynamics.DynamicEntity;
import uet.oop.bomberman.entities.dynamics.bomber.Bomber;
import uet.oop.bomberman.entities.dynamics.enemy.Enemy;
import uet.oop.bomberman.graphics.Sprite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Bomb extends DynamicEntity {
    private long bombTime;// = System.currentTimeMillis();
    public boolean explosion = false;
    public boolean done = false;
    public boolean canPass = true;
    public final List<Flame> flameList = new ArrayList<>();

    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        bombTime = 0;
        handleFlame();
    }

    private void chooseSprite() {
        sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, _animate, 200);
    }

    @Override
    public void update() {
        handleBomb();
        chooseSprite();
        animate();
        setImg(sprite.getFxImage());
    }

    private void handleBomb() {
        if (!done) {
            bombTime++;

            if (bombTime < 300) {
                explosion = false;
            } else {
                if (bombTime < 375) {
                    explosion = true;

                } else if (bombTime > 375) {
                    System.out.println("done bomb");
                    bombTime = 0;
                    done = true;

                }
            }
        }
    }

    // x y đã nhân với SCALED_SIZE rồi
    private void handleFlame() {
        flameList.clear();
        Flame newFlame = null;
        for (int i = 0; i < Flame.lenOfFlame; i++) {
            if (!leftable(x - i * Sprite.SCALED_SIZE, y)) {
                break;
            }
            newFlame =
                    new Flame(
                            (x - (1 + i) * Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE,
                            y / Sprite.SCALED_SIZE,
                            Sprite.explosion_horizontal.getFxImage());

            if (i != Flame.lenOfFlame - 1) {
                newFlame.setStatus("HORIZONTAL");
            } else {
                newFlame.setStatus("LEFT_LAST");
            }

            flameList.add(newFlame);

        }

        for (int i = 0; i < Flame.lenOfFlame; i++) {
            if (!rightable(x + i * Sprite.SCALED_SIZE, y)) {
                break;
            }
            newFlame =
                    new Flame(
                            (x + (1 + i) * Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE,
                            y / Sprite.SCALED_SIZE,
                            Sprite.explosion_horizontal.getFxImage());
            if (i != Flame.lenOfFlame - 1) {
                newFlame.setStatus("HORIZONTAL");
            } else {
                newFlame.setStatus("RIGHT_LAST");
            }

            flameList.add(newFlame);

        }

        for (int i = 0; i < Flame.lenOfFlame; i++) {
            if (!upable(x, y - i * Sprite.SCALED_SIZE)) {
                break;
            }
            newFlame =
                    new Flame(
                            x / Sprite.SCALED_SIZE,
                            (y - (1 + i) * Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE,
                            Sprite.explosion_vertical.getFxImage());
            if (i != Flame.lenOfFlame - 1) {
                newFlame.setStatus("VERTICAL");
            } else {
                newFlame.setStatus("TOP_LAST");
            }
            flameList.add(newFlame);


        }

        for (int i = 0; i < Flame.lenOfFlame; i++) {
            if (!downable(x, y + i * Sprite.SCALED_SIZE)) {
                break;
            }
            newFlame =
                    new Flame(
                            x / Sprite.SCALED_SIZE,
                            (y + (1 + i) * Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE,
                            Sprite.explosion_vertical.getFxImage());
            if (i != Flame.lenOfFlame - 1) {
                newFlame.setStatus("VERTICAL");
            } else {
                newFlame.setStatus("DOWN_LAST");
            }
            flameList.add(newFlame);

        }
        newFlame =
                new Flame(
                        x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE, Sprite.explosion_vertical.getFxImage());
        newFlame.setStatus("CENTER");
        flameList.add(newFlame);
    }

    //-----------------//co the khong dung
    @Override
    public void kill() {

    }

    @Override
    protected void afterKill() throws IOException {

    }
    //-----------------//
}
