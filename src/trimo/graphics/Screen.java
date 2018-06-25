package trimo.graphics;

import java.util.Random;

import trimo.entity.projectile.Projectile;
import trimo.level.tile.Tile;

public class Screen {

	public int width, height;
	public int[] pixels;
	public final int MAP_SIZE = 64;										//Muss Zweierpotenz sein
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;

	public int xOff, yOff;

	private int[] tiles = new int[MAP_SIZE * MAP_SIZE];

	private Random random = new Random();

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];

		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			tiles[i] = random.nextInt(0xffffff);
		}
	}
	
	public void renderSprite(int xpos, int ypos, Sprite sprite, boolean fixed){
		if(fixed){
			xpos -= xOff;
			ypos -= yOff;
		}
		
		for(int y = 0; y < sprite.getHeight(); y++){
			int ya = y + ypos;
			for(int x = 0; x < sprite.getWidth(); x++){
				int xa = x + xpos;
				if(xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				pixels[xa + ya * width] = sprite.pixels[x + y * sprite.getWidth()];
			}
		}
	}

	public void renderTile(int xpos, int ypos, Tile tile) {
		xpos -= xOff;
		ypos -= yOff;

		for (int y = 0; y < tile.sprite.SIZE; y++) {
			int yabs = y + ypos;

			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xabs = x + xpos;
				if (xabs < -tile.sprite.SIZE || xabs >= width || yabs < 0 || yabs >= height) break;
				if(xabs < 0) xabs = 0;

				pixels[xabs + yabs * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
			}
		}
	}
	
	public void renderProjectile(int xpos, int ypos, Projectile p) {
		xpos -= xOff;
		ypos -= yOff;
		
		for (int y = 0; y < p.getSprite().SIZE; y++) {
			int yabs = y + ypos;
			
			for (int x = 0; x < p.getSprite().SIZE; x++) {
				int xabs = x + xpos;
				if (xabs < -p.getSprite().SIZE || xabs >= width || yabs < 0 || yabs >= height) break;
				if(xabs < 0) xabs = 0;
				
				int col = p.getSprite().pixels[x + y * p.getSprite().SIZE];
				
				if(col != 0xffff00ff) pixels[xabs + yabs * width] = p.getSprite().pixels[x + y * p.getSprite().SIZE];
			}
		}
	}

	public void renderPlayer(int xpos, int ypos, Sprite sprite) {
		xpos -= xOff;
		ypos -= yOff;
		
		for (int y = 0; y < 16; y++) {
			int yabs = y + ypos;
			int ys = y;
			
			for (int x = 0; x < 16; x++) {
				int xabs = x + xpos;
				int xs = x;
				
				if (xabs < -16 || xabs >= width || yabs < 0 || yabs >= height) break;
				if(xabs < 0) xabs = 0;
				
				int col = sprite.pixels[xs + ys * 16];
				
				if(col != 0xffff00ff) pixels[xabs + yabs * width] = col; 
			}
		}
	}

	public void setOffset(int xOff, int yOff) {
		this.xOff = xOff;
		this.yOff = yOff;
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
}
