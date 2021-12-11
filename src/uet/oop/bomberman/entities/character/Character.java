package uet.oop.bomberman.entities.character;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.AnimatedEntitiy;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Portal;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Character extends AnimatedEntitiy {
	protected boolean _exploding = false;
	protected int _timeToDisapear = 0;
	protected boolean _destroyed = false;

	public boolean isDestroyed() {
		return _destroyed;
	}

	public Character(int xUnit, int yUnit, Image img) {
		super(xUnit, yUnit, img);
	}


	@Override
	protected void afterKill() {
		if (!_destroyed) {
			_timeToDisapear++;
			if (_timeToDisapear < Game.TIME_TO_DISAPPEAR) {
				_exploding = true;
			} else {
				_exploding = false;
				_timeToDisapear = 0;
				_destroyed = true;
			}
		}
	}


	/**
	 * Lấy ra entity mà character có thể không đi qua được tại vị trí x, y đã tính toán.
	 *
	 * @param x vị trí x đã tính toán.
	 * @param y vị trí y đã tính toán.
	 * @return entity tại vị trí đó.
	 */
	protected Entity getImpassableEntityAt(int x, int y) {
		Entity entity = null;

		for (Integer value : Game.getLayeredEntitySet()) {
			if (Game.LayeredEntity.get(value).peek().getX() == x && Game.LayeredEntity.get(value).peek().getY() == y) {
				return Game.LayeredEntity.get(value).peek();
			}
		}

		for (Entity e : Game.stillObjects) {
			if (e.getX() == x && e.getY() == y) {
				return e;
			}
		}


		return entity;
	}

	/**
	 * Kiểm tra xem tại vị trí hiện tại đối tượng có thể đi sang trái được không?
	 * Thực hiện tính toán và trả về giá trị kiêm tra.
	 *
	 * @param x_pos vị trí hiện tại của đối tượng cần kiểm tra.
	 * @param y_pos vị trí hiện tại của đối tượng cần kiểm tra.
	 * @return .
	 */
	protected boolean leftable(int x_pos, int y_pos) {
		x1_temp = (y_pos + pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
		y1_temp = (x_pos - pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;

		x2_temp = (y_pos + SIZE - pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
		y2_temp = (x_pos - pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;

//		if (getImpassableEntityAt(y1_temp, x1_temp) instanceof Portal && getImpassableEntityAt(y2_temp, x2_temp) instanceof Portal) {
//			if (((Portal) getImpassableEntityAt(y1_temp, x1_temp)).isCanPass() && ((Portal) getImpassableEntityAt(y2_temp, x2_temp)).isCanPass()) {
//				return true;
//			}
//		}
		return getImpassableEntityAt(y1_temp, x1_temp) instanceof Grass && getImpassableEntityAt(y2_temp, x2_temp) instanceof Grass;
	}

	/**
	 * Kiểm tra xem tại vị trí hiện tại đối tượng có thể đi sang trái được không?
	 * Thực hiện tính toán và trả về giá trị kiêm tra.
	 *
	 * @param x_pos vị trí hiện tại của đối tượng cần kiểm tra.
	 * @param y_pos vị trí hiện tại của đối tượng cần kiểm tra.
	 * @return .
	 */
	protected boolean rightable(int x_pos, int y_pos) {
		x1_temp = (y_pos + pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
		y1_temp = (x_pos + SIZE + pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;

		x2_temp = (y_pos + SIZE - pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
		y2_temp = (x_pos + SIZE + pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
//		if (getImpassableEntityAt(y1_temp, x1_temp) instanceof Portal && getImpassableEntityAt(y2_temp, x2_temp) instanceof Portal) {
//			if (((Portal) getImpassableEntityAt(y1_temp, x1_temp)).isCanPass() && ((Portal) getImpassableEntityAt(y2_temp, x2_temp)).isCanPass()) {
//				return true;
//			}
//		}
		return getImpassableEntityAt(y1_temp, x1_temp) instanceof Grass && getImpassableEntityAt(y2_temp, x2_temp) instanceof Grass;
	}

	/**
	 * Kiểm tra xem tại vị trí hiện tại đối tượng có thể đi xuống dưới được không?
	 * Thực hiện tính toán và trả về giá trị kiêm tra.
	 *
	 * @param x_pos vị trí hiện tại của đối tượng cần kiểm tra.
	 * @param y_pos vị trí hiện tại của đối tượng cần kiểm tra.
	 * @return .
	 */
	protected boolean downable(int x_pos, int y_pos) {
		x1_temp = (y_pos + SIZE + pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
		y1_temp = (x_pos + pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;

		x2_temp = (y_pos + SIZE + pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
		y2_temp = (x_pos + SIZE - pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
//		if (getImpassableEntityAt(y1_temp, x1_temp) instanceof Portal && getImpassableEntityAt(y2_temp, x2_temp) instanceof Portal) {
//			if (((Portal) getImpassableEntityAt(y1_temp, x1_temp)).isCanPass() && ((Portal) getImpassableEntityAt(y2_temp, x2_temp)).isCanPass()) {
//				return true;
//			}
//		}
		return getImpassableEntityAt(y1_temp, x1_temp) instanceof Grass && getImpassableEntityAt(y2_temp, x2_temp) instanceof Grass;
	}

	/**
	 * Kiểm tra xem tại vị trí hiện tại đối tượng có thể đi lên trên được không?
	 * Thực hiện tính toán và trả về giá trị kiêm tra.
	 *
	 * @param x_pos vị trí hiện tại của đối tượng cần kiểm tra.
	 * @param y_pos vị trí hiện tại của đối tượng cần kiểm tra.
	 * @return .
	 */
	protected boolean upable(int x_pos, int y_pos) {
		x1_temp = (y_pos - pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
		y1_temp = (x_pos + pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;

		x2_temp = (y_pos - pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
		y2_temp = (x_pos + SIZE - pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
//		if (getImpassableEntityAt(y1_temp, x1_temp) instanceof Portal && getImpassableEntityAt(y2_temp, x2_temp) instanceof Portal) {
//			if (((Portal) getImpassableEntityAt(y1_temp, x1_temp)).isCanPass() && ((Portal) getImpassableEntityAt(y2_temp, x2_temp)).isCanPass()) {
//				return true;
//			}
//		}
		return getImpassableEntityAt(y1_temp, x1_temp) instanceof Grass && getImpassableEntityAt(y2_temp, x2_temp) instanceof Grass;
	}
}
