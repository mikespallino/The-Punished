package com.spallino.thepunished.levels;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Town {

	private String name;
	private TiledMap[][] map;
	
	public Town(String name, int chunkSizeX, int chunkSizeY, String[] pathToFiles) throws Exception {
		if(chunkSizeX * chunkSizeY != pathToFiles.length) {
			throw new Exception("Chunk Size must match the number of map files.");
		} else {
			this.setName(name);
			setMap(new TiledMap[chunkSizeY][chunkSizeX]);
			loadMap(pathToFiles);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TiledMap[][] getMap() {
		return map;
	}

	public void setMap(TiledMap[][] map) {
		this.map = map;
	}
	
	private void loadMap(String[] pathToFiles) throws SlickException {
		int i = 0;
		for(int y = 0; y < map.length; y++) {
			for(int x = 0; x < map[y].length; x++) {
				if(i < pathToFiles.length) {
					map[y][x] = new TiledMap(pathToFiles[i]);
				}
				i++;
			}
		}
	}
	
	public TiledMap getChunk(int x, int y) {
		return map[y][x];
	}
	
}
