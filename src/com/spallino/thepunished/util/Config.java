package com.spallino.thepunished.util;

public class Config {

	public static enum DIRECTIONS {NORTH, EAST, SOUTH, WEST};
	public static String[] LUROK_TEXTURES = {"/res/maps/lurok/map.tmx", "/res/maps/lurok/map2.tmx", "/res/maps/lurok/map3.tmx", "/res/maps/lurok/map4.tmx", "/res/maps/lurok/map5.tmx", "/res/maps/lurok/map6.tmx"};
	public static String[] LUROK_SOUNDTRAK = {"/sounds/EM2_Final.wav", "/sounds/The_Harbinger_(2).wav"};
	public static String[][] PLAYER_SPRITES = {{"/res/sprites/player/up1.png", "/res/sprites/player/up2.png", "/res/sprites/player/up3.png"}, 
												{"/res/sprites/player/right1.png", "/res/sprites/player/right2.png", "/res/sprites/player/right3.png"}, 
												{"/res/sprites/player/down1.png", "/res/sprites/player/down2.png", "/res/sprites/player/down3.png"}, 
												{"/res/sprites/player/left1.png", "/res/sprites/player/left2.png", "/res/sprites/player/left3.png"}};
	public static int[] ANIMATION_DURATION = {300,300,300};
}
