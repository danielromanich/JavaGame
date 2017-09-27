package com.game.entity;

import java.awt.Graphics;
import java.util.ArrayList;

import com.game.Game;

public class GameObjects {
	
	private Game game;
	private final ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	public GameObjects(Game game) {
		this.game = game;
	}
	
	public void addObject(GameObject o) {
		this.objects.add(o);
	}
	
	public void removeObject(GameObject o) {
		this.objects.remove(o);
	}
	
	public void renderObjects(Graphics g) {
		for (GameObject e : objects) {
			if (game.getLandscape().withinFOV(e)) {
				e.drawEntity(g, game.getCamera());
				if (game.getSettings().isDebuggingObjects())
					g.drawRect(e.getBoundingBox().x, e.getBoundingBox().y, e.getBoundingBox().width, e.getBoundingBox().height);
			}
		}
	}
	
	public ArrayList<GameObject> getAll() {
		return this.objects;
	}
	
	public void animateAll() {
		for (GameObject e : objects) {
			e.animate();
		}
	}

}
