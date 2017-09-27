package com.game.entity.collisionentities;

import java.awt.Image;

import com.game.Game;
import com.game.entity.CollisionEntity;
import com.game.entity.PhysicsEntity;
import com.game.landscape.Collision;
import com.game.res.ResourceLoader;
import com.game.util.Data;
import com.game.util.Position;

public class MovingUpPlatform extends CollisionEntity {

	private Position origin;
	private int moveLength, speed;
	
	private int dir = -1;
	
	public MovingUpPlatform(Game game, Collision collision, Position origin, int moveLength, int speed) {
		super(2, 1, game, collision);
		this.speed = speed;
		this.origin = new Position(origin.getX(), origin.getY());
		this.setPosition(origin);
		this.moveLength = moveLength;
	}
	
	@Override
	public void tick() {
		if (dir == -1) {
			if (getPosition().getY() <= origin.getY() - moveLength || getPosition().getY() <= 1) {
				dir = 1;
			} else {
				this.smallY -= speed;
			}
		} else {
			if (getPosition().getY() >= origin.getY() || getPosition().getY() >= Data.LANDSCAPE_TILE_HEIGHT - 1) {
				dir = -1;
			} else {
				this.smallY += speed;
			}
		}
		if (this.smallY >= 64) {
			this.setPosition(new Position(this.getPosition().getX(), this.getPosition().getY() + 1));
			this.smallY = 0;
		} else if (this.smallY < 0) {
			this.setPosition(new Position(this.getPosition().getX(), this.getPosition().getY() - 1));
			this.smallY = 64;
		}
		for (int i = 0; i < this.attachedEntities.size(); i++) {
			PhysicsEntity e = this.attachedEntities.get(i);
			e.setPosition(new Position(e.getPosition().getX(), getPosition().getY() - 1));
			e.setSmallY(getSmallY());
			if (!getCollision().overlaps(e, this)) {
				e.removeAttached();
			}
		}
	}	
	
	@Override
	public Image getRenderImage() {
		return ResourceLoader.getImage("animations/moving_platform/moving_platform");
	}
	

}
