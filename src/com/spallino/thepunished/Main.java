package com.spallino.thepunished;

import java.util.logging.Level;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.spallino.thepunished.entities.Player;
import com.spallino.thepunished.levels.Town;
import com.sun.istack.internal.logging.Logger;


public class Main extends BasicGame {

	private TiledMap map;
	private Town lurok;
	private Player player;
	
	public Main(String name) {
		super(name);
	}
	
	public void init(GameContainer gc) throws SlickException {
		try {
			lurok = new Town("Lurok", 2, 3, new String[] {"/res/maps/lurok/map.tmx", "/res/maps/lurok/map2.tmx", "/res/maps/lurok/map3.tmx", 
														  "/res/maps/lurok/map4.tmx", "/res/maps/lurok/map5.tmx", "/res/maps/lurok/map6.tmx"}
										  , new String[] {"/sounds/EM2 Final.wav", "/sounds/The Harbinger (2).wav"});
			player = new Player("Bob", 100, new String[][] {{"/res/sprites/player/up1.png", "/res/sprites/player/up2.png", "/res/sprites/player/up3.png"}, 
															{"/res/sprites/player/right1.png", "/res/sprites/player/right2.png", "/res/sprites/player/right3.png"}, 
															{"/res/sprites/player/down1.png", "/res/sprites/player/down2.png", "/res/sprites/player/down3.png"}, 
															{"/res/sprites/player/left1.png", "/res/sprites/player/left2.png", "/res/sprites/player/left3.png"}}, 
															new int[] {300,300,300}, lurok);
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
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		map.render(0,0);
		player.getSprite().draw(player.getX(), player.getY());
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