package uet.oop.bomberman.entities.character.enemy.mode;

public class FindingPathMode {
	int G;
	int H;
	int F; // G + H
	int direction;

	public FindingPathMode(int g, int h, int f, int direction) {
		G = g;
		H = h;
		F = f;
		this.direction = direction;
	}

	public int getF() {
		return F;
	}

	public int getDirection() {
		return direction;
	}
}
