package uet.oop.bomberman.entities.character.bomber;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.maps.GameMap;
import uet.oop.bomberman.modules.Keyboard;

import java.io.IOException;

public class Bomber extends Player {
	private static int VELOCITY = 2;
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
	}

	@Override
	public void kill() {
	}

	@Override
	protected void afterKill() throws IOException {
		if (_time > 0) _time--;
		if (_time == 0) {
			resetBomber();
		}
	}

	public void render(GraphicsContext gc) {
		gc.drawImage(img, x, y);
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
		animate();
		calculateMove();
		setImg(sprite.getFxImage());
	}

	public static void setVELOCITY(int v) {
		VELOCITY = v;
	}

	private void calculateMove() {
		if (alive) {
			int dx = 0;
			int dy = 0;
			if (Keyboard.UP) {
				if (upable(x, y)) {// && upableBrick(x, y) ) {
					dy--;
				}
			}
			if (Keyboard.LEFT) {
				if (leftable(x, y)) {// && leftableBrick(x, y) ) {
					dx--;
				}
			}
			if (Keyboard.DOWN) {
				if (downable(x, y)) {// && downableBrick(x, y)) {
					dy++;
				}
			}
			if (Keyboard.RIGHT) {
				if (rightable(x, y)) {// && rightableBrick(x, y)) {
					dx++;
				}

			}
			x += dx * VELOCITY;
			y += dy * VELOCITY;

//            if (dx < 0 && canMoveLeft(dx, dy, this)) {
//                x += dx * VELOCITY;
//            }
//            if (dx > 0 && canMoveRight(dx,dy,this)) {
//                x += dx * VELOCITY;
//            }
//            if (dy < 0 && canMoveUp(0, dy, this) ) {
//                y += dy * VELOCITY;
//            }
//            if (dy > 0 && canMoveDown(0, dy, this)) {
//                y += dy * VELOCITY;
//            }

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

}