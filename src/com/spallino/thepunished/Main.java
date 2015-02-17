package com.spallino.thepunished;

import java.util.logging.Level;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.tiled.TiledMap;

import com.spallino.thepunished.entities.Player;
import com.spallino.thepunished.levels.Town;
import com.sun.istack.internal.logging.Logger;


public class Main extends BasicGame {

	private TiledMap map;
	private Town lurok;
	private Player player;
	private Sound track;
	
	public Main(String name) {
		super(name);
	}
	
	public void init(GameContainer gc) throws SlickException {
		try {
			lurok = new Town("Lurok", 2, 3, new String[] {"/res/map.tmx", "/res/map2.tmx", "/res/map3.tmx", 
														  "/res/map4.tmx", "/res/map5.tmx", "/res/map6.tmx"});
			player = new Player("Bob", 100, new String[][] {{"/res/up1.png", "/res/up2.png", "/res/up3.png"}, 
															{"/res/right1.png", "/res/right2.png", "/res/right3.png"}, 
															{"/res/down1.png", "/res/down2.png", "/res/down3.png"}, 
															{"/res/left1.png", "/res/left2.png", "/res/left3.png"}}, 
															new int[] {300,300,300}, lurok);
		} catch (Exception e) {
			Logger.getLogger(Main.class).log(Level.SEVERE, null, e);
			System.exit(-1);
		}
		track = new Sound(Main.class.getResource("/EM2 Final.wav"));
		track.play();
		map = player.getLocation().getChunk(0, 0);
	}
	
	public void update(GameContainer gc, int i) throws SlickException {
		player.update(i, gc.getInput());
		map = player.updateChunk(gc.getWidth(), gc.getHeight());
		if(!track.playing()) {
			track.play();
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