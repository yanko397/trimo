package trimo.entity.mob;

import trimo.Trimo;
import trimo.graphics.Screen;
import trimo.graphics.Sprite;
import trimo.input.Keyboard;
import trimo.input.Mouse;

public class Player extends Mob {

	private Keyboard input;
	private Sprite sprite = Sprite.player_s0;
	private int anima = 0;
	private boolean walking = false;

	public Player(Keyboard input) {
		this.input = input;
		animaSpeed = 20;										//standard: 20 (im 2. konstruktor auch ändern!)
	}

	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		animaSpeed = 30;
	}

	public void update() {
		int xa = 0, ya = 0;
		if (anima < 10000) anima++;
		else anima = 0;
		if(input.left) xa--;
		if(input.right) xa++;
		if(input.up) ya--;
		if(input.down) ya++;
		
		if(xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		}else{
			walking = false;
		}
		
		fireRateTick--;
		updateShooting();
	}

	private void updateShooting() {
		if(Mouse.getButton() == 1){
			double dx = Mouse.getX() - Trimo.getWindowWidth() / 2;				//Ankathete
			double dy = Mouse.getY() - Trimo.getWindowHeight() / 2;				//Gegenkathete
			double shootDir = Math.atan2(dy, dx);
			shoot(x + 3 , y + 3, shootDir);
		}
	}

	public void render(Screen screen) {
		if (dir == 0) sprite = Sprite.player_n0;
		if (dir == 1) sprite = Sprite.player_e0;
		if (dir == 2) sprite = Sprite.player_s0;
		if (dir == 3) sprite = Sprite.player_w0;

		if(walking){
			if(anima % animaSpeed > animaSpeed / 4 && anima % animaSpeed <= animaSpeed / 2){
				if (dir == 0) sprite = Sprite.player_n1;
				if (dir == 1) sprite = Sprite.player_e1;
				if (dir == 2) sprite = Sprite.player_s1;
				if (dir == 3) sprite = Sprite.player_w1;
			}

			if(anima % animaSpeed > animaSpeed / 4 * 3){
				if (dir == 0) sprite = Sprite.player_n2;
				if (dir == 1) sprite = Sprite.player_e2;
				if (dir == 2) sprite = Sprite.player_s2;
				if (dir == 3) sprite = Sprite.player_w2;
			}
		}

		screen.renderPlayer(x, y, sprite);
	}
}
