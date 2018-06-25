package trimo.entity.particle;

import trimo.entity.Entity;
import trimo.graphics.Screen;
import trimo.graphics.Sprite;

public class Particle extends Entity{

	private Sprite sprite;
	
	private int life;
	private int time;
	
	protected double xx, yy, zz;
	protected double xa, ya, za;
	
	public Particle(int x, int y, int life){
		this.x = x;
		this.y = y;
		this.xx = x + 4;
		this.yy = y + 9;
		this.life = life + random.nextInt(400);
		sprite = Sprite.particle_normal;
		
		this.xa = random.nextGaussian()/* + 1.8*/;
//		if(this.xa < 0) xa = 0.1;
		this.ya = random.nextGaussian();
		this.zz = random.nextFloat() + 5.0;								//sprunghoehe.. auch bei yy oben aendern!
	}
	
	public void update(){
		za -= 0.1;
		this.xx += xa * 0.6;
		this.yy += ya * 0.6;
		this.zz += za;
		
		if(zz < 0){
			zz = 0;
			xa *= 0.8;
			ya *= 0.8;
			za *= -0.7;
		}
	}
	
	public void render(Screen screen){
		screen.renderSprite((int)xx, (int)yy - (int) zz, sprite, true);
		time++;
		//zur sicherheit
		if(time >= 10000) time = 0;
		if(time > life) remove();
	}
}
