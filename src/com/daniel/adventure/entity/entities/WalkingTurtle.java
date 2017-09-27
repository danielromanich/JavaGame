package com.daniel.adventure.entity.entities;

import java.awt.Image;
import java.util.ArrayList;

import com.daniel.adventure.Game;
import com.daniel.adventure.entity.PhysicsEntity;
import com.daniel.adventure.landscape.Collision;
import com.daniel.adventure.res.ResourceLoader;
import com.daniel.adventure.util.Position;

public class WalkingTurtle extends PhysicsEntity {

	public WalkingTurtle(Game game, Collision collision, Position position) {
		super(1, 1, game, collision);
		this.setPosition(position);
		this.setVelocityX(3);
		this.animations.add(new ArrayList<Image>());
		for (int i = 1; i < 9; i++) {
			this.animations.get(0).add(ResourceLoader.getImage("animations/walking_turtle/walk_" + i));
		}
	}
	
	@Override
	public void tick() {
		if (this.getVelocityX() != 0 
				&& (!this.getCollision().isCollidingX(this, getVelocityX() > 0 ? 1 : -1) || this.getVelocityX() < 0 && this.getSmallX() > 0 || this.getVelocityX() > 0 && this.getSmallX() < 0)) {
			for (int i = 0; i < Math.abs(getVelocityX()); i++) {
				if (!this.getCollision().isCollidingX(this, getVelocityX() > 0 ? 1 : -1))
					if (getVelocityX() > 0)
						this.smallX++;
					else
						this.smallX--;
				else {
					this.setVelocityX(this.getVelocityX() < 0 ? 3 : -3);
					break;
				}
			}
			if (this.smallX > 32) {
				this.setPosition(new Position(this.getPosition().getX() + 1, this.getPosition().getY()));
				this.smallX = this.smallX - 64;
			} else if (this.smallX < -32) {
				this.setPosition(new Position(this.getPosition().getX() - 1, this.getPosition().getY()));
				this.smallX = this.smallX + 64;
			}
		} else if (this.getCollision().isCollidingX(this, getVelocityX() > 0 ? 1 : -1)) {
			if (this.getVelocityX() < 0 && this.getSmallX() <= 0) {
				this.setVelocityX(3);
			} else if (this.getVelocityX() > 0 && this.getSmallX() >= 0) {
				this.setVelocityX(-3);
			}
		}
		if (getPosition().getDistance(getGame().getPlayer().getPosition()) <= 3) {
			if (getBoundingBox().intersects(getGame().getPlayer().getBoundingBox())) {
				if (getGame().getPlayer().getVelocityY() > 0) {
					getGame().getPlayer().setVelocityY(-20);
					DeadTurtle t = new DeadTurtle(getGame(), getCollision(), this);
					getGame().getEntities().removeEntity(this);
					getGame().getEntities().addEntity(t);
				} else {
					getGame().setDead(true);
				}
			}
		}
		this.gravitationalTick();
	}

}
