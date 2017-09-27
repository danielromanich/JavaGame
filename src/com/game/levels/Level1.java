package com.game.levels;

import com.game.GameCore;
import com.game.Level;
import com.game.entity.collisionentities.MovingUpPlatform;
import com.game.entity.entities.FlyingTurtle;
import com.game.entity.entities.Ghost;
import com.game.entity.objects.MysteryBox;
import com.game.landscape.Tile;
import com.game.landscape.TileType;
import com.game.util.Data;
import com.game.util.Position;

public class Level1 extends Level {
	
	private Tile[][] tiles = new Tile[Data.LANDSCAPE_TILE_WIDTH][Data.LANDSCAPE_TILE_HEIGHT];

	public Level1(GameCore gameCore) {
		super(gameCore);
		
		game.getPlayer().setPosition(new Position(1, 17));
		
		for (int x = 0; x < Data.LANDSCAPE_TILE_WIDTH; x++) {
			for (int y = 0; y < Data.LANDSCAPE_TILE_HEIGHT; y++) {
				if (x == 14 && y > 10)
					tiles[x][y] = new Tile(TileType.GROUND, new Position(x, y));
				else if (y >= 18)
					tiles[x][y] = new Tile(TileType.GROUND, new Position(x, y));
				else
					tiles[x][y] = new Tile(TileType.EMPTY_TILE, new Position(x, y));
			}
		}
		
		//Test level, level design will be handled from text files in the future
		for (int x = 20; x < 40; x++) {
			tiles[x][14] = new Tile(TileType.PLATFORM, new Position(x, 14));
			game.getGameObjects().addObject(new MysteryBox(game, 16, 16, new Position(x, 11)));
		}
		
		game.getEntities().addEntity(new Ghost(new Position(22, 14), game, game.getLandscape().getCollision()));
		
		game.getLandscape().setTiles(tiles);
		game.getGameObjects().addObject(new MysteryBox(game, 0, 16, new Position(4, 15)));
		game.getGameObjects().addObject(new MysteryBox(game, 32, 16, new Position(4, 15)));
		game.getGameObjects().addObject(new MysteryBox(game, 0, 16, new Position(5, 15)));
		game.getGameObjects().addObject(new MysteryBox(game, 32, 16, new Position(5, 15)));
		
		game.getEntities().addEntity(new FlyingTurtle(game, game.getLandscape().getCollision(), new Position(3, 13)));
		game.getEntities().addEntity(new MovingUpPlatform(game, game.getLandscape().getCollision(), new Position(10, 18), 6, 2));
		game.start();
	}	
	
	@Override
	public Level getResetLevel() {
		return new Level1(gameCore);
	}

}
