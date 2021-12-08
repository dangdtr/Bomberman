package uet.oop.bomberman.entities.dynamics.enemy.mode;

import java.util.Random;

public class RandomMode extends AI {

    @Override
    public int calcDirection() {
        return random.nextInt(600);
    }
}
