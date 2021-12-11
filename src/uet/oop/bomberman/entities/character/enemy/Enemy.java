package uet.oop.bomberman.entities.character.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.entities.character.enemy.mode.AI;

public abstract class Enemy extends Character {
	protected AI ai;
	protected int speed = Game.SPEED_OF_ENEMY;
	protected int direction = -1;
	protected boolean isDie = false;


	public Enemy(int xUnit, int yUnit, Image img) {
		super(xUnit, yUnit, img);
	}

	public boolean isDie() {
		return isDie;
	}

	public void enemyDie() {
		isDie = true;
	}

	protected Entity getImpassableEntityAt(int x, int y) {
		Entity entity = null;
		for (Entity e : Game.stillObjects) {
			if (e.getX() == x && e.getY() == y) {
				entity = e;
			}
		}
		for (Integer value : Game.getLayeredEntitySet()) {
			if (Game.LayeredEntity.get(value).peek().getX() == x && Game.LayeredEntity.get(value).peek().getY() == y) {
				entity = Game.LayeredEntity.get(value).peek();
			}
		}

		for (Bomb bomb : Game.getBombList()) {
			if (bomb.getX() == x && bomb.getY() == y) {
				System.out.println("detech bomb");

				entity = bomb;
			}
		}

		return entity;
	}

	public abstract void killBomber();

	protected abstract void chooseSprite();
}
