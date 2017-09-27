package com.game.entity.entities;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.game.Game;
import com.game.entity.PhysicsEntity;
import com.game.landscape.Camera;
import com.game.landscape.Collision;
import com.game.res.ResourceLoader;
import com.game.util.Data;
import com.game.util.Position;

public class Player extends PhysicsEntity {
	
	private Camera camera;
	
	public boolean right, left;

	public Player(Camera camera, Game game, Collision collision) {
		super(1, 1, game, collision);
		this.camera = camera;
		this.setPosition(new Position(5, 10));
		this.camera.setX(this.camera.getX() + getVelocityX(), this);
		this.animations.add(new ArrayList<Image>());
		this.animations.add(new ArrayList<Image>());
		this.animations.add(new ArrayList<Image>());
		this.animations.get(0).add(ResourceLoader.getImage("animations/player/idle"));
		this.animations.get(1).add(ResourceLoader.getImage("animations/player/idle"));
		this.animations.get(1).add(ResourceLoader.getImage("animations/player/walk_1"));
		this.animations.get(1).add(ResourceLoader.getImage("animations/player/walk_2"));
		this.animations.get(1).add(ResourceLoader.getImage("animations/player/walk_3"));
		this.animations.get(1).add(ResourceLoader.getImage("animations/player/walk_4"));
		this.animations.get(2).add(ResourceLoader.getImage("animations/player/jump_1"));
	}
	
	@Override
	public void tick() {
		this.setVelocityX(0);
		this.addVelocity(this.right ? 6 : 0);
		this.addVelocity(this.left ? - 6 : 0);
		if (this.getVelocityY() != 0)
			this.setCurrentAnimation(2);
		else if (this.getVelocityX() == 0)
			this.setCurrentAnimation(0);
		else
			this.setCurrentAnimation(1);
		if (this.getVelocityX() != 0 
				&& (!this.getCollision().isCollidingX(this, getVelocityX() > 0 ? 1 : -1) || this.getVelocityX() < 0 && this.getSmallX() > 0 || this.getVelocityX() > 0 && this.getSmallX() < 0)) {
			for (int i = 0; i < Math.abs(getVelocityX()); i++) {
				if (!this.getCollision().isCollidingX(this, getVelocityX() > 0 ? 1 : -1))
					if (getVelocityX() > 0)
						this.smallX++;
					else
						this.smallX--;
				else {
					this.setVelocityX(0);
					break;
				}
			}
			this.camera.setX(this.getPosition().getX() * Data.TILE_WIDTH + this.getSmallX(), this);
			if (this.smallX > 32) {
				this.setPosition(new Position(this.getPosition().getX() + 1, this.getPosition().getY()));
				this.smallX = this.smallX - 64;
			} else if (this.smallX < -32) {
				this.setPosition(new Position(this.getPosition().getX() - 1, this.getPosition().getY()));
				this.smallX = this.smallX + 64;
			}
		}
		gravitationalTick();
		//this.camera.setY(this.camera.getY() + getVelocityY(), this);
	}
	
	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(camera.getLocalX(this) + (Data.TILE_WIDTH * getSizeX() / 2) - (30 / 2), 
				camera.getLocalY(this) + (getSizeY() * Data.TILE_HEIGHT - 63), 
				30, 
				63);
	}

}
