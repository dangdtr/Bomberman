package uet.oop.bomberman.entities.tile.destroyable;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends DestroyableTile {


	public void setExploded(boolean exploded) {
		this._exploding = exploded;
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
		if (!_destroyed) {
			_timeToDisapear++;
			if (_timeToDisapear < 50) {
				_exploding = true;
			} else {
				_exploding = false;
				System.out.println("done brick");
				_timeToDisapear = 0;
				_destroyed = true;
			}
		}

	}

	private void animate() {
		if (_animate < 120) _animate++;
		else _animate = 0;
	}

	private void chooseSprite() {
		if (_exploding) {
			sprite = Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, _animate, 200);
		} else {
			sprite = Sprite.brick;
		}
	}
}