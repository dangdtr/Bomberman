package uet.oop.bomberman.entities.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.AnimatedEntitiy;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.maps.GameMap;

import java.io.IOException;

public class AbstractBomb extends AnimatedEntitiy {
	public AbstractBomb(int xUnit, int yUnit, Image img) {
		super(xUnit, yUnit, img);
	}

	protected boolean leftable(int x_pos, int y_pos) {
		x1_temp = (y_pos + pixel) / Sprite.SCALED_SIZE;
		y1_temp = (x_pos - pixel) / Sprite.SCALED_SIZE;

		x2_temp = (y_pos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE;
		y2_temp = (x_pos - pixel) / Sprite.SCALED_SIZE;

		return GameMap.getMap()[x1_temp][y1_temp] != '#' &&
				GameMap.getMap()[x2_temp][y2_temp] != '#';
	}


	protected boolean rightable(int x_pos, int y_pos) {
		x1_temp = (y_pos + pixel) / Sprite.SCALED_SIZE;
		y1_temp = (x_pos + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE;

		x2_temp = (y_pos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE;
		y2_temp = (x_pos + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE;

		return GameMap.getMap()[x1_temp][y1_temp] != '#' &&
				GameMap.getMap()[x2_temp][y2_temp] != '#';
	}


	protected boolean downable(int x_pos, int y_pos) {
		x1_temp = (y_pos + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE;
		y1_temp = (x_pos + pixel) / Sprite.SCALED_SIZE;

		x2_temp = (y_pos + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE;
		y2_temp = (x_pos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE;

		return GameMap.getMap()[x1_temp][y1_temp] != '#' &&
				GameMap.getMap()[x2_temp][y2_temp] != '#';
	}

	protected boolean upable(int x_pos, int y_pos) {
		x1_temp = (y_pos - pixel) / Sprite.SCALED_SIZE;
		y1_temp = (x_pos + pixel) / Sprite.SCALED_SIZE;

		x2_temp = (y_pos - pixel) / Sprite.SCALED_SIZE;
		y2_temp = (x_pos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE;

		return GameMap.getMap()[x1_temp][y1_temp] != '#' &&
				GameMap.getMap()[x2_temp][y2_temp] != '#';
	}


	protected boolean leftableBrick(int x_pos, int y_pos) {
		x1_temp = (y_pos + pixel) / Sprite.SCALED_SIZE;
		y1_temp = (x_pos - pixel) / Sprite.SCALED_SIZE;

		x2_temp = (y_pos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE;
		y2_temp = (x_pos - pixel) / Sprite.SCALED_SIZE;

		if (Game.LayeredEntity.containsKey(Game.generateKey(y1_temp, x1_temp)) ||
				Game.LayeredEntity.containsKey(Game.generateKey(y2_temp, x2_temp))) {
			return !(Game.LayeredEntity.get(Game.generateKey(y1_temp, x1_temp)).peek() instanceof Brick) &&
					!(Game.LayeredEntity.get(Game.generateKey(y2_temp, x1_temp)).peek() instanceof Brick);
		}
		return true;
	}


	protected boolean rightableBrick(int x_pos, int y_pos) {
		x1_temp = (y_pos + pixel) / Sprite.SCALED_SIZE;
		y1_temp = (x_pos + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE;

		x2_temp = (y_pos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE;
		y2_temp = (x_pos + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE;

		if (Game.LayeredEntity.containsKey(Game.generateKey(y1_temp, x1_temp)) ||
				Game.LayeredEntity.containsKey(Game.generateKey(y2_temp, x2_temp))) {
			return !(Game.LayeredEntity.get(Game.generateKey(y1_temp, x1_temp)).peek() instanceof Brick) &&
					!(Game.LayeredEntity.get(Game.generateKey(y2_temp, x1_temp)).peek() instanceof Brick);
		}
		return true;
	}


	protected boolean downableBrick(int x_pos, int y_pos) {
		x1_temp = (y_pos + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE;
		y1_temp = (x_pos + pixel) / Sprite.SCALED_SIZE;

		x2_temp = (y_pos + Sprite.SCALED_SIZE + pixel) / Sprite.SCALED_SIZE;
		y2_temp = (x_pos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE;

		if (Game.LayeredEntity.containsKey(Game.generateKey(y1_temp, x1_temp)) ||
				Game.LayeredEntity.containsKey(Game.generateKey(y2_temp, x2_temp))) {
			return !(Game.LayeredEntity.get(Game.generateKey(y1_temp, x1_temp)).peek() instanceof Brick) &&
					!(Game.LayeredEntity.get(Game.generateKey(y2_temp, x1_temp)).peek() instanceof Brick);
		}
		return true;
	}

	protected boolean upableBrick(int x_pos, int y_pos) {
		x1_temp = (y_pos - pixel) / Sprite.SCALED_SIZE;
		y1_temp = (x_pos + pixel) / Sprite.SCALED_SIZE;

		x2_temp = (y_pos - pixel) / Sprite.SCALED_SIZE;
		y2_temp = (x_pos + Sprite.SCALED_SIZE - pixel) / Sprite.SCALED_SIZE;
		if (Game.LayeredEntity.containsKey(Game.generateKey(y1_temp, x1_temp)) ||
				Game.LayeredEntity.containsKey(Game.generateKey(y2_temp, x2_temp))) {
			return !(Game.LayeredEntity.get(Game.generateKey(y1_temp, x1_temp)).peek() instanceof Brick) &&
					!(Game.LayeredEntity.get(Game.generateKey(y2_temp, x1_temp)).peek() instanceof Brick);
		}
		return true;
	}

	@Override
	public void kill() {

	}

	@Override
	protected void afterKill() {

	}

	@Override
	public void update() throws IOException {

	}
}
