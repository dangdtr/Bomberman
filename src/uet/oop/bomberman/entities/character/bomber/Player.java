package uet.oop.bomberman.entities.character.bomber;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.character.Character;


public abstract class Player extends Character {
	private boolean alive = true;
	public Player(int xUnit, int yUnit, Image img) {
		super(xUnit, yUnit, img);
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	//cái này sẽ custome tiếp theo pixel nhân nhân vật
	/*
	private Entity getImpassableEntityAt(int x, int y) {
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
		for (Bomb bomb : Game.bombList) {
			if (bomb.getX() == x && bomb.getY() == y) {
				return bomb;
			}
		}


		return entity;
	}

	protected boolean leftable(int x_pos, int y_pos) {
		x1_temp = (y_pos + 0 * 10 + pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
		y1_temp = (x_pos - pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;

		x2_temp = (y_pos - 0 * 10 + SIZE - pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
		y2_temp = (x_pos - pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;


		return getImpassableEntityAt(y1_temp, x1_temp) instanceof Grass && getImpassableEntityAt(y2_temp, x2_temp) instanceof Grass;

//		return GameMap.getMap()[x1_temp][y1_temp] != '#' &&
//				GameMap.getMap()[x2_temp][y2_temp] != '#';
	}


	protected boolean rightable(int x_pos, int y_pos) {
		x1_temp = (y_pos + 0 * 10 + pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
		y1_temp = (x_pos - 0 * 15 + SIZE + pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;

		x2_temp = (y_pos - 0 * 10 + SIZE - pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
		y2_temp = (x_pos - 0 * 15 + SIZE + pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;

		return getImpassableEntityAt(y1_temp, x1_temp) instanceof Grass && getImpassableEntityAt(y2_temp, x2_temp) instanceof Grass;
	}


	protected boolean downable(int x_pos, int y_pos) {
		x1_temp = (y_pos + 0 * 10 + SIZE + pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
		y1_temp = (x_pos + pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;

		x2_temp = (y_pos + SIZE + pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
		y2_temp = (x_pos - 0 * 15 + SIZE - pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;

		return getImpassableEntityAt(y1_temp, x1_temp) instanceof Grass && getImpassableEntityAt(y2_temp, x2_temp) instanceof Grass;
	}

	protected boolean upable(int x_pos, int y_pos) {
		x1_temp = (y_pos + 0 * 10 - pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
		y1_temp = (x_pos + pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;

		x2_temp = (y_pos - pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;
		y2_temp = (x_pos - 0 * 15 + SIZE - pixel) / Sprite.SCALED_SIZE * Sprite.SCALED_SIZE;

		return getImpassableEntityAt(y1_temp, x1_temp) instanceof Grass && getImpassableEntityAt(y2_temp, x2_temp) instanceof Grass;
	}
	*/
}
