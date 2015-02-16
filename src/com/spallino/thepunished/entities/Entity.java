package com.spallino.thepunished.entities;

import java.util.logging.Level;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.spallino.thepunished.util.Config;
import com.sun.istack.internal.logging.Logger;

public class Entity {

	private Animation sprite, up, right, down, left;
	private float x, y;
	
	public Entity(String[][] pathToFiles, int[] duration) throws SlickException {
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
	
	public void update(int i) {
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
	
	public void incrementX(float amount) {
		x += amount;
	}
	
	public void  decrementX(float amount) {
		x -= amount;
	}
	
	public void incrementY(float amount) {
		y += amount;
	}
	
	public void  decrementY(float amount) {
		y -= amount;
	}
	
}
