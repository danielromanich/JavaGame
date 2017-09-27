package com.daniel.adventure.entity;

import java.awt.Rectangle;

import com.daniel.adventure.Game;
import com.daniel.adventure.util.Position;

public abstract class GameObject extends Entity {
	
	private int width, height;
	private Game game;
	
	public GameObject(Game game, int offsetX, int offsetY, int width, int height) {
		this.game = game;
		this.smallX = offsetX;
		this.smallY = offsetY;
		this.width = width;
		this.height = height;
	}
	
	public int getOffsetX() {
		return this.smallX;
	}
	
	public int getOffsetY() {
		return this.smallY;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}

	public abstract void onCollision(PhysicsEntity e, Position direction);
	
	public Rectangle getBoundingBox() {
		return new Rectangle(game.getCamera().getLocalX(this), 
				game.getCamera().getLocalY(this), 
				getWidth(), 
				getHeight());
	}
	
}
