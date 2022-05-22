import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
	public Rectangle cont = new Rectangle(2*Game.WIDTH/3 + 100, 50, 180, 60);
	public Rectangle restart = new Rectangle(2*Game.WIDTH/3 + 100, 150, 180, 60);
	public Rectangle play = new Rectangle(2*Game.WIDTH/3 + 100, 150, 180, 60);
	public Rectangle settings = new Rectangle(2*Game.WIDTH/3 + 100, 250, 180, 60);
	public Rectangle quit = new Rectangle(2*Game.WIDTH/3 + 100, 350, 180, 60);

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Font font0 = new Font("arial", Font.BOLD, 50);
		Font font_death = new Font("arial", Font.BOLD, 100);
		g.setColor(Color.white);
		if(Game.getState() == Game.STATE.MENU && Game.isStart == false) {
			g.setFont(font0);
			g.drawString("Wizard Game", 2*Game.WIDTH/3+20, 100);
		}else if(Game.getState() == Game.STATE.DEATH){
			g.setFont(font_death);
			g.drawString("Game Over", 2*Game.WIDTH/3-50, 100);
		}
		Font font1 = new Font("arial", Font.BOLD, 30);
		g.setFont(font1);
		if(Game.getState() == Game.STATE.STOP && Game.isStart == true) {
			g.drawString("Continue", cont.x + 10, cont.y+ 40);
			g2d.draw(cont);
			g.drawString("New Game", restart.x + 10, restart.y + 40);
			g2d.draw(restart);
			g.drawString("Settings", settings.x + 10, settings.y + 40);
			g2d.draw(settings);
			g.drawString("Quit", quit.x + 10, quit.y + 40);
			g2d.draw(quit);
		}else if (Game.getState() == Game.STATE.SETTINGS){
			if(Game.isSoundOn) {
				g.drawString("Sound Off", play.x + 10, play.y + 40);
			}else {
				g.drawString("Sound On", play.x + 10, play.y + 40);

			}
			g2d.draw(play);
			g.drawString("Difficulty", settings.x + 10, settings.y + 40);
			g.drawString("Back", quit.x + 10, quit.y + 40);
			g2d.draw(settings);
			g2d.draw(quit);
		}else if(Game.getState() == Game.STATE.DIFFICULTY) {
			if(Game.getDifficulty() == Game.DIFFICULTY.EASY) {
				g.drawString("Easy AI", play.x + 10, play.y + 40);
			}else {
				g.drawString("Hard AI", play.x + 10, play.y + 40);

			}
			g.drawString("Back", settings.x + 10, settings.y + 40);
			g2d.draw(play);
			g2d.draw(settings);
		}else if(Game.getState() == Game.STATE.DEATH) {
			g.drawString("Play Again", play.x + 10, play.y + 40);
			g2d.draw(play);
			g.drawString("Settings", settings.x + 10, settings.y + 40);
			g2d.draw(settings);
			g.drawString("Quit", quit.x + 10, quit.y + 40);
			g2d.draw(quit);		
	
		}else {
			g.drawString("Play", play.x + 10, play.y + 40);
			g2d.draw(play);
			g.drawString("Settings", settings.x + 10, settings.y + 40);
			g2d.draw(settings);
			g.drawString("Quit", quit.x + 10, quit.y + 40);
			g2d.draw(quit);
		}

	}
}