package uet.oop.bomberman.entities.character.enemy.mode;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.character.bomber.Bomber;
import uet.oop.bomberman.entities.character.enemy.Enemy;

public class AI2 extends AI {
	Bomber bomber = Game.getBomber();
	Enemy enemy;

	//    public AI2(GameMap gameMap, Enemy enemy) {
//        this.bomber = Game.getBomber();
//        this.enemy = enemy;
//    }
	public AI2(Enemy enemy) {
//    this.bomber = Game.getBomber();
		this.enemy = enemy;
	}

	@Override
	public int calcDirection() {
		return findDirection();
	}

	public int findDirection() {
		if ((double) Math.abs(enemy.getX() - bomber.getX()) < (double) Math.abs(enemy.getY() - bomber.getY())) {
			int _dir = dicrectionRow();
			if (_dir != -1) {
				return _dir;
			}
			return dicrectionCol();
		} else if ((double) Math.abs(enemy.getX() - bomber.getX()) > (double) Math.abs(enemy.getY() - bomber.getY())) {
			int _dir = dicrectionCol();
			if (_dir != -1) {
				return _dir;
			}
			return dicrectionRow();
		} else {
			int _dir = random.nextInt(2);
			if (_dir == 0) _dir = dicrectionCol();
			else _dir = dicrectionRow();
			return _dir;
		}
	}

	public int dicrectionRow() {
		if (bomber.getX() > enemy.getX()) return 1;
		if (bomber.getX() < enemy.getX()) return 3;
		return -1;
	}

	public int dicrectionCol() {
		if (bomber.getY() < enemy.getY()) return 0;
		if (bomber.getY() > enemy.getY()) return 2;
		return -1;
	}
}
