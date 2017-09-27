package com.game.entity.entities;

import java.awt.Image;
import java.awt.Rectangle;

import com.game.Game;
import com.game.entity.PhysicsEntity;
import com.game.landscape.Collision;
import com.game.res.ResourceLoader;
import com.game.util.Data;
import com.game.util.Position;

public class Ghost extends PhysicsEntity {

	public Ghost(Position pos, Game game, Collision collision) {
		super(1, 1, game, collision);
		this.setPosition(pos);
	}
	
	private int lastDir = 0;
	
	//GHOSTS IGNORE COLLISION	
	@Override
	public void tick() {
		if (getPosition().getDistance(getGame().getPlayer().getPosition()) < 8) {
			int dirX = getGame().getPlayer().getPosition().getX() - getPosition().getX();
			if (dirX < -1 || Math.abs(dirX) <= 1 && lastDir == -1) {
				this.setVelocityX(-1);
				lastDir = -1;
			} else if (dirX > 1 || Math.abs(dirX) <= 1 && lastDir == 1) {
				this.setVelocityX(1);
				lastDir = 1;
			}
			this.smallX += this.getVelocityX();
			if (this.smallX > 32) {
				this.setPosition(new Position(this.getPosition().getX() + 1, this.getPosition().getY()));
				this.smallX = this.smallX - 64;
			} else if (this.smallX < -32) {
				this.setPosition(new Position(this.getPosition().getX() - 1, this.getPosition().getY()));
				this.smallX = this.smallX + 64;
			}
			
			int dirY = getGame().getPlayer().getPosition().getY() - getPosition().getY();
			if (dirY < 0) {
				this.setVelocityY(-1);
				lastDir = -1;
			} else if (dirY > 0) {
				this.setVelocityY(1);
			} else {
				this.setVelocityY(0);
			}
			this.smallY += this.getVelocityY();
			if (this.smallY >= 64) {
				this.setPosition(new Position(this.getPosition().getX(), this.getPosition().getY() + 1));
				this.smallY = 0;
			} else if (this.smallY < 0) {
				this.setPosition(new Position(this.getPosition().getX(), this.getPosition().getY() - 1));
				this.smallY = 64;
			}
		}
		if (getPosition().getDistance(getGame().getPlayer().getPosition()) <= 3) {
			if (getBoundingBox().intersects(getGame().getPlayer().getBoundingBox())) {
				getGame().setDead(true);
			}
		}
	}
	
	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(getGame().getCamera().getLocalX(this) + 10, getGame().getCamera().getLocalY(this) + 10, 
				Data.TILE_WIDTH * this.getSizeX() - 20, Data.TILE_HEIGHT * this.getSizeY() - 20);
	}
	
	@Override
	public Image getRenderImage() {
		return ResourceLoader.getImage("animations/ghost/ghost");
	}

}
