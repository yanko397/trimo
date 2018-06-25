package trimo.graphics;

import java.util.Random;

public class Sprite {

	public final int SIZE;
	private int x, y;
	private int width, height;
	public int[] pixels;
	private SpriteSheet sheet;
	
	private static Random random = new Random();
	
	//BEI DOPPELTER GRÖSSE KORDIS HALBIEREN
	
	//TILES
	public static Sprite grass 		= new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite flower 		= new Sprite(16, 0, 2, SpriteSheet.tiles);
	public static Sprite wood 			= new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite planks 		= new Sprite(16, 1, 2, SpriteSheet.tiles);
	public static Sprite stone 		= new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite leaves 		= new Sprite(16, 3, 0, SpriteSheet.tiles);
	public static Sprite bricks 		= new Sprite(16, 4, 0, SpriteSheet.tiles);
	public static Sprite water 		= new Sprite(16, 5, 0, SpriteSheet.tiles);
	public static Sprite water2 		= new Sprite(16, 5, 1, SpriteSheet.tiles);
	public static Sprite water3 		= new Sprite(16, 5, 2, SpriteSheet.tiles);
	public static Sprite deepWater 	= new Sprite(16, 5, 4, SpriteSheet.tiles);
	public static Sprite deepWater2 	= new Sprite(16, 5, 5, SpriteSheet.tiles);
	public static Sprite deepWater3 	= new Sprite(16, 5, 6, SpriteSheet.tiles);
	public static Sprite lava 			= new Sprite(16, 5, 8, SpriteSheet.tiles);
	public static Sprite lava2 		= new Sprite(16, 5, 9, SpriteSheet.tiles);
	public static Sprite lava3 		= new Sprite(16, 5, 10, SpriteSheet.tiles);
	public static Sprite sand 			= new Sprite(16, 6, 0, SpriteSheet.tiles);
	public static Sprite sand2 		= new Sprite(16, 6, 2, SpriteSheet.tiles);

	public static Sprite voidSprite = new Sprite(16, 0xffff00ff);
	
	
	//PLAYER
	public static Sprite player_n0 = new Sprite(16, 0, 0, SpriteSheet.npc);
	public static Sprite player_n1 = new Sprite(16, 0, 1, SpriteSheet.npc);
	public static Sprite player_n2 = new Sprite(16, 0, 2, SpriteSheet.npc);

	public static Sprite player_e0 = new Sprite(16, 1, 0, SpriteSheet.npc);
	public static Sprite player_e1 = new Sprite(16, 1, 1, SpriteSheet.npc);
	public static Sprite player_e2 = new Sprite(16, 1, 2, SpriteSheet.npc);

	public static Sprite player_s0 = new Sprite(16, 2, 0, SpriteSheet.npc);
	public static Sprite player_s1 = new Sprite(16, 2, 1, SpriteSheet.npc);
	public static Sprite player_s2 = new Sprite(16, 2, 2, SpriteSheet.npc);

	public static Sprite player_w0 = new Sprite(16, 3, 0, SpriteSheet.npc);
	public static Sprite player_w1 = new Sprite(16, 3, 1, SpriteSheet.npc);
	public static Sprite player_w2 = new Sprite(16, 3, 2, SpriteSheet.npc);

	//PROJECTILES
	public static Sprite fireball = new Sprite(16, 0, 0, SpriteSheet.projectiles);
	
	//PARTICLES
	public static Sprite particle_normal = new Sprite(2, 0xff0000);
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[size * size];
		this.x = x * size;					//"* size" um zum richtigen Sprite zu kommen
		this.y = y * size;
		this.sheet = sheet;
		this.width = size;
		this.height = size;
		load();
	}
	
	public Sprite(int width, int height, int colour){
		SIZE = -1;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		setColour(colour);
	}
	
	public Sprite(int size, int colour){
		SIZE = size;
		pixels = new int[SIZE*SIZE];
		this.width = size;
		this.height = size;
		setColour(colour);
	}
	
	public void setColour(int colour){
		for(int i = 0; i < width * height; i++){
			pixels[i] = colour;
		}
	}

	private void load(){
		for(int y = 0; y < SIZE; y++){
			for(int x = 0; x < SIZE; x++){
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
}
