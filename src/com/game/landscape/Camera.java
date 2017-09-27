package com.game.landscape;

import java.awt.Rectangle;

import com.game.entity.Entity;
import com.game.util.Data;
import com.game.util.Position;

public class Camera {
	
	private int x, y; //True x and y coordinates related to the game screen itself (and not the ingame coordinates)
	public Camera(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x, Entity player) {
		if (player.getPosition().getX() * Data.TILE_WIDTH + player.getSmallX() > Data.TILE_WIDTH * Data.LANDSCAPE_TILE_WIDTH - Data.WIDTH / 2) {
			this.x = Data.TILE_WIDTH * Data.LANDSCAPE_TILE_WIDTH - Data.WIDTH / 2;
		} else if (player.getPosition().getX() * Data.TILE_WIDTH + player.getSmallX() <= Data.WIDTH / 2) {
			this.x = Data.WIDTH / 2;
		} else {
			this.x = x;
		}
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y, Entity player) {
		this.y = y;
	}
	
	public int getLocalX(Position p) {
		return p.getX() * Data.TILE_WIDTH + Data.WIDTH / 2 - getX();
	}
	
	public int getLocalY(Position p) {
		return p.getY() * Data.TILE_HEIGHT + Data.HEIGHT - getY();
	}
	
	public int getLocalX(Entity e) {
		return e.getSmallX() + (e.getPosition().getX() * Data.TILE_WIDTH) + (Data.WIDTH / 2 - getX());
	}
	
	public int getLocalY(Entity e) {
		return e.getSmallY() + ((e.getPosition().getY() - (e.getSizeY() - 1)) * Data.TILE_HEIGHT) + (Data.HEIGHT - getY());
	}
	
	public Rectangle getCameraBounds() {
		return new Rectangle(getX() - Data.WIDTH / 2, getY() - Data.HEIGHT, Data.WIDTH, Data.HEIGHT);
	}
	
	public boolean contains(Position position) {
		final Rectangle r = getCameraBounds();
		return r.intersects(new Rectangle(position.getX() * Data.TILE_WIDTH, position.getY() * Data.TILE_HEIGHT, Data.TILE_WIDTH, Data.TILE_HEIGHT));
	}

}
