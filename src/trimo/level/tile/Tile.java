package trimo.level.tile;

import trimo.graphics.Screen;
import trimo.graphics.Sprite;

public class Tile {

	public int x, y;
	public int anima = 0;
	public int animaSpeed = 0;
	public Sprite sprite;
	
	public static Tile grass 		= new GrassTile(Sprite.grass);
	public static Tile flower 		= new FlowerTile(Sprite.flower);
	public static Tile wood 		= new WoodTile(Sprite.wood);
	public static Tile planks 		= new PlanksTile(Sprite.planks);
	public static Tile stone 		= new StoneTile(Sprite.stone);
	public static Tile leaves 		= new LeavesTile(Sprite.leaves);
	public static Tile bricks 		= new BricksTile(Sprite.bricks);
	public static Tile water 		= new WaterTile(Sprite.water);
	public static Tile deepWater 	= new DeepWaterTile(Sprite.deepWater);
	public static Tile lava 		= new LavaTile(Sprite.lava);
	public static Tile sand 		= new SandTile(Sprite.sand);
	public static Tile sand2 		= new SandTile(Sprite.sand2);
	
	public static Tile voidTile 	= new VoidTile(Sprite.voidSprite);

	public Tile(Sprite sprite){
		this.sprite = sprite;
	}
	
	public void update(){
		//
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	
	public boolean solid(){
		return false;
	}
}
