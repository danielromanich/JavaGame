package com.daniel.adventure.entity;
import java.util.ArrayList;

import com.daniel.adventure.Game;
import com.daniel.adventure.landscape.Collision;

public class CollisionEntity extends PhysicsEntity {
	
	
	protected final ArrayList<PhysicsEntity> attachedEntities = new ArrayList<PhysicsEntity>();
	
	public CollisionEntity(int sizeX, int sizeY, Game game, Collision collision) {
		super(sizeX, sizeY, game, collision);
	}
	
	public boolean isCollidingX(Entity e, int dir) {
		return false;
	}
	
	public boolean isCollidingY(Entity e, int dir) {
		return false;
	}
	
	public void attachEntity(PhysicsEntity e) {
		if (!this.attachedEntities.contains(e))
			this.attachedEntities.add(e);
	}

	public void removeEntity(PhysicsEntity e) {
		this.attachedEntities.remove(e);
	}

}
