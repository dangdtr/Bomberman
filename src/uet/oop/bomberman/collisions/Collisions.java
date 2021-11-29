package uet.oop.bomberman.collisions;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;


public class Collisions {
    public static boolean checkCollision(Entity A, Entity B) {
        int leftA, leftB;
        int rightA, rightB;
        int topA, topB;
        int bottomA, bottomB;

        leftB = B.getX();
        rightB = leftB + Sprite.SCALED_SIZE;
        topB = B.getY();
        bottomB = topB + Sprite.SCALED_SIZE;

        leftA = A.getX();
        rightA = A.getX() + Sprite.SCALED_SIZE;
        topA = A.getY();
        bottomA = A.getY() + Sprite.SCALED_SIZE;

        if (!((bottomA <= topB) || (topA >= bottomB) || (rightA <= leftB) || (leftA >= rightB))) {
            return true;
        }

        return false;
    }
}
