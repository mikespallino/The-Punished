package com.spallino.thepunished.entities;

import java.util.logging.Level;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.spallino.thepunished.levels.Town;
import com.spallino.thepunished.util.Config;
import com.sun.istack.internal.logging.Logger;

public class Entity {

	private Animation sprite, up, right, down, left;
	private float x, y;
	private int chunkX, chunkY;
	private Town location;
	
	public Entity(String[][] pathToFiles, int[] duration) throws SlickException {
		this(pathToFiles, duration, null);
	}
	
	public Entity(String[][] pathToFiles, int[] duration, Town location) throws SlickException {
		this.setLocation(location);
		Image[] movementUp = new Image[pathToFiles[0].length];
		Image[] movementRight = new Image[pathToFiles[1].length];
		Image[] movementDown = new Image[pathToFiles[2].length];
		Image[] movementLeft = new Image[pathToFiles[3].length];
		for(int i = 0; i < pathToFiles.length; i++) {
			for(int j = 0; j < pathToFiles[i].length; j++) {
				switch(i) {
					case 0:
						movementUp[j] = new Image(pathToFiles[i][j]);
						break;
					case 1:
						movementRight[j] = new Image(pathToFiles[i][j]);
						break;
					case 2:
						movementDown[j] = new Image(pathToFiles[i][j]);
						break;
					case 3:
						movementLeft[j] = new Image(pathToFiles[i][j]);
						break;
					default:
						Logger.getLogger(Entity.class).log(Level.SEVERE, null, "Problem with loading entity sprites.");
						break;
				}
			}
		}
		up = new Animation(movementUp, duration, false);
		right = new Animation(movementRight, duration, false);
		down = new Animation(movementDown, duration, false);
		left = new Animation(movementLeft, duration, false);
		sprite = down;
		x = 0f;
		y = 0f;
	}
	
	public Animation getSprite() {
		return sprite;
	}
	
	public TiledMap updateChunk(int width, int height) {
		TiledMap map = location.getChunk(chunkX,chunkY);
		if(x > width) {
			if(chunkX < location.getChunkWidth() - 1) {
				chunkX++;
				x = 0.0f;
				map = location.getChunk(chunkX,chunkY);
			} else {
				x = width;
			}
		} else if(x < 0) {
			if(chunkX > 0) {
				chunkX--;
				x = width;
				map = location.getChunk(chunkX,chunkY);
			} else {
				x = 0.0f;
			}
		} else if(y > height) {
			if(chunkY < location.getChunkHeight() - 1) {
				chunkY++;
				y = 0.0f;
				map = location.getChunk(chunkX,chunkY);
			} else {
				y = height;
			}
		} else if(y < 0) {
			if(chunkY > 0) {
				chunkY--;
				y = height;
				map = location.getChunk(chunkX,chunkY);
			} else {
				y = 0.0f;
			}
		}
		return map;
	}
	
	public void update(int i, Input input) {
		sprite.update(i);
	}
	
	public void changeDirection(Config.DIRECTIONS dir) {
		switch(dir) {
			case NORTH:
				sprite = up;
				break;
			case EAST:
				sprite = right;
				break;
			case SOUTH:
				sprite = down;
				break;
			case WEST:
				sprite = left;
				break;
			default:
				Logger.getLogger(Entity.class).log(Level.SEVERE, null, "Invalid direction supplied.");
				break;
		}
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public void move(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public int getChunkX() {
		return chunkX;
	}

	public void setChunkX(int chunkX) {
		this.chunkX = chunkX;
	}

	public int getChunkY() {
		return chunkY;
	}

	public void setChunkY(int chunkY) {
		this.chunkY = chunkY;
	}

	public Town getLocation() {
		return location;
	}

	public void setLocation(Town location2) {
		this.location = location2;
	}
	
}
