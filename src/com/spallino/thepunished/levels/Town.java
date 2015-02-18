package com.spallino.thepunished.levels;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.tiled.TiledMap;

public class Town {

	private String name;
	private TiledMap[][] map;
	private ArrayList<Sound> songs = new ArrayList<Sound>();
	private Random r = new Random();
	
	public Town(String name, int chunkSizeX, int chunkSizeY, String[] pathToMaps, String[] pathToSongs) throws Exception {
		if(chunkSizeX * chunkSizeY != pathToMaps.length) {
			throw new Exception("Chunk Size must match the number of map files.");
		} else {
			this.setName(name);
			setMap(new TiledMap[chunkSizeY][chunkSizeX]);
			loadMap(pathToMaps);
			for(String str : pathToSongs) {
				songs.add(new Sound(Town.class.getResource(str)));
			}
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
	
	public int getChunkWidth() {
		return map[0].length;
	}
	
	public int getChunkHeight() {
		return map.length;
	}
	
	public Sound getRandonTrack() {
		r.setSeed(System.nanoTime());
		return songs.get(r.nextInt(songs.size()));
	}
	
	public Sound getTrack(int i) {
		return i < songs.size() ? songs.get(i) : null;
	}
	
	public Sound getTrackPlaying() {
		for(Sound s : songs) {
			if(s.playing()) {
				return s;
			}
		}
		return null;
	}
}
