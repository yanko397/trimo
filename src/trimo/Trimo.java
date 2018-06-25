package trimo;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;

import trimo.entity.mob.Player;
import trimo.graphics.Screen;
import trimo.input.Keyboard;
import trimo.input.Mouse;
import trimo.level.Level;
import trimo.level.SpawnLevel;
import trimo.level.TileCoordinate;

public class Trimo extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	private static int width = 450;
	private static int height = width / 16 * 9;
	private static int scale = 3;
	public String title = "Trimo";

	private Thread thread;
	protected JFrame frame;
	private Screen screen;
	private Keyboard keyboard;
	private Level level;
	private TileCoordinate playerSpawn;
	private Player player;
	private boolean running = false;
	
	private int tick = 0;
	private int sec = 0;
	private int min = 0;
	private int hour = 0;
	private int day = 0;

	private BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();				//img in int array konvertieren

	public Trimo() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);

		screen = new Screen(width, height);
		frame = new JFrame();
		keyboard = new Keyboard();
		level = new SpawnLevel("/levels/spawn_level.png");
		playerSpawn = new TileCoordinate(9, 10);
		player = new Player(playerSpawn.x(), playerSpawn.y(), keyboard);
		player.init(level);

		addKeyListener(keyboard);

		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Spiel");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int ticks = 0;
		int frames = 0;

		requestFocus();

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			while (delta >= 1) {
				update();

				ticks++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(title + "   |   FPS: " + frames + "   Ticks/sec: " + ticks);
				ticks = 0;
				frames = 0;
			}
		}

		stop();
	}

	public void update() {
		if(tick % 60 == 0){
			sec++;
			if(sec % 60 == 0){
				sec = 0;
				min++;
				if(min % 60 == 0){
					min = 0;
					hour++;
					if(hour % 24 == 0){
						hour = 0;
						day++;
					}
				}
			}
		}

		if(tick == 60000) tick = 0;
		else tick++;
		
		keyboard.update();
		player.update();
		level.update();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);								//Tripple Buffering
			return;
		}

		screen.clear();
		int xScroll = player.x - screen.width / 2 + 8;
		int yScroll = player.y - screen.height / 2 + 8;
		
		level.render(xScroll, yScroll, screen);
		player.render(screen);
		level.renderParticles(screen);

		for (int i = 0; i < pixels.length; i++) {						//kopieren von screen
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.RED);
		g.setFont(new Font("Verdana", 0, 50));
//		g.fillRect(Mouse.getX() - 16, Mouse.getY() - 16, 32, 32);
		g.drawString("Tick:" + tick, 20, 60);
		
		String secS = "" + sec;
		if(sec < 10) secS = "0" + sec;
		String minS = "" + min;
		if(min < 10) minS = "0" + min;
		String hourS = "" + hour;
		if(hour < 10) hourS = "0" + hour;
		String dayS = "" + day;
		if(day < 10) dayS = "0" + day;
		g.drawString("Time: " + dayS + ":" + hourS + ":" + minS + ":" + secS, 20, 110);
		
		g.dispose();
		bs.show();
	}
	
	public static int getWindowWidth(){
		return width * scale;
	}
	
	public static int getWindowHeight(){
		return height * scale;
	}
}
