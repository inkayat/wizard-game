
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import org.newdawn.slick.SlickException;

public class Game extends Canvas implements Runnable{

	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 612;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public boolean isRunning = false;
	public static boolean isStart = false;
	private Thread thread;
	private Handler handler;
	private Camera camera;
	private SpriteSheet ss;
	
	public static AudioManager audio = new AudioManager();
	public static boolean isSoundOn = true;
	public boolean isMusicPlaying = false;
	
	private BufferedImage level=null;
	private BufferedImage sprite_sheet=null;
	private BufferedImage floor=null;
	
	private Menu menu = new Menu();
	
	public int ammo=100;
	public int hp=100;
	
	public static enum STATE{
		MENU,
		GAME,
		SETTINGS,
		DIFFICULTY
	}
	
	public static enum DIFFICULTY{
		EASY,
		HARD
	}
	
	private static STATE State = STATE.MENU;
	private static DIFFICULTY difficulty = DIFFICULTY.EASY;
	
	
	public Game() throws SlickException {
		new Window(SCALE*WIDTH, SCALE*HEIGHT, "Wizard Game", this);
		
		audio.load();
		//audio.getMusic("music").loop();
		
		start();
	
		handler = new Handler();
		camera= new Camera(0,0);
		
		this.addKeyListener(new KeyInput (handler));
		
		
		BufferedImageLoader loader =new BufferedImageLoader();
		level=loader.loadImage("/gamemap1_1.png");
		
		sprite_sheet = loader.loadImage("/sprite_sheet.png");
		ss=new SpriteSheet(sprite_sheet);
		floor=ss.grabImage(4, 2, 32, 32);		
		this.addMouseListener(new MouseInput(handler,camera,this,ss) );
		loadLevel(level);
		
	}
	
	private void start() {
		isRunning =true;
		thread=new Thread(this);
		thread.start();
	
	}
	
	private void stop() {
		
		isRunning=false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
	}
	public void run() {
		this.requestFocus();
		  long lastTime = System.nanoTime();
		  double amountOfTicks = 60.0;
		  double ns = 1000000000 / amountOfTicks;
		  double delta = 0;
		  long timer = System.currentTimeMillis();
		  int frames = 0;
		  while (isRunning) {
		   long now = System.nanoTime();
		   delta += (now - lastTime) /ns;
		   lastTime = now;
		   while(delta >= 1) {
		    tick();
		    //updates++;
		    delta--;
		   }
		   render();
		   frames++;
		   
		   if (System.currentTimeMillis() - timer > 1000) {
		    timer += 1000;
		    frames = 0;
		    //updates = 0;
		   }
		  }
		  stop();
		
	}
	
	public void tick() {
		if(getState() == STATE.MENU && !isMusicPlaying) {
			audio.getMusic("music").loop();
			isMusicPlaying = true;
		}else if(getState() == STATE.GAME && isStart == false) {
			isStart = true;
			audio.getMusic("music").stop();
			loadLevel(level);
		}else if(getState() == STATE.GAME) {
			
			for(int i=0;i<handler.object.size();i++) {
				if(handler.object.get(i).getId()== ID.Player) {
					camera.tick(handler.object.get(i));	
				}	
			}
		}
		handler.tick();
	}
	
	public void render() {
		BufferStrategy bs= this.getBufferStrategy();
		if (bs==null) {
			this.createBufferStrategy(3);
			return;
		}
	
		Graphics g=bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;

		g2d.translate(-camera.getX(), -camera.getY());
		for(int xx=0;xx<30*72;xx+=32) {
				for(int yy=0;yy<32*70;yy+=32) {
							g.drawImage(floor, xx,yy,null);
				}					
		}
				
		handler.render(g);
		g2d.translate(camera.getX(), camera.getY());
		
		if(getState() == STATE.GAME) {

			g.setColor(Color.red);
			g.fillRect(5, 5, 200, 32);
			g.setColor(Color.green);
			g.fillRect(5, 5, hp*2, 32);
			g.setColor(Color.black);
			g.drawRect(5, 5, 200, 32);
			
			g.setColor(Color.white);
			g.drawString("Fireball :" + " " + ammo,5,50);
		}else {
			menu.render(g);
		}
		g.dispose();
		bs.show();			

	}
	
		//loading the level
	private void loadLevel(BufferedImage image) {
		if (getState() == Game.STATE.GAME) {
			int w=image.getWidth();
			int h=image.getHeight();
			
			for(int xx=0;xx<w;xx++) {
				
				for(int yy=0;yy<h;yy++) {
					int pixel =image.getRGB(xx, yy);
					int red= (pixel>>16) & 0xff;
					int green=(pixel>>8) & 0xff;
					int blue=(pixel) & 0xff;
					
					if(red==255 )
						handler.addObject(new Block(xx*32,yy*32,ID.Block,ss));
					if(blue==255 && green==0)
						handler.addObject(new Wizard(xx*32,yy*32,ID.Player,handler,this, ss));
					if(green==255 && blue==0)
						handler.addObject(new Enemy(xx*32,yy*32,ID.Enemy,handler, ss));
					if(green==255 && blue==255)
						handler.addObject(new Create(xx*32,yy*32,ID.Create, ss));
					}
				}
		
		}
		
	}
	
	public static void main(String args[]) throws SlickException {
		new Game();
	}

	public static STATE getState() {
		return State;
	}

	public static STATE setState(STATE state) {
		State = state;
		return state;
	}

	public static DIFFICULTY getDifficulty() {
		return difficulty;
	}

	public static void setDifficulty(DIFFICULTY difficulty) {
		Game.difficulty = difficulty;
	}	


}
