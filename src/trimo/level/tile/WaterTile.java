package trimo.level.tile;

import trimo.graphics.Screen;
import trimo.graphics.Sprite;

public class WaterTile extends Tile {

	public WaterTile(Sprite sprite) {
		super(sprite);
		animaSpeed = 60;
	}

	public void update() {
		if (anima < 10000) anima++;
		else anima = 0;
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
		
		if (anima % animaSpeed < animaSpeed/3) {
			sprite = Sprite.water;
		}

		if (anima % animaSpeed >= animaSpeed/3 && anima % animaSpeed < animaSpeed/3*2) {
			sprite = Sprite.water2;
		}

		if (anima % animaSpeed >= animaSpeed/3*2) {
			sprite = Sprite.water3;
		}
	}
}
