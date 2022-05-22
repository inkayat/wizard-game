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
		int x = e.getX();
		int y = e.getY();
		if(Game.getState() == Game.STATE.MENU && Game.isStart == false) {
			if((x >= 2*Game.WIDTH / 3 + 100 && x <= 2*Game.WIDTH / 3 + 280) && (y >= 150 && y <= 210)) {
				Game.setState(Game.STATE.GAME);
			}else if((x >= 2*Game.WIDTH / 3 + 100 && x <= 2*Game.WIDTH / 3 + 280) && (y >= 250 && y <= 310)) {
				Game.setState(Game.STATE.SETTINGS);
			}else if((x >= 2*Game.WIDTH / 3 + 100 && x <= 2*Game.WIDTH / 3 + 280) && (y >= 350 && y <= 410)) {
				System.exit(1);
			}
		}else if(Game.getState() == Game.STATE.STOP && Game.isStart == true) {
			if ((x >= 2*Game.WIDTH / 3 + 100 && x <= 2*Game.WIDTH / 3 + 280) && (y >= 50 && y <= 110)) {
				Game.setState(Game.STATE.GAME);
				Game.setPrev_state(Game.STATE.MENU);
			}else if((x >= 2*Game.WIDTH / 3 + 100 && x <= 2*Game.WIDTH / 3 + 280) && (y >= 150 && y <= 210)) {
				Game.setState(Game.STATE.RESTART);
				Game.setPrev_state(Game.STATE.MENU);
			}else if((x >= 2*Game.WIDTH / 3 + 100 && x <= 2*Game.WIDTH / 3 + 280) && (y >= 250 && y <= 310)) {
				Game.setState(Game.STATE.SETTINGS);
			}else if((x >= 2*Game.WIDTH / 3 + 100 && x <= 2*Game.WIDTH / 3 + 280) && (y >= 350 && y <= 410)) {
				System.exit(1);
			}
		}else if(Game.getState() == Game.STATE.SETTINGS) {
			if((x >= 2*Game.WIDTH / 3 + 100 && x <= 2*Game.WIDTH / 3 + 280) && (y >= 350 && y <= 410)) {
				if(Game.getPrev_state() == Game.STATE.STOP) {
					Game.setState(Game.STATE.STOP);
				}else {
					Game.setState(Game.STATE.MENU);
				}
			}else if((x >= 2*Game.WIDTH / 3 + 100 && x <= 2*Game.WIDTH / 3 + 280) && (y >= 150 && y <= 210)){
				if(Game.isSoundOn) {
					Game.audio.getMusic("music").pause();
					Game.isSoundOn = false;
				}else {
					Game.audio.getMusic("music").resume();
					Game.isSoundOn = true;
				}
			}else if((x >= 2*Game.WIDTH / 3 + 100 && x <= 2*Game.WIDTH / 3 + 280) && (y >= 250 && y <= 310)){
				Game.setState(Game.STATE.DIFFICULTY);
			}
		}else if(Game.getState() == Game.STATE.DIFFICULTY) {
			if((x >= 2*Game.WIDTH / 3 + 100 && x <= 2*Game.WIDTH / 3 + 280) && (y >= 150 && y <= 210)){
				if(Game.getDifficulty() == Game.DIFFICULTY.EASY) {
					Game.setDifficulty(Game.DIFFICULTY.HARD);
				}else {
					Game.setDifficulty(Game.DIFFICULTY.EASY);
				}
			}else if((x >= 2*Game.WIDTH / 3 + 100 && x <= 2*Game.WIDTH / 3 + 280) && (y >= 250 && y <= 310)) {
				Game.setState(Game.STATE.SETTINGS);
			}
		}else if(Game.getState() == Game.STATE.DEATH) {
			if((x >= 2*Game.WIDTH / 3 + 100 && x <= 2*Game.WIDTH / 3 + 280) && (y >= 150 && y <= 210)) {
				Game.setState(Game.STATE.RESTART);
			}else if((x >= 2*Game.WIDTH / 3 + 100 && x <= 2*Game.WIDTH / 3 + 280) && (y >= 250 && y <= 310)) {
				Game.setState(Game.STATE.SETTINGS);
			}else if((x >= 2*Game.WIDTH / 3 + 100 && x <= 2*Game.WIDTH / 3 + 280) && (y >= 350 && y <= 410)) {
				System.exit(1);
			}
		
		}else if(Game.getState() != Game.STATE.STOP && Game.getPrev_state() != Game.STATE.STOP) {
			for(int i=0;i<handler.object.size();i++) {
			
				GameObject tempObject = handler.object.get(i);
			
				if(tempObject.getId() == ID.Player && game.ammo>=1) {
					handler.addObject(new Bullet(tempObject.getX()+16, tempObject.getY()+24,ID.Bullet,handler,mx,my,ss));
					game.ammo--;
			
					}
				
				}
		
			}
	
		}
	
}
