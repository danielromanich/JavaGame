package com.daniel.adventure.entity.entities;

import java.awt.Image;
import java.util.ArrayList;

import com.daniel.adventure.Game;
import com.daniel.adventure.entity.PhysicsEntity;
import com.daniel.adventure.landscape.Collision;
import com.daniel.adventure.res.ResourceLoader;
import com.daniel.adventure.util.Position;

public class FlyingTurtle extends PhysicsEntity {

	public FlyingTurtle(Game game, Collision collision, Position pos) {
		super(1, 1, game, collision);
		this.ignoreCollision = true;
		this.setPosition(pos);
		this.setVelocityX(3);
		this.animations.add(new ArrayList<Image>());
		this.setAnimationSpeed(8);
		for (int i = 1; i < 11; i++) {
			this.animations.get(0).add(ResourceLoader.getImage("animations/flying_turtle/fly_"+i));
		}
	}
	
	@Override
	public void tick() {
		if (this.getPosition().getX() <= 0) {
			this.setVelocityX(3);
		} else if (this.getPosition().getX() >= 20)
			this.setVelocityX(-3);
		this.smallX += getVelocityX();
		if (this.smallX > 32) {
			this.setPosition(new Position(this.getPosition().getX() + 1, this.getPosition().getY()));
			this.smallX = this.smallX - 64;
		} else if (this.smallX < -32) {
			this.setPosition(new Position(this.getPosition().getX() - 1, this.getPosition().getY()));
			this.smallX = this.smallX + 64;
		}
		if (getPosition().getDistance(getGame().getPlayer().getPosition()) <= 3) {
			if (getBoundingBox().intersects(getGame().getPlayer().getBoundingBox())) {
				if (getGame().getPlayer().getVelocityY() > 0) {
					getGame().getPlayer().setVelocityY(-20);
					getGame().getEntities().removeEntity(this);
					WalkingTurtle t = new WalkingTurtle(getGame(), getCollision(), getPosition());
					t.setSmallX(getSmallX());
					t.setSmallY(getSmallY() + 40);
					t.setVelocityX(getVelocityX() > 0 ? 3 : -3);
					getGame().getEntities().addEntity(t);
				} else {
					getGame().setDead(true);
				}
			}
		}
	}

}
