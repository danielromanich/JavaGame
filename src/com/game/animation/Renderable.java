package com.daniel.adventure.animation;

import java.awt.Graphics;
import java.awt.Image;

import com.daniel.adventure.landscape.Camera;

public interface Renderable {
	
	public Image getRenderImage();
	public void drawEntity(Graphics g, Camera c);

}
