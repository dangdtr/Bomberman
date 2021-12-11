package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.collisions.Collisions;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.graphics.Sprite;

public abstract class AnimatedEntitiy extends Entity {
	protected int _animate = 0;
	protected int _time = 36;
	protected boolean moving = true;
	protected final int MAX_ANIMATE = 7500;
	protected final int SIZE = Sprite.SCALED_SIZE - 6;//3/4 * Sprite.SCALED_SIZE;


	public AnimatedEntitiy(int xUnit, int yUnit, Image img) {
		super(xUnit, yUnit, img);
	}

	protected int x1_temp, x2_temp, y1_temp, y2_temp;
	protected final int pixel = 1;

	public boolean collideBomb(Bomb bomb, AnimatedEntitiy animatedEntitiy) {
		if (bomb == null || animatedEntitiy == null) return false;
		int dx = Math.abs(bomb.getX() - animatedEntitiy.getX());
		int dy = Math.abs(bomb.getY() - animatedEntitiy.getY());
		// bomber đang ở vị trí đặt bom.
		if (!(dx >= 0 && dx <= Sprite.SCALED_SIZE && dy >= 0 && dy <= Sprite.SCALED_SIZE)) {
			bomb.canPass = false;
		}

		if (!bomb.canPass) {
			return Collisions.checkCollision(bomb, animatedEntitiy);
		}

		return false;
	}


	public abstract void kill();

	protected abstract void afterKill();

	protected void animate() {
		if (_animate < MAX_ANIMATE) _animate++;
		else _animate = 0;
	}

}
