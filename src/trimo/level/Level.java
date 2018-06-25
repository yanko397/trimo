package trimo.level;

import java.util.ArrayList;
import java.util.List;

import trimo.entity.Entity;
import trimo.entity.particle.Particle;
import trimo.entity.projectile.Projectile;
import trimo.graphics.Screen;
import trimo.level.tile.Tile;

public class Level {

	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	
	private List<Entity> entities = new ArrayList<Entity>();				//auch Projectiles werden hierin gespeichert, falls noetig aendern
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	private List<Particle> particles = new ArrayList<Particle>();

	public Level(int width, int height) {									//Konstruktor fuer random Map
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		randomLevel();
	}

	public Level(String path) {												//Konstuktor fuer aus Datei
		loadLevel(path);
	}

	public void add(Entity e){
		e.init(this);
		
		if(e instanceof Projectile){
			projectiles.add((Projectile) e);
		}else if(e instanceof Particle){
			particles.add((Particle) e);
		}else{
			entities.add(e);
		}
	}
	
	//FARBEN DER TILES AUF MAPS
	public static final int grass_col     = 0xff00ff00;
	public static final int flower_col    = 0xffffff00;
	public static final int wood_col      = 0xff6c3511;
	public static final int planks_col    = 0xff923310;
	public static final int stone_col     = 0xff808080;
	public static final int leaves_col    = 0xff007f0e;
	public static final int bricks_col    = 0xff982025;
	public static final int water_col     = 0xff0000c4;
	public static final int deepWater_col = 0xff000055;
	public static final int lava_col      = 0xffc61100;
	public static final int sand_col      = 0xffEDCF7C;
	public static final int sand2_col     = 0xffff9659;
	public static final int void_col      = 0xffffffff;

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.deepWater;			//außerhalb der map
		if (tiles[x + y * width] == grass_col) return Tile.grass;
		if (tiles[x + y * width] == flower_col) return Tile.flower;
		if (tiles[x + y * width] == wood_col) return Tile.wood;
		if (tiles[x + y * width] == planks_col) return Tile.planks;
		if (tiles[x + y * width] == stone_col) return Tile.stone;
		if (tiles[x + y * width] == leaves_col) return Tile.leaves;
		if (tiles[x + y * width] == water_col) return Tile.water;
		if (tiles[x + y * width] == deepWater_col) return Tile.deepWater;
		if (tiles[x + y * width] == lava_col) return Tile.lava;
		if (tiles[x + y * width] == sand_col) return Tile.sand;
		if (tiles[x + y * width] == sand2_col) return Tile.sand2;
		return Tile.voidTile;														//auffüllen
	}

	protected void loadLevel(String path) {
		//
	}

	protected void randomLevel() {
		//
	}

	public void update() {
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).update();
		}
		
		for(int i = 0; i < projectiles.size(); i++){
			projectiles.get(i).update();
		}
		
		for(int i = 0; i < particles.size(); i++){
			particles.get(i).update();
		}
		
		clearLists();
		
		Tile.water.update();
		Tile.deepWater.update();
		Tile.lava.update();
	}
	
	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;												//Corner Pins
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;
		
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
		
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).render(screen);
		}
		
		for(int i = 0; i < projectiles.size(); i++){
			projectiles.get(i).render(screen);
		}
	}
	
	//extra, damit particles ueber dem player gerendert werden koennen
	public void renderParticles(Screen screen){
		for(int i = 0; i < particles.size(); i++){
			particles.get(i).render(screen);
		}		
	}
	
	public void clearLists(){
		//cleart entities, die als removed markiert sind
		for(int i = 0; i < entities.size(); i++){
			if(entities.get(i).isRemoved()) entities.remove(i);
		}
		
		for(int i = 0; i < projectiles.size(); i++){
			if(projectiles.get(i).isRemoved()) projectiles.remove(i);
		}
		
		for(int i = 0; i < particles.size(); i++){
			if(particles.get(i).isRemoved()) particles.remove(i);
		}
	}

	public boolean tileCollision(double x, double y, double nx, double ny, int size){
		for(int i = 0; i < 4; i++){
			int xt = (((int)x + (int)nx) + i % 2 * size) / 16;
			int yt = (((int)y + (int)ny) + i / 2 * size) / 16;
			if (getTile(xt, yt).solid()) return true;
		}
		return false;
	}

//	private void time() {
//		
//	}
}
