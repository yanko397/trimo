package trimo.level.tile;

import trimo.graphics.Sprite;

public class PlanksTile extends Tile {

	public PlanksTile(Sprite sprite) {
		super(sprite);
	}

	public boolean solid(){
		return true;
	}
}
