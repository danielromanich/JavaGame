package com.game.landscape;

import com.game.util.Position;

public enum TileType {
	
	EMPTY_TILE("tiles/empty_tile"),
	GROUND("tiles/empty_tile", new Position(0, 1), new Position(0, -1), new Position(1, 0), new Position(-1, 0)),
	BLOCK("tiles/full_tile", new Position(0, 1), new Position(0, -1), new Position(1, 0), new Position(-1, 0)),
	PLATFORM("tiles/platform_tile", new Position(0, 1));
	
	private String tileName;
	private Position[] blockingDirections;
	TileType(String tileName, Position...blockingDirections) {
		this.tileName = tileName;
		this.blockingDirections = blockingDirections;
	}
	
	public String getTileName() {
		return this.tileName;
	}
	
	public Position[] getBlockingDirections() {
		return this.blockingDirections;
	}

}
