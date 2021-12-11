package uet.oop.bomberman.entities.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.collisions.Collisions;
import uet.oop.bomberman.entities.character.bomber.Bomber;
import uet.oop.bomberman.entities.character.enemy.Enemy;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Bomb extends AbstractBomb {
	private long _timeToExplode;// = System.currentTimeMillis();
	public boolean _exploding = false;
	public boolean _destroyed = false;
	public boolean canPass = true;
	private final List<Flame> flameList = new ArrayList<>();

	public List<Flame> getFlameList() {
		return flameList;
	}

	public Bomb(int xUnit, int yUnit, Image img) {
		super(xUnit, yUnit, img);
		_timeToExplode = 0;
		initAndHandleFlame();
	}

	private void chooseSprite() {
		sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, _animate, 200);
	}

	public boolean collideBom() {
		Enemy enemy = Game.getEnemy();
		if (enemy == null) return false;
		return Collisions.checkCollision(this, enemy);
	}

	public void killBomber() {
		if (!Game.bombList.isEmpty()) {
			for (Bomb bomb : Game.bombList) {
				if (bomb != null) {
					if (!bomb._destroyed && bomb._exploding) {
						for (int i = 0; i < bomb.getFlameList().size(); i++) {
							if (Collisions.checkCollision(bomb.getFlameList().get(i), Objects.requireNonNull(Game.getBomber()))) {
								Bomber.alive = false;
								break;
							}
						}
					}
					if (bomb._destroyed && Game.NUMBER_OF_BOMBS < 1) {
						Game.NUMBER_OF_BOMBS++;
					}
				}
			}
		}
	}

	public void killEnemy() {
		Enemy enemy = Game.getEnemy();
		if (enemy == null) return;
		if (!Game.bombList.isEmpty()) {
			for (Bomb bomb : Game.bombList) {
				if (bomb != null) {
					if (!bomb._destroyed && bomb._exploding) {
						for (int i = 0; i < bomb.getFlameList().size(); i++) {
							if (Collisions.checkCollision(bomb.getFlameList().get(i), enemy)) {
								enemy.enemyDie();
								break;
							}
						}
					}
					if (bomb._destroyed && Game.NUMBER_OF_BOMBS < 1) {
						Game.NUMBER_OF_BOMBS++;
					}
				}
			}
		}
	}

	@Override
	public void update() {
//		killBomber();
//		killEnemy();
		handleBomb();
		chooseSprite();
		animate();
		setImg(sprite.getFxImage());
	}

	private void handleBomb() {
		if (!_destroyed) {
			_timeToExplode++;

			if (_timeToExplode < Game.TIME_TO_EXPLOSION_BOMB) {
				_exploding = false;
			} else {
				if (_timeToExplode < Game.TIME_TO_EXPLOSION_BOMB + Game.TIME_TO_DISAPPEAR) {
					_exploding = true;

				} else if (_timeToExplode > Game.TIME_TO_EXPLOSION_BOMB + Game.TIME_TO_DISAPPEAR) {
					_timeToExplode = 0;
					_destroyed = true;

				}
			}
		}
	}

	// x y đã nhân với SCALED_SIZE rồi
	private void initAndHandleFlame() {
		flameList.clear();
		Flame newFlame = null;
		for (int i = 0; i < Game.LENGTH_OF_FLAME; i++) {
			if (!leftable(x - i * Sprite.SCALED_SIZE, y)) {
				break;
			}
			newFlame =
					new Flame(
							(x - (1 + i) * Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE,
							y / Sprite.SCALED_SIZE,
							Sprite.explosion_horizontal.getFxImage());

			if (i != Game.LENGTH_OF_FLAME - 1) {
				newFlame.setStatus("HORIZONTAL");
			} else {
				newFlame.setStatus("LEFT_LAST");
			}
			flameList.add(newFlame);
		}

		for (int i = 0; i < Game.LENGTH_OF_FLAME; i++) {
			if (!rightable(x + i * Sprite.SCALED_SIZE, y)) {
				break;
			}
			newFlame =
					new Flame(
							(x + (1 + i) * Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE,
							y / Sprite.SCALED_SIZE,
							Sprite.explosion_horizontal.getFxImage());
			if (i != Game.LENGTH_OF_FLAME - 1) {
				newFlame.setStatus("HORIZONTAL");
			} else {
				newFlame.setStatus("RIGHT_LAST");
			}
			flameList.add(newFlame);
		}

		for (int i = 0; i < Game.LENGTH_OF_FLAME; i++) {
			if (!upable(x, y - i * Sprite.SCALED_SIZE)) {
				break;
			}
			newFlame =
					new Flame(
							x / Sprite.SCALED_SIZE,
							(y - (1 + i) * Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE,
							Sprite.explosion_vertical.getFxImage());
			if (i != Game.LENGTH_OF_FLAME - 1) {
				newFlame.setStatus("VERTICAL");
			} else {
				newFlame.setStatus("TOP_LAST");
			}
			flameList.add(newFlame);
		}

		for (int i = 0; i < Game.LENGTH_OF_FLAME; i++) {
			if (!downable(x, y + i * Sprite.SCALED_SIZE)) {
				break;
			}
			newFlame =
					new Flame(
							x / Sprite.SCALED_SIZE,
							(y + (1 + i) * Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE,
							Sprite.explosion_vertical.getFxImage());
			if (i != Game.LENGTH_OF_FLAME - 1) {
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
	protected void afterKill() {
	}
	//-----------------//
}
