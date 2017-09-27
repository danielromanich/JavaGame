package com.daniel.adventure.entity;

import java.awt.Graphics;

import com.daniel.adventure.animation.Animateable;
import com.daniel.adventure.landscape.Camera;
import com.daniel.adventure.util.Position;

public class Entity extends Animateable {
	
	private Position position;
	protected int smallX, smallY, sizeX = 1, sizeY = 1;
	
	public int getSmallX() {
		return this.smallX;
	}
	
	public int getSmallY() {
		return this.smallY;
	}
	
	public int getSizeX() {
		return this.sizeX;
	}
	
	public int getSizeY() {
		return this.sizeY;
	}
	
	public Position getPosition() {
		return this.position;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}

	@Override
	public void drawEntity(Graphics g, Camera c) {
		g.drawImage(getRenderImage(), c.getLocalX(this), c.getLocalY(this), null);
	}

}
