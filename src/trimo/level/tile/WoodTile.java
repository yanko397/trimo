package trimo.level.tile;

import trimo.graphics.Sprite;

public class WoodTile extends Tile{
	
	public WoodTile(Sprite sprite) {
		super(sprite);
	}

	public boolean solid(){
		return true;
	}
}
