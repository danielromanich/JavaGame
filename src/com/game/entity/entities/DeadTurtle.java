package com.game.entity.entities;

import java.awt.Image;
import java.util.ArrayList;

import com.game.Game;
import com.game.entity.PhysicsEntity;
import com.game.landscape.Collision;
import com.game.res.ResourceLoader;

public class DeadTurtle extends PhysicsEntity {
	
	private WalkingTurtle turtle;

	public DeadTurtle(Game game, Collision collision, WalkingTurtle turtle) {
		super(1, 1, game, collision);
		this.turtle = turtle;
		this.ignoreCollision = true;
		this.setPosition(turtle.getPosition());
		this.setSmallX(turtle.getSmallX());
		this.setSmallY(turtle.getSmallY());
		this.animations.add(new ArrayList<Image>());
		this.animations.get(0).add(ResourceLoader.getImage("animations/dead_turtle/idle"));
	}

	int timer = 0;
	
	@Override
	public void tick() {
		if (timer > 120) {
			getGame().getEntities().removeEntity(this);
			getGame().getEntities().addEntity(turtle);
		}
		timer++;
	}

}
