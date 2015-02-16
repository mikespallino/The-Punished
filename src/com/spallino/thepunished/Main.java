package com.spallino.thepunished;

import java.util.logging.Level;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.spallino.thepunished.entities.Player;
import com.spallino.thepunished.levels.Town;
import com.spallino.thepunished.util.Config;
import com.sun.istack.internal.logging.Logger;


public class Main extends BasicGame {

	private TiledMap map;
	private Town lurok;
	private Player player;
	private int chunkX = 0, chunkY = 0;
	
	public Main(String name) {
		super(name);
	}
	
	public void init(GameContainer gc) throws SlickException {
		player = new Player("Bob", 100, new String[][] {{"/res/up1.png", "/res/up2.png", "/res/up3.png"}, {"/res/right1.png", "/res/right2.png", "/res/right3.png"}, {"/res/down1.png", "/res/down2.png", "/res/down3.png"}, {"/res/left1.png", "/res/left2.png", "/res/left3.png"}}, new int[] {300,300,300});
		try {
			lurok = new Town("Lurok", 2, 3, new String[] {"/res/map.tmx", "/res/map2.tmx", "/res/map3.tmx", "/res/map4.tmx", "/res/map5.tmx", "/res/map6.tmx"});
		} catch (Exception e) {
			Logger.getLogger(Main.class).log(Level.SEVERE, null, e);
			System.exit(-1);
		}
		map = lurok.getChunk(0, 0);
	}
	
	public void update(GameContainer gc, int i) throws SlickException {
		Input input = gc.getInput();
		if (input.isKeyDown(Input.KEY_UP)) {
			player.changeDirection(Config.DIRECTIONS.NORTH);
			player.update(i);
			player.decrementY(i * 0.1f);
		}
		else if (input.isKeyDown(Input.KEY_DOWN)) {
			player.changeDirection(Config.DIRECTIONS.SOUTH);
			player.update(i);
			player.incrementY(i * 0.1f);
		}
		else if (input.isKeyDown(Input.KEY_LEFT)) {
			player.changeDirection(Config.DIRECTIONS.WEST);
			player.update(i);
			player.decrementX(i * 0.1f);
		}
		else if (input.isKeyDown(Input.KEY_RIGHT)) {
			player.changeDirection(Config.DIRECTIONS.EAST);
			player.update(i);
			player.incrementX(i * 0.1f);
		}
		
		//Position stuff
		float x = player.getX();
		float y = player.getY();
		if(x > gc.getWidth()) {
			if(chunkX < 1) {
				chunkX++;
				
				player.setX(0.0f);
				map = lurok.getChunk(chunkX,chunkY);
			} else {
				player.setX(gc.getWidth());
			}
		} else if(x < 0) {
			if(chunkX > 0) {
				chunkX--;
				player.setX(gc.getWidth());
				map = lurok.getChunk(chunkX,chunkY);
			} else {
				player.setX(0.0f);
			}
		} else if(y > gc.getHeight()) {
			if(chunkY < 2) {
				chunkY++;
				player.setY(0.0f);
				map = lurok.getChunk(chunkX,chunkY);
			} else {
				player.setY(gc.getHeight());
			}
		} else if(y < 0) {
			if(chunkY > 0) {
				chunkY--;
				player.setY(gc.getHeight());
				map = lurok.getChunk(chunkX,chunkY);
			} else {
				player.setY(0.0f);
			}
		}
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		map.render(0,0);
		player.getSprite().draw(player.getX(), player.getY());
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