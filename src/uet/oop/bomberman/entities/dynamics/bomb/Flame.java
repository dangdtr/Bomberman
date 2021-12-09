package uet.oop.bomberman.entities.dynamics.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.dynamics.DynamicEntity;
import uet.oop.bomberman.graphics.Sprite;

import java.io.IOException;

public class Flame extends DynamicEntity {
	private String status;
	public static int lenOfFlame = 1;
	private final String LEFT_LAST = "LEFT_LAST";
	private final String RIGHT_LAST = "RIGHT_LAST";
	private final String UP_LAST = "TOP_LAST";
	private final String DOWN_LAST = "DOWN_LAST";

	private final String HORIZONTAL = "HORIZONTAL";
	private final String VERTICAL = "VERTICAL";

	public Flame(int xUnit, int yUnit, Image img) {
		super(xUnit, yUnit, img);
	}

	//-----------------//co the khong dung
	@Override
	public void kill() {

	}

	@Override
	protected void afterKill() throws IOException {

	}
	//-----------------//


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public void update() {
		chooseSprite();
		animateFlame();
		setImg(sprite.getFxImage());
	}

	private void animateFlame() {
		if (_animate < 120) _animate++;
		else _animate = 0;
	}

	private void chooseSprite() {
		_time = 180;
		switch (status) {
			case "LEFT_LAST":
				sprite =
						Sprite.movingSprite(
								Sprite.explosion_horizontal_left_last,
								Sprite.explosion_horizontal_left_last1,
								Sprite.explosion_horizontal_left_last2,
								_animate,
								_time);
				break;

			case "RIGHT_LAST":
				sprite =
						Sprite.movingSprite(
								Sprite.explosion_horizontal_right_last,
								Sprite.explosion_horizontal_right_last1,
								Sprite.explosion_horizontal_right_last2,
								_animate,
								_time);
				break;

			case "TOP_LAST":
				sprite =
						Sprite.movingSprite(
								Sprite.explosion_vertical_top_last,
								Sprite.explosion_vertical_top_last1,
								Sprite.explosion_vertical_top_last2,
								_animate,
								_time);

				break;

			case "DOWN_LAST":
				sprite =
						Sprite.movingSprite(
								Sprite.explosion_vertical_down_last,
								Sprite.explosion_vertical_down_last1,
								Sprite.explosion_vertical_down_last2,
								_animate,
								_time);

				break;

			case "HORIZONTAL":
				sprite =
						Sprite.movingSprite(
								Sprite.explosion_horizontal,
								Sprite.explosion_horizontal1,
								Sprite.explosion_horizontal2,
								_animate,
								_time);
				break;

			case "VERTICAL":
				sprite =
						Sprite.movingSprite(
								Sprite.explosion_vertical,
								Sprite.explosion_vertical1,
								Sprite.explosion_vertical2,
								_animate,
								_time);
				break;
			default:
				sprite =
						Sprite.movingSprite(
								Sprite.bomb_exploded,
								Sprite.bomb_exploded1,
								Sprite.bomb_exploded2,
								_animate,
								_time);
				break;
		}
	}
}
