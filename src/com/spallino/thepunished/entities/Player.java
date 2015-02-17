package com.spallino.thepunished.entities;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.spallino.thepunished.levels.Town;
import com.spallino.thepunished.util.Config;

public class Player extends Entity {

	private String name;
	private int health;
	
	public Player(String name, int health, String[][] pathToFiles, int[] duration) throws SlickException {
		super(pathToFiles, duration);
		this.setName(name);
		this.setHealth(health);
	}
	public Player(String name, int health, String[][] pathToFiles, int[] duration, Town location) throws SlickException {
		super(pathToFiles, duration, location);
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
	
	@Override
	public void update(int i, Input input) {
		if (input.isKeyDown(Input.KEY_UP)) {
			changeDirection(Config.DIRECTIONS.NORTH);
			setY(getY() - (i * 0.1f));
			getSprite().update(i);
		}
		else if (input.isKeyDown(Input.KEY_DOWN)) {
			changeDirection(Config.DIRECTIONS.SOUTH);
			setY(getY() + (i * 0.1f));
			getSprite().update(i);
		}
		else if (input.isKeyDown(Input.KEY_LEFT)) {
			changeDirection(Config.DIRECTIONS.WEST);
			setX(getX() - (i * 0.1f));
			getSprite().update(i);
		}
		else if (input.isKeyDown(Input.KEY_RIGHT)) {
			changeDirection(Config.DIRECTIONS.EAST);
			setX(getX() + (i * 0.1f));
			getSprite().update(i);
		}
	}

}
