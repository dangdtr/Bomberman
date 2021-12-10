package uet.oop.bomberman.entities.character.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.entities.character.enemy.mode.AI;

public abstract class Enemy extends Character {
	protected AI ai;
	protected int speed = 2;
	protected int direction = -1;

	public Enemy(int xUnit, int yUnit, Image img) {
		super(xUnit, yUnit, img);
	}

	public abstract void killBomber();

	protected abstract void chooseSprite();
}
