package com.daniel.adventure.graphic;

import java.awt.Color;
import java.awt.Graphics;
import com.daniel.adventure.GameCore;
import com.daniel.adventure.landscape.Tile;
import com.daniel.adventure.res.ResourceLoader;
import com.daniel.adventure.util.Data;
import com.daniel.adventure.util.Position;

public class GameRenderer {
	
	private GameCore game;
	
	public GameRenderer(GameCore game) {
		this.game = game;
	}

	public void renderGame(Graphics g) {
		g.drawImage(ResourceLoader.getImage("backgrounds/normal"), Data.WIDTH / 2 - game.getGame().getCamera().getX(), Data.HEIGHT - game.getGame().getCamera().getY(), null);
		
		for (int x = 0; x < game.getGame().getLandscape().getTiles().length; x++) {
			for (int y = 0; y < game.getGame().getLandscape().getTiles()[x].length; y++) {
				Tile t = game.getGame().getLandscape().getTiles()[x][y];
				if (game.getGame().getLandscape().withinFOV(t)) {
					g.drawImage(ResourceLoader.getImage(t.getTileType().getTileName()), game.getGame().getCamera().getLocalX(t.getPosition()), game.getGame().getCamera().getLocalY(t.getPosition()), null);
					if (game.getSettings().isDebuggingTiles()) {
						g.setColor(Color.white);
						g.drawRect((x * 64) + (Data.WIDTH / 2 - game.getGame().getCamera().getX()), (y * 64) + (Data.HEIGHT - game.getGame().getCamera().getY()), 64, 64);
					}
					if (t.isBlocking(new Position(0, -1))) {
						g.setColor(new Color(0, 0, 0, 150));
						g.fillRect((x * 64) + (Data.WIDTH / 2 - game.getGame().getCamera().getX()), (y * 64) + (Data.HEIGHT - game.getGame().getCamera().getY()), 64, 64);
						g.setColor(new Color(255, 255, 255, 255));
					}
				}
			}
		}
		
		//Renders all entities in the landscape
		game.getGame().getGameObjects().renderObjects(g);
		game.getGame().getEntities().renderEntities(g);
		
		//Renders the player
		game.getGame().getPlayer().drawEntity(g, game.getGame().getCamera());
		if (game.getSettings().isDebuggingPlayer())
			g.drawRect(game.getGame().getPlayer().getBoundingBox().x, game.getGame().getPlayer().getBoundingBox().y, game.getGame().getPlayer().getBoundingBox().width, game.getGame().getPlayer().getBoundingBox().height);
		
		if (game.getSettings().isDebuggingPlayer()) {
			g.setColor(Color.WHITE);
			g.drawString("Player position: " + game.getGame().getPlayer().getPosition(), 10, 10);
			g.drawString("Player speedX: " + game.getGame().getPlayer().getVelocityX(), 10, 25);
			g.drawString("Player speedY: " + game.getGame().getPlayer().getVelocityY(), 10, 40);
			g.drawString("Player smallX: " + game.getGame().getPlayer().getSmallX(), 10, 55);
			g.drawString("Player smallY: " + game.getGame().getPlayer().getSmallY(), 10, 70);
			g.drawString("Player top x: " + game.getGame().getPlayer().getBoundingBox().x, 10, 85);
			g.drawString("Player local y: " + game.getGame().getPlayer().getBoundingBox().y, 10, 100);
		}
	}
	
}
