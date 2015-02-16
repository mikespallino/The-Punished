import java.util.logging.Level;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.sun.istack.internal.logging.Logger;


public class Main extends BasicGame {

	private TiledMap map;
	private TiledMap[][] maps = new TiledMap[3][2];
	private Animation sprite, up, right, down, left;
	private float x = 34f, y = 34f;
	private int chunkX = 0, chunkY = 0;
	
	public Main(String name) {
		super(name);
	}
	
	public void init(GameContainer gc) throws SlickException {
		final TiledMap m1 = new TiledMap("/res/map.tmx");
		final TiledMap m2 = new TiledMap("/res/map2.tmx");
		final TiledMap m3 = new TiledMap("/res/map3.tmx");
		final TiledMap m4 = new TiledMap("/res/map4.tmx");
		final TiledMap m5 = new TiledMap("/res/map5.tmx");
		final TiledMap m6 = new TiledMap("/res/map6.tmx");
		maps[0][0] = m1;
		maps[0][1] = m2;
		maps[1][0] = m3;
		maps[1][1] = m4;
		maps[2][0] = m5;
		maps[2][1] = m6;
		Image[] movementUp = {new Image("/res/up1.png"), new Image("/res/up2.png"), new Image("/res/up3.png")};
		Image[] movementRight = {new Image("/res/right1.png"), new Image("/res/right2.png"), new Image("/res/right3.png")};
		Image[] movementDown = {new Image("/res/down1.png"), new Image("/res/down2.png"), new Image("/res/down3.png")};
		Image[] movementLeft = {new Image("/res/left1.png"), new Image("/res/left2.png"), new Image("/res/left3.png")};
		int[] duration = {300, 300, 300};
		up = new Animation(movementUp, duration, false);
		right = new Animation(movementRight, duration, false);
		down = new Animation(movementDown, duration, false);
		left = new Animation(movementLeft, duration, false);
		sprite = down;
		map = maps[0][0];
	}
	
	public void update(GameContainer gc, int i) throws SlickException {
		Input input = gc.getInput();
		if (input.isKeyDown(Input.KEY_UP)) {
		    sprite = up;
		    sprite.update(i);
		    // The lower the delta the slowest the sprite will animate.
		    y -= i * 0.1f;
		}
		else if (input.isKeyDown(Input.KEY_DOWN)) {
		    sprite = down;
		    sprite.update(i);
		    y += i * 0.1f;
		}
		else if (input.isKeyDown(Input.KEY_LEFT)) {
		    sprite = left;
		    sprite.update(i);
		    x -= i * 0.1f;
		}
		else if (input.isKeyDown(Input.KEY_RIGHT)) {
		    sprite = right;
		    sprite.update(i);
		    x += i * 0.1f;
		}
		if(x > gc.getWidth()) {
			if(chunkX < 1) {
				chunkX++;
				x = 0;
				map = maps[chunkY][chunkX];
			} else {
				x = gc.getWidth();
			}
		} else if(x < 0) {
			if(chunkX > 0) {
				chunkX--;
				x = gc.getWidth();
				map = maps[chunkY][chunkX];
			} else {
				x = 0;
			}
		} else if(y > gc.getHeight()) {
			if(chunkY < 2) {
				chunkY++;
				y = 0;
				map = maps[chunkY][chunkX];
			} else {
				y = gc.getHeight();
			}
		} else if(y < 0) {
			if(chunkY > -1) {
				chunkY--;
				y = gc.getHeight();
				map = maps[chunkY][chunkX];
			} else {
				y = 0;
			}
		}
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		map.render(0,0);
		sprite.draw(x,y);
	}
	
	public static void main(String[] args) {
		try {
			AppGameContainer appgc = new AppGameContainer(new Main("The Punished"));
			appgc.setDisplayMode(640, 480, false);
			appgc.start();
		} catch (SlickException ex) {
			Logger.getLogger(Main.class).log(Level.SEVERE, null, ex);
		}
	}
	
}
