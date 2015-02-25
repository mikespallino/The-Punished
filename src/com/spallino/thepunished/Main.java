package com.spallino.thepunished;

import java.util.logging.Level;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.spallino.thepunished.entities.Enemy;
import com.spallino.thepunished.entities.Player;
import com.spallino.thepunished.levels.Town;
import com.spallino.thepunished.util.Config;
import com.sun.istack.internal.logging.Logger;


public class Main extends BasicGame {

	private TiledMap map;
	private Town lurok;
	private Player player;
	private Enemy enemy;
	
	public Main(String name) {
		super(name);
	}
	
	public void init(GameContainer gc) throws SlickException {
		try {
			lurok = new Town("Lurok", 2, 3, Config.LUROK_TEXTURES, Config.LUROK_SOUNDTRAK);
			player = new Player("Bob", 100, Config.PLAYER_SPRITES, Config.ANIMATION_DURATION, lurok);
			enemy = new Enemy(Config.PLAYER_SPRITES, Config.ANIMATION_DURATION, lurok, 10);
			enemy.setX(30);
			enemy.setY(30);
			enemy.setChunkX(0);
			enemy.setChunkY(0);
		} catch (Exception e) {
			Logger.getLogger(Main.class).log(Level.SEVERE, null, e);
			System.exit(-1);
		}
		map = player.getLocation().getChunk(0, 0);
	}
	
	public void update(GameContainer gc, int i) throws SlickException {
		player.update(i, gc.getInput());
		map = player.updateChunk(gc.getWidth(), gc.getHeight());
		if(player.getLocation().getTrackPlaying() == null) {
			player.getLocation().getRandonTrack().play();
		}
		if(enemy.getX() < 200 && enemy.getY() < 200) {
			enemy.move((float) (enemy.getX()+0.2), (float) (enemy.getY()+0.2));
		}
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		map.render(0,0);
		player.getSprite().draw(player.getX(), player.getY());
		enemy.getSprite().draw(enemy.getX(), enemy.getY());
	}
	
	public static void main(String[] args) {
		AppGameContainer appgc;
		try {
			appgc = new AppGameContainer(new Main("The Punished"));
			appgc.setDisplayMode(640, 480, false);
			appgc.start();
		} catch (SlickException ex) {
			Logger.getLogger(Main.class).log(Level.SEVERE, null, ex);
		}
	}
	
}