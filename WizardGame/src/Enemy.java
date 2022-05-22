
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends GameObject {
	private BufferedImage[] enemy_image = new BufferedImage[3];
	Animation anim;
	private Handler handler;
	
	
	Random r= new Random();
	int choose=0;
	int hp=100;
	
	public Enemy(int x, int y, ID id,Handler handler,SpriteSheet ss) {
		super(x, y, id, ss);
		this.handler=handler;
		enemy_image[0]=ss.grabImage(4, 1, 32, 32);
		enemy_image[1]=ss.grabImage(5, 1, 32, 32);
		enemy_image[2]=ss.grabImage(6, 1, 32, 32);

		anim = new Animation(3, enemy_image);
		
		}

	public void tick() {
		if (Game.getState() != Game.STATE.STOP && Game.getPrev_state() != Game.STATE.STOP) {
			x+=velX;
			y+=velY;
			
			choose=r.nextInt(10);
		
			for(int i=0;i<handler.object.size();i++) {
				GameObject tempObject = handler.object.get(i);
				
				if(tempObject.getId() == ID.Block) {
					
					if(getBoundsBig().intersects(tempObject.getBounds())) {
						x+=(velX*1) * -1;
						y+=(velY*1) * -1;
						velX*=-1;
						velY*=-1;
					}
					else if(choose==0) {
						
						velX=(r.nextInt(4 - -4) + -4);
						velY=(r.nextInt(4 - -4) + -4);
					}
				}
			
					if(tempObject.getId() == ID.Bullet) {
						if(getBounds().intersects(tempObject.getBounds())) {
							hp-=50;
							handler.removeObject(tempObject);
						}
						
					}
			}
			
			anim.runAnimation();
			if(hp<=0) {
				Game.audio.getSound("goblin_death").play();
				handler.removeObject(this);
			}
		}
	}

	public void render(Graphics g) {
		anim.drawAnimation(g, x, y, 0);	
		
	}

	public Rectangle getBounds() {
		return new Rectangle (x,y,32,32);
	}
	public Rectangle getBoundsBig() {
		return new Rectangle (x-16,y-16,64,64);
	}
	
	
	
}
