package uet.oop.bomberman.entities.character.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.collisions.Collisions;
import uet.oop.bomberman.entities.character.bomber.Bomber;
import uet.oop.bomberman.entities.character.enemy.mode.RandomMode;
import uet.oop.bomberman.graphics.Sprite;

import java.io.IOException;
import java.util.Objects;

public class Balloom extends Enemy {
	private int count = 0;
//	public boolean done = false;


	public Balloom(int xUnit, int yUnit, Image img) {
		super(xUnit, yUnit, img);
		sprite = Sprite.balloom_left1;
		ai = new RandomMode();
	}

	@Override
	public void kill() {

	}


	private void calculateMove() {
		if (!this.isDie()){
			count++;
			if (count < 150 && count >= 0) {
				if (rightable(x, y)) {// && rightableBrick(x, y)) {
					x += speed;
					direction = 1;
				}
			}
			if (count >= 150 && count < 300) {
				if (leftable(x, y)) {// && leftableBrick(x, y)) {
					x -= speed;
					direction = 3;
				}
			}
			if (count >= 300 && count < 450) {
				if (upable(x, y)) {// && upableBrick(x,y)) {
					y -= speed;
					direction = 0;
				}
			}
			if (count >= 450 && count < 600) {
				if (downable(x, y)) {// && downableBrick(x, y)) {
					y += speed;
					direction = 2;
				}
			}
			if (count >= 600) count = ai.calcDirection();
		}
	}

	protected void chooseSprite() {
		if (!this.isDie()) {
			switch (direction) {
				case 0:
				case 1:
					sprite = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, _animate, _time);
					break;
				case 2:
				case 3:
					sprite = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, _animate, _time);
					break;
			}
		} else {
			sprite = Sprite.balloom_dead;
		}
	}

	public void killBomber() {
		if (Objects.requireNonNull(Game.getBomber()).isAlive()) {
			if (Collisions.checkCollision(this, Objects.requireNonNull(Game.getBomber()))) {
//                Bomber.alive = false;
				Game.getBomber().setAlive(false);
			}
		}
	}

	@Override
	public void update() throws IOException {
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
}
