import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Create extends GameObject {

	private BufferedImage create_image;
	
	public Create(int x, int y, ID id,SpriteSheet ss) {
		super(x, y, id, ss);
		create_image=ss.grabImage(6, 2, 32, 32);
	}

	
	public void tick() {
	
		
	}


	public void render(Graphics g) {
		g.drawImage(create_image, x, y, null);
	}

	public Rectangle getBounds() {
		
		return new Rectangle(x,y,32,32);
	}

}
