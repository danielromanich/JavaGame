package com.daniel.adventure.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.daniel.adventure.Game;
import com.daniel.adventure.landscape.Camera;
import com.daniel.adventure.landscape.Collision;
import com.daniel.adventure.util.Data;
import com.daniel.adventure.util.Position;

public class PhysicsEntity extends Entity {
	
	private int velocityY, velocityX, lastDirection = 1;
	private Collision collision;
	private Game game;
	protected boolean ignoreCollision = false;
	
	private CollisionEntity attached;
	
	public PhysicsEntity(int sizeX, int sizeY, Game game, Collision collision) {
		this.game = game;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.collision = collision;
	}
	
	public void tick() {
		if (!ignoreCollision)
			gravitationalTick();
	}
	
	public boolean gravitationalTick() {
		if (attached == null) {
			this.velocityY += 2;
			if (this.velocityY > Data.MAX_FALL_SPEED)
				this.velocityY = Data.MAX_FALL_SPEED;
			for (int i = 0; i < Math.abs(this.velocityY); i++) {
				if (this.velocityY > 0) {
					for (PhysicsEntity e : collision.getLandscape().game.getEntities().entities) {
						if (e instanceof CollisionEntity) {
							CollisionEntity en = (CollisionEntity) e;
							if (getCollision().overlaps(this, en)) {
								if (getPosition().getY() - en.getPosition().getY() == -1 && getVelocityY() >= 0) {
									if (getSmallY() == en.getSmallY()) {
										setAttached(en);
										return false;
									}
								}
							}
						}
					}
				}
				if (!collision.isCollidingY(this, this.velocityY < 0 ? -1 : 1)) {
					if (this.velocityY < 0)
						this.smallY--;
					else
						this.smallY++;
				} else {
					this.velocityY = 0;
					break;
				}
				if (this.smallY >= Data.TILE_HEIGHT) {
					this.setPosition(new Position(this.getPosition().getX(), this.getPosition().getY() + 1));
					this.smallY = 0;
				} else if (this.smallY < 0) {
					this.setPosition(new Position(this.getPosition().getX(), this.getPosition().getY() - 1));
					this.smallY = Data.TILE_HEIGHT;
				}
			}
		}
		return true;
	}
	
	public int getLastDirection() {
		return this.lastDirection;
	}
	
	public int getVelocityY() {
		return this.velocityY;
	}
	
	public void setVelocityX(int x) {
		this.lastDirection = x == 0 ? this.lastDirection : x > 0 ? 1 : -1;
		this.velocityX = x;
	}
	
	public void addVelocity(int x) {
		this.velocityX += x;
	}
	
	public int getVelocityX() {
		return this.velocityX;
	}
	
	public void setVelocityY(int y) {
		if (y != this.velocityY && y != 0) {
			removeAttached();
		}
		this.velocityY = y;
	}
	
	public void setSmallX(int x) {
		this.smallX = x;
	}
	
	public void setSmallY(int y) {
		this.smallY = y;
	}
	
	public CollisionEntity getAttached() {
		return this.attached;
	}
	
	public void setAttached(CollisionEntity e) {
		this.velocityY = 0;
		this.attached = e;
		e.attachEntity(this);
	}
	
	public void removeAttached() {
		if (this.attached != null)
			this.attached.removeEntity(this);
		this.attached = null;
	}

	public Collision getCollision() {
		return this.collision;
	}
	
	public Game getGame() {
		return this.game;
	}
	
	public Rectangle getBoundingBox() {
		Image i = getRenderImage();
		return new Rectangle(game.getCamera().getLocalX(this) + (Data.TILE_WIDTH * getSizeX() / 2) - (i.getWidth(null) / 2), 
				game.getCamera().getLocalY(this) + (getSizeY() * Data.TILE_HEIGHT - i.getHeight(null)), 
				i.getWidth(null), 
				i.getHeight(null));
	}
	
	public void setLastDirection(int dir) {
		this.lastDirection = dir;
	}
	
	@Override
	public void drawEntity(Graphics g, Camera c) {
		Image i = getRenderImage();
		g.drawImage(i, getLastDirection() == 1 ? c.getLocalX(this) + (Data.TILE_WIDTH * getSizeX() / 2) - (i.getWidth(null) / 2) : 
					c.getLocalX(this) + i.getWidth(null) + (Data.TILE_WIDTH * getSizeX() / 2) - (i.getWidth(null) / 2), 
						c.getLocalY(this) + (getSizeY() * Data.TILE_HEIGHT - i.getHeight(null)), 
						getLastDirection() == 1 ? i.getWidth(null)/*getSizeX() * Data.TILE_WIDTH*/ : -i.getWidth(null)/*Data.TILE_WIDTH * getSizeX()*/, 
						i.getHeight(null)/*Data.TILE_HEIGHT * sizeY*/, null);
	}
	
	public void onCollision(Entity colliding) {
		
	}
	
}
