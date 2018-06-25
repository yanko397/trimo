package trimo.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheet {
	
	private String path;
	public final int SIZE;
	public int[] pixels;
	
	public static SpriteSheet tiles = new SpriteSheet("/textures/tiles.png", 256);
	public static SpriteSheet npc = new SpriteSheet("/textures/npc/npc.png", 256);
	public static SpriteSheet projectiles = new SpriteSheet("/textures/projectiles/projectiles.png", 64);
	
	public SpriteSheet(String path, int size){
		this.path = path;
		SIZE = size;
		pixels = new int[size * size];
		load();
	}
	
	public void load(){
		try {
			BufferedImage img = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = img.getWidth();
			int h = img.getHeight();
			img.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
