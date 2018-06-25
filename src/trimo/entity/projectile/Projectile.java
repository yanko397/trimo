package trimo.entity.projectile;

import trimo.entity.Entity;
import trimo.graphics.Sprite;

public abstract class Projectile extends Entity {

	protected final int xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double x, y;
	protected double nx, ny;
	protected double distance;
	public double speed, fireRate, range, damage;
	
	public Projectile(int x, int y, double dir){
		this.x = x;
		this.y = y;
		xOrigin = x;
		yOrigin = y;
		angle = dir;
	}
	
	public double distance(){
		distance = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));

		return distance;
	}
	
	protected void move(){

	}

	public Sprite getSprite(){
		return sprite;
	}
}
