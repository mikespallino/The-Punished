package com.spallino.thepunished.entities;

import org.newdawn.slick.SlickException;

public class Player extends Entity {

	private String name;
	private int health;
	
	public Player(String name, int health, String[][] pathToFiles, int[] duration) throws SlickException {
		super(pathToFiles, duration);
		this.setName(name);
		this.setHealth(health);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

}
