package uet.oop.bomberman.entities.tile.destroyable;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.tile.Tile;

public class DestroyableTile extends Tile {
	protected int _animate = 0;
	//    protected int _time = 36;
	protected boolean _exploding = false;
	protected int _timeToDisapear = 0;
	protected boolean _destroyed = false;

	public boolean isDestroyed() {
		return _destroyed;
	}

	public DestroyableTile(int xUnit, int yUnit, Image img) {
		super(xUnit, yUnit, img);
	}
}
