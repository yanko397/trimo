package trimo.level.tile;

import trimo.graphics.Sprite;

public class LeavesTile extends Tile {

	public LeavesTile(Sprite sprite) {
		super(sprite);
	}
	
	public boolean solid(){
		return true;
	}
}
