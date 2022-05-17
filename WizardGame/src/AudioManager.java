import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioManager {
	
	public Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public Map<String, Music> musicMap = new HashMap<String, Music>();
	
	
	public void load() throws SlickException {

		musicMap.put("music", new Music("res/menu.ogg"));
		soundMap.put("fire0", new Sound("res/fire-magic.ogg"));
		soundMap.put("goblin_death", new Sound("res/goblin-death.ogg"));

	}
	
	
	public Music getMusic(String key) {
		return musicMap.get(key);
	}
	
	public Sound getSound(String key) {
		return soundMap.get(key);
	}
	

}
