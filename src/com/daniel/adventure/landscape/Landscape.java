package com.daniel.adventure.landscape;

import com.daniel.adventure.Game;
import com.daniel.adventure.entity.Entity;
import com.daniel.adventure.util.Data;
import com.daniel.adventure.util.Position;

public class Landscape {
	
	public static int LANDSCAPE_WIDTH = Data.TILE_WIDTH * Data.LANDSCAPE_TILE_WIDTH, LANDSCAPE_HEIGHT = Data.TILE_HEIGHT * Data.LANDSCAPE_TILE_HEIGHT;
	private Tile[][] tiles = new Tile[Data.LANDSCAPE_TILE_WIDTH][Data.LANDSCAPE_TILE_HEIGHT];
	private Camera camera = new Camera(Data.WIDTH / 2, Data.LANDSCAPE_TILE_HEIGHT * Data.TILE_HEIGHT);
	
	public Collision collosion;
	public Game game;
	
	public Landscape(Game game) {
		this.game = game;
		//for test purposes
		this.collosion = new Collision(this);
	}
	
	public Collision getCollision() {
		return this.collosion;
	}
	
	public Camera getCamera() {
		return this.camera;
	}
	
	public boolean withinFOV(Tile tile) {
		return camera.contains(tile.getPosition());
	}
	
	public boolean withinFOV(Position tile) {
		return camera.contains(tile);
	}
	
	public boolean withinFOV(Entity e) {
		for (int x = 0; x < e.getSizeX(); x++) {
			for (int y = 0; y < e.getSizeY(); y++) {
				if (withinFOV(new Position(e.getPosition().getX() + x, e.getPosition().getY() + y)))
					return true;
			}
		}
		return false;
	}
	
	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}
	
	public Tile[][] getTiles() {
		return this.tiles;
	}

}
