package com.game.landscape;

import java.awt.Rectangle;

import com.game.entity.GameObject;
import com.game.entity.PhysicsEntity;
import com.game.util.Data;
import com.game.util.Position;

public class Collision {
	
	private Landscape landscape;
	
	public Collision(Landscape landscape) {
		this.landscape = landscape;
	}
	
	public boolean isCollidingX(PhysicsEntity entity, int dir) {
		if (getObjectCollidingX(entity, dir) != null)
			return true;
		else if (entity.getPosition().getX() + dir <= 0 || entity.getPosition().getX() + dir >= Data.LANDSCAPE_TILE_WIDTH - 1)
			return true;
		else {
			for (int i = 0; i < entity.getSizeY(); i++) {
				Tile t = landscape.getTiles()[entity.getPosition().getX() + dir][entity.getPosition().getY() - i];
				Rectangle box = new Rectangle(landscape.getCamera().getLocalX(t.getPosition()) - 1, landscape.getCamera().getLocalY(t.getPosition()), Data.TILE_WIDTH + 1, Data.TILE_HEIGHT);
				if (t.isBlocking(new Position(dir, 0)) && box.intersects(entity.getBoundingBox()))
					return true;
			}
			Tile t = landscape.getTiles()[entity.getPosition().getX() + dir][entity.getPosition().getY() - (entity.getSmallY() > 0 ? -1 : entity.getSizeY())];
			Rectangle box = new Rectangle(landscape.getCamera().getLocalX(t.getPosition()) - 1, landscape.getCamera().getLocalY(t.getPosition()), Data.TILE_WIDTH + 1, Data.TILE_HEIGHT);
			return entity.getSmallY() != 0 && t.isBlocking(new Position(dir, 0)) && box.intersects(entity.getBoundingBox());
		}
	}
	
	public boolean isCollidingY(PhysicsEntity entity, int dir) {
		if (entity.getAttached() != null)
			return true;
		else if (getObjectCollidingY(entity, dir) != null)
			return true;
		else if (entity.getPosition().getY() + dir <= 0 || entity.getPosition().getY() + dir >= Data.LANDSCAPE_TILE_HEIGHT - 1)
			return true;
		else {
			for (int i = 0; i < entity.getSizeX(); i++) {
				Tile t = landscape.getTiles()[entity.getPosition().getX() + i][entity.getPosition().getY() + dir - (dir > 0 ? 0 : entity.getSizeY() - 1)];
				Rectangle box = new Rectangle(landscape.getCamera().getLocalX(t.getPosition()) + (entity.getLastDirection() > 0 ? 1 : -1), landscape.getCamera().getLocalY(t.getPosition()) - 1, Data.TILE_WIDTH + (entity.getLastDirection() > 0 ? -1 : 1), Data.TILE_HEIGHT + 1);
				if (t.isBlocking(new Position(0, dir)) && (dir > 0 ? box.intersects(entity.getBoundingBox()) && !getRectangle(t.getPosition()).intersects(entity.getBoundingBox()) : 
					box.intersects(entity.getBoundingBox()))) {
					return true;
				}
			}
			Tile t = landscape.getTiles()[entity.getPosition().getX() + (entity.getSmallX() > 0 ? entity.getSizeX() : -1)][entity.getPosition().getY() + dir - (dir > 0 ? 0 : entity.getSizeY() - 1)];
			Rectangle box = new Rectangle(landscape.getCamera().getLocalX(t.getPosition()) + (entity.getLastDirection() > 0 ? 1 : -1), landscape.getCamera().getLocalY(t.getPosition()) - 1, Data.TILE_WIDTH - 1, Data.TILE_HEIGHT + 1);
			if (t.isBlocking(new Position(0, dir)) && (dir > 0 ? box.intersects(entity.getBoundingBox()) && !getRectangle(t.getPosition()).intersects(entity.getBoundingBox()) : 
				box.intersects(entity.getBoundingBox()))) {
				return true;
			}
		}
		return false;
	}
	
	public GameObject getObjectCollidingX(PhysicsEntity e, int dir) {
		for (GameObject o : landscape.game.getGameObjects().getAll()) {
			if (o.getBoundingBox().intersects(e.getBoundingBox())) {
				o.onCollision(e, new Position(dir, 0));
				return o;
			}
		}
		return null;
	}
	
	public GameObject getObjectCollidingY(PhysicsEntity e, int dir) {
		for (GameObject o : landscape.game.getGameObjects().getAll()) {
			Rectangle box = new Rectangle(o.getBoundingBox().x, o.getBoundingBox().y - (dir < 0 ? 0 : 1), o.getBoundingBox().width, o.getBoundingBox().height);
			if (dir > 0 ? box.intersects(e.getBoundingBox()) && !o.getBoundingBox().intersects(e.getBoundingBox()) : 
				box.intersects(e.getBoundingBox())) {
				o.onCollision(e, new Position(0, dir));
				return o;
			}
		}
		return null;
	}
	
	private Rectangle getRectangle(Position pos) {
		return new Rectangle(landscape.getCamera().getLocalX(pos), landscape.getCamera().getLocalY(pos), Data.TILE_WIDTH, Data.TILE_HEIGHT);
	}
	
	public boolean overlaps(PhysicsEntity a, PhysicsEntity b) {
		return !(a.getPosition().getX() + (a.getSizeX() - 1) < b.getPosition().getX()) && !(b.getPosition().getX() + (b.getSizeX() - 1) < a.getPosition().getX());
	}
	
	public Landscape getLandscape() {
		return this.landscape;
	}
	
}
