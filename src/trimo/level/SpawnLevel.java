package trimo.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpawnLevel extends Level{

	public SpawnLevel(String path) {
		super(path);
	}

	protected void loadLevel(String path){
		try {
			BufferedImage img = ImageIO.read(SpawnLevel.class.getResource(path));
			width = img.getWidth();
			height = img.getHeight();
			tiles = new int[width * height];
			img.getRGB(0, 0, width, height, tiles, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Level konnt nich geladn wern");
		}
	}
}
