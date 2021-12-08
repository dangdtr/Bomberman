package uet.oop.bomberman.entities.dynamics.enemy.mode;

import uet.oop.bomberman.entities.dynamics.bomber.Bomber;
import uet.oop.bomberman.entities.dynamics.enemy.Enemy;
import uet.oop.bomberman.maps.GameMap;

public class AI2 extends AI{
    Bomber bomber;
    Enemy enemy;

    public AI2(GameMap gameMap, Enemy enemy) {
        this.bomber = gameMap.getBomber();
        this.enemy = enemy;
    }

    @Override
    public int calcDirection() {
        return findDirection();
    }

    public int findDirection() {
        if ((double) Math.abs(enemy.getX() - bomber.getX()) < (double) Math.abs(enemy.getY() - bomber.getY())) {
            int _dir = dicrectionRow();
            if (_dir != - 1) {
                return _dir;
            }
            return dicrectionCol();
        } else if ((double) Math.abs(enemy.getX() - bomber.getX()) > (double) Math.abs(enemy.getY() - bomber.getY())) {
            int _dir = dicrectionCol();
            if (_dir != - 1) {
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
    public int dicrectionCol(){
        if (bomber.getY() < enemy.getY()) return 0;
        if (bomber.getY() > enemy.getY()) return 2;
        return -1;
    }
}
