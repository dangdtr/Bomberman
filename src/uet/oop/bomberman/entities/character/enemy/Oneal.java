package uet.oop.bomberman.entities.character.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.collisions.Collisions;
import uet.oop.bomberman.entities.character.bomber.Bomber;
import uet.oop.bomberman.entities.character.enemy.mode.AI2;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Objects;


public class Oneal extends Enemy {
	//    static GameMap gameMap;
	public static boolean isDie = false;

	public Oneal(int xUnit, int yUnit, Image img) {
		super(xUnit, yUnit, img);
		sprite = Sprite.oneal_left1;
//        ai = new AI2(gameMap, this);
		ai = new AI2(this);

	}

	@Override
	public void kill() {

	}

	public void killBomber() {
		if (Objects.requireNonNull(Game.getBomber()).isAlive()) {
			if (Collisions.checkCollision(this, Objects.requireNonNull(Game.getBomber()))) {
//                Bomber.alive = false;
				Game.getBomber().setAlive(false);

			}
		}
	}


	public void calculateMove() {
		direction = ai.calcDirection();

		if (!this.isDie()){
			if (direction == 0) {
				if (upable(x, y)) {
					y -= speed;
				}

			} else if (direction == 1) {
				if (rightable(x, y)) {
					x += speed;
				}
			} else if (direction == 2) {
				if (downable(x, y)) {
					y += speed;
				}
			} else if (direction == 3) {
				if (leftable(x, y)) {
					x -= speed;
				}
			}
		}
	}

	@Override
	public void update() {
		if (Objects.requireNonNull(Game.getBomber()).isAlive()) {
			killBomber();
		}
		if (this.isDie()) {
			afterKill();
		}
		chooseSprite();
		animate();
		calculateMove();
		setImg(sprite.getFxImage());
	}

	public void render(GraphicsContext gc) {

		gc.drawImage(img, x, y);
	}

	@Override
	protected void chooseSprite() {
		if (!this.isDie()) {
			switch (direction) {
				case 0:
				case 1:
					if (moving)
						sprite = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, _animate, _time);
					else
						sprite = Sprite.oneal_left1;
					break;
				case 2:
				case 3:
					if (moving)
						sprite = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, _animate, _time);
					else
						sprite = Sprite.oneal_left1;
					break;
			}
		} else {
			sprite = Sprite.oneal_dead;
		}
	}
}
