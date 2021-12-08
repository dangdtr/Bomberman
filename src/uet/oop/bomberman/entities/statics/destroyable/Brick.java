package uet.oop.bomberman.entities.statics.destroyable;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends DestroyableTile {
    protected int _animate = 0;
    protected int _time = 36;
    public boolean exploded = false;
    public int timeBrick = 0;
    public boolean done = false;

    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }

    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        handleBrick();
        chooseSprite();
        animate();
        setImg(sprite.getFxImage());
    }

    private void handleBrick() {
        if (!done) {
            timeBrick++;
            if (timeBrick < 50) {
                exploded = true;
            } else {
                exploded = false;
                System.out.println("done brick");
                timeBrick = 0;
                done = true;
            }
        }

    }

    private void animate() {
        if (_animate < 120) _animate++;
        else _animate = 0;
    }

    private void chooseSprite() {
        if (exploded) {
            sprite = Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, _animate, 200);
        }else {
            sprite = Sprite.brick;
        }
    }
}