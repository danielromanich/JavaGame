package com.game.landscape;

import java.util.ArrayList;

import com.game.util.Position;

public class Tile {
	
	private TileType tileType;
	private Position position;
	private final ArrayList<Position> blockingDirections = new ArrayList<Position>();
	
	public Tile(TileType tileType, Position position) {
		this.tileType = tileType;
		this.position = position;
		for (Position b : tileType.getBlockingDirections())
			blockingDirections.add(b);
	}
	
	public TileType getTileType() {
		return this.tileType;
	}
	
	public boolean isBlocking(Position direction) {
		return blockingDirections.contains(direction);
	}
	
	public Position getPosition() {
		return this.position;
	}

}
