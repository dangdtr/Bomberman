package uet.oop.bomberman.collisions;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;


public class Collisions {
	public static boolean checkCollision(Entity A, Entity B) {
		int leftA, leftB;
		int rightA, rightB;
		int topA, topB;
		int bottomA, bottomB;

		leftB = B.getX() - 6;
		rightB = leftB + Sprite.SCALED_SIZE + 6;
		topB = B.getY() - 6;
		bottomB = topB + 6 + Sprite.SCALED_SIZE;

		leftA = A.getX();
		rightA = A.getX() + Sprite.SCALED_SIZE;
		topA = A.getY();
		bottomA = A.getY() + Sprite.SCALED_SIZE;

        return !((bottomA <= topB) || (topA >= bottomB) || (rightA <= leftB) || (leftA >= rightB));
    }
}
