
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet  extends GameObject{

	private Handler handler;
	
	public Bullet(int x, int y, ID id, Handler handler, int mx, int my,SpriteSheet ss) {
		  super(x, y, id,ss);
		  this.handler = handler;
		  int speed = 10;
		  
		  double bulletAngle = Math.toDegrees(Math.atan2(my - y, mx - x));
		  velX = (float)(Math.cos(Math.toRadians(bulletAngle))*speed);
		  velY = (float)(Math.sin(Math.toRadians(bulletAngle ))*speed);
		  Game.audio.getSound("fire0").play();
		 }


	public void tick(){ 
		x+=velX;
		y+=velY;
	
		for(int i=0;i<handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Block) {
				if(getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(this);
					
				}
				
			}
		}
	
	}


	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillOval(x, y, 8, 8);
	}

	
	public Rectangle getBounds() {
		
		return new Rectangle(x,y,8,8);
	}

}

