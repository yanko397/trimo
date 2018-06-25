package trimo.level.tile;

import trimo.graphics.Sprite;

public class StoneTile extends Tile {

	public StoneTile(Sprite sprite) {
		super(sprite);
	}
	
	public boolean solid(){
		return true;
	}
}
