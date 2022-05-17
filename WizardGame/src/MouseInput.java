import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter{

	private Handler handler;
	private Camera camera;
	private Game game;
	private SpriteSheet ss;
	
	public MouseInput(Handler handler,Camera camera,Game game,SpriteSheet ss) {
		this.handler=handler;
		this.camera=camera;
		this.game=game;
		this.ss=ss;
		}
	
	public void  mousePressed (MouseEvent e) {
		
		int mx =(int) (e.getX() + camera.getX());
		int my =(int) (e.getY() + camera.getY());
	
	
		if(Game.getState() == Game.STATE.MENU && Game.isStart == false) {
			if((mx >= 2*Game.WIDTH / 3 + 100 && mx <= 2*Game.WIDTH / 3 + 250) && (my >= 150 && my <= 210)) {
				Game.setState(Game.STATE.GAME);
			}else if((mx >= 2*Game.WIDTH / 3 + 100 && mx <= 2*Game.WIDTH / 3 + 250) && (my >= 250 && my <= 310)) {
				Game.setState(Game.STATE.SETTINGS);
			}else if((mx >= 2*Game.WIDTH / 3 + 100 && mx <= 2*Game.WIDTH / 3 + 250) && (my >= 350 && my <= 410)) {
				System.exit(1);
			}
		}else if(Game.getState() == Game.STATE.MENU && Game.isStart == true) {
			if((mx >= 2*Game.WIDTH / 3 + 100 && mx <= 2*Game.WIDTH / 3 + 250) && (my >= 150 && my <= 210)) {
				Game.setState(Game.STATE.GAME);
			}else if((mx >= 2*Game.WIDTH / 3 + 100 && mx <= 2*Game.WIDTH / 3 + 250) && (my >= 250 && my <= 310)) {
				Game.setState(Game.STATE.SETTINGS);
			}else if((mx >= 2*Game.WIDTH / 3 + 100 && mx <= 2*Game.WIDTH / 3 + 250) && (my >= 350 && my <= 410)) {
				System.exit(1);
			}
		}else if(Game.getState() == Game.STATE.SETTINGS) {
			if((mx >= 2*Game.WIDTH / 3 + 100 && mx <= 2*Game.WIDTH / 3 + 250) && (my >= 350 && my <= 410)) {
				Game.setState(Game.STATE.MENU);
			}else if((mx >= 2*Game.WIDTH / 3 + 100 && mx <= 2*Game.WIDTH / 3 + 250) && (my >= 150 && my <= 210)){
				if(Game.isSoundOn) {
					Game.audio.getMusic("music").pause();
					Game.isSoundOn = false;
				}else {
					Game.audio.getMusic("music").resume();
					Game.isSoundOn = true;
				}
			}else if((mx >= 2*Game.WIDTH / 3 + 100 && mx <= 2*Game.WIDTH / 3 + 250) && (my >= 250 && my <= 310)){
				Game.setState(Game.STATE.DIFFICULTY);
			}
		}else if(Game.getState() == Game.STATE.DIFFICULTY) {
			if((mx >= 2*Game.WIDTH / 3 + 100 && mx <= 2*Game.WIDTH / 3 + 250) && (my >= 150 && my <= 210)){
				if(Game.getDifficulty() == Game.DIFFICULTY.EASY) {
					Game.setDifficulty(Game.DIFFICULTY.HARD);
				}else {
					Game.setDifficulty(Game.DIFFICULTY.EASY);
				}
		}
		}
		
		for(int i=0;i<handler.object.size();i++) {
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player && game.ammo>=1) {
				
				handler.addObject(new Bullet(tempObject.getX()+16, tempObject.getY()+24,ID.Bullet,handler,mx,my,ss));
				game.ammo--;
			
			}
			
		}
		
	}
	
	
	
}
