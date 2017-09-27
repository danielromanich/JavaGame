package com.game.animation;

import java.awt.Graphics;
import java.awt.Image;

import com.game.landscape.Camera;

public interface Renderable {
	
	public Image getRenderImage();
	public void drawEntity(Graphics g, Camera c);

}
