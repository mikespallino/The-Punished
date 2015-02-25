package com.spallino.thepunished.entities;

import org.newdawn.slick.SlickException;

import com.spallino.thepunished.levels.Town;

public class Enemy extends Entity {

	private int health;
	
	public Enemy(String[][] pathToFiles, int[] duration, int health) throws SlickException {
		super(pathToFiles, duration);
		this.setHealth(health);
	}
	public Enemy(String[][] pathToFiles, int[] duration, Town location, int health) throws SlickException {
		super(pathToFiles, duration, location);
		this.setHealth(health);
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}

}
