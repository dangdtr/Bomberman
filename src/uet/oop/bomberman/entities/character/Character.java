package uet.oop.bomberman.entities.character;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.AnimatedEntitiy;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.graphics.Sprite;

import java.net.ServerSocket;

public abstract class Character extends AnimatedEntitiy {
	public Character(int xUnit, int yUnit, Image img) {
		super(xUnit, yUnit, img);
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
		for (Entity e : Game.stillObjects) {
			if (e.getX() == x && e.getY() == y) {
				return e;
			}
		}
		for (Integer value : Game.getLayeredEntitySet()) {
			if (Game.LayeredEntity.get(value).peek().getX() == x && Game.LayeredEntity.get(value).peek().getY() == y) {
				return Game.LayeredEntity.get(value).peek();
			}
		}

//		for (Bomb bomb : Game.getBombList()) {
//			if (bomb.getX() == x && bomb.getY() == y) {
//				entity = bomb;
//			}
//		}

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
		return getImpassableEntityAt(y1_temp, x1_temp) instanceof Grass && getImpassableEntityAt(y2_temp, x2_temp) instanceof Grass;
	}
}
