package trimo.level.tile;

import trimo.graphics.Sprite;

public class BricksTile extends Tile {

	public BricksTile(Sprite sprite) {
		super(sprite);
	}

	public boolean solid(){
		return true;
	}
}
