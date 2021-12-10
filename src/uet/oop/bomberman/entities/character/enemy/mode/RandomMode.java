package uet.oop.bomberman.entities.character.enemy.mode;

public class RandomMode extends AI {

	@Override
	public int calcDirection() {
		return random.nextInt(600);
	}
}
