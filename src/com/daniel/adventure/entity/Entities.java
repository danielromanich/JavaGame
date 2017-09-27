package com.daniel.adventure.entity;

import java.awt.Graphics;
import java.util.ArrayList;

import com.daniel.adventure.Game;

public class Entities {
	
	private Game game;
	public Entities(Game game) {
		this.game = game;
	}
	
	public ArrayList<PhysicsEntity> entities = new ArrayList<PhysicsEntity>();
	
	public void tickEntities() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).tick();
		}
	}
	
	public void addEntity(PhysicsEntity e) {
		this.entities.add(e);
	}
	
	public void removeEntity(PhysicsEntity e) {
		this.entities.remove(e);
	}
	
	public void renderEntities(Graphics g) {
		for (PhysicsEntity e : entities) {
			if (game.getLandscape().withinFOV(e)) {
				e.drawEntity(g, game.getCamera());
				if (game.getSettings().isDebuggingEntities())
					g.drawRect(e.getBoundingBox().x, e.getBoundingBox().y, e.getBoundingBox().width, e.getBoundingBox().height);
			}
		}
	}
	
	public void animateAll() {
		for (PhysicsEntity e : entities) {
			e.animate();
		}
	}

}
