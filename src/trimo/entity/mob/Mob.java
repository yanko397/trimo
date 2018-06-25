package trimo.entity.mob;

import trimo.entity.Entity;
import trimo.entity.projectile.Fireball;
import trimo.entity.projectile.Projectile;
import trimo.graphics.Sprite;

public abstract class Mob extends Entity{

	protected Sprite sprite;
	protected int dir = 2;							//0 = norden, dann im uhrzeigersinn
	protected boolean moving = false;
	protected int animaSpeed = 0;
	protected double fireRateTick = 0;
	
//	protected List<Projectile> projectiles = new ArrayList<Projectile>();
	
	public void move(int xa, int ya){
		if(xa > 0) dir = 1;
		if(xa < 0) dir = 3;
		if(ya > 0) dir = 2;
		if(ya < 0) dir = 0;
		
		if(!collision(xa, 0))
			x += xa;
		
		if(!collision(0, ya)) y += ya;
	}
	
	public void update(){
		fireRateTick--;
	}
	
	public void render(){
		
	}
	
	protected void shoot(int x, int y, double dir){
		if(fireRateTick <= 0){
			Projectile p = new Fireball(x, y, dir);
			fireRateTick = p.fireRate;
			level.add(p);
		}
	}
	
	private boolean collision(int xa, int ya){
		for(int i = 0; i < 4; i++){
			int xt = ((x + xa) + i % 2 * /* hitbox -> */ 9 + 3 /* <- hitbox */) / 16;
			int yt = ((y + ya) + i / 2 * /* hitbox -> */ 7 + 10 /* <- hitbox */) / 16;
			if (level.getTile(xt, yt).solid()) return true;
		}
		return false;
	}
}
