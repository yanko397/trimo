package trimo.entity.projectile;

import trimo.entity.spawner.ParticleSpawner;
import trimo.graphics.Screen;
import trimo.graphics.Sprite;

public class Fireball extends Projectile{
	
	public Fireball(int x, int y, double dir) {
		super(x, y, dir);
		range = 200;
		speed = 4;
		damage = 5;
		fireRate = 20;
		sprite = Sprite.fireball;

		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}
	
	public void update(){
		move();
	}
	
	public void move(){
		if(!level.tileCollision(x + 4, y + 4, nx, ny, 1)){
//		if(!level.tileCollision(x, y, nx, ny, 9)){
			x += nx;
			y += ny;
		}else{
			level.add(new ParticleSpawner((int)x, (int)y, 2000, 30, level));
			remove();
		}
		
		if(distance() > range) remove();
	}
	
	public void render(Screen screen){
		screen.renderProjectile((int)x, (int)y, this);
	}
}
